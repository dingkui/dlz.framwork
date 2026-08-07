package com.dlz.kit.json.core;

import com.dlz.kit.json.JSONList;
import com.dlz.kit.json.JSONMap;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * JSON 文本解析器（语法层，包内私有）。
 *
 * <p>采用手写的递归下降（recursive descent）解析算法，逐个字符扫描输入字符串，
 * 将其构建为 Java 原生对象：对象→{@link java.util.LinkedHashMap}、数组→{@link java.util.ArrayList}、
 * 字符串→String、数字→Integer/Long/BigInteger/BigDecimal、布尔→Boolean、null→null。
 * 解析行为受 {@link JsonOptions} 控制（特性开关与安全上限）。</p>
 */
final class JsonParser {
    /** 待解析的原始 JSON 文本。 */
    private final String input;
    /** 解析选项（特性与上限）。 */
    private final JsonOptions options;
    /** 当前读取位置（字符偏移量）。 */
    private int offset;
    /** 当前行号（用于错误定位）。 */
    private int line = 1;
    /** 当前列号（用于错误定位）。 */
    private int column = 1;

    JsonParser(String input, JsonOptions options) {
        if (input == null) {
            throw new IllegalArgumentException("json cannot be null");
        }
        this.input = input;
        this.options = options;
    }

    /**
     * 解析入口：跳过前后空白/注释后解析根值，并确保末尾没有多余字符。
     *
     * @return 解析得到的 Java 对象
     */
    Object parse() {
        skipIgnorable();
        if (isEnd()) {
            throw error("JSON input is empty");
        }
        Object result = parseValue(0);
        skipIgnorable();
        if (!isEnd()) {
            throw error("Unexpected trailing character '" + peek() + "'");
        }
        return result;
    }

    /**
     * 解析任意 JSON 值（对象/数组/字符串/数字/布尔/null/裸值），按当前字符分派。
     *
     * @param depth 当前嵌套深度，用于防止过深递归
     * @return 解析后的 Java 值
     */
    private Object parseValue(int depth) {
        checkDepth(depth);
        skipIgnorable();
        if (isEnd()) {
            throw error("Expected a JSON value");
        }
        char value = peek();
        if (value == '{') {
            return parseObject(depth + 1);
        }
        if (value == '[') {
            return parseArray(depth + 1);
        }
        if (value == '"' || (value == '\'' && enabled(JsonFeature.ALLOW_SINGLE_QUOTES))) {
            return parseString(read());
        }
        if (value == 't' && matchesLiteral("true")) {
            consumeLiteral("true");
            return Boolean.TRUE;
        }
        if (value == 'f' && matchesLiteral("false")) {
            consumeLiteral("false");
            return Boolean.FALSE;
        }
        if (value == 'n' && matchesLiteral("null")) {
            consumeLiteral("null");
            return null;
        }
        if (value == '-' || isDigit(value)) {
            return parseNumber();
        }
        if (enabled(JsonFeature.ALLOW_UNQUOTED_STRING_VALUES)) {
            return parseBareToken();
        }
        throw error("Unexpected character '" + value + "'");
    }

    /**
     * 解析 JSON 对象 {@code {...}}，键保持插入顺序（LinkedHashMap）。
     *
     * @param depth 当前嵌套深度
     * @return 解析得到的对象映射
     */
    private JSONMap parseObject(int depth) {
        expect('{');
        JSONMap result = new JSONMap();
        skipIgnorable();
        if (consumeIf('}')) {
            return result;
        }
        while (true) {
            skipIgnorable();
            String key = parseObjectKey();
            skipIgnorable();
            expect(':');
            Object value = parseValue(depth);
            if (enabled(JsonFeature.FAIL_ON_DUPLICATE_KEYS) && result.containsKey(key)) {
                throw error("Duplicate object key '" + key + "'");
            }
            result.put(key, value);
            checkContainerSize(result.size());
            skipIgnorable();
            if (consumeIf('}')) {
                return result;
            }
            expect(',');
            skipIgnorable();
            if (peekIf('}')) {
                if (!enabled(JsonFeature.ALLOW_TRAILING_COMMA)) {
                    throw error("Trailing comma is not allowed");
                }
                read();
                return result;
            }
        }
    }

    /**
     * 解析对象键：支持双引号/单引号包裹的键，以及在开启
     * {@link JsonFeature#ALLOW_UNQUOTED_KEYS} 时的裸键。
     *
     * @return 键字符串
     */
    private String parseObjectKey() {
        if (isEnd()) {
            throw error("Expected an object key");
        }
        char quote = peek();
        if (quote == '"' || (quote == '\'' && enabled(JsonFeature.ALLOW_SINGLE_QUOTES))) {
            read();
            return parseString(quote);
        }
        if (!enabled(JsonFeature.ALLOW_UNQUOTED_KEYS)) {
            throw error("Object keys must be quoted");
        }
        int start = offset;
        while (!isEnd()) {
            char value = peek();
            if (value == ':' || isJsonWhitespace(value)) {
                break;
            }
            if (value == ',' || value == '}' || value == '{' || value == '[' || value == ']') {
                throw error("Invalid unquoted object key");
            }
            read();
        }
        if (start == offset) {
            throw error("Expected an object key");
        }
        String key = input.substring(start, offset);
        checkStringLength(key.length());
        return key;
    }

