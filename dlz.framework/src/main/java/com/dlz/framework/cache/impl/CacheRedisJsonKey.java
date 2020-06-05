package com.dlz.framework.cache.impl;

import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.ValUtil;
import redis.clients.util.SafeEncoder;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * 使用Redis实现缓存
 * 缓存存储使用键值对方式
 *
 * @author dk
 */
public class CacheRedisJsonKey extends CacheRedisJsonHash {
    private String getRedisKey(String name, Serializable key) {
        return getRedisKey(name).append(keySplit).append(key.toString().replaceAll(":","")).toString();
    }

    private static String nxxx = "XX";
    private static String expx = "EX";

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
        String redisKey = getRedisKey(name, key);
        if (milliseconds > -1) {
            this.excuteByJedis(j -> j.set(redisKey, ValUtil.getStr(value),nxxx,expx,milliseconds ));
//            this.excuteByJedis(j -> j.pexpire(redisKey, milliseconds));
        }else{
            this.excuteByJedis(j -> j.set(redisKey, ValUtil.getStr(value)));
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