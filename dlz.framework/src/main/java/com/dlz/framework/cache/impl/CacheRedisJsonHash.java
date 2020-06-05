package com.dlz.framework.cache.impl;

import com.dlz.comm.exception.RemoteException;
import com.dlz.comm.util.ExceptionUtils;
import com.dlz.comm.util.ValUtil;
import com.dlz.framework.cache.ICache;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.SafeEncoder;

import java.io.Serializable;
import java.util.Set;

/**
 * 使用Redis实现缓存
 * 缓存存储使用hash方式
 *
 * @author dk
 */
@Slf4j
public class CacheRedisJsonHash implements ICache {
    @Value("${spring.application.name}")
    private String appName = "app";
    @Value("${spring.profiles.active}")
    private String profiles = "dev";
    @Autowired
    private JedisPool jedisPool;
    protected static final String keySplit = ":";
    @FunctionalInterface
    protected interface JedisExecutor<T, R> {
        R excute(T t) throws RemoteException;
    }

    /**
     * 处理 jedis请求
     *
     * @param j 处理逻辑，通过 lambda行为参数化
     * @return 处理结果
     */
    protected <T> T excuteByJedis(JedisExecutor<Jedis, T> j) throws RemoteException {
        try (Jedis jedis = jedisPool.getResource()) {
            return j.excute(jedis);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(RemoteException.buildException("redis异常", e)));
        }
        return null;
    }


    protected StringBuilder getRedisKey(String name) {
        StringBuilder sb = new StringBuilder(appName);
        return sb.append(keySplit).append(profiles).append(keySplit).append(name);
    }

    @Override
    public <T extends Serializable> T get(String name, Serializable key, Class<T> tClass) {
        String str = this.excuteByJedis(j -> j.hget(getRedisKey(name).toString(), String.valueOf(key)));
        if (str != null) {
            return ValUtil.getObj(str, tClass);
        }
        return null;
    }

    @Override
    public void put(String name, Serializable key, Serializable value, long milliseconds) {
        String key1 = getRedisKey(name).toString();
        this.excuteByJedis(j -> j.hset(key1,String.valueOf(key), ValUtil.getStr(value)));
        if (milliseconds > -1) {
            this.excuteByJedis(j -> j.pexpire(key1, milliseconds));
        }
    }

    @Override
    public void remove(String name, Serializable key) {
        this.excuteByJedis(j -> j.hdel(getRedisKey(name).toString(), String.valueOf(key)));
    }

    @Override
    public void removeAll(String name) {
        this.excuteByJedis(j -> j.del(getRedisKey(name).toString()));
    }
}