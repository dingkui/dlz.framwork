package com.dlz.kit.json.core;

import java.util.EnumSet;

/**
 * JSON 编解码器使用的"特性开关 + 安全上限"配置，不可变（immutable）。
 *
 * <p>实例通过 {@link Builder} 构建，提供两种预设：
 * <ul>
 *   <li>{@link #JSON_OPTIONS_STRICT}：严格合规 JSON（默认输出 null 字段）。</li>
 *   <li>{@link #JSON_OPTIONS_LENIENT}：放宽对非标准 JSON 的容忍（注释、单引号、裸键、尾随逗号等）。</li>
 * </ul>
 * 一旦构建完成，配置不可修改，可安全地在多线程间共享。</p>
 */
public final class JsonOptions {
    /**
     * 默认最大嵌套深度（防深层递归攻击）。
     */
    private static final int DEFAULT_MAX_DEPTH = 256;
    /**
     * 默认字符串最大长度（10MB）。
     */
    private static final int DEFAULT_MAX_STRING_LENGTH = 10 * 1024 * 1024;
    /**
     * 默认容器（对象/数组）最大元素个数。
     */
    private static final int DEFAULT_MAX_CONTAINER_SIZE = 1_000_000;

    /**
     * 已开启的特性集合（构建时克隆，保证不可变）。
     */
    private final EnumSet<JsonFeature> features;
    /**
     * 最大嵌套深度。
     */
    private final int maxDepth;
    /**
     * 字符串最大长度。
     */
    private final int maxStringLength;
    /**
     * 容器最大元素个数。
     */
    private final int maxContainerSize;

    private JsonOptions(Builder builder) {
        this.features = builder.features.clone();
        this.maxDepth = builder.maxDepth;
        this.maxStringLength = builder.maxStringLength;
        this.maxContainerSize = builder.maxContainerSize;
    }

    /**
     * 严格模式预设：仅开启 {@link JsonFeature#WRITE_NULLS}。
     */
    public static JsonOptions strict() {
        return builder().enable(JsonFeature.WRITE_NULLS).build();
    }

    /**
     * 严格模式预设：放开常见非标准 JSON 写法，同时输出 null 字段。
     */
    public static final JsonOptions JSON_OPTIONS_STRICT = builder()
            .enable(JsonFeature.WRITE_NULLS) // 输出 null 字段
            .build();
    /**
     * 宽松模式预设：放开常见非标准 JSON 写法，同时输出 null 字段。
     */
    public static final JsonOptions JSON_OPTIONS_LENIENT = builder()
            .enable(JsonFeature.ALLOW_COMMENTS) // 允许注释
            .enable(JsonFeature.ALLOW_SINGLE_QUOTES)// 允许单引号
            .enable(JsonFeature.ALLOW_UNQUOTED_KEYS)// 允许裸键
            .enable(JsonFeature.ALLOW_UNQUOTED_STRING_VALUES)// 允许裸值
            .enable(JsonFeature.ALLOW_TRAILING_COMMA)// 允许尾随逗号
            .enable(JsonFeature.WRITE_NULLS)// 输出 null 字段
            .build();

    /**
     * 创建构建器。
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * 判断某特性是否开启。
     */
    public boolean isEnabled(JsonFeature feature) {
        return features.contains(feature);
    }

    /**
     * 获取最大嵌套深度。
     */
    public int getMaxDepth() {
        return maxDepth;
    }

    /**
     * 获取字符串最大长度。
     */
    public int getMaxStringLength() {
        return maxStringLength;
    }

    /**
     * 获取容器最大元素个数。
     */
    public int getMaxContainerSize() {
        return maxContainerSize;
    }

    /**
     * 流式构建器，用于构造不可变的 {@link JsonOptions}。
     */
    public static final class Builder {
        private final EnumSet<JsonFeature> features = EnumSet.noneOf(JsonFeature.class);
        private int maxDepth = DEFAULT_MAX_DEPTH;
        private int maxStringLength = DEFAULT_MAX_STRING_LENGTH;
        private int maxContainerSize = DEFAULT_MAX_CONTAINER_SIZE;

        /**
         * 开启某个特性（支持链式调用）。
         */
        public Builder enable(JsonFeature feature) {
            features.add(requireFeature(feature));
            return this;
        }

        /**
         * 关闭某个特性（支持链式调用）。
         */
        public Builder disable(JsonFeature feature) {
            features.remove(requireFeature(feature));
            return this;
        }

        /**
         * 设置最大嵌套深度。
         */
        public Builder maxDepth(int value) {
            this.maxDepth = requirePositive("maxDepth", value);
            return this;
        }

        /**
         * 设置字符串最大长度。
         */
        public Builder maxStringLength(int value) {
            this.maxStringLength = requirePositive("maxStringLength", value);
            return this;
        }

        /**
         * 设置容器最大元素个数。
         */
        public Builder maxContainerSize(int value) {
            this.maxContainerSize = requirePositive("maxContainerSize", value);
            return this;
        }

        /**
         * 构建不可变的 {@link JsonOptions} 实例。
         */
        public JsonOptions build() {
            return new JsonOptions(this);
        }

        private static JsonFeature requireFeature(JsonFeature feature) {
            if (feature == null) {
                throw new IllegalArgumentException("feature cannot be null");
            }
            return feature;
        }

        /**
         * 校验数值参数为正数。
         */
        private static int requirePositive(String name, int value) {
            if (value <= 0) {
                throw new IllegalArgumentException(name + " must be greater than zero");
            }
            return value;
        }
    }
}
