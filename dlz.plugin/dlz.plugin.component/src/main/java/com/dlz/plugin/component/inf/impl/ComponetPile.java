package com.dlz.plugin.component.inf.impl;

import com.dlz.plugin.component.inf.IComponetPile;

import java.util.ArrayList;
import java.util.List;

public class ComponetPile<T> implements IComponetPile<T> {
	protected List<T> plugins=new ArrayList<T>();
	/**
	 * 添加到插件桩
	 * @param t
	 */
	public void addPlugin2Pile(T t){
		plugins.add(t);
	}
	/**
	 * 添加到插件桩
	 * @param t
	 */
	public void addPlugin2Pile(T t,Integer index) {
		if(index==null){
			plugins.add(t);
		}else{
			plugins.add(index, t);
		}
	}
	/**
	 * 取得本插件桩中所有插件
	 * @param t
	 */
	public List<T> getPilePlugins(){
		return plugins;
	}
	/**
	 * 从得本插件桩中移除插件
	 * @param t
	 */
	public void removePluginsFromPile(T t) {
		plugins.remove(t);
	}
}
