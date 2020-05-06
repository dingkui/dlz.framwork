package com.dlz.framework.plugin.group2;

import org.springframework.stereotype.Component;

import com.dlz.framework.plugin.IUserPlugin;
import com.dlz.framework.plugin.UserPluginPile;
import com.dlz.framework.plugin.base.BasePlugin;

@Component
public class UserPluginTest22 extends BasePlugin<UserPluginPile,PluginGroup2> implements IUserPlugin{
	
	public void beforeSave(int a){
		System.out.println("UserPluginTest22.beforeSave"+a);
	}

	@Override
	public String getDescribtion() {
		return "测试22";
	}
}
