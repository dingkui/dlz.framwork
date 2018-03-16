package com.dlz.framework.plugin.test;

import org.junit.Before;
import org.junit.Test;

import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.plugin.PluginInfo;
import com.dlz.framework.plugin.UserPluginPile;
import com.dlz.framework.plugin.group.PluginGroup;
import com.dlz.framework.util.JacksonUtil;

public class PluginTest{
	
	UserPluginPile userplugin;
	PluginGroup pluginGroup;
	
	@Before
	public void setUp() throws Exception {
		SpringHolder.init();
		userplugin=SpringHolder.getBean(UserPluginPile.class);
		pluginGroup=SpringHolder.getBean(PluginGroup.class);
	}
	
	@Test
	public void init(){
		userplugin.beforeSave(33);
		pluginGroup.stop();
		userplugin.beforeSave(44);
		System.out.println(JacksonUtil.getJson(PluginInfo.getPluginInfos()));
	}
}
