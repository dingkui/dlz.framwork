package com.dlz.plugin.component.inf.impl;

import com.dlz.plugin.component.cache.ComponetStatusCache;
import com.dlz.plugin.component.inf.IComponetBase;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ComponetBase implements IComponetBase {

	String className=this.getClass().getName();
	@Autowired
	ComponetStatusCache pluginStatusCache;
	public Integer status=null;
	@Override
	public Integer getStatus() {
		if(status==null){
			status=pluginStatusCache.get(className);
			if(status==null){
				status=0;
				pluginStatusCache.saveCache2Db(className, status);
			}
		}
		return status;
	}

	@Override
	public void setStatus(int status) {
		this.status=status;
		pluginStatusCache.saveCache2Db(className, status);
	}
}
