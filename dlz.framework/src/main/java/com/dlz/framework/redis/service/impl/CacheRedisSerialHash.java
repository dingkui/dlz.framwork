package com.dlz.framework.redis.service.impl;

import com.dlz.comm.util.system.SerializeUtil;
import com.dlz.framework.cache.ICache;
import com.dlz.framework.redis.JedisExecutor;
import com.dlz.framework.redis.RedisKeyMaker;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.util.SafeEncoder;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * 使用Redis实现缓存
 *
 * @author dk
 */
public class CacheRedisSerialHash implements ICache {
    @Autowired
    RedisKeyMaker keyMaker;
    @Autowired
    JedisExecutor jedisExecutor;
    protected byte[] getRedisByteKey(String name) {
        return SafeEncoder.encode(keyMaker.getKey(name));
    }

    private byte[] getByteKey(Serializable key) {
        return SafeEncoder.encode(key.toString().replaceAll(":", ""));
    }

    @Override
    public <T extends Serializable> T get(String name, Serializable key, Type type) {
        return jedisExecutor.excuteByJedis(j -> {
            j.getClient().hget(getRedisByteKey(name), getByteKey(key));
            final byte[] result = j.getClient().getBinaryBulkReply();
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
            byte[] key1 = getRedisByteKey(name);
            Long hset = j.hset(key1, getByteKey(key), SerializeUtil.serialize(value));
            if (seconds > -1) {
                j.expire(key1, seconds);
            }
            return hset;
        });
    }

    @Override
    public void remove(String name, Serializable key) {
        jedisExecutor.excuteByJedis(j -> j.hdel(getRedisByteKey(name), getByteKey(key)));
    }

    @Override
    public void removeAll(String name) {
        jedisExecutor.excuteByJedis(j -> j.del(getRedisByteKey(name)));
    }


}