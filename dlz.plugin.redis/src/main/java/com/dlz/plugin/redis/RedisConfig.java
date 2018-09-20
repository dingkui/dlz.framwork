package com.dlz.plugin.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.codec.CodecException;

import com.dlz.framework.logger.MyLogger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis连接池初始化
 */
@Configuration
public class RedisConfig {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
    private MyLogger logger = MyLogger.getLogger(getClass());
    
    @Value("${redis.host}")
    private String host;
    
    @Value("${redis.password}")
    private String password;
    
    @Value("${redis.port}")
    private int port;
    
    @Value("${redis.timeout}")
    private int timeout;
    
    private static JedisPool jedisPool=null;
    
    public static Jedis getJedis(){
    	if(jedisPool==null){
    		throw new CodecException("JedisPool 未初始化！");
    	}
    	return jedisPool.getResource();
    }

    @Bean(name="jedisPool")
    public JedisPool jedisPool() throws Exception{
        try{
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(200);
            jedisPoolConfig.setMaxIdle(-1);
            jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout);
            logger.info(">>>>>>>>>>>>>>>>>>>>>> Initial Jedis Pool Successfully.");
            return jedisPool;
        }catch (Exception e){
            logger.error(">>>>>>>>>>>>>>>>>>>>>> Initial Jedis Pool Error.",e);
            throw e;
        }
    }
}
