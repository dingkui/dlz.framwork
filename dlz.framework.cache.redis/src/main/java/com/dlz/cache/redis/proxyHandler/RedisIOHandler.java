package com.dlz.cache.redis.proxyHandler;

import java.lang.reflect.Method;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlz.cache.redis.queue.annotation.RedisQueueProvider;
import com.dlz.framework.springframework.iproxy.AInterfaceProxyHandler;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author 杨斌冰-工具组-技术中心
 *         <p>
 *         2018/3/1 14:22
 */
@Component("redisQueue")
public class RedisIOHandler extends AInterfaceProxyHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JedisPool jedisSentinelPool;

    public void setJedisSentinelPool(JedisPool jedisSentinelPool) {
        this.jedisSentinelPool = jedisSentinelPool;
    }

	@Override
	public Object done(Class<?> clas, Method method, Object[] args) throws Exception {
		long rId = 0;
        if(method.isAnnotationPresent(RedisQueueProvider.class)){
            if(args == null || args.length != 1){
                throw new Exception("Redis Queue Provider Java Interface Defined Error.");
            }
            RedisQueueProvider redisQueueProvider = method.getDeclaredAnnotation(RedisQueueProvider.class);
            String queueName = StringUtils.isNotEmpty(redisQueueProvider.value()) ? redisQueueProvider.value() : redisQueueProvider.queueName();
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