    /**
     * 解析 JSON 数组 {@code [...]}。
     *
     * @param depth 当前嵌套深度
     * @return 解析得到的列表
     */
    private JSONList parseArray(int depth) {
        expect('[');
        JSONList result = new JSONList();
        skipIgnorable();
        if (consumeIf(']')) {
            return result;
        }
        while (true) {
            result.add(parseValue(depth));
            checkContainerSize(result.size());
            skipIgnorable();
            if (consumeIf(']')) {
                return result;
            }
            expect(',');
            skipIgnorable();
            if (peekIf(']')) {
                if (!enabled(JsonFeature.ALLOW_TRAILING_COMMA)) {
                    throw error("Trailing comma is not allowed");
                }
                read();
                return result;
            }
        }
    }

    /**
     * 解析被引号包裹的字符串，处理转义序列（{@code \\}、{@code \"}、{@code \\uXXXX} 等），
     * 并实时校验字符串长度上限。
     *
     * @param quote 字符串的引号字符（{@code "} 或 {@code '}）
     * @return 解析后的字符串
     */
    private String parseString(char quote) {
        StringBuilder result = new StringBuilder();
        while (!isEnd()) {
            char value = read();
            if (value == quote) {
                return result.toString();
            }
            if (value == '\\') {
                appendEscape(result, quote);
            } else {
                if (value < 0x20) {
                    throw error("Unescaped control character in string");
                }
                result.append(value);
            }
            if (result.length() > options.getMaxStringLength()) {
                throw error("String exceeds maxStringLength " + options.getMaxStringLength());
            }
        }
        throw error("Unterminated string");
    }

    /**
     * 处理一个转义字符（紧跟在反斜杠 {@code \} 之后的字符），将其对应的真实字符追加到结果。
     *
     * @param result 结果字符串构建器
     * @param quote  当前字符串的引号字符，用于判断是否允许 {@code \'}
     */
    private void appendEscape(StringBuilder result, char quote) {
        if (isEnd()) {
            throw error("Unterminated escape sequence");
        }
        char escaped = read();
        switch (escaped) {
            case '"': result.append('"'); return;
            case '\'':
                if (quote == '\'' && enabled(JsonFeature.ALLOW_SINGLE_QUOTES)) {
                    result.append('\'');
                    return;
                }
                throw error("Invalid escape sequence \\'");
            case '\\': result.append('\\'); return;
            case '/': result.append('/'); return;
            case 'b': result.append('\b'); return;
            case 'f': result.append('\f'); return;
            case 'n': result.append('\n'); return;
            case 'r': result.append('\r'); return;
            case 't': result.append('\t'); return;
            case 'u': result.append(parseUnicodeEscape()); return;
            default: throw error("Invalid escape sequence \\" + escaped + "'");
        }
    }

