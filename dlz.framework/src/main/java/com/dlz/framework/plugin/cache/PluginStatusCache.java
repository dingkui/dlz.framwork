package com.dlz.framework.plugin.cache;

import org.springframework.stereotype.Component;

import com.dlz.framework.cache.AbstractCache;
import com.dlz.framework.logger.MyLogger;

@Component
public class PluginStatusCache extends AbstractCache<String, Integer>{
	protected final MyLogger logger = MyLogger.getLogger(getClass());
	public PluginStatusCache() {
		super(PluginStatusCache.class.getSimpleName());
		dbOperator=new DbOperator<String, Integer>() {
			protected Integer getFromDb(String dicdCode) {
				//TODO
				return 2;
			} 
			protected void saveToDb(String key,Integer val) {
				//TODO
			} 
		};
	}
	
}
