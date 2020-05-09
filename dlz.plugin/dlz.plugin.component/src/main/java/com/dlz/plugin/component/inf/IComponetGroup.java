package com.dlz.plugin.component.inf;

import java.util.List;

/**
 * 插件组接口
 * @author dingkui
 *
 * @param <T>
 */
public interface IComponetGroup extends IComponetBase {
	/**
	 * 插件启动注册到插件组
	 * @return
	 */
	void regeistPlugin(IMyComponet plugin);
	/**
	 * 取得插件状态信息
	 * @return
	 */
	List<String[]> getPluginInfo();
	/**
	 * 取得所有插件
	 * @return
	 */
	List<IMyComponet> getPlugins();
}
