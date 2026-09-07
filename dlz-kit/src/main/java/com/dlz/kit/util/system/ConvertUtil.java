package com.dlz.kit.util.system;

import com.dlz.kit.exception.SystemException;
import com.dlz.kit.json.JSONMap;
import com.dlz.kit.json.core.Json;
import com.dlz.kit.util.ValUtil;
import com.dlz.kit.util.system.annotation.SetValue;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConvertUtil {

    public static <T> T convert(Object input, Class<T> clazz) {
        return convert(input, clazz, null);
    }

    public static <T> T convert(Object input, Class<T> tClass, Consumer<T> fn) {
        if (input == null) {
            return null;
        }
        if (tClass.isInstance(input)) {
            return (T) input;
        }

        T re = null;
        if (tClass.isArray()) {
//            return (T) ValUtil.toArray(input);
            return convertToArray(input, tClass);
        } else if (Map.class.isAssignableFrom(tClass)) {
            re = convertToMap(input, tClass);
        } else if (Collection.class.isAssignableFrom(tClass)) {
            re = convertToList(input, tClass);
        } else {
            Function fnc = ValUtil.CONVERTS_NATIVE.get(tClass);
            if (fnc != null) {
                re = (T) fnc.apply(input);
            }
        }
        if (re == null) {
            re = convertToBean(input, tClass);
        }

        if (fn != null && re != null) {
            fn.accept(re);
        }
        return re;
    }

    /**
     * object to Map
     *
     * @param input
     * @param tClass
     * @param <T>
     */
    private static <T> T convertToMap(final Object input, final Class<T> tClass) {
        if (tClass.isArray()) {
            throw new SystemException("不能将数组转化数Map");
        }
        if (Collection.class.isInstance(input)) {
            throw new SystemException("不能将List转化Map");
        }
        Map re = (Map) Reflections.newInstance(tClass); // 创建 Map 对象
        if (input instanceof Map) {
            re.putAll((Map) input);
        } else {
            JSONMap reTmp = new JSONMap(); // 创建 Map 对象
            final List<Field> fields = FieldReflections.getFields(input.getClass());
            fields.stream().forEach(field -> {
                SetValue annotation = field.getAnnotation(SetValue.class);
                String name = field.getName();
                String sourceName = name;
                Object value = FieldReflections.getValue(input, field);
                if (annotation != null && !"".equals(annotation.value())) {
                    sourceName = annotation.value() + "." + name;
                    reTmp.set(sourceName, value);
                } else {
                    reTmp.put(sourceName, value);
                }
            });
            re.putAll(reTmp);
        }
        return (T) re;
    }


    /**
     * object to Array
     *
     * @param input
     * @param tClass
     * @param <T>
     */
    private static <T> T convertToArray(final Object input, final Class<T> tClass) {
        // 取数组的组件类型（元素类型），委托 ValUtil 按元素类型正确构造数组
        Class<?> componentType = tClass.getComponentType();
        return (T) ValUtil.toArray(input, componentType);
    }
    /**
     * object to Collection
     *
     * @param input
     * @param tClass
     * @param <T>
     */
    private static <T> T convertToList(final Object input, final Class<T> tClass) {
        Collection re = (Collection) Reflections.newInstance(tClass); // 创建集合对象
        if (input.getClass().isArray()) {
            for (int i = 0; i < Array.getLength(input); i++) {
                re.add(Array.get(input, i));
            }
        }else if (Collection.class.isInstance(input)) {
            re.addAll(((Collection) input));
        }else if (input instanceof CharSequence){
            re.addAll(Json.parseArray(input.toString()));
        }else{
            throw new SystemException("不支持将"+input.getClass().getName()+"转化为" + tClass.getName());
        }
        return (T) re;
    }

    /**
     * object to Bean
     *
     * @param input
     * @param tClass
     * @param <T>
     */
    private static <T> T convertToBean(Object input, final Class<T> tClass) {
        if (tClass.isArray()) {
            throw new SystemException("不能将Map转化数组");
        }
        if (Collection.class.isInstance(input)) {
            throw new SystemException("不能将List转化Map" + tClass.getName());
        }
        if (CharSequence.class.isInstance(input)) {
            input = Json.parseObject(input.toString());
        }
        T re = Reflections.newInstance(tClass); // 创建 Map 对象
        final List<Field> targetFields = FieldReflections.getFields(tClass);
        if (input instanceof Map) {
            JSONMap reTmp = new JSONMap(input); // 创建 Map 对象
            targetFields.parallelStream().forEach(field -> {
                SetValue annotation = field.getAnnotation(SetValue.class);
                String name = field.getName();
                String sourceName = name;
                if (annotation != null && !"".equals(annotation.value())) {
                    sourceName = annotation.value() + "." + name;
                }
                Object value = reTmp.getObj(sourceName, field.getType());
                if (value != null) {
                    FieldReflections.setValue(re, field, value);
                }
            });
        } else {
            final Object bean = input;
            final Map<String, Field> inputFields = FieldReflections
                    .getFields(bean.getClass())
                    .stream()
                    .collect(Collectors.toMap(Field::getName, f -> f));
            targetFields.parallelStream().forEach(field -> {
                SetValue annotation = field.getAnnotation(SetValue.class);
                String name = field.getName();
                Field inputField = inputFields.get(name);
                if (inputField == null) {
                    return;
                }
                Object value = FieldReflections.getValue(bean, inputField);
                if (value == null) {
                    return;
                }
                if (annotation != null && !"".equals(annotation.value())) {
                    value = ValUtil.at(value, annotation.value());
                }
                if (value != null) {
                    FieldReflections.setValue(re, field, value);
                }
            });
        }
        return re;
    }

    /**
     * Map转Bean
     *
     * @param <T>
     * @param input
     * @param tClass
     */
    public static <T> List<T> convertList(List<?> input, Class<T> tClass, Consumer<T> fn) {
        if (ValUtil.isEmpty(input)) {
            return new ArrayList<>();
        }
        return input.stream().map(o -> convert(o, tClass, fn)).collect(Collectors.toList());
    }

    public static <T, T1> List<T> convertList(List<T1> input, Class<T> clazz) {
        return convertList(input, clazz, null);
    }
}
