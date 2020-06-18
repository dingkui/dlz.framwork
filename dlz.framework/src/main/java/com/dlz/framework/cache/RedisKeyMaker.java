package com.dlz.framework.cache;

import com.dlz.comm.exception.RemoteException;
import com.dlz.comm.util.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 使用Redis实现缓存
 *
 * @author dk
 */
@Slf4j
@Component
public class RedisKeyMaker {
    @Value("${spring.application.name:app}")
    @Nullable
    private String appName;
    @Value("${spring.profiles.active:dev}")
    @Nullable
    private String profiles;
    protected static final String keySplit = ":";

    private String keyPrefix;
    public String getKey(String key,Object ... keys){
        if(keyPrefix==null){
            keyPrefix = appName + keySplit + profiles;
        }
        StringBuilder sb = new StringBuilder(keyPrefix);
        int i = key.indexOf(keySplit);
        if(i!=0){
            sb.append(keySplit);
        }
        sb.append(keySplit).append(key);
        for (Object s : keys) {
            sb.append(keySplit).append(s);
        }
        return sb.toString().replaceAll("[:]+",":").replaceAll(":$","");
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