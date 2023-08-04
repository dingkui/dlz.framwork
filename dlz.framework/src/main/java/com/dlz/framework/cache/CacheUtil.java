package com.dlz.framework.cache;

import com.dlz.comm.util.ExceptionUtils;
import com.dlz.framework.holder.SpringHolder;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * 缓存工具类
 *
 * @author dk
 */
@Slf4j
public class CacheUtil {
    private static Class<? extends ICache> cacheClass;
    public static void init(Class<? extends ICache> c) {
        if (c != null) {
            cacheClass = c;
        }
    }

    /**
     * 获取缓存对象
     *
     * @return Cache
     */
    public static ICache getCache(String cacheName) {
        return CacheHolder.get(cacheName,cacheClass);
    }


    /**
     * 获取缓存
     */
    public static Serializable get(String cacheName, String key) {
        return getCache(cacheName).get(cacheName, key, null);
    }

    /**
     * 获取缓存
     */
    public static <T> T get(String cacheName, String key, Class<T> type) {
        return getCache(cacheName).get(cacheName, key, type);
    }

    /**
     * 获取缓存
     */
    public static <T> T get(String cacheName, String key, Callable<T> valueLoader) {
        try {
            ICache cache = getCache(cacheName);
            T re = cache.get(cacheName, key, null);
            if (re == null && valueLoader != null) {
                re = valueLoader.call();
                if (re != null) {
                    cache.put(cacheName, key, (Serializable)re, -1);
                }
            }
            return re;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * 设置缓存
     *
     * @param cacheName 缓存名
     * @param key       缓存键值
     * @param value     缓存值
     */
    public static void put(String cacheName, String key, Serializable value, int second) {
        getCache(cacheName).put(cacheName, key, value, second);
    }

    /**
     * 清除缓存
     *
     * @param cacheName 缓存名
     * @param key       缓存键值
     */
    public static void evict(String cacheName, String key) {
        getCache(cacheName).remove(cacheName, key);
    }

    /**
     * 清空缓存
     *
     * @param cacheName 缓存名
     */
    public static void clear(String cacheName) {
        getCache(cacheName).removeAll(cacheName);
    }
}
