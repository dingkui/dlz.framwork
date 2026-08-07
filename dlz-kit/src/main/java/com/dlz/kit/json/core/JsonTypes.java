package com.dlz.kit.json.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * 仅依赖 JDK 反射 API 的泛型类型描述工具。
 *
 * <p>用于在不引入 Jackson/Gson 等库的前提下，构造参数化类型（如 {@code List<String>}、
 * {@code Map<String, Foo>}），便于在解码时携带完整泛型信息。</p>
 */
public final class JsonTypes {
    /** 工具类禁止实例化。 */
    private JsonTypes() {
    }

    /** 构造 {@code List<T>} 类型。 */
    public static Type listOf(Type elementType) {
        return parameterized(List.class, elementType);
    }

    /**
     * 构造一个参数化类型对象。
     *
     * @param rawType      原始类型（如 List.class、Map.class）
     * @param typeArguments 实际类型参数，至少一个且不能为 null
     * @return 代表该参数化类型的 {@link Type}
     * @throws IllegalArgumentException 参数为空或含 null 时抛出
     */
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

    /** 轻量 {@link ParameterizedType} 实现，持有原始类型与实际类型参数。 */
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
