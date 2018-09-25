package com.dlz.plugin.redis;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.framework.cache.AbstractCache.ICacheDeal;
import com.dlz.framework.cache.ICacheCreator;
import com.dlz.framework.logger.MyLogger;
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
	private static MyLogger logger = MyLogger.getLogger(CacheDeaRedisImpl.class);

	@Autowired
	private JedisPool jedisSentinelPool;

	@Override
	public ICacheDeal createCaheDeal(String cacheName) {
		return new Cache(cacheName);
	}

	private class Cache implements ICacheDeal {
		String name;

		Cache(String name) {
			this.name = name;
			logger.debug("redis缓存初始化：" + name);
		}

		@Override
		public Serializable get(Serializable key) {
			final Jedis resource = jedisSentinelPool.getResource();
			final Client client = resource.getClient();
			client.hget(SafeEncoder.encode(name), SafeEncoder.encode(key.toString()));
			final byte[] result = client.getBinaryBulkReply();
			if (null != result) {
				return (Serializable)SerializeUtil.deserialize(result);
			} else {
				return null;
			}
		}

		@Override
		public void put(Serializable key, Serializable value, int exp) {
			final Jedis resource = jedisSentinelPool.getResource();
			final Client client = resource.getClient();
			client.hset(SafeEncoder.encode(name), SafeEncoder.encode(key.toString()), SerializeUtil.serialize(value));
			client.getIntegerReply();
		}

		@Override
		public void remove(Serializable key) {
			jedisSentinelPool.getResource().hdel(name, key.toString());
		}

		@Override
		public void removeAll() {
			jedisSentinelPool.getResource().del(name);
		}
	}
}