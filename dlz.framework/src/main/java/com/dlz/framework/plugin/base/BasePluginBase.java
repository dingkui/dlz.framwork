package com.dlz.framework.plugin.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.framework.plugin.cache.PluginStatusCache;
import com.dlz.framework.plugin.inf.IPluginBase;
import com.dlz.framework.util.StringUtils;

public abstract class BasePluginBase implements IPluginBase {
	@Autowired
	PluginStatusCache pluginStatusCache;
	public Integer status=null;
	@Override
	public Integer getStatus() {
		if(status==null){
			String beanId = StringUtils.getBeanId(this.getClass().getName());
			status=pluginStatusCache.get(beanId);
			if(status==null){
				status=0;
				pluginStatusCache.saveCache2Db(beanId, status);
			}
		}
		return status;
	}

	@Override
	public void setStatus(int status) {
		this.status=status;
		pluginStatusCache.saveCache2Db(StringUtils.getBeanId(this.getClass().getName()), status);
	}
}
