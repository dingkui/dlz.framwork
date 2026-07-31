package com.dlz.kit.json.core;

import java.lang.reflect.Type;

/** Pluggable bean mapping extension used on top of the JSON syntax core. */
public interface JsonMapper {
    /** Higher priority mappers are selected first. */
    int priority();

    String write(Object value);

    <T> T convert(Object value, Type targetType);
}
