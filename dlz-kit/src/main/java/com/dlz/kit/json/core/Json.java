package com.dlz.kit.json.core;

import java.util.List;
import java.util.Map;

/** Zero-dependency JSON entry point. */
public final class Json {
    private static final JsonOptions STRICT = JsonOptions.strict();

    private Json() {
    }

    public static Object parse(String json) {
        return parse(json, STRICT);
    }

    public static Object parse(String json, JsonOptions options) {
        return new JsonParser(json, requireOptions(options)).parse();
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseObject(String json) {
        return parseObject(json, STRICT);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseObject(String json, JsonOptions options) {
        Object value = parse(json, options);
        if (!(value instanceof Map)) {
            throw new JsonException("JSON root must be an object");
        }
        return (Map<String, Object>) value;
    }

    @SuppressWarnings("unchecked")
    public static List<Object> parseArray(String json) {
        return parseArray(json, STRICT);
    }

    @SuppressWarnings("unchecked")
    public static List<Object> parseArray(String json, JsonOptions options) {
        Object value = parse(json, options);
        if (!(value instanceof List)) {
            throw new JsonException("JSON root must be an array");
        }
        return (List<Object>) value;
    }

    public static String stringify(Object value) {
        return stringify(value, STRICT);
    }

    public static String stringify(Object value, JsonOptions options) {
        return new JsonWriter(requireOptions(options)).write(value);
    }

    private static JsonOptions requireOptions(JsonOptions options) {
        if (options == null) {
            throw new IllegalArgumentException("options cannot be null");
        }
        return options;
    }
}
