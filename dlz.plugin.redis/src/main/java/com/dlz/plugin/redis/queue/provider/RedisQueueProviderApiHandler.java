package com.dlz.plugin.redis.queue.provider;

import java.lang.reflect.Method;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.springframework.iproxy.ApiProxyHandler;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.StringUtils;
import com.dlz.plugin.redis.annotation.AnnoRedisQueueProvider;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis队列生产者动态代理实现类
 */
@Component
public class RedisQueueProviderApiHandler extends ApiProxyHandler {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
    private MyLogger logger = MyLogger.getLogger(getClass());

    @Autowired
    private JedisPool jedisSentinelPool;

    public void setJedisSentinelPool(JedisPool jedisSentinelPool) {
        this.jedisSentinelPool = jedisSentinelPool;
    }

	@Override
	public Object done(Class<?> clas, Method method, Object[] args) throws Exception {
		long rId = 0;
        if(method.isAnnotationPresent(AnnoRedisQueueProvider.class)){
            if(args == null || args.length != 1){
                throw new Exception("Redis Queue Provider Java Interface Defined Error.");
            }
            AnnoRedisQueueProvider redisQueueProvider = method.getDeclaredAnnotation(AnnoRedisQueueProvider.class);
            String queueName = redisQueueProvider.value();
//            String queueName = StringUtils.isNotEmpty(redisQueueProvider.value()) ? redisQueueProvider.value() : redisQueueProvider.queueName();
            if(StringUtils.isEmpty(queueName)){
                throw new Exception("Redis Queue Provider Java Config Error,please Check your configuration");
            }
            try(Jedis jedis = jedisSentinelPool.getResource()){
                switch (redisQueueProvider.strategy()){
                    case SYNC:
                        try{
                            rId = jedis.rpush(queueName, JacksonUtil.getJson(args[0]));
                            logger.info("Sync Send Message [{}] To Queue [{}].",JacksonUtil.getJson(args[0]),queueName);
                        }catch (Exception e){
                            logger.error("Sync Send Message [" + JacksonUtil.getJson(args[0]) + "] To Queue [" + queueName + "] Error.",e);
                        }
                        return rId;
                    case ASYNC:
                        Executors.newSingleThreadExecutor().submit(()-> {
                            try{
                                jedis.rpush(queueName,JacksonUtil.getJson(args[0]));
                                logger.info("Async Send Message [{}] To Queue [{}].",JacksonUtil.getJson(args[0]),queueName);
                            }catch (Exception e){
                                logger.error("ASync Send Message [" + JacksonUtil.getJson(args[0]) + "] To Queue [" + queueName + "] Error.",e);
                            }
                        });
                        return 1;
                }
            }
        }
        return rId;
	}
}
