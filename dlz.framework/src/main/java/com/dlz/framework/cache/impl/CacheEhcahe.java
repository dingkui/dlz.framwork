package com.dlz.framework.cache.impl;

import com.dlz.comm.util.ValUtil;
import com.dlz.framework.cache.ICache;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.Serializable;

/**
 * 使用ehcache实现缓存
 *
 * @author dk
 */
@Slf4j
public class CacheEhcahe implements ICache {
    static CacheManager manager = CacheManager.getInstance();

    private Cache getCache(String name) {
        Cache cache = manager.getCache(name);
        if (cache == null) {
            if (cache == null) {
                log.info("缓存初始化：" + manager.getConfiguration().getDiskStoreConfiguration().getPath() + "/" + name);
                manager.addCache(name);
                cache = manager.getCache(name);
            }
        }
        return cache;
    }

    @Override
    public <T extends Serializable> T get(String name, Serializable key,Class<T> tClass) {
        Element element = getCache(name).get(key);
        if (element != null) {
            return (T) element.getObjectValue();
        }
        return null;
    }

    @Override
    public void put(String name, Serializable key, Serializable value, long milliseconds) {
        Element element = new Element(key, value);
        if (milliseconds > -1) {
            element.setTimeToLive(ValUtil.getInt(milliseconds/1000));
        }
        getCache(name).put(element);
    }

    @Override
    public void remove(String name, Serializable key) {
        getCache(name).remove(key.toString());
    }

    @Override
    public void removeAll(String name) {
        getCache(name).removeAll();
    }
}