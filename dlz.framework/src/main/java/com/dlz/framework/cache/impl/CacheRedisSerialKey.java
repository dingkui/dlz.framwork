package com.dlz.framework.cache.impl;

import com.dlz.comm.util.system.SerializeUtil;
import com.dlz.framework.cache.ICache;
import redis.clients.util.SafeEncoder;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * 使用Redis实现缓存
 *
 * @author dk
 */
public class CacheRedisSerialKey extends BaseCacheRedis implements ICache {
    protected byte[] getKey(String name, Serializable key) {
        return SafeEncoder.encode(getRedisKey(name).append(keySplit).append(key.toString().replaceAll(":", "")).toString());
    }

    @Override
    public <T extends Serializable> T get(String name, Serializable key, Type type) {
        return this.excuteByJedis(j -> {
            final byte[] result = j.get(getKey(name, key));
            if (null != result) {
                return (T) SerializeUtil.deserialize(result);
            } else {
                return null;
            }
        });
    }


    public <T extends Serializable> T get(String name, Serializable key) {
        return get(name, key ,null);
    }

    @Override
    public void put(String name, Serializable key, Serializable value, long exp) {
        this.excuteByJedis(j -> {
            byte[] key1 = getKey(name, key);
            String set = j.set(key1, SerializeUtil.serialize(value));
            if (exp > -1) {
                j.pexpire(key1, exp);
            }
            return set;
        });
    }

    @Override
    public void remove(String name, Serializable key) {
        this.excuteByJedis(j -> j.del(getKey(name, key)));
    }

    @Override
    public void removeAll(String name) {
        Set<String> strings = this.excuteByJedis(j -> j.keys(getRedisKey(name).append("*").toString()));
        this.excuteByJedis(j -> j.del(strings.toArray(new String[strings.size()])));
    }
}