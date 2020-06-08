package com.dlz.framework.cache.impl;

import com.dlz.comm.exception.RemoteException;
import com.dlz.comm.util.ExceptionUtils;
import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.ValUtil;
import com.dlz.framework.cache.ICache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * 使用Redis实现缓存
 *
 * @author dk
 */
@Slf4j
public class BaseCacheRedis {
    @Value("${spring.application.name:app}")
    @Nullable
    private String appName = "app";
    @Value("${spring.profiles.active:dev}")
    @Nullable
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
        return sb.append(keySplit).append(profiles).append(keySplit).append(name.replaceAll(":",""));
    }

}