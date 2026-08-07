package com.dlz.kit.json.core;

/**
 * JSON 编解码过程中的可选特性（特性开关）枚举。
 *
 * <p>默认采用严格合规的 JSON 解析/写入行为。通过枚举值可放宽对非标准 JSON 的容忍度，
 * 或改变序列化输出格式。这些特性由 {@link JsonOptions} 持有并在解析/写入时查询。</p>
 */
public enum JsonFeature {
    /** 允许 JSON 中出现注释（{@code //} 行注释与 {@code /* *\/} 块注释）。 */
    ALLOW_COMMENTS,
    /** 允许字符串使用单引号（{@code '}）包裹，而非仅限双引号。 */
    ALLOW_SINGLE_QUOTES,
    /** 允许对象键不加引号（裸键）。 */
    ALLOW_UNQUOTED_KEYS,
    /** 允许未加引号的裸字符串值（非标准 JSON）。 */
    ALLOW_UNQUOTED_STRING_VALUES,
    /** 允许对象/数组最后一个元素后保留多余的尾随逗号。 */
    ALLOW_TRAILING_COMMA,
    /** 遇到重复的对象键时直接报错（默认覆盖处理）。 */
    FAIL_ON_DUPLICATE_KEYS,
    /** 序列化时输出值为 null 的字段（默认严格模式开启）。 */
    WRITE_NULLS,
    /** 序列化时按层级缩进换行的"美化"输出。 */
    WRITE_PRETTY,
    /** 序列化时把非 ASCII 字符转义为 {@code \\uXXXX}。 */
    ESCAPE_NON_ASCII
}
