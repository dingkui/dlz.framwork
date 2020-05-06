package com.dlz.framework.plugin.base;

import java.util.ArrayList;
import java.util.List;

import com.dlz.framework.plugin.inf.IPlugin;
import com.dlz.framework.plugin.inf.IPluginGroup;

public abstract class BasePluginGroup extends BasePluginBase implements IPluginGroup {
	protected List<IPlugin> plugins=new ArrayList<IPlugin>();

	@Override
	public List<String[]> getPluginInfo() {
		List<String[]> list=new ArrayList<String[]>();
		list.add(new String[]{this.getClass().getName(),getDescribtion(),String.valueOf(getStatus())});
		for(IPlugin plugin:plugins){
			list.add(new String[]{plugin.getClass().getName(),plugin.getDescribtion(),String.valueOf(plugin.getStatus())});
		}
		return list;
	}
	
	public void regeistPlugin(IPlugin plugin) {
		plugins.add(plugin);
		status=getStatus();
		if(status!=0){
			if(plugin.getStatus()==2){
				plugin.init();
			}
		}
	}
	

	@Override
	public List<IPlugin> getPlugins() {
		return plugins;
	}
	@Override
	public void stop() {
		for(IPlugin plugin:plugins){
			if(plugin.getStatus()==2){
				plugin.stop();
			}
		}
	}

	@Override
	public void start() {
		for(IPlugin plugin:plugins){
			if(plugin.getStatus()==1){
				plugin.start();
			}
		}
	}
	
	/**
	 * 未安装(=0)的全部安装
	 */
	@Override
	public void install() {
		for(IPlugin plugin:plugins){
			if(plugin.getStatus()==0){
				plugin.install();
			}
		}
		if(status==0){
			status=1;
			setStatus(status);
		}
	}

	/**
	 * 已结启动的全部停止
	 * 已结停止的全部卸载
	 */
	@Override
	public void uninstall() {
		stop();
		for(IPlugin plugin:plugins){
			plugin.uninstall();
		}
	}
}
