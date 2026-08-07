package com.dlz.kit.json.core;

/**
 * DLZ JSON 路径的一次切分结果：当前段（segment）与剩余路径（remaining）。
 *
 * <p>由 {@link JsonPathParser#split(String)} 产生，用于逐层遍历路径。
 * 例如路径 {@code a.b.c} 首次切分为 segment={@code a}、remaining={@code b.c}。</p>
 */
public final class JsonPathPart {
    /** 当前路径段（如键名或 {@code [0]} 索引片段）。 */
    private final String segment;
    /** 该段之后尚未消费的路径；已到末尾则为 null。 */
    private final String remaining;

    JsonPathPart(String segment, String remaining) {
        this.segment = segment;
        this.remaining = remaining;
    }

    /** 获取当前路径段。 */
    public String getSegment() {
        return segment;
    }

    /** 获取剩余路径（可能为 null）。 */
    public String getRemaining() {
        return remaining;
    }
}
