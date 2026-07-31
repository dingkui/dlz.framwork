package com.dlz.kit.util;

import com.dlz.kit.exception.SystemException;
import com.dlz.kit.json.InternalJsonMapper;
import com.dlz.kit.json.JSONList;
import com.dlz.kit.json.JSONMap;
import com.dlz.kit.json.core.JsonException;
import com.dlz.kit.json.core.JsonMapper;
import com.dlz.kit.json.core.JsonPath;
import com.dlz.kit.json.core.JsonPathParser;
import com.dlz.kit.json.core.JsonPathPart;
import com.dlz.kit.json.core.JsonPathResolver;
import com.dlz.kit.json.core.JsonText;
import com.dlz.kit.json.core.JsonTypes;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

/** JSON facade backed by the internal mapper or an optional provider module. */
@SuppressWarnings({"rawtypes", "unchecked"})
public final class JsonUtil {
    private static final JsonMapper MAPPER = new InternalJsonMapper();
    private static final JsonPathResolver BEAN_PATH_RESOLVER = new JsonPathResolver() {
        @Override
        public Object resolve(Object value, String path) {
            return JsonPath.get(convertValue(value, JSONMap.class), path, this);
        }
    };

    private JsonUtil() {
    }

    public static JsonMapper getMapper() {
        return MAPPER;
    }

    public static String getJson(Object value) {
        return MAPPER.write(value);
    }

    public static byte[] toJsonAsBytes(Object value) {
        return getJson(value).getBytes(StandardCharsets.UTF_8);
    }

    public static JSONMap readValue(String content) {
        return convertValue(content, JSONMap.class);
    }

    public static JSONMap readValue(Object content) {
        return convertValue(content, JSONMap.class);
    }

    public static <T> T readValue(String content, Class<T> valueType) {
        return convertValue(content, valueType);
    }

    public static <T> T readValue(Object content, Class<T> valueType) {
        return convertValue(content, valueType);
    }

    public static <T> T readValue(Object content, Type valueType) {
        return convertValue(content, valueType);
    }

    public static <T> T read(String content, Class<T> valueType) {
        return readValue(content, valueType);
    }

    public static <T> List<T> readList(Object content, Class<T> elementClass) {
        return convertValue(content, JsonTypes.listOf(elementClass));
    }

    public static JSONList readList(String content) {
        return convertValue(content, JSONList.class);
    }

    public static JSONList readList(Object content) {
        return convertValue(content, JSONList.class);
    }

    public static <T> List<T> readListValue(String content, Class<T> valueType) {
        return convertValue(content, JsonTypes.listOf(valueType));
    }

    public static <T> T convertValue(Object value, Class<T> targetType) {
        return convertValue(value, (Type) targetType);
    }

    public static <T> T convertValue(Object value, Type targetType) {
        try {
            return MAPPER.convert(value, targetType);
        } catch (SystemException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new SystemException("JSON conversion failed: " + targetType, e);
        }
    }

    public static boolean canSerialize(Object value) {
        try {
            getJson(value);
            return true;
        } catch (RuntimeException unsupported) {
            return false;
        }
    }

    public static <T> T coverObj(Object value, Class<T> targetType) {
        if (value == null || targetType == null) {
            return (T) value;
        }
        return targetType.isInstance(value) ? (T) value : convertValue(value, targetType);
    }

    public static <T> T coverObj(Object value, Type targetType) {
        if (value == null || targetType == null) {
            return (T) value;
        }
        return convertValue(value, targetType);
    }

    public static VAL<String, String> splitKey(String key) {
        try {
            JsonPathPart part = JsonPathParser.split(key);
            return VAL.of(part.getSegment(), part.getRemaining());
        } catch (JsonException invalidPath) {
            throw new SystemException(invalidPath.getMessage(), invalidPath);
        }
    }

    public static <T> T at(Object data, String key, Class<T> valueType) {
        Object value = at(data, key);
        return value == null ? null : coverObj(value, valueType);
    }

    public static Object at(Object data, String key) {
        if (data == null || key == null || key.isEmpty()) {
            return data;
        }
        if (data instanceof CharSequence) {
            String text = data.toString();
            if (JsonText.isObject(text)) {
                data = new JSONMap(text);
            } else if (JsonText.isArray(text)) {
                data = new JSONList(text);
            } else {
                return null;
            }
        }
        return JsonPath.get(data, key, BEAN_PATH_RESOLVER);
    }

    public static boolean isJsonObj(String value) {
        return JsonText.isObject(value);
    }

    public static boolean isJsonArray(String value) {
        return JsonText.isArray(value);
    }

}
