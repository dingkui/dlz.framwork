package com.dlz.framework.redis;

import com.dlz.comm.exception.RemoteException;
import com.dlz.comm.util.ExceptionUtils;
import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.ValUtil;
import com.dlz.framework.executor.Executor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Redis key构建器
 *
 * @author dk
 */
@Slf4j
public class JedisExecutor {
    @Autowired
    private RedisKeyMaker keyMaker;
    @Autowired
    private JedisPool jedisPool;



    /**
     * 处理 jedis请求
     *
     * @param j 处理逻辑，通过 lambda行为参数化
     * @return 处理结果
     */
    public <T> T excuteByJedis(Executor<Jedis, T> j) throws RemoteException {
        try (Jedis jedis = jedisPool.getResource()) {
            return j.excute(jedis);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(RemoteException.buildException("redis操作异常", e)));
        }
        return null;
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     */
    public Boolean expire(String key, int time) {
        if (time > 0) {
            excuteByJedis(j -> j.expire(keyMaker.getKey(key), time));
        }
        return true;
    }

    /**
     * 查找匹配key
     *
     * @param pattern key
     * @return /
     */
    public List<String> keys(String pattern) {
        return keyMaker.getClientKey(excuteByJedis(j -> {
//                ScanResult<String> scan = j.scan(pattern);
            return j.keys(pattern);
        }).stream()).collect(Collectors.toList());
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public Boolean hasKey(String key) {
        return excuteByJedis(j -> j.exists(keyMaker.getKey(key)));
    }

    /**
     * 删除缓存
     *
     * @param keys 可以传一个值 或多个
     */
    public Long del(String... keys) {
        return excuteByJedis(j -> j.del(keyMaker.getKeys(keys)));
    }

    // ============================String=============================

    /**
     * 批量获取
     *
     * @param keys
     * @return
     */
    public List<String> multiGet(String... keys) {
        return excuteByJedis(j -> j.mget(keyMaker.getKeys(keys)));
    }



    public String get(String key){
        return excuteByJedis(j -> j.get(keyMaker.getKey(key)));
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public Boolean set(String key, String value) {
        return set(key, value, 0);
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public Boolean set(String key, String value, int time) {
            return excuteByJedis(j -> {
                String key1 = keyMaker.getKey(key);
                j.set(key1, value);
                if (time > 0) {
                    j.expire(key1, time);
                }
                return true;
            });
    }

    // ================================Object=================================
    public Boolean setObj(Serializable key, Serializable value) {
        return setObj(key, value, 0);
    }
    public Boolean setObj(Serializable key, Serializable value, int time) {
        return set(ValUtil.getStr(key), ValUtil.getStr(value), time);
    }
    public <T> T getObj(Serializable key, Class<T> tClass) {
        String str = get(ValUtil.getStr(key));
        if(str!=null && str.length()>0){
            return ValUtil.getObj(str, tClass);
        }
        return null;
    }
    public <T extends Serializable> T getObj(Serializable key, Type type) {
        String str = get(ValUtil.getStr(key));
        if (str != null && str.length()>0) {
            return ValUtil.getObj(str, JacksonUtil.getJavaType(type));
        }
        return null;
    }
    public Boolean hsetObj(Serializable key, Serializable item,Serializable value) {
        return hsetObj(key, value,item, 0);
    }
    public Boolean hsetObj(Serializable key,Serializable item, Serializable value, int time) {
        return hset(ValUtil.getStr(key),ValUtil.getStr(item), ValUtil.getStr(value), time);
    }
    public <T> T hgetObj(Serializable key,Serializable item, Class<T> tClass) {
        String str = hget(ValUtil.getStr(key),ValUtil.getStr(item));
        if(str!=null && str.length()>0){
            return ValUtil.getObj(str, tClass);
        }
        return null;
    }
    public <T extends Serializable> T hgetObj(Serializable key,Serializable item, Type type) {
        String str = hget(ValUtil.getStr(key),ValUtil.getStr(item));
        if (str != null && str.length()>0) {
            return ValUtil.getObj(str, JacksonUtil.getJavaType(type));
        }
        return null;
    }
    // ================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public String hget(String key, String item) {
        return excuteByJedis(j -> j.hget(keyMaker.getKey(key), item));
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<String, String> hmget(String key) {
        Map<String, String> result = excuteByJedis(j -> j.hgetAll(keyMaker.getKey(key)));
        Map<String, String> map = new HashMap<>(result.size());
        result.forEach((k, v) -> map.put(keyMaker.getClientKey(k),v));
        return map;
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public Boolean hmset(String key, Map<String, String> map) {
        return hmset(key, map, 0);
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public Boolean hmset(String key, Map<String, String> map, int time) {
        return excuteByJedis(j -> {
            String key1 = keyMaker.getKey(key);
            map.entrySet().forEach(m -> j.hset(key1, m.getKey(), m.getValue()));
            if (time > 0) {
                j.expire(key1, time);
            }
            return true;
        });
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public Boolean hset(String key, String item, String value) {
        return hset(key, item, value, 0);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public Boolean hset(String key, String item, String value, int time) {
        return excuteByJedis(j -> {
            String key1 = keyMaker.getKey(key);
            j.hset(key1, item, value);
            if (time > 0) {
                j.expire(key1, time);
            }
            return true;
        });
    }

    /**
     * 删除hash表中的值
     *
     * @param key   键 不能为null
     * @param items 项 可以使多个 不能为null
     */
    public void hdel(String key, String... items) {
        excuteByJedis(j -> {
            j.hdel(keyMaker.getKey(key), items);
            return true;
        });
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public Boolean hHasKey(String key, String item) {
        return excuteByJedis(j -> j.hexists(keyMaker.getKey(key), item));
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public Long hincr(String key, String item, long by) {
        return excuteByJedis(j -> j.hincrBy(keyMaker.getKey(key), item, by));
    }

    // ============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<String> sGet(String key) {
        return keyMaker.getClientKey(excuteByJedis(j -> j.smembers(keyMaker.getKey(key))).stream()).collect(Collectors.toSet());
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public Boolean sHasKey(String key, String value) {
        return excuteByJedis(j -> j.sismember(keyMaker.getKey(key), value));
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, int time, String... values) {
        return excuteByJedis(j -> {
            String key1 = keyMaker.getKey(key);
            Long sadd = j.sadd(key1, values);
            if (time > 0) {
                j.expire(key1, time);
            }
            return sadd;
        });
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, String... values) {
        return sSet(keyMaker.getKey(key), 0, values);
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public Long setRemove(String key, String... values) {
        return excuteByJedis(j -> j.srem(keyMaker.getKey(key), values));
    }

    // ===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return
     */
    public List<String> lGet(String key, long start, long end) {
        return keyMaker.getClientKey(excuteByJedis(j -> j.lrange(keyMaker.getKey(key), start, end)).stream()).collect(Collectors.toList());
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public long llen(String key) {
        return excuteByJedis(j -> j.llen(keyMaker.getKey(key)));
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        return excuteByJedis(j -> j.lindex(keyMaker.getKey(key), index));
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public Long lpush(String key, String... value) {
        return lpush(key, 0, value);
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param time  时间(秒)
     * @param value 值
     * @return
     */
    public Long lpush(String key, int time, String... value) {
        return excuteByJedis(j -> {
            String key1 = keyMaker.getKey(key);
            Long lpush = j.lpush(key1, value);
            if (time > 0) {
                j.expire(key1, time);
            }
            return lpush;
        });
    }


    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return /
     */
    public Boolean lUpdateIndex(String key, long index, String value) {
        excuteByJedis(j -> j.lset(keyMaker.getKey(key), index, value));
        return true;
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, String value) {
            return excuteByJedis(j -> j.lrem(keyMaker.getKey(key), count, value));
    }

    /**
     * 递增 此时value值必须为int类型 否则报错
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return excuteByJedis(j -> j.incrBy(keyMaker.getKey(key), delta));
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return excuteByJedis(j -> j.decrBy(keyMaker.getKey(key), delta));
    }

}