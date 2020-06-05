package com.dlz.framework.cache.impl;

import com.dlz.comm.util.system.SerializeUtil;
import redis.clients.jedis.Client;
import redis.clients.util.SafeEncoder;

import java.io.Serializable;

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
    public <T extends Serializable> T get(String name, Serializable key, Class<T> tClass) {
        return this.excuteByJedis(j -> {
            final Client client = j.getClient();
            j.hget(getKey(name), SafeEncoder.encode(key.toString().replaceAll(":","")));
            final byte[] result = client.getBinaryBulkReply();
            try {
                if (null != result) {
                    return (T) SerializeUtil.deserialize(result);
                } else {
                    return null;
                }
            } finally {
                client.close();
            }
        });
    }

    @Override
    public void put(String name, Serializable key, Serializable value, long milliseconds) {
        this.excuteByJedis(j -> {
            final Client client = j.getClient();
            byte[] key1 = getKey(name);
            client.hset(key1, SafeEncoder.encode(key.toString().replaceAll(":","")), SerializeUtil.serialize(value));
            if (milliseconds > -1) {
                j.pexpire(key1, milliseconds);
            }
            client.getBinaryBulkReply();
            client.close();
            try {
                client.getBinaryBulkReply();
            } finally {
                client.close();
            }
            return null;
        });
    }

    @Override
    public void remove(String name, Serializable key) {
        this.excuteByJedis(j -> j.hdel(getKey(name), SafeEncoder.encode(key.toString().replaceAll(":",""))));
    }

    @Override
    public void removeAll(String name) {
        this.excuteByJedis(j -> j.del(getKey(name)));
    }
}