package com.dlz.framework.plugin;

import org.springframework.stereotype.Component;

import com.dlz.framework.plugin.base.BasePluginPile;

/**
 * 插件桩
 * 需要执行插件的地方添加插件桩，调用插件方法，如果插件已经存在并且已经启动则会自动运行
 * @author dingkui
 *
 */
@Component
public class UserPluginPile extends BasePluginPile<IUserPlugin> implements IUserPlugin{
	public void beforeSave(int a){
		System.out.println("\n\nUserPluginPile.beforeSave"+a);
		for(IUserPlugin userPlugin:plugins){
			userPlugin.beforeSave(a);
		}
	}
}
