package com.dlz.plugin.component.inf;

/**
 * 插件组，插件桩，插件都有的基本信息
 * 
 * @author dingkui
 *
 * @param <T>
 */
public interface IComponetBase {
	/**
	 * 取得插件功能描述
	 * 
	 * @return
	 */
	String getDescribtion();
	
	/**
	 * 取得插件组状态
	 * -1:已卸载
	 * 0:未安装
	 * 1:未启用
	 * 2:已启用
	 */
	Integer getStatus();
	/**
	 * 取得插件组状态
	 * -1:已卸载
	 * 0:未安装
	 * 1:未启用
	 * 2:已启用
	 */
	void setStatus(int statu);

	/**
	 * 安装本插件
	 */
	void install();

	/**
	 * 卸载本插件
	 */
	void uninstall();
	
	/**
	 * 启用本插件
	 */
	void start();

	/**
	 * 停用本插件
	 */
	void stop();
}
