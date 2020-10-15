package com.dlz.framework.redis.service.impl;

import com.dlz.comm.exception.RemoteException;
import com.dlz.framework.executor.Executor;
import com.dlz.framework.redis.JedisExecutor;
import com.dlz.framework.redis.RedisKeyMaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

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
    private JedisExecutor jedisExecutor;

    /**
     * 处理 jedis请求
     *
     * @param j 处理逻辑，通过 lambda行为参数化
     * @return 处理结果
     */
    protected <T> T excuteByJedis(Executor<Jedis, T> j) throws RemoteException {
        return jedisExecutor.excuteByJedis(j);
    }
    protected String getRedisKey(String key,Object ... keys) {
        return keyMaker.getKey(key,keys);
    }
}