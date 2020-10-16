package com.dlz.framework.redis.service.impl;

import com.dlz.framework.cache.ICache;
import com.dlz.framework.redis.JedisExecutor;
import com.dlz.framework.redis.RedisKeyMaker;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * 使用Redis实现缓存
 * 缓存存储使用键值对方式
 *
 * @author dk
 */
public class CacheRedisJsonKey implements ICache {
    @Autowired
    RedisKeyMaker keyMaker;
    @Autowired
    JedisExecutor jedisExecutor;
    @Override
    public <T extends Serializable> T get(String name, Serializable key, Type type) {
        return jedisExecutor.getObj(name + RedisKeyMaker.keySplit + key, type);
    }

    @Override
    public void put(String name, Serializable key, Serializable value, int seconds) {
        jedisExecutor.setObj(name + RedisKeyMaker.keySplit + key, value, seconds);
    }

    @Override
    public void remove(String name, Serializable key) {
        jedisExecutor.del(name + RedisKeyMaker.keySplit + key);
    }

    @Override
    public void removeAll(String name) {
        jedisExecutor.excuteByJedis(j -> {
            Set<String> keys = j.keys(keyMaker.getKey(name +"*"));
            j.del(keys.toArray(new String[keys.size()]));
            return true;
        });
    }
}