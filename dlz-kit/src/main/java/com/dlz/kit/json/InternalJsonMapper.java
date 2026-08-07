package com.dlz.kit.json;

import com.dlz.kit.exception.SystemException;
import com.dlz.kit.json.core.Json;
import com.dlz.kit.json.core.JsonMapper;
import com.dlz.kit.json.core.JsonOptions;
import com.dlz.kit.util.DateUtil;
import com.dlz.kit.util.ValUtil;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Default JDK-only bean mapper. */
public final class InternalJsonMapper implements JsonMapper {
    private static final JsonOptions WRITE_OPTIONS = JsonOptions.builder().build();

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public String write(Object value) {
        return Json.stringify(toJsonValue(value, new IdentityHashMap<>()), WRITE_OPTIONS);
    }

    @Override
    public <T> T convert(Object value, Type targetType) {
        return (T) convertValue(parseIfNecessary(value, targetType), targetType);
    }

    private Object parseIfNecessary(Object value, Type targetType) {
        if (!(value instanceof CharSequence) || targetType == String.class) {
            return value;
        }
        String text = value.toString().trim();
        if (text.startsWith("{") || text.startsWith("[") || text.startsWith("\"")) {
            Object parsed = Json.parse(text, JsonOptions.JSON_OPTIONS_LENIENT);
            if (parsed instanceof CharSequence) {
                String nested = parsed.toString().trim();
                if (nested.startsWith("{") || nested.startsWith("[")) {
                    return Json.parse(nested, JsonOptions.JSON_OPTIONS_LENIENT);
                }
            }
            return parsed;
        }
        return value;
    }

    private Object toJsonValue(Object value, IdentityHashMap<Object, Boolean> visiting) {
        if (value == null || value instanceof CharSequence || value instanceof Number
                || value instanceof Boolean || value instanceof Character || value instanceof Enum) {
            return value;
        }
        if (value instanceof Date) {
            return DateUtil.DATETIME.format((Date) value);
        }
        if (value instanceof LocalDateTime) {
            return DateUtil.DATETIME.format((LocalDateTime) value);
        }
        if (value instanceof LocalDate) {
            return DateUtil.DATE.format((LocalDate) value);
        }
        if (value instanceof LocalTime || value instanceof TemporalAccessor) {
            return value.toString();
        }
        enter(value, visiting);
        try {
            if (value instanceof Map) {
                Map<String, Object> result = new LinkedHashMap<String, Object>();
                for (Map.Entry<?, ?> entry : ((Map<?, ?>) value).entrySet()) {
                    result.put(String.valueOf(entry.getKey()), toJsonValue(entry.getValue(), visiting));
                }
                return result;
            }
            if (value instanceof Iterable) {
                List<Object> result = new ArrayList<Object>();
                for (Object item : (Iterable<?>) value) {
                    result.add(toJsonValue(item, visiting));
                }
                return result;
            }
            if (value.getClass().isArray()) {
                List<Object> result = new ArrayList<Object>();
                for (int i = 0; i < Array.getLength(value); i++) {
                    result.add(toJsonValue(Array.get(value, i), visiting));
                }
                return result;
            }
            Map<String, Object> result = new LinkedHashMap<String, Object>();
            for (Field field : fieldsOf(value.getClass())) {
                field.setAccessible(true);
                result.put(field.getName(), toJsonValue(field.get(value), visiting));
            }
            return result;
        } catch (IllegalAccessException e) {
            throw new SystemException("JSON bean serialization failed: " + value.getClass().getName(), e);
        } finally {
            visiting.remove(value);
        }
    }