    /** 解析 {@code \\uXXXX} 形式的 4 位十六进制 Unicode 转义，返回对应字符。 */
    private char parseUnicodeEscape() {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            if (isEnd()) {
                throw error("Incomplete unicode escape");
            }
            int digit = Character.digit(read(), 16);
            if (digit < 0) {
                throw error("Invalid unicode escape");
            }
            value = (value << 4) | digit;
        }
        return (char) value;
    }

    /**
     * 解析数字：支持负号、整数、小数、指数形式；按数值大小自动选择
     * Integer / Long / BigInteger / BigDecimal 类型返回。
     *
     * @return 解析后的数字对象
     */
    private Number parseNumber() {
        int start = offset;
        consumeIf('-');
        if (consumeIf('0')) {
            if (!isEnd() && isDigit(peek())) {
                throw error("Leading zero is not allowed in a number");
            }
        } else {
            requireDigits("Expected an integer digit");
        }
        boolean decimal = false;
        if (consumeIf('.')) {
            decimal = true;
            requireDigits("Expected a digit after decimal point");
        }
        if (!isEnd() && (peek() == 'e' || peek() == 'E')) {
            decimal = true;
            read();
            if (!isEnd() && (peek() == '+' || peek() == '-')) {
                read();
            }
            requireDigits("Expected an exponent digit");
        }
        String token = input.substring(start, offset);
        try {
            if (decimal) {
                return new BigDecimal(token);
            }
            BigInteger integer = new BigInteger(token);
            if (integer.bitLength() < 32) {
                return Integer.valueOf(integer.intValue());
            }
            if (integer.bitLength() < 64) {
                return Long.valueOf(integer.longValue());
            }
            return integer;
        } catch (NumberFormatException exception) {
            throw error("Invalid number '" + token + "'");
        }
    }

    /**
     * 解析未加引号的裸值（仅在开启 {@link JsonFeature#ALLOW_UNQUOTED_STRING_VALUES} 时调用），
     * 读取到空白/分隔符为止。
     *
     * @return 裸值字符串
     */
    private String parseBareToken() {
        int start = offset;
        while (!isEnd()) {
            char value = peek();
            if (isJsonWhitespace(value) || value == ',' || value == ']' || value == '}') {
                break;
            }
            read();
        }
        if (start == offset) {
            throw error("Expected a value");
        }
        String value = input.substring(start, offset);
        checkStringLength(value.length());
        return value;
    }

    /** 消费（逐字符匹配）指定的字面量（true/false/null），不匹配则报错。 */
    private void consumeLiteral(String literal) {
        for (int i = 0; i < literal.length(); i++) {
            if (isEnd() || read() != literal.charAt(i)) {
                throw error("Invalid literal, expected '" + literal + "'");
            }
        }
    }

    /** 判断从当前位置起是否匹配某字面量，且其后跟随空白或分隔符（即字面量已结束）。 */
    private boolean matchesLiteral(String literal) {
        if (!input.regionMatches(offset, literal, 0, literal.length())) {
            return false;
        }
        int end = offset + literal.length();
        if (end >= input.length()) {
            return true;
        }
        char next = input.charAt(end);
        return isJsonWhitespace(next) || next == ',' || next == ']' || next == '}';
    }

    /** 消费至少一个数字字符；若没有则抛出给定信息的错误。 */
    private void requireDigits(String message) {
        int start = offset;
        while (!isEnd() && isDigit(peek())) {
            read();
        }
        if (start == offset) {
            throw error(message);
        }
    }

    /**
     * 跳过所有可忽略内容：空白字符，以及在开启 {@link JsonFeature#ALLOW_COMMENTS} 时的
     * 行注释（{@code //}）与块注释（{@code /* ... *\/}）。循环处理连续注释，直到无可跳过内容。
     */
    private void skipIgnorable() {
        boolean progressed;
        do {
            progressed = false;
            while (!isEnd() && isJsonWhitespace(peek())) {
                read();
                progressed = true;
            }
            if (enabled(JsonFeature.ALLOW_COMMENTS) && !isEnd() && peek() == '/' && offset + 1 < input.length()) {
                char next = input.charAt(offset + 1);
                if (next == '/') {
                    read();
                    read();
                    while (!isEnd() && peek() != '\n' && peek() != '\r') {
                        read();
                    }
                    progressed = true;
                } else if (next == '*') {
                    read();
                    read();
                    boolean closed = false;
                    while (!isEnd()) {
                        if (peek() == '*' && offset + 1 < input.length() && input.charAt(offset + 1) == '/') {
                            read();
                            read();
                            closed = true;
                            break;
                        }
                        read();
                    }
                    if (!closed) {
                        throw error("Unterminated block comment");
                    }
                    progressed = true;
                }
            }
        } while (progressed);
    }

    /** 校验嵌套深度是否超过上限（防栈溢出/拒绝服务）。 */
    private void checkDepth(int depth) {
        if (depth > options.getMaxDepth()) {
            throw error("JSON nesting depth exceeds " + options.getMaxDepth());
        }
    }

    /** 校验容器元素个数是否超过上限。 */
    private void checkContainerSize(int size) {
        if (size > options.getMaxContainerSize()) {
            throw error("JSON container size exceeds " + options.getMaxContainerSize());
        }
    }

    /** 校验字符串长度是否超过上限。 */
    private void checkStringLength(int length) {
        if (length > options.getMaxStringLength()) {
            throw error("String exceeds maxStringLength " + options.getMaxStringLength());
        }
    }

    /** 查询某解析特性是否开启。 */
    private boolean enabled(JsonFeature feature) {
        return options.isEnabled(feature);
    }

    private void expect(char expected) {
        if (isEnd() || peek() != expected) {
            throw error("Expected '" + expected + "'");
        }
        read();
    }

    /** 若当前字符为指定字符则消费之并返回 true，否则返回 false（不报错）。 */
    private boolean consumeIf(char expected) {
        if (peekIf(expected)) {
            read();
            return true;
        }
        return false;
    }

    /** 不消费字符，仅判断当前字符是否为指定字符。 */
    private boolean peekIf(char expected) {
        return !isEnd() && peek() == expected;
    }

    /** 返回当前字符但不移动游标（已到末尾调用会越界，需配合 isEnd）。 */
    private char peek() {
        return input.charAt(offset);
    }

    /** 消费并返回当前字符，同时维护行号/列号（遇换行重置列号）。 */
    private char read() {
        char value = input.charAt(offset++);
        if (value == '\n') {
            line++;
            column = 1;
        } else {
            column++;
        }
        return value;
    }

    /** 是否已读至输入末尾。 */
    private boolean isEnd() {
        return offset >= input.length();
    }

    /** 以当前位置（offset/line/column）构造统一的解析异常。 */
    private JsonParseException error(String message) {
        return new JsonParseException(message, offset, line, column);
    }

    /** 判断字符是否为数字 0-9。 */
    private static boolean isDigit(char value) {
        return value >= '0' && value <= '9';
    }

    /** 判断字符是否为 JSON 规定的空白（空格/制表符/换行/回车）。 */
    private static boolean isJsonWhitespace(char value) {
        return value == ' ' || value == '\t' || value == '\n' || value == '\r';
    }
}
