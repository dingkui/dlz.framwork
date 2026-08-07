package com.dlz.kit.json.core;

/**
 * 针对 JSON 文本的"快速、浅层"根形态判断工具。
 *
 * <p>仅通过首尾字符快速判断字符串像是对象还是数组，<b>不做语法校验</b>。
 * 适用于需要快速分派（如判断要不要走对象解析）的场景，精确性不如完整解析。</p>
 */
public final class JsonText {
    /** 工具类禁止实例化。 */
    private JsonText() {
    }

    /** 判断文本是否"像"JSON 对象（以 {@code {} } 包裹，去掉首尾空白后）。 */
    public static boolean isObject(String value) {
        return hasDelimiters(value, '{', '}');
    }

    /** 判断文本是否"像"JSON 数组（以 {@code []} 包裹，去掉首尾空白后）。 */
    public static boolean isArray(String value) {
        return hasDelimiters(value, '[', ']');
    }

    /** 判断去空白后首尾字符是否分别为给定的开/闭分隔符。 */
    private static boolean hasDelimiters(String value, char opening, char closing) {
        if (value == null) {
            return false;
        }
        String trimmed = value.trim();
        return trimmed.length() >= 2
                && trimmed.charAt(0) == opening
                && trimmed.charAt(trimmed.length() - 1) == closing;
    }
}
