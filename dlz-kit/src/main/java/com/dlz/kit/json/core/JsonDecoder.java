package com.dlz.kit.json.core;

import java.lang.reflect.Type;

/** Decodes JSON text or values into a target Java type. */
public interface JsonDecoder {
    <T> T decode(Object value, Class<T> targetType);

    <T> T decode(Object value, Type targetType);
}
