package com.dlz.test.framework.redis;

import com.dlz.comm.exception.SystemException;
import com.dlz.comm.util.StringUtils;
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

    @Value("${redis.host}")
    private String host;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.timeout}")
    private int timeout;

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
            if (StringUtils.isNotEmpty(password)) {
                jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
            } else {
                jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
            }
            logger.info(">>>>>>>>>>>>>>>>>>>>>> Initial Jedis Pool Successfully.");
            return jedisPool;
        } catch (Exception e) {
            logger.error(">>>>>>>>>>>>>>>>>>>>>> Initial Jedis Pool Error.", e);
            throw e;
        }
    }
}
