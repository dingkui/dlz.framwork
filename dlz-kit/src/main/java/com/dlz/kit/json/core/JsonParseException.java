package com.dlz.kit.json.core;

/** Parsing failure with a stable source location. */
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
