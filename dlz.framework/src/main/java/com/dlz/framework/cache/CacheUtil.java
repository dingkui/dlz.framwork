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
    private static ICache cache;
    public static void init(ICache c) {
        if (c != null) {
            cache = c;
        }
    }
    public static <T extends ICache> void init(Class<T> c) {
        if (c != null) {
            cache = SpringHolder.createBean(c);
        }
    }

    /**
     * 获取缓存对象
     *
     * @return Cache
     */
    public static ICache getCache() {
        if (cache == null) {
            cache = SpringHolder.getBean(ICache.class);
        }
        return cache;
    }


    /**
     * 获取缓存
     */
    public static Serializable get(String cacheName, String key) {
        return getCache().get(cacheName, key, null);
    }

    /**
     * 获取缓存
     */
    public static <T> T get(String cacheName, String key, Class<T> type) {
        return getCache().get(cacheName, key, type);
    }

    /**
     * 获取缓存
     */
    public static <T> T get(String cacheName, String key, Callable<T> valueLoader) {
        try {
            T re = getCache().get(cacheName, key, null);
            if (re == null && valueLoader != null) {
                re = valueLoader.call();
                if (re != null) {
                    getCache().put(cacheName, key, (Serializable)re, -1);
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
        getCache().put(cacheName, key, value, second);
    }

    /**
     * 清除缓存
     *
     * @param cacheName 缓存名
     * @param key       缓存键值
     */
    public static void evict(String cacheName, String key) {
        getCache().remove(cacheName, key);
    }

    /**
     * 清空缓存
     *
     * @param cacheName 缓存名
     */
    public static void clear(String cacheName) {
        getCache().removeAll(cacheName);
    }
}
