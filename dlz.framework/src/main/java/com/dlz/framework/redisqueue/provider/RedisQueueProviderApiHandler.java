package com.dlz.framework.redisqueue.provider;

import com.dlz.comm.exception.SystemException;
import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.StringUtils;
import com.dlz.framework.cache.RedisKeyMaker;
import com.dlz.framework.redisqueue.annotation.AnnoRedisQueueProvider;
import com.dlz.framework.spring.iproxy.ApiProxyHandler;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Method;
import java.util.concurrent.Executors;

/**
 * Redis队列生产者动态代理实现类
 */
@Component
public class RedisQueueProviderApiHandler extends ApiProxyHandler {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    private JedisPool jedisSentinelPool;

    @Autowired
    RedisKeyMaker keyMaker;

    @Override
    public Object done(Class<?> clas, Method method, Object[] args) throws Exception {
        long rId = 0;
        if (method.isAnnotationPresent(AnnoRedisQueueProvider.class)) {
            if (args == null || args.length != 1) {
                throw new Exception("Redis Queue Provider Java Interface Defined Error.");
            }
            AnnoRedisQueueProvider redisQueueProvider = method.getDeclaredAnnotation(AnnoRedisQueueProvider.class);
            String queueName = redisQueueProvider.value();
//            String queueName = StringUtils.isNotEmpty(redisQueueProvider.value()) ? redisQueueProvider.value() : redisQueueProvider.queueName();
            SystemException.notEmpty(queueName,()->"生产者未配置队列名字:"+clas+"."+method.getName());
            String redisQueueName = keyMaker.getKey(queueName);
            try (Jedis jedis = jedisSentinelPool.getResource()) {
                switch (redisQueueProvider.strategy()) {
                    case SYNC:
                        try {
                            rId = jedis.rpush(redisQueueName, JacksonUtil.getJson(args[0]));
                            logger.info("Sync Send Message [{}] To Queue [{}].", JacksonUtil.getJson(args[0]), redisQueueName);
                        } catch (Exception e) {
                            logger.error("Sync Send Message [" + JacksonUtil.getJson(args[0]) + "] To Queue [" + redisQueueName + "] Error.", e);
                        }
                        return rId;
                    case ASYNC:
                        Executors.newSingleThreadExecutor().submit(() -> {
                            try {
                                jedis.rpush(redisQueueName, JacksonUtil.getJson(args[0]));
                                logger.info("Async Send Message [{}] To Queue [{}].", JacksonUtil.getJson(args[0]), redisQueueName);
                            } catch (Exception e) {
                                logger.error("ASync Send Message [" + JacksonUtil.getJson(args[0]) + "] To Queue [" + redisQueueName + "] Error.", e);
                            }
                        });
                        return 1;
                }
            }
        }
        return rId;
    }
}
