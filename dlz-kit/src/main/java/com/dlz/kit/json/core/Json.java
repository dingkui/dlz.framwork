package com.dlz.kit.json.core;

import com.dlz.kit.json.JSONList;
import com.dlz.kit.json.JSONMap;

import java.util.List;
import java.util.Map;

/**
 * 零依赖（zero-dependency）的 JSON 处理入口类。
 *
 * <p>本类提供了一组静态工具方法，用于把 JSON 文本解析成 Java 对象（{@link Map}、{@link List} 等），
 * 或者把 Java 对象序列化（stringify）成 JSON 文本。所有解析/序列化行为均通过 {@link JsonOptions} 控制。
 * `parse(String)` 默认使用严格模式；`parseObject`、`parseArray` 和 `stringify` 的无选项重载使用宽松模式。</p>
 *
 * <p>典型用法：
 * <pre>
 *   Map&lt;String, Object&gt; obj = Json.parseObject("{\"a\":1}");
 *   String text = Json.stringify(obj);
 * </pre>
 * </p>
 */
public final class Json {
    /** 工具类禁止实例化。 */
    private Json() {
    }

    /**
     * 使用严格模式把 JSON 文本解析成通用 Java 对象。
     *
     * @param json 待解析的 JSON 文本；对象渲染为 {@link Map}，数组渲染为 {@link List}
     * @return 解析后的 Java 对象（可能是 Map / List / String / Number / Boolean / null）
     */
    public static Object parse(String json) {
        return parse(json, JsonOptions.JSON_OPTIONS_STRICT);
    }

    /**
     * 按指定选项把 JSON 文本解析成通用 Java 对象。
     *
     * @param json    待解析的 JSON 文本
     * @param options 解析选项（特性开关与上限配置），不可为 null
     * @return 解析后的 Java 对象
     */
    public static Object parse(String json, JsonOptions options) {
        return new JsonParser(json, requireOptions(options)).parse();
    }

    /** 默认宽松模式：解析 JSON 对象（根必须是 {@code {...}}）。 */
    @SuppressWarnings("unchecked")
    public static JSONMap parseObject(String json) {
        return parseObject(json, JsonOptions.JSON_OPTIONS_LENIENT);
    }

    /**
     * 按指定选项解析 JSON 对象。
     *
     * @param json    待解析的 JSON 文本
     * @param options 解析选项
     * @return 键值对映射，根节点必须为 JSON 对象，否则抛出 {@link JsonException}
     */
    public static JSONMap parseObject(String json, JsonOptions options) {
        Object value = parse(json, options);
        if (!(value instanceof JSONMap)) {
            throw new JsonException("JSON root must be an object");
        }
        return (JSONMap) value;
    }

    /** 默认宽松模式：解析 JSON 数组（根必须是 {@code [...]}）。 */
    public static JSONList parseArray(String json) {
        return parseArray(json, JsonOptions.JSON_OPTIONS_LENIENT);
    }

    /**
     * 按指定选项解析 JSON 数组。
     *
     * @param json    待解析的 JSON 文本
     * @param options 解析选项
     * @return 列表，根节点必须为 JSON 数组，否则抛出 {@link JsonException}
     */
    @SuppressWarnings("unchecked")
    public static JSONList parseArray(String json, JsonOptions options) {
        Object value = parse(json, options);
        if (!(value instanceof JSONList)) {
            throw new JsonException("JSON root must be an array");
        }
        return (JSONList) value;
    }

    /** 默认宽松模式：把 Java 对象序列化（stringify）为 JSON 文本。 */
    public static String stringify(Object value) {
        return stringify(value, JsonOptions.JSON_OPTIONS_LENIENT);
    }

    /**
     * 按指定选项把 Java 对象序列化（stringify）为 JSON 文本。
     *
     * @param value  任意 Java 对象（Map / List / 数组 / 字符串 / 数字 / 布尔 / null 等）
     * @param options 序列化选项
     * @return 序列化后的 JSON 文本
     */
    public static String stringify(Object value, JsonOptions options) {
        return new JsonWriter(requireOptions(options)).write(value);
    }

    /** 校验选项非 null，避免后续逻辑出现 NPE。 */
    private static JsonOptions requireOptions(JsonOptions options) {
        if (options == null) {
            throw new IllegalArgumentException("options cannot be null");
        }
        return options;
    }
}
