package com.dlz.plgin.componet.group2;

import com.dlz.plugin.component.springframework.AnnoMyComponent;
import com.dlz.plgin.componet.IUserPlugin;
import com.dlz.plgin.componet.UserPluginPile;
import com.dlz.plugin.component.inf.impl.MyComponet;
import org.springframework.stereotype.Component;

/**
 * 插件逻辑
 * 实现插件桩接口
 * @author dingkui
 * UserPluginPile指定注入到的插件桩
 * PluginGroup 指定的插件组
 * IUserPlugin 插件的功能接口
 */
@Component
@AnnoMyComponent
public class UserPluginTest12 extends MyComponet<UserPluginPile,PluginGroup2> implements IUserPlugin {
	

	public void beforeSave(int a){
		System.out.println("UserPluginTest12.beforeSave"+a);
	}

	/**
	 * 定义插件名称用途等描述信息，主要用于在插件管理页面显示
	 */
	@Override
	public String getDescribtion() {
		return "测试12";
	}

}
