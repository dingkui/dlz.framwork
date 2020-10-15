package com.dlz.framework.redis;

import com.dlz.comm.exception.RemoteException;
import com.dlz.comm.util.ExceptionUtils;
import com.dlz.framework.executor.Executor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis key构建器
 *
 * @author dk
 */
@Slf4j
public class JedisExecutor {
    @Autowired
    RedisKeyMaker keyMaker;
    @Autowired
    private JedisPool jedisPool;
    /**
     * 处理 jedis请求
     *
     * @param j 处理逻辑，通过 lambda行为参数化
     * @return 处理结果
     */
    public <T> T excuteByJedis(Executor<Jedis, T> j) throws RemoteException {
        try (Jedis jedis = jedisPool.getResource()) {
            return j.excute(jedis);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(RemoteException.buildException("redis操作异常", e)));
        }
        return null;
    }
}