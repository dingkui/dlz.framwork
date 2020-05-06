package com.dlz.framework.plugin.inf;

/**
 * 插件接口
 * 
 * @author dingkui
 *
 * @param <T>
 */
public interface IPlugin extends IPluginBase{
	public Integer getPluginIndex();
	public void init();
}
