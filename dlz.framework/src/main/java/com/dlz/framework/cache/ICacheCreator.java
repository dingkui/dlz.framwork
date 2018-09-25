package com.dlz.framework.cache;

import com.dlz.framework.cache.AbstractCache.ICacheDeal;

/**
 * 缓存实现
 * @author dk
 */
public interface ICacheCreator{
	ICacheDeal createCaheDeal(String cacheName);
}