package com.dlz.kit.json.core;

/**
 * 携带稳定源码定位信息的 JSON 解析失败异常。
 *
 * <p>相比 {@link JsonException}，本异常额外记录出错位置：字符偏移量（offset）、行号（line）、
 * 列号（column），便于上层定位并展示具体的解析错误位置。</p>
 */
public final class JsonParseException extends JsonException {
    private final int offset;
    private final int line;
    private final int column;

    JsonParseException(String message, int offset, int line, int column) {
        super(message + " at line " + line + ", column " + column + " (offset " + offset + ")");
        this.offset = offset;
        this.line = line;
        this.column = column;
    }

    public int getOffset() {
        return offset;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
