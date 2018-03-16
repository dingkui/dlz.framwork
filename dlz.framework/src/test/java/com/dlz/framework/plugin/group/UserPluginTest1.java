package com.dlz.framework.plugin.group;

import org.springframework.stereotype.Component;

import com.dlz.framework.plugin.IUserPlugin;
import com.dlz.framework.plugin.UserPluginPile;
import com.dlz.framework.plugin.base.BasePlugin;

@Component()
public class UserPluginTest1 extends BasePlugin<UserPluginPile,PluginGroup> implements IUserPlugin{
	

	public void beforeSave(int a){
		System.out.println("Test1Plugin.beforeSave"+a);
	}

	@Override
	public String getDescribtion() {
		return "测试1";
	}

}
