package com.dlz.framework.cache;

import com.dlz.comm.exception.SystemException;
import com.dlz.framework.cache.service.impl.CacheEhcahe;
import com.dlz.framework.holder.SpringHolder;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存记录
 *
 * @author dk
 */
@Slf4j
public class CacheHolder {
    private static Map<String, ICache> CacheSet = new HashMap<>();

    public static void clearAll() {
        for (Map.Entry<String, ICache> deal : CacheSet.entrySet()) {
            deal.getValue().removeAll(deal.getKey());
        }
    }

    public static void clear(String cacheName) {
        CacheSet.get(cacheName).removeAll(cacheName);
    }

    public static ICache get(String cacheName, Class<? extends ICache> cacheClass) {
        if (CacheSet.containsKey(cacheName)) {
            return CacheSet.get(cacheName);
        }
        ICache cache;
        if (cacheClass == null) {
            cache = SpringHolder.registerBean(CacheEhcahe.class);
        } else {
            cache = SpringHolder.registerBean(cacheClass);
        }
        CacheSet.put(cacheName, cache);
        return cache;
    }

    public static ICache get(String cacheName, ICache cache) {
        if (CacheSet.containsKey(cacheName)) {
            return CacheSet.get(cacheName);
        }
        SystemException.notNull(cache, () -> "缓存已经存在，不能重复定义：" + cacheName);
        CacheSet.put(cacheName, cache);
        return cache;
    }

    public static void add(String cacheName, ICache cache) {
        SystemException.isTrue(CacheSet.containsKey(cacheName), () -> "缓存已经存在，不能重复定义：" + cacheName);
        SystemException.isTrue(cache == null, () -> "ICache 未定义！");
        CacheSet.put(cacheName, cache);
    }
}