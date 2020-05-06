package com.dlz.plugin.redis;

import java.io.Serializable;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.framework.cache.AbstractCache;
import com.dlz.framework.cache.AbstractCache.ICacheDeal;
import com.dlz.framework.cache.ICacheCreator;
import com.dlz.framework.util.system.SerializeUtil;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.SafeEncoder;

/**
 * 使用Redis实现缓存
 * 
 * @author dk
 */
public class CacheDeaRedisImpl implements ICacheCreator {
	private static Logger logger = LoggerFactory.getLogger(CacheDeaRedisImpl.class);
	
	private static byte[] nxxx=SafeEncoder.encode("XX");
	private static byte[] expx=SafeEncoder.encode("EX");

	@Autowired
	private JedisPool jedisSentinelPool;

	@Override
	public ICacheDeal createCaheDeal(String cacheName) {
		return new Cache(cacheName);
	}

	private class Cache implements ICacheDeal {
		String name;

		Cache(String name) {
			this.name = AbstractCache.CACHE_NAME_GLOBAL.equals(name)?"":(name+"_");
			logger.debug("redis缓存初始化：" + name);
		}

		@Override
		public Serializable get(Serializable key) {
			final Jedis resource = jedisSentinelPool.getResource();
			final Client client = resource.getClient();
//			client.hget(SafeEncoder.encode(name), SafeEncoder.encode(key.toString()));
			client.get(SafeEncoder.encode(name+key.toString()));
			final byte[] result = client.getBinaryBulkReply();
			try{
				if (null != result) {
					return (Serializable)SerializeUtil.deserialize(result);
				} else {
					return null;
				}
			}finally{
				client.close();
				resource.close();
			}
		}
		
		@Override
		public void put(Serializable key, Serializable value, int exp) {
			final Jedis resource = jedisSentinelPool.getResource();
			final Client client = resource.getClient();
//			client.hset(SafeEncoder.encode(name), SafeEncoder.encode(key.toString()), SerializeUtil.serialize(value));
			if(exp>0){
				client.set(SafeEncoder.encode(name+key.toString()), SerializeUtil.serialize(value), nxxx, expx, exp);
			}else{
				client.set(SafeEncoder.encode(name+key.toString()), SerializeUtil.serialize(value));
			}
			client.getBinaryBulkReply();
			client.close();
			resource.close();
		}

		@Override
		public void remove(Serializable key) {
//			jedisSentinelPool.getResource().hdel(name,key.toString());
			final Jedis resource = jedisSentinelPool.getResource();
			resource.del(name+key.toString());
			resource.close();
		}

		@Override
		public void removeAll() {
//			jedisSentinelPool.getResource().del(name);
			if(name.length()>=0){
				final Set<String> keys = jedisSentinelPool.getResource().keys(name+"*");
				final Jedis resource = jedisSentinelPool.getResource();
				resource.del((String[])keys.toArray());
				resource.close();
			}else{
				logger.warn("redis _global cache can't removeAll");
			}
		}
	}
}