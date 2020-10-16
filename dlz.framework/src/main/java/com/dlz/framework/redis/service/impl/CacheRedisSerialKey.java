package com.dlz.framework.redis.service.impl;

import com.dlz.comm.util.system.SerializeUtil;
import com.dlz.framework.cache.ICache;
import com.dlz.framework.redis.JedisExecutor;
import com.dlz.framework.redis.RedisKeyMaker;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.util.SafeEncoder;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * 使用Redis实现缓存
 *
 * @author dk
 */
public class CacheRedisSerialKey implements ICache {
    @Autowired
    RedisKeyMaker keyMaker;
    @Autowired
    JedisExecutor jedisExecutor;

    protected byte[] getRedisByteKey(String name, Serializable key) {
        return SafeEncoder.encode(keyMaker.getKey(name + RedisKeyMaker.keySplit + key));
    }

    @Override
    public <T extends Serializable> T get(String name, Serializable key, Type type) {
        return jedisExecutor.excuteByJedis(j -> {
            final byte[] result = j.get(getRedisByteKey(name, key));
            if (null != result) {
                return (T) SerializeUtil.deserialize(result);
            } else {
                return null;
            }
        });
    }


    public <T extends Serializable> T get(String name, Serializable key) {
        return get(name, key, null);
    }

    @Override
    public void put(String name, Serializable key, Serializable value, int seconds) {
        jedisExecutor.excuteByJedis(j -> {
            byte[] key1 = getRedisByteKey(name, key);
            String set = j.set(key1, SerializeUtil.serialize(value));
            if (seconds > -1) {
                j.expire(key1, seconds);
            }
            return set;
        });
    }

    @Override
    public void remove(String name, Serializable key) {
        jedisExecutor.excuteByJedis(j -> j.del(getRedisByteKey(name, key)));
    }

    @Override
    public void removeAll(String name) {
        jedisExecutor.excuteByJedis(j -> {
            Set<String> keys = j.keys(keyMaker.getKey(name+"*"));
            j.del(keys.toArray(new String[keys.size()]));
            return true;
        });
    }
}