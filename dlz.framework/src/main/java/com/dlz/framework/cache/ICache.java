package com.dlz.framework.cache;

import java.io.Serializable;
import java.lang.reflect.Type;

public interface ICache {
    <T extends Serializable> T get(String name, Serializable key, Type tClass);

    default <T extends Serializable> T get(String name, Serializable key) {
        return get(name, key, null);
    }

    default void put(String name, Serializable key, Serializable value) {
        put(name, key, value, -1);
    }

    void put(String name, Serializable key, Serializable value, int seconds);

    void remove(String name, Serializable key);

    void removeAll(String name);
}