package com.dlz.framework.cache.impl;

import com.dlz.comm.util.ValUtil;

import java.io.Serializable;
import java.util.Set;

/**
 * 使用Redis实现缓存
 * 缓存存储使用键值对方式
 *
 * @author dk
 */
public class CacheRedisJsonKey extends CacheRedisJsonHash {
    private String getRedisKey(String name, Serializable key) {
        return getRedisKey(name).append(keySplit).append(key).toString();
    }

    @Override
    public <T extends Serializable> T get(String name, Serializable key, Class<T> tClass) {
        String str = this.excuteByJedis(j -> j.get(getRedisKey(name, key)));
        if (str != null) {
            return ValUtil.getObj(str, tClass);
        }
        return null;
    }

    @Override
    public void put(String name, Serializable key, Serializable value, long milliseconds) {
        String redisKey = getRedisKey(name, key);
        this.excuteByJedis(j -> j.set(redisKey, ValUtil.getStr(value)));
        if (milliseconds > -1) {
            this.excuteByJedis(j -> j.pexpire(redisKey, milliseconds));
        }
    }

    @Override
    public void remove(String name, Serializable key) {
        this.excuteByJedis(j -> j.del(getRedisKey(name, key)));
    }

    @Override
    public void removeAll(String name) {
        Set<String> strings = this.excuteByJedis(j -> j.keys(getRedisKey(name).append("*").toString()));
        this.excuteByJedis(j -> j.del((String[])strings.toArray()));
    }
}