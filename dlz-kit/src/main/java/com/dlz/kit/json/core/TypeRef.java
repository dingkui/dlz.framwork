package com.dlz.kit.json.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/** Captures a generic target type without a Jackson or Gson dependency. */
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
