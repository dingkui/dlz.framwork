package com.dlz.framework.cache;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dlz.framework.cache.AbstractCache.ICacheDeal;
import com.dlz.framework.exception.CodeException;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 使用ehcache实现缓存
 * 
 * @author dk
 */
public class CacheCreatorEhcaheImpl implements ICacheCreator {
	private static Logger logger = LoggerFactory.getLogger(CacheCreatorEhcaheImpl.class);

	@Override
	public ICacheDeal createCaheDeal(String cacheName) {
		return new Cache(cacheName);
	}

	private class Cache implements ICacheDeal {
		private net.sf.ehcache.Cache cache;
		Cache(String name) {
			try {
				CacheManager manager = CacheManager.getInstance();
				logger.debug("缓存初始化：" + manager.getConfiguration().getDiskStoreConfiguration().getPath() + "/" + name);
				cache = manager.getCache(name);
				if (cache == null) {
					manager.addCache(name);
					cache = manager.getCache(name);
				}
			} catch (net.sf.ehcache.CacheException e) {
				logger.error("缓存创建失败:"+name, e);
				throw new CodeException("缓存创建失败:"+name,e);
			}
		}

		@Override
		public Serializable get(Serializable key) {
			Element element = cache.get(key);
			if (element != null) {
				return (Serializable) element.getObjectValue();
			}
			return null;
		}

		@Override
		public void put(Serializable key, Serializable value, int exp) {
			Element element = new Element(key, value);
			if (exp > -1) {
				element.setTimeToLive(exp);
			}
			cache.put(element);
		}

		@Override
		public void remove(Serializable key) {
			cache.remove(key.toString());
		}

		@Override
		public void removeAll() {
			cache.removeAll();
		}
	}
}