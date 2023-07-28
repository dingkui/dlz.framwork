package com.dlz.framework.redis.service.impl;

import com.dlz.comm.util.ValUtil;
import com.dlz.framework.cache.ICache;
import com.dlz.framework.redis.excutor.JedisExecutor;
import com.dlz.framework.redis.excutor.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * 使用Redis实现缓存
 *
 * @author dk
 */
public class CacheRedisSerialKey implements ICache {
//    @Autowired
//    RedisKeyMaker keyMaker;
    @Autowired
    JedisExecutor jedisExecutor;

    @Override
    public <T extends Serializable> T get(String name, Serializable key, Type type) {
        return jedisExecutor.getSe(SerializationUtils.getKey(name,key),type);
//        return jedisExecutor.excuteByJedis(j -> {
//            final byte[] result = j.get(getRedisByteKey(name, key));
//            if (result==null){
//                return null;
//            }
//            Object obj = SerializeUtil.deserialize(result);
//            if (type==null){
//                return (T)obj;
//            }
//            return ValUtil.getObj(obj, JacksonUtil.getJavaType(type));
//        });
    }

    @Override
    public void put(String name, Serializable key, Serializable value, int seconds) {
        jedisExecutor.setSe(SerializationUtils.getKey(name, key),value,seconds);
//        jedisExecutor.excuteByJedis(j -> {
//            byte[] key1 = getRedisByteKey(name, key);
//            String set = j.set(key1, SerializeUtil.serialize(value));
//            if (seconds > -1) {
//                j.expire(key1, seconds);
//            }
//            return set;
//        });
    }

    @Override
    public void remove(String name, Serializable key) {
        jedisExecutor.del(SerializationUtils.getRedisKey(name, key));
//        jedisExecutor.excuteByJedis(j -> j.del(SerializationUtils.getRedisByteKey(name, key)));
    }

    @Override
    public void removeAll(String name) {
        jedisExecutor.excuteByJedis(j -> {
            Set<String> keys = j.keys(SerializationUtils.getRedisKey(name+"*"));
            j.del(keys.toArray(new String[keys.size()]));
            return true;
        });
    }
}