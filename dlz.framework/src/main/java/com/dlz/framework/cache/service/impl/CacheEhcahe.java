package com.dlz.framework.cache.service.impl;

import com.dlz.comm.util.ValUtil;
import com.dlz.framework.cache.ICache;
import com.dlz.framework.holder.SpringHolder;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * 使用ehcache实现缓存
 *
 * @author dk
 */
@Slf4j
public class CacheEhcahe implements ICache {
    private CacheManager manager;
    private CacheManager getManager(){
        if(manager ==null){
            synchronized (this.getClass()){
                if(manager ==null) {
                    manager = SpringHolder.getBean(CacheManager.class);
                    if (manager == null) {
                        manager = CacheManager.getInstance();
                    }
                }
            }
        }
        return manager;
    }

    protected Cache getCache(String name) {
        CacheManager manager = getManager();
        Cache cache = manager.getCache(name);
        if (cache == null) {
            synchronized(this.getClass()) {
                cache = manager.getCache(name);
                if (cache == null) {
                    log.info("缓存初始化：" + manager.getConfiguration().getDiskStoreConfiguration().getPath() + "/" + name);
                    manager.addCache(name);
                    cache = manager.getCache(name);
                }
            }
        }
        return cache;
    }

    @Override
    public <T extends Serializable> T get(String name, Serializable key, Type tClass) {
        Element element = getCache(name).get(key);
        if (element != null) {
            return (T) element.getObjectValue();
        }
        return null;
    }

    @Override
    public void put(String name, Serializable key, Serializable value, int seconds) {
        Element element = new Element(key, value);
        if (seconds > -1) {
            element.setTimeToLive(ValUtil.getInt(seconds));
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