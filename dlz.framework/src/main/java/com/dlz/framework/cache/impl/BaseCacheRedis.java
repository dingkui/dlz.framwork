package com.dlz.framework.cache.impl;

import com.dlz.comm.exception.RemoteException;
import com.dlz.comm.util.ExceptionUtils;
import com.dlz.framework.cache.RedisKeyMaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 使用Redis实现缓存
 *
 * @author dk
 */
@Slf4j
public class BaseCacheRedis {
    @Autowired
    RedisKeyMaker keyMaker;
    @Autowired
    private JedisPool jedisPool;
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
    protected String getRedisKey(String key,Object ... keys) {
        return keyMaker.getKey(key,keys);
    }
}