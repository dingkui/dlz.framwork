package com.dlz.framework.cache;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.exception.CodeException;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.logger.MyLogger;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Ehcache缓存实现
 * 
 * @author dk
 *
 */
@SuppressWarnings("unchecked")
public abstract class AbstractCache<KEY,T>{
	private static MyLogger logger = MyLogger.getLogger(AbstractCache.class);

	private static Set<String> CacheSet = new HashSet<String>();
	
	@Autowired
	public ICommService commService;
	
	@SuppressWarnings("rawtypes")
	public static void clearAll(){
		for(String caches:CacheSet){
			((AbstractCache)SpringHolder.getBean(caches)).clear();
		}
	}
	
	public CacheDeal cacheDeal=null;
	public DbOperator<KEY,T> dbOperator=null;
	
	public class DbOperator<KEY1,T1>{
		protected T1 getFromDb(KEY1 key){return null;}
		protected void saveToDb(T1 t){}
	}
	
	public class CacheDeal{
		private net.sf.ehcache.Cache cache;
		<T1,KEY1> T1 readDb(KEY1 key,DbOperator<KEY1,T1> dbOperator){
			T1 t= null;
			try{
				t=dbOperator.getFromDb(key);
			}catch(Exception e){
				logger.error("从数据库加载缓存失败："+cacheDeal.getName()+"."+key,e);
			}
			return t;
		}
		private <T1,KEY1> void save(T1 t,DbOperator<KEY1,T1> dbOperator){
			try{
				dbOperator.saveToDb(t);
			}catch(Exception e){
				logger.error("保存缓存到数据库失败："+cacheDeal.getName());
				logger.error(e.getMessage(),e);
			}
		}
		
		public CacheDeal(String cacheName) { 
			try {
				CacheManager manager = CacheManager.getInstance();
				logger.debug("缓存初始化："+manager.getConfiguration().getDiskStoreConfiguration().getPath()+"/"+cacheName);
				cache = manager.getCache(cacheName);
				if (cache == null) {
					manager.addCache(cacheName);
					cache = manager.getCache(cacheName);
				}
			} catch (net.sf.ehcache.CacheException e) {
				logger.error(e.getMessage(), e);
			}
		}
		public <T1,KEY1> T1 get(Serializable key,DbOperator<KEY1,T1> dbOperator) {
			T1 t=null;
			try {
				if (key != null) {
					Element element = cache.get(key.toString());
					if (element != null) {
						t=(T1)element.getObjectValue();
					}
					if(t==null && dbOperator!=null){
						t=readDb((KEY1)key,dbOperator);
						if(t!=null){
							put(key,(Serializable)t,timeToLiveSeconds);
						}
					}
				}
			} catch (net.sf.ehcache.CacheException e) {
				logger.error(e.getMessage(), e);
			}
			return t;
		}
		public <T1> T1 get(Serializable key) {
			return get(key,null);
		}
		public void put(Serializable key, Serializable value, int exp) {
			try {
				Element element = new Element(key.toString(),value);
				if(exp>-1){
					element.setTimeToLive(exp);
				}
				cache.put(element);
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage(), e);
			} catch (IllegalStateException e) {
				logger.error(e.getMessage(), e);
			}
		}
		public void remove(Serializable key) {
			try {
				cache.remove(key.toString());
			} catch (ClassCastException e) {
				logger.error(e.getMessage(), e);
			} catch (IllegalStateException e) {
				logger.error(e.getMessage(), e);
			}
		}
		public void clear() {
			try {
	            cache.removeAll();
	        } catch (IllegalStateException e) {
	        	logger.error(e.getMessage(), e);
	        } 
		}
		public String getName() {
	        return cache.getName();
		}
		/**
		 * 保存缓存到数据库
		 * @param key
		 */
		public <T1,KEY1> void saveCache2Db(KEY1 key,T1 value,DbOperator<KEY1,T1> dbOperator) {
			if(value==null){
				if(key==null){
					logger.error("缓存保存失败：key或value必须设置一个！"+cache.getName());
					return;
				}
				value=get((Serializable)key);
			}
			if(value!=null && dbOperator!=null){
				save(value,dbOperator);
			}
		}
		/**
		 * 重新设置缓存数据
		 * @param key
		 * @param value
		 * @return
		 */
		public <T1,KEY1> T1 reset(KEY1 key, T1 value,DbOperator<KEY1,T1> dbOperator) {
			if(value==null){
				value=readDb(key,dbOperator);
			}
			if(value!=null){
				put((Serializable)key, (Serializable)value,timeToLiveSeconds);
			}else{
				remove((Serializable)key);
			}
			if(value==null){
				logger.error("缓存设置失败：cacheName="+cacheDeal.getName()+" key="+key);
			}
			return value;
		}
		
