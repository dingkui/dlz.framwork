package com.dlz.framework.redis.service.impl;

import com.dlz.comm.util.ValUtil;
import com.dlz.comm.util.system.SerializeUtil;
import com.dlz.framework.cache.ICache;
import com.dlz.framework.redis.RedisKeyMaker;
import com.dlz.framework.redis.excutor.JedisExecutor;
import com.dlz.framework.redis.util.JedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.util.SafeEncoder;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * 使用Redis实现缓存
 *
 * @author dk
 */
public class CacheRedisSerialHash implements ICache {
//    @Autowired
//    RedisKeyMaker keyMaker;
    @Autowired
    JedisExecutor jedisExecutor;
//    protected byte[] getRedisByteKey(String name) {
//        return SafeEncoder.encode(keyMaker.getKey(name));
//    }
//
//    private byte[] getByteKey(Serializable key) {
//        return SafeEncoder.encode(key.toString().replaceAll(":", ""));
//    }

    @Override
    public <T extends Serializable> T get(String name, Serializable key, Type type) {
        return jedisExecutor.hgetSe(JedisKeyUtils.getRedisKey(name),ValUtil.getStr(key),type);
//        return jedisExecutor.excuteByJedis(j -> {
//            j.getClient().hget(getRedisByteKey(name), getByteKey(key));
//            final byte[] result = j.getClient().getBinaryBulkReply();
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
        jedisExecutor.hsetSe(JedisKeyUtils.getRedisKey(name),ValUtil.getStr(key),value,0);
//        jedisExecutor.excuteByJedis(j -> {
//            byte[] key1 = getRedisByteKey(name);
//            Long hset = j.hset(key1, getByteKey(key), SerializeUtil.serialize(value));
//            if (seconds > -1) {
//                j.expire(key1, seconds);
//            }
//            return hset;
//        });
    }

    @Override
    public void remove(String name, Serializable key) {
        jedisExecutor.hdel(JedisKeyUtils.getRedisKey(name),ValUtil.getStr(key));
//        jedisExecutor.excuteByJedis(j -> j.hdel(getRedisByteKey(name), getByteKey(key)));
    }

    @Override
    public void removeAll(String name) {
        jedisExecutor.del(JedisKeyUtils.getRedisKey(name));
//        jedisExecutor.excuteByJedis(j -> j.del(getRedisByteKey(name)));
    }


}