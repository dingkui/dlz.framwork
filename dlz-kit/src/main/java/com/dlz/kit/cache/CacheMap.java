package com.dlz.kit.cache;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  缓存Map
 *
 * @author dk
 */
@Slf4j
public class CacheMap<K,V> extends ConcurrentHashMap<K,V>{
    public V getAndSet(K key, Callable<V> valueLoader){
        try {
            V v = get(key);
            if (v == null && valueLoader != null) {
                v = valueLoader.call();
                if (v != null) {
                    putIfAbsent(key, v);
                    v = get(key);
                }
            }
            return v;
        } catch (Exception e) {
            log.error("CacheMap getAndSet error", e);
            return null;
        }
    }
}
