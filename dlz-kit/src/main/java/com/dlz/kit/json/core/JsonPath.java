package com.dlz.kit.json.core;

import com.dlz.kit.util.system.FieldReflections;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 使用 DLZ 路径语法，从 Map、Collection、数组或 JSON 文本中读取值的工具类。
 *
 * <p>路径语法支持点号取键（{@code a.b.c}）与方括号索引（{@code list[0]}），例如
 * {@code "user.address[0].city"}。当源数据为字符串时，会先以"兼容模式"解析为 JSON。</p>
 *
 * <p>对于非原生容器（如普通 Java Bean），可通过 {@link JsonPathResolver} 扩展解析能力。</p>
 */
public final class JsonPath {
    /**
     * 用于把字符串源数据解析为 JSON 的兼容选项（允许注释、单引号、裸键）。
     */
    private static final JsonOptions COMPATIBLE_JSON = JsonOptions.builder()
            .enable(JsonFeature.ALLOW_COMMENTS)
            .enable(JsonFeature.ALLOW_SINGLE_QUOTES)
            .enable(JsonFeature.ALLOW_UNQUOTED_KEYS)
            .enable(JsonFeature.WRITE_NULLS)
            .build();

    /**
     * 工具类禁止实例化。
     */
    private JsonPath() {
    }

    /**
     * 从数据中按路径取值（不传自定义解析器）。
     *
     * @param data 源数据（Map / Collection / 数组 / JSON 字符串等）
     * @param path 路径表达式
     * @return 命中的值，未命中或源为 null 时返回 null
     */
    public static Object get(Object data, String path) {
        return get(data, path, null);
    }

    /**
     * 从数据中按路径取值，支持自定义解析器处理非原生容器。
     *
     * @param data     源数据
     * @param path     路径表达式
     * @param resolver 自定义解析器（可为 null）
     * @return 命中的值，未命中时返回 null
     */
    public static Object get(Object data, String path, JsonPathResolver resolver) {
        if (data == null) {
            return null;
        }
        if (path == null || path.isEmpty()) {
            return data;
        }
        // 字符串先尝试解析成 JSON 对象/数组
        if (data instanceof CharSequence) {
            try {
                data = Json.parse(data.toString(), COMPATIBLE_JSON);
            } catch (JsonException invalidJson) {
                return null;
            }
        }
        // 去掉开头的点号
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
        return resolver == null ? FieldReflections.getValue(data, normalized) : resolver.resolve(data, normalized);
    }

    /**
     * 从 Map 中按路径取值，支持多级键与 {@code [index]} 索引混合。
     *
     * @param map      源映射
     * @param path     剩余路径
     * @param resolver 自定义解析器
     * @return 命中值或 null
     */
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

    /**
     * 从 Collection 中按 {@code [index]} 索引取值，并继续回溯剩余路径。
     *
     * @param collection 源集合
     * @param path       剩余路径（应以 {@code [} 开头）
     * @param resolver   自定义解析器
     * @return 命中值或 null
     */
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

    /**
     * 从 Java 数组中按 {@code [index]} 索引取值，并继续回溯剩余路径。
     *
     * @param array    源数组
     * @param path     剩余路径（应以 {@code [} 开头）
     * @param resolver 自定义解析器
     * @return 命中值或 null
     */
    private static Object getFromArray(Object array, String path, JsonPathResolver resolver) {
        IndexPath indexPath = parseIndex(path, Array.getLength(array));
        if (indexPath == null) {
            return null;
        }
        Object value = Array.get(array, indexPath.index);
        return indexPath.remaining.isEmpty() ? value : get(value, indexPath.remaining, resolver);
    }

    /**
     * 解析路径开头的 {@code [index]} 索引片段。
     * 支持负数从末尾倒数（{@code [-1]} 表示最后一个），越界返回 null。
     *
     * @param path 剩余路径
     * @param size 集合/数组长度
     * @return 解析出的索引与剩余路径，或 null（格式非法/越界）
     */
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

    /**
     * 找出路径中第一个分隔符（点号或左方括号）的位置，用于切分一级键。
     */
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

    /**
     * 持有一次索引解析结果：命中下标与尚未消费的剩余路径。
     */
    private static final class IndexPath {
        private final int index;
        private final String remaining;

        private IndexPath(int index, String remaining) {
            this.index = index;
            this.remaining = remaining;
        }
    }
}
