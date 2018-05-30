package com.dlz.apps.sys.plugin;

import com.dlz.framework.bean.JSONResult;

/**
 * 插件功能接口
 * 定义插件桩和插件的方法
 * @author dingkui
 *
 */
public interface IUserPlugin{
	public void afterGetUser(JSONResult result,Long userId);
	public void afterSave(JSONResult result,String jsonPara);
	public void beforeSave(Long userId);
}
