package com.dlz.framework.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Redis key构建器
 *
 * @author dk
 */
@Slf4j
public class RedisKeyMaker {
    private String prefix = prefix_auto;
    public static final String keySplit = ":";
    public static final String prefix_auto = "auto";

    @Autowired
    public void setEnvironment(Environment environment) {
        this.prefix = environment.getProperty("dlz.redis.prefix", prefix_auto);
        if (prefix.equals(prefix_auto)) {
            prefix = environment.getProperty("spring.application.name", prefix_auto) + keySplit +
                    environment.getProperty("spring.profiles.active", "dev");
        }
    }

    @Deprecated
    public String getKey(String key,Object ... obj) {
        StringBuilder sb = new StringBuilder(prefix);
        sb.append(keySplit).append(key);
        return sb.toString().replaceAll("[:]+", ":").replaceAll(":$", "");
    }
    public String getKey(String key) {
        StringBuilder sb = new StringBuilder(prefix);
        sb.append(keySplit).append(key);
        return sb.toString().replaceAll("[:]+", ":").replaceAll(":$", "");
    }

    public String[] getKeys(String... keys) {
        String[] newkeys=new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            newkeys[i] = getKey(keys[i]);
        }
        return newkeys;
    }

    public String getClientKey(String key) {
        return key.substring(prefix.length() + 1);
    }
    public Stream<String> getClientKey(Stream<String> key) {
        return key.map(o -> getClientKey(o));
    }

    public static void main(String[] args) {
        RedisKeyMaker keyMaker = new RedisKeyMaker();
        System.out.println(keyMaker.getKey(":xxx:xxx::"));
        System.out.println(keyMaker.getKey(":xxx::xxx::"));
        System.out.println(keyMaker.getKey(":xxx::xxx::","aa"));
        System.out.println(keyMaker.getKey(":xxx::xxx::",":aa:"));
        System.out.println(keyMaker.getKey(":xxx::xxx::","aa:"));
        System.out.println(keyMaker.getKey(":xxx::xxx::", "*:"));
        System.out.println(keyMaker.getClientKey("auto::xxx:xxx::"));
        System.out.println(keyMaker.getKeys("auto::xxx:xxx::"));
    }
}