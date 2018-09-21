package com.dlz.framework.plugin.cache;

import org.springframework.stereotype.Component;

import com.dlz.framework.cache.AbstractCache;
import org.slf4j.Logger;

@Component
public class PluginStatusCache extends AbstractCache<String, Integer>{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	protected final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
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
