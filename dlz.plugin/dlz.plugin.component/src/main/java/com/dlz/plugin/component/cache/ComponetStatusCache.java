package com.dlz.plugin.component.cache;

import com.dlz.framework.cache.AbstractCache;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class ComponetStatusCache extends AbstractCache<String, Integer>{
	public ComponetStatusCache() {
		super(ComponetStatusCache.class.getSimpleName());
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
