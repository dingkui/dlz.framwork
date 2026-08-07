package com.dlz.kit.json.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型类型捕获器（Type Reference），用于在不依赖 Jackson/Gson 的前提下保留泛型信息。
 *
 * <p>由于 Java 的泛型擦除，直接传 {@code List<Foo>.class} 是无法表达的。本类利用"匿名子类
 * 的父类泛型参数在运行时可获取"的特性来捕获完整泛型类型。用法：
 * <pre>
 *   TypeRef&lt;List&lt;Foo&gt;&gt; ref = new TypeRef&lt;List&lt;Foo&gt;&gt;() {};
 *   Type t = ref.getType();
 * </pre>
 * 必须以匿名子类（末尾加 {@code {}})方式创建，否则会抛异常。</p>
 *
 * @param <T> 要捕获的目标类型
 */
public abstract class TypeRef<T> {
    private final Type type;

    protected TypeRef() {
        Type generic = getClass().getGenericSuperclass();
        if (!(generic instanceof ParameterizedType)) {
            throw new IllegalStateException("TypeRef must be created with an anonymous generic subclass");
        }
        this.type = ((ParameterizedType) generic).getActualTypeArguments()[0];
    }

    public final Type getType() {
        return type;
    }
}
