package com.dlz.plgin.componet.group2;

import com.dlz.plugin.component.springframework.AnnoMyComponent;
import com.dlz.plgin.componet.IUserPlugin;
import com.dlz.plgin.componet.UserPluginPile;
import com.dlz.plugin.component.inf.impl.MyComponet;
import org.springframework.stereotype.Component;

@Component
@AnnoMyComponent
public class UserPluginTest22 extends MyComponet<UserPluginPile,PluginGroup2> implements IUserPlugin {
	
	public void beforeSave(int a){
		System.out.println("UserPluginTest22.beforeSave"+a);
	}

	@Override
	public String getDescribtion() {
		return "测试22";
	}
}
