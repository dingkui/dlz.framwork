package com.dlz.framework.redis.service.impl;

import com.dlz.framework.cache.ICache;
import com.dlz.framework.redis.excutor.JedisExecutor;
import com.dlz.framework.redis.excutor.SerializationUtils;
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
//    @Autowired
//    RedisKeyMaker keyMaker;
    @Autowired
    JedisExecutor jedisExecutor;
    @Override
    public <T extends Serializable> T get(String name, Serializable key, Type type) {
        return jedisExecutor.getSo(SerializationUtils.getKey(name,key), type);
    }

    @Override
    public void put(String name, Serializable key, Serializable value, int seconds) {
        jedisExecutor.setSo(SerializationUtils.getKey(name,key), value, seconds);
    }

    @Override
    public void remove(String name, Serializable key) {
        jedisExecutor.del(SerializationUtils.getRedisKey(name,key));
    }

    @Override
    public void removeAll(String name) {
        jedisExecutor.excuteByJedis(j -> {
            Set<String> keys = j.keys(SerializationUtils.getRedisKey(name +"*"));
            j.del(keys.toArray(new String[keys.size()]));
            return true;
        });
    }
}