package com.dlz.cache.redis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 杨斌冰-工具组-技术中心
 *         <p>
 *         2018/3/1 14:38
 */
@Configuration
public class RedisQueueConfiguration {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Value("${host}")
    private String host;
    
    @Value("${password}")
    private String password;
    
    @Value("${port}")
    private int port;
    
    @Value("${timeout}")
    private int timeout;

    @Bean(name="jedisPool")
    public JedisPool jedisPool() throws Exception{
        try{
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(200);
            jedisPoolConfig.setMaxIdle(-1);
			JedisPool jedisSentinelPool = new JedisPool(jedisPoolConfig,host,port,timeout);
            logger.info(">>>>>>>>>>>>>>>>>>>>>> Initial Jedis Pool Successfully.");
            return jedisSentinelPool;
        }catch (Exception e){
            logger.error(">>>>>>>>>>>>>>>>>>>>>> Initial Jedis Pool Error.",e);
            throw e;
        }
    }
}
