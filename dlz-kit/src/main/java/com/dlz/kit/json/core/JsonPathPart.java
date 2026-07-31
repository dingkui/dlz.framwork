package com.dlz.kit.json.core;

/** First segment and remaining suffix of a DLZ JSON path. */
public final class JsonPathPart {
    private final String segment;
    private final String remaining;

    JsonPathPart(String segment, String remaining) {
        this.segment = segment;
        this.remaining = remaining;
    }

    public String getSegment() {
        return segment;
    }

    public String getRemaining() {
        return remaining;
    }
}
