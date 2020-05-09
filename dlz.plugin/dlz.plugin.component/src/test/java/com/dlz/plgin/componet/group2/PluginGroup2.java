package com.dlz.plgin.componet.group2;

import com.dlz.plugin.component.inf.impl.ComponetGroup;
import org.springframework.stereotype.Component;

/**
 * 插件组
 * 一个插件组定义一批插件，可以批量进行安装，卸载，启动，停止
 * @author dingkui
 *
 */
@Component
public class PluginGroup2 extends ComponetGroup {
	@Override
	public String getDescribtion() {
		return "测试插件组2";
	}
}
