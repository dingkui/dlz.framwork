package com.dlz.kit.json.core;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * JSON 序列化器（语法层，包内私有）。
 *
 * <p>把 Java 对象递归写成 JSON 文本：Map→对象、Iterable/数组→数组、字符串→带引号字符串、
 * 数字→数字、布尔→布尔、null→null、枚举→其 name()。使用 {@link IdentityHashMap} 检测
 * 循环引用，避免无限递归。行为受 {@link JsonOptions} 控制（null 输出、美化、非 ASCII 转义等）。</p>
 */
final class JsonWriter {
    /** 序列化选项（特性与上限）。 */
    private final JsonOptions options;
    /** 输出缓冲区。 */
    private final StringBuilder output = new StringBuilder();
    /** 正在写入的对象集合，用于检测循环引用（按对象身份去重）。 */
    private final IdentityHashMap<Object, Boolean> visiting = new IdentityHashMap<Object, Boolean>();

    JsonWriter(JsonOptions options) {
        this.options = options;
    }

    /**
     * 入口：把值序列化为 JSON 文本字符串。
     *
     * @param value 任意 Java 对象
     * @return JSON 文本
     */
    String write(Object value) {
        writeValue(value, 0);
        return output.toString();
    }

    /**
     * 按类型分派，写入单个值。
     *
     * @param value 待写入的值
     * @param depth 当前嵌套深度
     */
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

    /**
     * 写入 JSON 对象 {@code {...}}，键保持顺序；未开启 WRITE_NULLS 时跳过 null 值字段。
     *
     * @param value 源映射
     * @param depth 当前嵌套深度
     */
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

    /**
     * 写入 JSON 数组 {@code [...]}（来源为 Iterable）。
     *
     * @param iterator 元素迭代器
     * @param identity 用于循环检测的原始集合对象
     * @param depth    当前嵌套深度
     */
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

    /**
     * 写入 JSON 数组（来源为 Java 原生数组，使用反射逐个取值）。
     *
     * @param value 源数组
     * @param depth 当前嵌套深度
     */
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
        if (value instanceof Double && !Double.isFinite((value).doubleValue())) {
            throw new JsonException("Non-finite double is not valid JSON");
        }
        if (value instanceof Float && !Float.isFinite((value).floatValue())) {
            throw new JsonException("Non-finite float is not valid JSON");
        }
        if (value instanceof BigDecimal) {
            output.append(((BigDecimal) value).toPlainString());
        } else {
            output.append(value.toString());
        }
    }

    /**
     * 写入带双引号的字符串，转义控制字符与引号；开启 ESCAPE_NON_ASCII 时转义非 ASCII。
     *
     * @param value 源字符串
     */
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

    /** 把一个字符写成 {@code \\uXXXX} 形式的转义序列（不足 4 位前补 0）。 */
    private void appendUnicode(char value) {
        output.append("\\u");
        String hex = Integer.toHexString(value);
        for (int i = hex.length(); i < 4; i++) {
            output.append('0');
        }
        output.append(hex);
    }

    /** 开启 WRITE_PRETTY 时换行并按层级缩进（每层两个空格）。 */
    private void newlineAndIndent(int depth) {
        if (!enabled(JsonFeature.WRITE_PRETTY)) {
            return;
        }
        output.append('\n');
        for (int i = 0; i < depth; i++) {
            output.append("  ");
        }
    }

    /** 进入一个容器对象：若已处于访问中说明存在循环引用，立即报错。 */
    private void enter(Object value) {
        if (visiting.put(value, Boolean.TRUE) != null) {
            throw new JsonException("Circular reference detected while writing JSON");
        }
    }

    /** 离开容器对象：从访问集合中移除，配合 {@link #enter(Object)} 使用。 */
    private void leave(Object value) {
        visiting.remove(value);
    }

    /** 校验嵌套深度是否超过上限。 */
    private void checkDepth(int depth) {
        if (depth > options.getMaxDepth()) {
            throw new JsonException("JSON nesting depth exceeds " + options.getMaxDepth());
        }
    }

    /** 校验容器元素个数是否超过上限。 */
    private void checkContainerSize(int size) {
        if (size > options.getMaxContainerSize()) {
            throw new JsonException("JSON container size exceeds " + options.getMaxContainerSize());
        }
    }

    /** 查询某序列化特性是否开启。 */
    private boolean enabled(JsonFeature feature) {
        return options.isEnabled(feature);
    }
}
