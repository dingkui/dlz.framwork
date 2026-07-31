package com.dlz.kit.json.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/** JDK-only helpers for describing generic JSON target types. */
public final class JsonTypes {
    private JsonTypes() {
    }

    public static Type listOf(Type elementType) {
        return parameterized(List.class, elementType);
    }

    public static Type parameterized(Type rawType, Type... typeArguments) {
        if (rawType == null) {
            throw new IllegalArgumentException("rawType must not be null");
        }
        if (typeArguments == null || typeArguments.length == 0) {
            throw new IllegalArgumentException("typeArguments must not be empty");
        }
        Type[] arguments = typeArguments.clone();
        for (Type argument : arguments) {
            if (argument == null) {
                throw new IllegalArgumentException("typeArguments must not contain null");
            }
        }
        return new SimpleParameterizedType(rawType, arguments);
    }

    private static final class SimpleParameterizedType implements ParameterizedType {
        private final Type rawType;
        private final Type[] typeArguments;

        private SimpleParameterizedType(Type rawType, Type[] typeArguments) {
            this.rawType = rawType;
            this.typeArguments = typeArguments;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return typeArguments.clone();
        }

        @Override
        public Type getRawType() {
            return rawType;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType that = (ParameterizedType) other;
            return rawType.equals(that.getRawType())
                    && that.getOwnerType() == null
                    && Arrays.equals(typeArguments, that.getActualTypeArguments());
        }

        @Override
        public int hashCode() {
            return rawType.hashCode() ^ Arrays.hashCode(typeArguments);
        }

        @Override
        public String getTypeName() {
            StringBuilder name = new StringBuilder(rawType.getTypeName()).append('<');
            for (int i = 0; i < typeArguments.length; i++) {
                if (i > 0) {
                    name.append(", ");
                }
                name.append(typeArguments[i].getTypeName());
            }
            return name.append('>').toString();
        }

        @Override
        public String toString() {
            return getTypeName();
        }
    }
}
