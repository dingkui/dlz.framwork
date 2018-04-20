package com.dlz.framework.plugin.group;

import org.springframework.stereotype.Component;

import com.dlz.framework.plugin.base.BasePluginGroup;

/**
 * 插件组
 * 一个插件组定义一批插件，可以批量进行安装，卸载，启动，停止
 * @author dingkui
 *
 */
@Component
public class PluginGroup extends BasePluginGroup{
	@Override
	public String getDescribtion() {
		return "测试插件组";
	}
}
