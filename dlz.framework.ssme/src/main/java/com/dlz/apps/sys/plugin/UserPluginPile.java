package com.dlz.apps.sys.plugin;

import org.springframework.stereotype.Component;

import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.plugin.base.BasePluginPile;

/**
 * 插件桩
 * 需要执行插件的地方添加插件桩，调用插件方法，如果插件已经存在并且已经启动则会自动运行
 * @author dingkui
 *
 */
@Component
public class UserPluginPile extends BasePluginPile<IUserPlugin> implements IUserPlugin{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	@Override
	public void afterGetUser(JSONResult result, Long userId) {
		for(IUserPlugin userPlugin:plugins){
			userPlugin.afterGetUser(result, userId);
		}
	}

	@Override
	public void afterSave(JSONResult result, String jsonPara) {
		for(IUserPlugin userPlugin:plugins){
			userPlugin.afterSave(result, jsonPara);;
		}
	}

	@Override
	public void beforeSave(Long userId) {
		for(IUserPlugin userPlugin:plugins){
			userPlugin.beforeSave(userId);
		}
	}
}
