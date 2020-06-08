package com.dlz.framework.cache.impl;

import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.ValUtil;
import com.dlz.framework.cache.ICache;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * 使用Redis实现缓存
 * 缓存存储使用hash方式
 *
 * @author dk
 */
@Slf4j
public class CacheRedisJsonHash extends BaseCacheRedis implements ICache {

    @Override
    public <T extends Serializable> T get(String name, Serializable key, Type type) {
        String str = this.excuteByJedis(j -> j.hget(getRedisKey(name).toString(), String.valueOf(key).replaceAll(":","")));
        if (str != null) {
            return ValUtil.getObj(str, JacksonUtil.getJavaType(type));
        }
        return null;
    }

    @Override
    public void put(String name, Serializable key, Serializable value, long milliseconds) {
        String key1 = getRedisKey(name).toString();
        this.excuteByJedis(j -> j.hset(key1,String.valueOf(key).replaceAll(":",""), ValUtil.getStr(value)));
        if (milliseconds > -1) {
            this.excuteByJedis(j -> j.pexpire(key1, milliseconds));
        }
    }

    @Override
    public void remove(String name, Serializable key) {
        this.excuteByJedis(j -> j.hdel(getRedisKey(name).toString(), String.valueOf(key).replaceAll(":","")));
    }

    @Override
    public void removeAll(String name) {
        this.excuteByJedis(j -> j.del(getRedisKey(name).toString()));
    }
}