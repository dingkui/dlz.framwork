package com.dlz.framework.cache.impl;

import com.dlz.comm.exception.CodeException;
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
    private Cache cache;
	public void init(String name) {
        try {
            CacheManager manager = CacheManager.getInstance();
            log.debug("缓存初始化：" + manager.getConfiguration().getDiskStoreConfiguration().getPath() + "/" + name);
            cache = manager.getCache(name);
            if (cache == null) {
                manager.addCache(name);
                cache = manager.getCache(name);
            }
        } catch (net.sf.ehcache.CacheException e) {
            log.error("缓存创建失败:" + name, e);
            throw new CodeException("缓存创建失败:" + name, e);
        }
    }

    @Override
    public Serializable get(Serializable key) {
        Element element = cache.get(key);
        if (element != null) {
            return (Serializable) element.getObjectValue();
        }
        return null;
    }

    @Override
    public void put(Serializable key, Serializable value, int exp) {
        Element element = new Element(key, value);
        if (exp > -1) {
            element.setTimeToLive(exp);
        }
        cache.put(element);
    }

    @Override
    public void remove(Serializable key) {
        cache.remove(key.toString());
    }

    @Override
    public void removeAll() {
        cache.removeAll();
    }
}