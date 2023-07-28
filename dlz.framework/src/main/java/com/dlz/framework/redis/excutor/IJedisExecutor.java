package com.dlz.framework.redis.excutor;

import com.dlz.framework.executor.Executor;
import redis.clients.jedis.Jedis;

/**
 * Redis key构建器
 *
 * @author dk
 */
interface IJedisExecutor {
    /**
     * 处理 jedis请求
     *
     * @param j 处理逻辑，通过 lambda行为参数化
     * @return 处理结果
     */
    <T> T excuteByJedis(Executor<Jedis, T> j);
}