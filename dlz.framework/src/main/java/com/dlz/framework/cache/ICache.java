package com.dlz.framework.cache;

import java.io.Serializable;

public interface ICache {
    void init(String name);

    Serializable get(Serializable key);

    void put(Serializable key, Serializable value, int exp);

    void remove(Serializable key);

    void removeAll();
}