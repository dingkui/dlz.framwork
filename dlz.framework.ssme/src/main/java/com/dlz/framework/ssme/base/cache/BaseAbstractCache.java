package com.dlz.framework.ssme.base.cache;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;

public abstract class BaseAbstractCache<K, V> {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected long version = 0L;
	
	/**
	 * 缓存Map
	 */
	protected Cache<K, V> cache = null;
	
	/**
	 * 缓存初始化方法
	 */
	protected abstract void loadCache() throws Exception;
	
	/**
	 * 根据key获取value
	 * @param key 缓存key
	 * @return value
	 * @throws ExecutionException 
	 */
	public final V get(K key) {
		return cache.getIfPresent(key);
	}
	
	public Map<K, V> asMap() {
		return cache.asMap();
	}
	
}
