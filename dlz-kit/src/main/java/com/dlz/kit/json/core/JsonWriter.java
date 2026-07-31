package com.dlz.kit.json.core;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;

final class JsonWriter {
    private final JsonOptions options;
    private final StringBuilder output = new StringBuilder();
    private final IdentityHashMap<Object, Boolean> visiting = new IdentityHashMap<Object, Boolean>();

    JsonWriter(JsonOptions options) {
        this.options = options;
    }

    String write(Object value) {
        writeValue(value, 0);
        return output.toString();
    }

    private void writeValue(Object value, int depth) {
        checkDepth(depth);
        if (value == null) {
            output.append("null");
        } else if (value instanceof CharSequence || value instanceof Character || value instanceof Enum) {
            writeString(value instanceof Enum ? ((Enum<?>) value).name() : value.toString());
        } else if (value instanceof Boolean) {
            output.append(value);
        } else if (value instanceof Number) {
            writeNumber((Number) value);
        } else if (value instanceof Map) {
            writeObject((Map<?, ?>) value, depth + 1);
        } else if (value instanceof Iterable) {
            writeArray(((Iterable<?>) value).iterator(), value, depth + 1);
        } else if (value.getClass().isArray()) {
            writeJavaArray(value, depth + 1);
        } else {
            throw new JsonException("Unsupported JSON value type: " + value.getClass().getName());
        }
    }

    private void writeObject(Map<?, ?> value, int depth) {
        checkContainerSize(value.size());
        enter(value);
        try {
            output.append('{');
            boolean first = true;
            for (Map.Entry<?, ?> entry : value.entrySet()) {
                if (entry.getKey() == null) {
                    throw new JsonException("JSON object key cannot be null");
                }
                if (entry.getValue() == null && !enabled(JsonFeature.WRITE_NULLS)) {
                    continue;
                }
                if (!first) {
                    output.append(',');
                }
                newlineAndIndent(depth);
                writeString(entry.getKey().toString());
                output.append(enabled(JsonFeature.WRITE_PRETTY) ? ": " : ":");
                writeValue(entry.getValue(), depth);
                first = false;
            }
            if (!first) {
                newlineAndIndent(depth - 1);
            }
            output.append('}');
        } finally {
            leave(value);
        }
    }

    private void writeArray(Iterator<?> iterator, Object identity, int depth) {
        enter(identity);
        try {
            output.append('[');
            boolean first = true;
            int size = 0;
            while (iterator.hasNext()) {
                checkContainerSize(++size);
                if (!first) {
                    output.append(',');
                }
                newlineAndIndent(depth);
                writeValue(iterator.next(), depth);
                first = false;
            }
            if (!first) {
                newlineAndIndent(depth - 1);
            }
            output.append(']');
        } finally {
            leave(identity);
        }
    }

    private void writeJavaArray(Object value, int depth) {
        enter(value);
        try {
            output.append('[');
            int length = Array.getLength(value);
            checkContainerSize(length);
            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    output.append(',');
                }
                newlineAndIndent(depth);
                writeValue(Array.get(value, i), depth);
            }
            if (length > 0) {
                newlineAndIndent(depth - 1);
            }
            output.append(']');
        } finally {
            leave(value);
        }
    }

    private void writeNumber(Number value) {
        if (value instanceof Double && !Double.isFinite(((Double) value).doubleValue())) {
            throw new JsonException("Non-finite double is not valid JSON");
        }
        if (value instanceof Float && !Float.isFinite(((Float) value).floatValue())) {
            throw new JsonException("Non-finite float is not valid JSON");
        }
        if (value instanceof BigDecimal) {
            output.append(((BigDecimal) value).toPlainString());
        } else {
            output.append(value.toString());
        }
    }

    private void writeString(String value) {
        if (value.length() > options.getMaxStringLength()) {
            throw new JsonException("String exceeds maxStringLength " + options.getMaxStringLength());
        }
        output.append('"');
        for (int i = 0; i < value.length(); i++) {
            char current = value.charAt(i);
            switch (current) {
                case '"': output.append("\\\""); break;
                case '\\': output.append("\\\\"); break;
                case '\b': output.append("\\b"); break;
                case '\f': output.append("\\f"); break;
                case '\n': output.append("\\n"); break;
                case '\r': output.append("\\r"); break;
                case '\t': output.append("\\t"); break;
                default:
                    if (current < 0x20 || (enabled(JsonFeature.ESCAPE_NON_ASCII) && current > 0x7f)) {
                        appendUnicode(current);
                    } else {
                        output.append(current);
                    }
            }
        }
        output.append('"');
    }

    private void appendUnicode(char value) {
        output.append("\\u");
        String hex = Integer.toHexString(value);
        for (int i = hex.length(); i < 4; i++) {
            output.append('0');
        }
        output.append(hex);
    }

    private void newlineAndIndent(int depth) {
        if (!enabled(JsonFeature.WRITE_PRETTY)) {
            return;
        }
        output.append('\n');
        for (int i = 0; i < depth; i++) {
            output.append("  ");
        }
    }

    private void enter(Object value) {
        if (visiting.put(value, Boolean.TRUE) != null) {
            throw new JsonException("Circular reference detected while writing JSON");
        }
    }

    private void leave(Object value) {
        visiting.remove(value);
    }

    private void checkDepth(int depth) {
        if (depth > options.getMaxDepth()) {
            throw new JsonException("JSON nesting depth exceeds " + options.getMaxDepth());
        }
    }

    private void checkContainerSize(int size) {
        if (size > options.getMaxContainerSize()) {
            throw new JsonException("JSON container size exceeds " + options.getMaxContainerSize());
        }
    }

    private boolean enabled(JsonFeature feature) {
        return options.isEnabled(feature);
    }
}
