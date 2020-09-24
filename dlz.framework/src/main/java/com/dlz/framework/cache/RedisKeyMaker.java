package com.dlz.framework.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Redis key构建器
 *
 * @author dk
 */
@Slf4j
@Component
public class RedisKeyMaker {
    @Value("${dlz.redis.prefix:auto}")
    private String prefix;
    @Value("${spring.application.name:app}")
    private String appName;
    @Value("${spring.profiles.active:dev}")
    private String profiles;
    protected static final String keySplit = ":";

    public String getKey(String key, Object... keys) {
        if ("auto".equals(prefix)) {
            prefix = appName + keySplit + profiles;
        }
        StringBuilder sb = new StringBuilder(prefix);
        sb.append(keySplit).append(key);
        for (Object s : keys) {
            sb.append(keySplit).append(s);
        }
        return sb.toString().replaceAll("[:]+", ":").replaceAll(":$", "");
    }

//    public static void main(String[] args) {
//        RedisKeyMaker keyMaker = new RedisKeyMaker();
////        System.out.println(keyMaker.getKey(":xxx:xxx::"));
////        System.out.println(keyMaker.getKey(":xxx::xxx::"));
////        System.out.println(keyMaker.getKey(":xxx::xxx::","aa"));
////        System.out.println(keyMaker.getKey(":xxx::xxx::",":aa:"));
////        System.out.println(keyMaker.getKey(":xxx::xxx::","aa:"));
//        System.out.println(keyMaker.getKey(":xxx::xxx::","*:"));
//    }
}