package com.dlz.framework.cache.impl;

import com.dlz.comm.util.system.SerializeUtil;
import redis.clients.util.SafeEncoder;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * 使用Redis实现缓存
 *
 * @author dk
 */
public class CacheRedisSerialHash extends CacheRedisJsonHash {
    protected byte[] getKey(String name) {
        return SafeEncoder.encode(getRedisKey(name).toString());
    }

    @Override
    public <T extends Serializable> T get(String name, Serializable key, Type type) {
        return this.excuteByJedis(j -> {
            j.getClient().hget(getKey(name), SafeEncoder.encode(key.toString().replaceAll(":", "")));
            final byte[] result = j.getClient().getBinaryBulkReply();
            if (null != result) {
                return (T) SerializeUtil.deserialize(result);
            } else {
                return null;
            }
        });
    }

    @Override
    public void put(String name, Serializable key, Serializable value, long milliseconds) {
        this.excuteByJedis(j -> {
            byte[] key1 = getKey(name);
            j.getClient().hset(key1, SafeEncoder.encode(key.toString().replaceAll(":", "")), SerializeUtil.serialize(value));
            if (milliseconds > -1) {
                j.pexpire(key1, milliseconds);
            }
            return j.getClient().getStatusCodeReply();
        });
    }

    @Override
    public void remove(String name, Serializable key) {
        this.excuteByJedis(j -> j.hdel(getKey(name), SafeEncoder.encode(key.toString().replaceAll(":", ""))));
    }

    @Override
    public void removeAll(String name) {
        this.excuteByJedis(j -> j.del(getKey(name)));
    }
}