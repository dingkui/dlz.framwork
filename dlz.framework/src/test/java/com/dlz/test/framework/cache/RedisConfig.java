package com.dlz.test.framework.cache;

import com.dlz.comm.exception.SystemException;
import com.dlz.comm.util.StringUtils;
import com.dlz.framework.cache.ICache;
import com.dlz.framework.cache.impl.CacheRedisJsonKey;
import com.dlz.framework.cache.impl.CacheRedisSerialHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis连接池初始化
 */
@Configuration
public class RedisConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${redis.host:127.0.0.1}")
    private String host;
    @Value("${redis.password:}")
    private String password;
    @Value("${redis.port:6379}")
    private int port;
    @Value("${redis.timeout:3000}")
    private int timeout;
    @Value("${redis.database:0}")
    private int database;

    private static JedisPool jedisPool = null;

    public static Jedis getJedis() {
        if (jedisPool == null) {
            throw new SystemException("JedisPool 未初始化！");
        }
        return jedisPool.getResource();
    }


    @Bean(name = "jedisPool")
    public JedisPool jedisPool() throws Exception {
        try {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(200);
            jedisPoolConfig.setMaxIdle(-1);
            if (StringUtils.isEmpty(password)) {
                password=null;
            }
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout,password,database,null);
            logger.info(">>>>>>>>>>>>>>>>>>>>>> Initial Jedis Pool Successfully.");
            return jedisPool;
        } catch (Exception e) {
            logger.error(">>>>>>>>>>>>>>>>>>>>>> Initial Jedis Pool Error.", e);
            throw e;
        }
    }

    @Bean(name="myCache")
    public ICache getCache(){
        return new CacheRedisSerialHash();
    }
}
