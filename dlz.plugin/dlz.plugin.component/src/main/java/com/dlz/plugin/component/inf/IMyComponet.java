package com.dlz.plugin.component.inf;

/**
 * 插件接口
 * 
 * @author dingkui
 *
 * @param <T>
 */
public interface IMyComponet extends IComponetBase {
	Integer getPluginIndex();
	void init();
}
