package com.dlz.framework.cache.impl;

import com.dlz.comm.util.system.SerializeUtil;
import com.dlz.framework.cache.ICache;
import redis.clients.util.SafeEncoder;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * 使用Redis实现缓存
 *
 * @author dk
 */
public class CacheRedisSerialHash extends BaseCacheRedis implements ICache {
    protected byte[] getRedisByteKey(String name) {
        return SafeEncoder.encode(getRedisKey(name));
    }

    private byte[] getByteKey(Serializable key) {
        return SafeEncoder.encode(key.toString().replaceAll(":", ""));
    }

    @Override
    public <T extends Serializable> T get(String name, Serializable key, Type type) {
        return this.excuteByJedis(j -> {
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
    public void put(String name, Serializable key, Serializable value, long milliseconds) {
        this.excuteByJedis(j -> {
            byte[] key1 = getRedisByteKey(name);
            Long hset = j.hset(key1, getByteKey(key), SerializeUtil.serialize(value));
            if (milliseconds > -1) {
                j.pexpire(key1, milliseconds);
            }
            return hset;
        });
    }

    @Override
    public void remove(String name, Serializable key) {
        this.excuteByJedis(j -> j.hdel(getRedisByteKey(name), getByteKey(key)));
    }

    @Override
    public void removeAll(String name) {
        this.excuteByJedis(j -> j.del(getRedisByteKey(name)));
    }


}