    private Object convertValue(Object value, Type targetType) {
        if (targetType instanceof Class) {
            return convertClass(value, (Class<?>) targetType);
        }
        if (!(targetType instanceof ParameterizedType)) {
            throw new SystemException("Unsupported JSON target type: " + targetType);
        }
        ParameterizedType parameterized = (ParameterizedType) targetType;
        Class<?> rawType = (Class<?>) parameterized.getRawType();
        Type[] arguments = parameterized.getActualTypeArguments();
        if (Collection.class.isAssignableFrom(rawType)) {
            Collection<Object> result = createCollection(rawType);
            if (value instanceof Collection) {
                for (Object item : (Collection<?>) value) {
                    result.add(convertValue(item, arguments[0]));
                }
            }
            return result;
        }
        if (Map.class.isAssignableFrom(rawType)) {
            Map<Object, Object> result = createMap(rawType);
            if (value instanceof Map) {
                for (Map.Entry<?, ?> entry : ((Map<?, ?>) value).entrySet()) {
                    result.put(convertValue(entry.getKey(), arguments[0]), convertValue(entry.getValue(), arguments[1]));
                }
            }
            return result;
        }
        return convertClass(value, rawType);
    }

    private Object convertClass(Object value, Class<?> targetType) {
        if (value == null || targetType == Object.class) {
            return value;
        }
        if (targetType.isInstance(value)) {
            return value;
        }
        Object nativeValue = ValUtil.toNativeObj(value, targetType);
        if (nativeValue != null) {
            return nativeValue;
        }
        if (targetType == JSONMap.class) {
            return value instanceof Map ? new JSONMap((Map<?, ?>) value) : new JSONMap(write(value));
        }
        if (targetType == JSONList.class) {
            return new JSONList(value);
        }
        if (Map.class.isAssignableFrom(targetType) && value instanceof Map) {
            Map<Object, Object> result = createMap(targetType);
            result.putAll((Map<?, ?>) value);
            return result;
        }
        if (Collection.class.isAssignableFrom(targetType) && value instanceof Collection) {
            Collection<Object> result = createCollection(targetType);
            result.addAll((Collection<?>) value);
            return result;
        }
        if (targetType.isEnum()) {
            return Enum.valueOf((Class<? extends Enum>) targetType, value.toString());
        }
        if (targetType.isArray() && value instanceof Collection) {
            Collection<?> source = (Collection<?>) value;
            Object array = Array.newInstance(targetType.getComponentType(), source.size());
            int index = 0;
            for (Object item : source) {
                Array.set(array, index++, convertClass(item, targetType.getComponentType()));
            }
            return array;
        }
        if (!(value instanceof Map)) {
            throw new SystemException("Cannot convert " + value.getClass().getName() + " to " + targetType.getName());
        }
        try {
            Object bean = targetType.newInstance();
            Map<?, ?> source = (Map<?, ?>) value;
            for (Field field : fieldsOf(targetType)) {
                if (!source.containsKey(field.getName())) {
                    continue;
                }
                field.setAccessible(true);
                field.set(bean, convertValue(source.get(field.getName()), field.getGenericType()));
            }
            return bean;
        } catch (ReflectiveOperationException e) {
            throw new SystemException("JSON bean conversion failed: " + targetType.getName(), e);
        }
    }

    private static List<Field> fieldsOf(Class<?> type) {
        List<Field> fields = new ArrayList<Field>();
        for (Class<?> current = type; current != null && current != Object.class; current = current.getSuperclass()) {
            for (Field field : current.getDeclaredFields()) {
                int modifiers = field.getModifiers();
                if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers) && !field.isSynthetic()) {
                    fields.add(field);
                }
            }
        }
        return fields;
    }

    private static Collection<Object> createCollection(Class<?> type) {
        if (type.isInterface()) {
            return new ArrayList<Object>();
        }
        try {
            return (Collection<Object>) type.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new SystemException("Cannot create collection: " + type.getName(), e);
        }
    }

    private static Map<Object, Object> createMap(Class<?> type) {
        if (type.isInterface()) {
            return new LinkedHashMap<Object, Object>();
        }
        try {
            return (Map<Object, Object>) type.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new SystemException("Cannot create map: " + type.getName(), e);
        }
    }

    private static void enter(Object value, IdentityHashMap<Object, Boolean> visiting) {
        if (visiting.put(value, Boolean.TRUE) != null) {
            throw new SystemException("Circular reference detected while writing JSON");
        }
    }
}