		/**
		 * 从数据库中重新加载
		 * @param key
		 * @return
		 */
		public <T1,KEY1> T1 reload(KEY1 key,DbOperator<KEY1,T1> dbOperator) {
			T1 t=readDb(key,dbOperator);
			if(t==null){
				logger.error("缓存刷新失败：cacheName="+cacheDeal.getName()+" key="+key);
			}else{
				reset(key, t,dbOperator);
			}
			return t;
		}
	}
	
	public AbstractCache(String name) { 
		name=name.substring(0, 1).toLowerCase()+name.substring(1);
		if(CacheSet.contains(name)){
			throw new CodeException("缓存已经存在，不能重复定义："+name);
		}
		CacheSet.add(name);
		cacheDeal=new CacheDeal(name.toLowerCase());
	}
	
	int timeToLiveSeconds=-1;
	public AbstractCache(String name,int timeToLiveSeconds) { 
		this(name);
		this.timeToLiveSeconds=timeToLiveSeconds;
	}


	/**
	 * 缓存中读取对象，取不到则从数据库中取得
	 * @param key
	 * @return
	 */
	public T get(KEY key) {
		T t=cacheDeal.get((Serializable)key,dbOperator);
		if(t==null){
			logger.warn("缓存取得失败：cacheName="+cacheDeal.getName()+" key="+key);
		}
		return t;
	}
	
	/**
	 * 缓存中读取对象，取不到则返回空
	 * @param key
	 * @return
	 */
	public T getFromCache(KEY key) {
		return cacheDeal.get((Serializable)key,null);
	}
	
	/**
	 * 设置缓存
	 * @param key
	 * @param value
	 */
	public void put(KEY key, T value) {
		put(key, value,timeToLiveSeconds);
	}
	
	/**
	 * 设置缓存
	 * @param key
	 * @param value
	 * @param exp 有效期：秒
	 */
	public void put(KEY key, T value, int exp) {
		cacheDeal.put((Serializable)key, (Serializable)value, exp);
	}

	/**
	 * 删除缓存
	 * @param key
	 */
	public void remove(KEY key) {
		cacheDeal.remove((Serializable)key);
	}
	
	/**
	 * 删除缓存
	 * @param key
	 */
	public void removeByStr(String key) {
		if("clearAll".equals(key)){
			cacheDeal.clear();
		}else{
			cacheDeal.remove(key);
		}
	}

	/**
	 * 清空缓存
	 */
	public void clear() {
		cacheDeal.clear();
	}
	
	/**
	 * 保存缓存到数据库
	 * @param key
	 */
	public void saveCache2Db(KEY key,T value) {
		cacheDeal.saveCache2Db(key,value, dbOperator);
	}
	
	/**
	 * 重新设置缓存数据
	 * @param key
	 * @param value
	 * @return
	 */
	public T reset(KEY key, T value) {
		return cacheDeal.reset(key, value, dbOperator);
	}
	
	/**
	 * 从数据库中重新加载
	 * @param key
	 * @return
	 */
	public T reload(KEY key) {
		return cacheDeal.reload(key, dbOperator);
	}
}