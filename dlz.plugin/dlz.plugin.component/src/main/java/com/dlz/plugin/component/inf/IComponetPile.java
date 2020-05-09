package com.dlz.plugin.component.inf;

import java.util.List;

/**
 * 插件桩接口
 * @author dingkui
 *
 * @param <T>
 */
public interface IComponetPile<T> {
	/**
	 * 添加到插件桩
	 * @param t
	 */
	 void addPlugin2Pile(T t, Integer index);
	/**
	 * 取得本插件桩中所有插件
	 * @param t
	 */
	 List<T> getPilePlugins();
	/**
	 * 从得本插件桩中移除插件
	 * @param t
	 */
	 void removePluginsFromPile(T t);
}
