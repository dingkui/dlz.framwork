package com.dlz.framework.cache;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.exception.CodeException;
import com.dlz.framework.holder.SpringHolder;

/**
 * 缓存实现
 * 
 * @author dk
 *
 */
@SuppressWarnings("unchecked")
public abstract class AbstractCache<KEY,T>{
	void doNothing1(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger logger = LoggerFactory.getLogger(AbstractCache.class);

	private static Set<String> CacheSet = new HashSet<String>();
	
	@Autowired
	public ICommService commService;
	@Autowired
	private ICacheCreator cacheCreator;
	
	@SuppressWarnings("rawtypes")
	public static void clearAll(){
		for(String caches:CacheSet){
			((AbstractCache)SpringHolder.getBean(caches)).clear();
		}
	}
	
	public class DbOperator{
		protected T getFromDb(KEY key){return null;}
		protected void saveToDb(KEY key,T t){}
	}
	public interface ICacheDeal {
		public Serializable get(Serializable key);
		public void put(Serializable key, Serializable value, int exp);
		public void remove(Serializable key);
		public void removeAll();
	}
	private ICacheDeal cache=null;
	protected DbOperator dbOperator=null;
	private int timeToLiveSeconds=-1;
	private String cacheName=null;
	
	public AbstractCache(String cacheName,int timeToLiveSeconds) { 
		try {
			cacheName=cacheName.substring(0, 1).toLowerCase()+cacheName.substring(1).toLowerCase();
			this.cacheName=cacheName;
			this.timeToLiveSeconds=timeToLiveSeconds;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	public AbstractCache(String cacheName) { 
		this(cacheName,-1);
	}
	
	@PostConstruct
	private void _init(){
		if(CacheSet.contains(cacheName)){
			throw new CodeException("缓存已经存在，不能重复定义："+cacheName);
		}
		CacheSet.add(cacheName);
		cache=cacheCreator.createCaheDeal(cacheName);
	}
	
	
	private T readDb(KEY key){
		if(dbOperator==null){
			return null;
		}
		T t= null;
		try{
			t=dbOperator.getFromDb(key);
		}catch(Exception e){
			logger.error("从数据库加载缓存失败："+getCacheName()+"."+key,e);
		}
		return t;
	}
	/**
	 * 保存缓存到数据库
	 * @param key
	 */
	public void saveCache2Db(KEY key,T value) {
		if(dbOperator==null){
			return;
		}
		if(value==null){
			if(key==null){
				logger.error("缓存保存失败：key或value必须设置一个！"+getCacheName());
				return;
			}
			value=getFromCache(key);
		}
		if(value!=null){
			try{
				dbOperator.saveToDb(key,value);
			}catch(Exception e){
				logger.error("保存缓存到数据库失败："+getCacheName());
				logger.error(e.getMessage(),e);
			}
		}
	}

	/**
	 * 缓存中读取对象，取不到则从数据库中取得
	 * @param key
	 * @return
	 */
	public T get(KEY key) {
		T t=null;
		try {
			if (key != null) {
				t = getFromCache(key);
				if(t==null){
					t=readDb(key);
					if(t!=null){
						put(key,t,timeToLiveSeconds);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return t;
	}
	
	/**
	 * 缓存中读取对象，取不到则返回空
	 * @param key
	 * @return
	 */
	public T getFromCache(KEY key) {
		return (T)cache.get((Serializable)key);
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
		try {
			cache.put((Serializable)key, (Serializable)value, exp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 删除缓存
	 * @param key
	 */
	public void remove(KEY key) {
		try {
			cache.remove((Serializable)key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 删除缓存
	 * @param key
	 */
	public void removeByStr(String key) {
		if("clearAll".equals(key)){
			cache.removeAll();
		}else{
			cache.remove(key);
		}
	}

	/**
	 * 清空缓存
	 */
	public void clear() {
		cache.removeAll();
	}
	
	/**
	 * 取得当前缓存的名称
	 */
	public String getCacheName() {
		return this.cacheName;
	}
	
	/**
	 * 从数据库中重新加载
	 * @param key
	 * @return
	 */
	public T reload(KEY key) {
		T t=readDb(key);
		if(t==null){
			logger.error("缓存刷新失败：cacheName="+getCacheName()+" key="+key);
		}else{
			put(key, t,timeToLiveSeconds);
		}
		return t;
	}
}