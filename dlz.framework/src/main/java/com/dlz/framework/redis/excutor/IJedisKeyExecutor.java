package com.dlz.framework.redis.excutor;

import com.dlz.framework.redis.util.JedisKeyUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Redis 执行器：Key（键）
 * http://doc.redisfans.com/
 *
 * @author dk
 */
public interface IJedisKeyExecutor extends IJedisExecutor {
    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     */
    default Boolean expire(String key, int seconds) {
        if (seconds > 0) {
            excuteByJedis(j -> j.expire(JedisKeyUtils.getRedisKey(key), seconds));
        }
        return true;
    }

    /**
     * 缓存类型
     *
     * @param key 键
     */
    default String type(String key) {
        return excuteByJedis(j -> j.type(JedisKeyUtils.getRedisKey(key)));
    }

    /**
     * 查找匹配key
     *
     * @param pattern key
     * @return /
     */
    default List<String> keys(String pattern) {
        Stream<String> stream = excuteByJedis(j -> {
//                ScanResult<String> scan = j.scan(pattern);
            return j.keys(JedisKeyUtils.getRedisKey(pattern));
        }).stream();
        return JedisKeyUtils.getClientKeyStream(stream).collect(Collectors.toList());
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    default Boolean exists(String key) {
        return excuteByJedis(j -> j.exists(JedisKeyUtils.getRedisKey(key)));
    }

    /**
     * 删除缓存
     *
     * @param keys 可以传一个值 或多个
     */
    default Long del(String... keys) {
        return excuteByJedis(j -> j.del(JedisKeyUtils.getRedisKeyArray(keys)));
    }
}