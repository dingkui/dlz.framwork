package com.dlz.framework.plugin.cache;

import org.slf4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.dlz.framework.cache.AbstractCache;

@Component
@Lazy
public class PluginStatusCache extends AbstractCache<String, Integer>{
	public PluginStatusCache() {
		super(PluginStatusCache.class.getSimpleName());
		dbOperator=new DbOperator() {
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
