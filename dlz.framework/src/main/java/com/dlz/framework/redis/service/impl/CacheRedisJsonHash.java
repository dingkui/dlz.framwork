package com.dlz.framework.redis.service.impl;

import com.dlz.comm.util.ValUtil;
import com.dlz.framework.cache.ICache;
import com.dlz.framework.redis.excutor.JedisExecutor;
import com.dlz.framework.redis.RedisKeyMaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * 使用Redis实现缓存
 * 缓存存储使用hash方式
 *
 * @author dk
 */
@Slf4j
public class CacheRedisJsonHash implements ICache {
//    @Autowired
//    RedisKeyMaker keyMaker;
    @Autowired
    JedisExecutor jedisExecutor;
    @Override
    public <T extends Serializable> T get(String name, Serializable key, Type type) {
        return jedisExecutor.hgetSo(name, ValUtil.getStr(key),type);
    }

    @Override
    public void put(String name, Serializable key, Serializable value, int seconds) {
        jedisExecutor.hsetSo(name, ValUtil.getStr(key),  value, seconds);
    }

    @Override
    public void remove(String name, Serializable key) {
        jedisExecutor.hdel(name, ValUtil.getStr(key));
    }

    @Override
    public void removeAll(String name) {
        jedisExecutor.del(name);
    }
}