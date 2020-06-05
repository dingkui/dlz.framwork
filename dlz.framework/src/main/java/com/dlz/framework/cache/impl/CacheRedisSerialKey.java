package com.dlz.framework.cache.impl;

import com.dlz.comm.util.system.SerializeUtil;
import redis.clients.jedis.Client;
import redis.clients.util.SafeEncoder;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * 使用Redis实现缓存
 *
 * @author dk
 */
public class CacheRedisSerialKey extends CacheRedisJsonHash {
    protected byte[] getKey(String name, Serializable key) {
        return SafeEncoder.encode(getRedisKey(name).append(keySplit).append(key.toString().replaceAll(":", "")).toString());
    }

    private static byte[] nxxx = SafeEncoder.encode("XX");
    private static byte[] expx = SafeEncoder.encode("EX");

    @Override
    public <T extends Serializable> T get(String name, Serializable key, Type type) {
        return this.excuteByJedis(j -> {
            j.getClient().get(getKey(name, key));
            final byte[] result = j.getClient().getBinaryBulkReply();
            if (null != result) {
                return (T) SerializeUtil.deserialize(result);
            } else {
                return null;
            }
        });
    }

    @Override
    public void put(String name, Serializable key, Serializable value, long exp) {
        this.excuteByJedis(j -> {
            if (exp > 0) {
                j.getClient().set(getKey(name, key), SerializeUtil.serialize(value), nxxx, expx, exp);
            } else {
                j.getClient().set(getKey(name, key), SerializeUtil.serialize(value));
            }
            return j.getClient().getStatusCodeReply();
        });
    }

    @Override
    public void remove(String name, Serializable key) {
        this.excuteByJedis(j -> j.del(getKey(name, key)));
    }

    @Override
    public void removeAll(String name) {
        Set<String> strings = this.excuteByJedis(j -> j.keys(getRedisKey(name).append("*").toString()));
        this.excuteByJedis(j -> j.del((String[]) strings.toArray()));
    }
}