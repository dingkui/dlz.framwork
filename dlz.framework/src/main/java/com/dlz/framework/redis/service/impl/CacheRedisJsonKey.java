package com.dlz.framework.redis.service.impl;

import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.ValUtil;
import com.dlz.framework.cache.ICache;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * 使用Redis实现缓存
 * 缓存存储使用键值对方式
 *
 * @author dk
 */
public class CacheRedisJsonKey extends BaseCacheRedis implements ICache {
    @Override
    public <T extends Serializable> T get(String name, Serializable key, Type type) {
        String str = this.excuteByJedis(j -> j.get(getRedisKey(name, key)));
        if (str != null) {
            return ValUtil.getObj(str, JacksonUtil.getJavaType(type));
        }
        return null;
    }

    @Override
    public void put(String name, Serializable key, Serializable value, long milliseconds) {
        this.excuteByJedis(j -> {
            String redisKey = getRedisKey(name, key);
            String set = j.set(redisKey, ValUtil.getStr(value));
            if (milliseconds > -1) {
                j.pexpire(redisKey, milliseconds);
            }
            return set;
        });
    }

    @Override
    public void remove(String name, Serializable key) {
        this.excuteByJedis(j -> j.del(getRedisKey(name, key)));
    }

    @Override
    public void removeAll(String name) {
        Set<String> strings = this.excuteByJedis(j -> j.keys(getRedisKey(name,"*")));
        this.excuteByJedis(j -> j.del(strings.toArray(new String[strings.size()])));
    }
}