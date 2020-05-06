package com.dlz.framework.plugin.group;

import org.springframework.stereotype.Component;

import com.dlz.framework.plugin.IUserPlugin;
import com.dlz.framework.plugin.UserPluginPile;
import com.dlz.framework.plugin.base.BasePlugin;

/**
 * 插件逻辑
 * 实现插件桩接口
 * @author dingkui
 * UserPluginPile指定注入到的插件桩
 * PluginGroup 指定的插件组
 * IUserPlugin 插件的功能接口
 */
@Component
public class UserPluginTest1 extends BasePlugin<UserPluginPile,PluginGroup> implements IUserPlugin{
	

	public void beforeSave(int a){
		System.out.println("Test1Plugin.beforeSave"+a);
	}

	/**
	 * 定义插件名称用途等描述信息，主要用于在插件管理页面显示
	 */
	@Override
	public String getDescribtion() {
		return "测试1";
	}

}
