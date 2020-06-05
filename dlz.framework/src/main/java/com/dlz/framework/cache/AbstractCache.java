package com.dlz.framework.cache;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * 缓存实现
 *
 * @author dk
 */
@SuppressWarnings("unchecked")
@Slf4j
public abstract class AbstractCache<KEY extends Serializable, T extends Serializable> {
    private ICache cache = null;
    public class DbOperator {
        protected T getFromDb(KEY key) {
            return null;
        }

        protected void saveToDb(KEY key, T t) {
        }
    }
    protected DbOperator dbOperator = new DbOperator() {
        public Object getFromDb(Object o) {
            return null;
        }
        public void saveToDb(Object o, Object o2) {}
    };
    private int timeToLiveSeconds = -1;
    private String cacheName;
    private Class<T> resultClass;

    public AbstractCache(String cacheName, int timeToLiveSeconds) {
        this(cacheName);
        this.timeToLiveSeconds = timeToLiveSeconds;
    }

    public AbstractCache(String cacheName) {
        this.cacheName = cacheName.toLowerCase();
        resultClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
    public AbstractCache(String cacheName,ICache cache) {
        this(cacheName);
        this.cache=cache;
    }
    public AbstractCache(ICache cache) {
        this();
        this.cache=cache;
    }

    public AbstractCache() {
        this.cacheName = this.getClass().getSimpleName().toLowerCase();
        resultClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @PostConstruct
    private void _init() {
        cache = CacheHolder.add(cacheName, cache);
    }

    public String toString(){
        return this.cacheName+"："+(cache==null?"未初始化":cache.toString());
    }


    private T readDb(KEY key) {
        T t = null;
        try {
            t = dbOperator.getFromDb(key);
        } catch (Exception e) {
            log.error("从数据库加载缓存失败：" + getCacheName() + "." + key, e);
        }
        return t;
    }

    /**
     * 保存缓存到数据库
     *
     * @param key
     */
    public void saveCache2Db(KEY key, T value) {
        if (value == null) {
            if (key == null) {
                log.error("缓存保存失败：key或value必须设置一个！" + getCacheName());
                return;
            }
            value = getFromCache(key);
        }
        if (value != null) {
            try {
                dbOperator.saveToDb(key, value);
            } catch (Exception e) {
                log.error("保存缓存到数据库失败：" + getCacheName());
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 缓存中读取对象，取不到则从数据库中取得
     *
     * @param key
     * @return
     */
    public T get(KEY key) {
        T t = null;
        try {
            if (key != null) {
                t = getFromCache(key);
                if (t == null) {
                    t = readDb(key);
                    if (t != null) {
                        put(key, t, timeToLiveSeconds);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return t;
    }

    /**
     * 缓存中读取对象，取不到则返回空
     *
     * @param key
     * @return
     */
    public T getFromCache(KEY key) {
        return (T) cache.get(cacheName, key, resultClass);
    }

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     */
    public void put(KEY key, T value) {
        put(key, value, timeToLiveSeconds);
    }

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     * @param exp   有效期：秒
     */
    public void put(KEY key, T value, int exp) {
        try {
            cache.put(cacheName, key, value, exp);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public void remove(KEY key) {
        try {
            cache.remove(cacheName, key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 清空缓存
     */
    public void clear() {
        cache.removeAll(cacheName);
    }

    /**
     * 取得当前缓存的名称
     */
    public String getCacheName() {
        return this.cacheName;
    }

    /**
     * 从数据库中重新加载
     * @param key
     * @return
     */
    public T reload(KEY key) {
        T t = readDb(key);
        if (t == null) {
            log.error("缓存刷新失败：cacheName=" + getCacheName() + " key=" + key);
        } else {
            put(key, t, timeToLiveSeconds);
        }
        return t;
    }
}