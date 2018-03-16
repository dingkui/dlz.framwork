package com.dlz.framework.plugin.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.framework.plugin.cache.PluginStatusCache;
import com.dlz.framework.plugin.inf.IPluginBase;

public abstract class BasePluginBase implements IPluginBase {
	String className=this.getClass().getName();
	@Autowired
	PluginStatusCache pluginStatusCache;
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
