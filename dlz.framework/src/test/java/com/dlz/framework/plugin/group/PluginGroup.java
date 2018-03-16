package com.dlz.framework.plugin.group;

import org.springframework.stereotype.Component;

import com.dlz.framework.plugin.base.BasePluginGroup;

@Component
public class PluginGroup extends BasePluginGroup{
//	private List<String> classes=new ArrayList<String>();
//	/**
//	 * 添加插件组中的插件
//	 */
//	public PluginGroup1(){
//		classes.add(Test2Plugin.class.getName());
//		classes.add(Test1Plugin.class.getName());
//	}
//
//	@Override
//	public List<String> getPluginClasses() {
//		return classes;
//	}

	@Override
	public String getDescribtion() {
		return "测试插件组";
	}
	
	public static void main(String[] args) {
		PluginGroup p=new PluginGroup();
		p.getStatus();
	}
}
