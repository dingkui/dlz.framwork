package com.dlz.framework.ssme.base.cache;

import java.util.List;

import com.dlz.framework.logger.MyLogger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CacheCenter { 
	
	/**
	 * 日志 logger
	 */
	private static MyLogger logger = MyLogger.getLogger(CacheCenter.class);

	/**
	 * 注入BeanFactory
	 */
	@Autowired
	private BeanFactory beanFactory = null;

	/**
	 * Spring注入所有Cache类字节码
	 */
	private List<Class<BaseAbstractCache<?, ?>>> cacheClassList = null;

	/**
	 * @param cacheClassList
	 *            参数 cacheClassList
	 */
	public void setCacheClassList(List<Class<BaseAbstractCache<?, ?>>> cacheClassList) {
		this.cacheClassList = cacheClassList;
	}

	/**
	 * @return the cacheClassList
	 */
	public List<Class<BaseAbstractCache<?, ?>>> getCacheClassList() {
		return cacheClassList;
	}

	/**
	 * 初始化所有缓存
	 */
	public void loadCache() {
		logger.debug("------------ loadCache ------------");
		// 添加缓存Class
		for (Class<BaseAbstractCache<?, ?>> cacheClass : cacheClassList) {
			BaseAbstractCache<?, ?> cacheBean = beanFactory.getBean(cacheClass);
			try{
				cacheBean.loadCache();
			}catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
		logger.debug("------------ loadCache finish ------------");
	}

}
