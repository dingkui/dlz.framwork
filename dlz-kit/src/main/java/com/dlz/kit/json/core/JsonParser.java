package com.dlz.kit.json.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class JsonParser {
    private final String input;
    private final JsonOptions options;
    private int offset;
    private int line = 1;
    private int column = 1;

    JsonParser(String input, JsonOptions options) {
        if (input == null) {
            throw new IllegalArgumentException("json cannot be null");
        }
        this.input = input;
        this.options = options;
    }

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

    private Map<String, Object> parseObject(int depth) {
        expect('{');
        Map<String, Object> result = new LinkedHashMap<String, Object>();
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

    private List<Object> parseArray(int depth) {
        expect('[');
        List<Object> result = new ArrayList<Object>();
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

    private void consumeLiteral(String literal) {
        for (int i = 0; i < literal.length(); i++) {
            if (isEnd() || read() != literal.charAt(i)) {
                throw error("Invalid literal, expected '" + literal + "'");
            }
        }
    }

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

    private void requireDigits(String message) {
        int start = offset;
        while (!isEnd() && isDigit(peek())) {
            read();
        }
        if (start == offset) {
            throw error(message);
        }
    }

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

    private void checkDepth(int depth) {
        if (depth > options.getMaxDepth()) {
            throw error("JSON nesting depth exceeds " + options.getMaxDepth());
        }
    }

    private void checkContainerSize(int size) {
        if (size > options.getMaxContainerSize()) {
            throw error("JSON container size exceeds " + options.getMaxContainerSize());
        }
    }

    private void checkStringLength(int length) {
        if (length > options.getMaxStringLength()) {
            throw error("String exceeds maxStringLength " + options.getMaxStringLength());
        }
    }

    private boolean enabled(JsonFeature feature) {
        return options.isEnabled(feature);
    }

    private void expect(char expected) {
        if (isEnd() || peek() != expected) {
            throw error("Expected '" + expected + "'");
        }
        read();
    }

    private boolean consumeIf(char expected) {
        if (peekIf(expected)) {
            read();
            return true;
        }
        return false;
    }

    private boolean peekIf(char expected) {
        return !isEnd() && peek() == expected;
    }

    private char peek() {
        return input.charAt(offset);
    }

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

    private boolean isEnd() {
        return offset >= input.length();
    }

    private JsonParseException error(String message) {
        return new JsonParseException(message, offset, line, column);
    }

    private static boolean isDigit(char value) {
        return value >= '0' && value <= '9';
    }

    private static boolean isJsonWhitespace(char value) {
        return value == ' ' || value == '\t' || value == '\n' || value == '\r';
    }
}
