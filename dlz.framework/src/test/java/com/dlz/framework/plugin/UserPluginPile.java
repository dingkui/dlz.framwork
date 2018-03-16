package com.dlz.framework.plugin;

import org.springframework.stereotype.Component;

import com.dlz.framework.plugin.base.BasePluginPile;

@Component
public class UserPluginPile extends BasePluginPile<IUserPlugin> implements IUserPlugin{
	public void beforeSave(int a){
		System.out.println("\n\nUserPluginPile.beforeSave"+a);
		for(IUserPlugin userPlugin:plugins){
			userPlugin.beforeSave(a);
		}
	}
}
