package com.dlz.kit.json.core;

import java.util.EnumSet;

/** Immutable limits and feature switches used by the JSON codec. */
public final class JsonOptions {
    private static final int DEFAULT_MAX_DEPTH = 256;
    private static final int DEFAULT_MAX_STRING_LENGTH = 10 * 1024 * 1024;
    private static final int DEFAULT_MAX_CONTAINER_SIZE = 1_000_000;

    private final EnumSet<JsonFeature> features;
    private final int maxDepth;
    private final int maxStringLength;
    private final int maxContainerSize;

    private JsonOptions(Builder builder) {
        this.features = builder.features.clone();
        this.maxDepth = builder.maxDepth;
        this.maxStringLength = builder.maxStringLength;
        this.maxContainerSize = builder.maxContainerSize;
    }

    public static JsonOptions strict() {
        return builder().enable(JsonFeature.WRITE_NULLS).build();
    }

    public static JsonOptions lenient() {
        return builder()
                .enable(JsonFeature.ALLOW_COMMENTS)
                .enable(JsonFeature.ALLOW_SINGLE_QUOTES)
                .enable(JsonFeature.ALLOW_UNQUOTED_KEYS)
                .enable(JsonFeature.ALLOW_UNQUOTED_STRING_VALUES)
                .enable(JsonFeature.ALLOW_TRAILING_COMMA)
                .enable(JsonFeature.WRITE_NULLS)
                .build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean isEnabled(JsonFeature feature) {
        return features.contains(feature);
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public int getMaxStringLength() {
        return maxStringLength;
    }

    public int getMaxContainerSize() {
        return maxContainerSize;
    }

    public static final class Builder {
        private final EnumSet<JsonFeature> features = EnumSet.noneOf(JsonFeature.class);
        private int maxDepth = DEFAULT_MAX_DEPTH;
        private int maxStringLength = DEFAULT_MAX_STRING_LENGTH;
        private int maxContainerSize = DEFAULT_MAX_CONTAINER_SIZE;

        public Builder enable(JsonFeature feature) {
            features.add(requireFeature(feature));
            return this;
        }

        public Builder disable(JsonFeature feature) {
            features.remove(requireFeature(feature));
            return this;
        }

        public Builder maxDepth(int value) {
            this.maxDepth = requirePositive("maxDepth", value);
            return this;
        }

        public Builder maxStringLength(int value) {
            this.maxStringLength = requirePositive("maxStringLength", value);
            return this;
        }

        public Builder maxContainerSize(int value) {
            this.maxContainerSize = requirePositive("maxContainerSize", value);
            return this;
        }

        public JsonOptions build() {
            return new JsonOptions(this);
        }

        private static JsonFeature requireFeature(JsonFeature feature) {
            if (feature == null) {
                throw new IllegalArgumentException("feature cannot be null");
            }
            return feature;
        }

        private static int requirePositive(String name, int value) {
            if (value <= 0) {
                throw new IllegalArgumentException(name + " must be greater than zero");
            }
            return value;
        }
    }
}
