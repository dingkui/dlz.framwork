package com.dlz.framework.redisqueue.provider;

import com.dlz.comm.exception.SystemException;
import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.ValUtil;
import com.dlz.framework.cache.RedisKeyMaker;
import com.dlz.framework.config.condition.RedisQueueCondition;
import com.dlz.framework.redisqueue.annotation.AnnoRedisQueueProvider;
import com.dlz.framework.spring.iproxy.ApiProxyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Method;
import java.util.concurrent.Executors;

/**
 * Redis队列生产者动态代理实现类
 */
@Component
@Conditional(RedisQueueCondition.class)
@Slf4j
public class RedisQueueProviderApiHandler extends ApiProxyHandler {
    @Autowired(required = false)
    private JedisPool jedisPool;
    @Autowired
    private RedisKeyMaker keyMaker;
    @Override
    public Object done(Class<?> clas, Method method, Object[] args) throws Exception {
        long rId = 0;
        if (method.isAnnotationPresent(AnnoRedisQueueProvider.class)) {
            if (args == null || args.length != 1) {
                throw new Exception("队列接口只能发送一个参数");
            }
            AnnoRedisQueueProvider redisQueueProvider = method.getDeclaredAnnotation(AnnoRedisQueueProvider.class);
            String queueName = redisQueueProvider.value();
//            String queueName = StringUtils.isNotEmpty(redisQueueProvider.value()) ? redisQueueProvider.value() : redisQueueProvider.queueName();
            SystemException.notEmpty(queueName,()->"生产者未配置队列名字:"+clas+"."+method.getName());
            String redisQueueName = keyMaker.getKey(queueName);
            try (Jedis jedis = jedisPool.getResource()) {
                switch (redisQueueProvider.strategy()) {
                    case SYNC:
                        String json = ValUtil.getStr(args[0]);
                        try {
                            rId = jedis.rpush(redisQueueName, json);
                            log.debug("同步发消息成功!{} -> {}", redisQueueName, json);
                        } catch (Exception e) {
                            log.error("同步发消息失败!{} -> {}", redisQueueName, json);
                            log.error(e.getMessage(), e);
                        }
                        return rId;
                    case ASYNC:
                        Executors.newSingleThreadExecutor().submit(() -> {
                            String json2 = ValUtil.getStr(args[0]);
                            try {
                                jedis.rpush(redisQueueName, json2);
                                log.debug("异步发消息成功!{} -> {}", redisQueueName, json2);
                            } catch (Exception e) {
                                log.error("异步发消息失败!{} -> {}", redisQueueName, json2);
                                log.error(e.getMessage(), e);
                            }
                        });
                        return 1;
                }
            }
        }
        return rId;
    }
}
