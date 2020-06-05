package com.dlz.framework.cache;

import java.io.Serializable;

public interface ICache {
    <T extends Serializable> T get(String name, Serializable key,Class<T> tClass);

    void put(String name, Serializable key, Serializable value, long milliseconds);

    void remove(String name, Serializable key);

    void removeAll(String name);
}