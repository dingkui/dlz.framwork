package com.dlz.plgin.componet.group;

import com.dlz.plugin.component.springframework.AnnoMyComponent;
import com.dlz.plgin.componet.IUserPlugin;
import com.dlz.plgin.componet.UserPluginPile;
import com.dlz.plugin.component.inf.impl.MyComponet;
import org.springframework.stereotype.Component;

@Component
@AnnoMyComponent
public class UserPluginTest2 extends MyComponet<UserPluginPile,PluginGroup> implements IUserPlugin {
	
	public void beforeSave(int a){
		System.out.println("Test2Plugin.beforeSave"+a);
	}

	@Override
	public String getDescribtion() {
		return "测试2";
	}
}
