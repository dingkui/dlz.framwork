package com.dlz.framework.config;

import com.dlz.framework.config.condition.InitPoolCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Slf4j
@Configuration
@Conditional(InitPoolCondition.class)
public class JedisConfig {
    @Value("${spring.redis.host:}")
    private String host;
    @Value("${spring.redis.port:}")
    private int port;
    @Value("${spring.redis.jedis.pool.max-active:200}")
    private int maxTotal;
    @Value("${spring.redis.jedis.pool.max-idle:-1}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.min-idle:1}")
    private int minIdle;
    @Value("${spring.redis.password:}")
    private String password;
    @Value("${spring.redis.timeout:5000}")
    private int timeout;
    @Value("${spring.redis.database:0}")
    private int database;

    @Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        if (password.length()==0) {
            password=null;
        }
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password,database);
        log.info("JedisPool初始化成功：{}:{}", host, port);
        return jedisPool;
    }
}