//package com.dlz.test.framework.cache;
//
//import com.dlz.comm.util.StringUtils;
//import com.dlz.framework.cache.ICache;
//import com.dlz.framework.redis.service.impl.CacheRedisSerialHash;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * redis连接池初始化
// */
//@Configuration
//@Slf4j
//public class RedisConfig {
//    @Value("${redis.host:192.168.1.89}")
//    private String host;
//    @Value("${redis.password:}")
//    private String password;
//    @Value("${redis.port:6379}")
//    private int port;
//    @Value("${redis.timeout:3000}")
//    private int timeout;
//    @Value("${redis.database:0}")
//    private int database;
//
//    @Bean(name = "jedisPool")
//    public JedisPool jedisPool() throws Exception {
//        try {
//            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//            jedisPoolConfig.setMaxTotal(200);
//            jedisPoolConfig.setMaxIdle(-1);
//            if (StringUtils.isEmpty(password)) {
//                password=null;
//            }
//            JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout,password,database,null);
//            log.info(">>>>>>>>>>>>>>>>>>>>>> Initial Jedis Pool Successfully.");
//            return jedisPool;
//        } catch (Exception e) {
//            log.error(">>>>>>>>>>>>>>>>>>>>>> Initial Jedis Pool Error.", e);
//            throw e;
//        }
//    }
//
//    @Bean(name="dlzCache")
//    public ICache getCache(){
//        return new CacheRedisSerialHash();
//    }
//}
