package com.dlz.kit.json.core;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/** Reads values from maps, collections, arrays, and JSON text using DLZ paths. */
public final class JsonPath {
    private static final JsonOptions COMPATIBLE_JSON = JsonOptions.builder()
            .enable(JsonFeature.ALLOW_COMMENTS)
            .enable(JsonFeature.ALLOW_SINGLE_QUOTES)
            .enable(JsonFeature.ALLOW_UNQUOTED_KEYS)
            .enable(JsonFeature.WRITE_NULLS)
            .build();

    private JsonPath() {
    }

    public static Object get(Object data, String path) {
        return get(data, path, null);
    }

    public static Object get(Object data, String path, JsonPathResolver resolver) {
        if (data == null) {
            return null;
        }
        if (path == null || path.isEmpty()) {
            return data;
        }
        if (data instanceof CharSequence) {
            try {
                data = Json.parse(data.toString(), COMPATIBLE_JSON);
            } catch (JsonException invalidJson) {
                return null;
            }
        }
        String normalized = path.startsWith(".") ? path.substring(1) : path;
        if (normalized.isEmpty()) {
            return data;
        }
        if (data instanceof Map) {
            return getFromMap((Map<?, ?>) data, normalized, resolver);
        }
        if (data instanceof Collection) {
            return getFromCollection((Collection<?>) data, normalized, resolver);
        }
        if (data.getClass().isArray()) {
            return getFromArray(data, normalized, resolver);
        }
        return resolver == null ? null : resolver.resolve(data, normalized);
    }

    private static Object getFromMap(Map<?, ?> map, String path, JsonPathResolver resolver) {
        if (map.containsKey(path)) {
            return map.get(path);
        }
        int separator = firstSeparator(path);
        if (separator < 0) {
            return null;
        }
        String key = path.substring(0, separator);
        if (!map.containsKey(key)) {
            return null;
        }
        return get(map.get(key), path.substring(separator), resolver);
    }

    private static Object getFromCollection(Collection<?> collection, String path, JsonPathResolver resolver) {
        IndexPath indexPath = parseIndex(path, collection.size());
        if (indexPath == null) {
            return null;
        }
        Object value;
        if (collection instanceof List) {
            value = ((List<?>) collection).get(indexPath.index);
        } else {
            int current = 0;
            value = null;
            for (Object item : collection) {
                if (current++ == indexPath.index) {
                    value = item;
                    break;
                }
            }
        }
        return indexPath.remaining.isEmpty() ? value : get(value, indexPath.remaining, resolver);
    }

    private static Object getFromArray(Object array, String path, JsonPathResolver resolver) {
        IndexPath indexPath = parseIndex(path, Array.getLength(array));
        if (indexPath == null) {
            return null;
        }
        Object value = Array.get(array, indexPath.index);
        return indexPath.remaining.isEmpty() ? value : get(value, indexPath.remaining, resolver);
    }

    private static IndexPath parseIndex(String path, int size) {
        if (!path.startsWith("[")) {
            return null;
        }
        int end = path.indexOf(']');
        if (end <= 1) {
            return null;
        }
        try {
            int index = Integer.parseInt(path.substring(1, end));
            if (index < 0) {
                index += size;
            }
            if (index < 0 || index >= size) {
                return null;
            }
            return new IndexPath(index, path.substring(end + 1));
        } catch (NumberFormatException invalidIndex) {
            return null;
        }
    }

    private static int firstSeparator(String path) {
        int dot = path.indexOf('.');
        int bracket = path.indexOf('[');
        if (dot < 0) {
            return bracket;
        }
        if (bracket < 0) {
            return dot;
        }
        return Math.min(dot, bracket);
    }

    private static final class IndexPath {
        private final int index;
        private final String remaining;

        private IndexPath(int index, String remaining) {
            this.index = index;
            this.remaining = remaining;
        }
    }
}
