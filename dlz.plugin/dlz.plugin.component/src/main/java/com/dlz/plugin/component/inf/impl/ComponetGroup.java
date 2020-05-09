package com.dlz.plugin.component.inf.impl;

import com.dlz.plugin.component.inf.IMyComponet;
import com.dlz.plugin.component.inf.IComponetGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class ComponetGroup extends ComponetBase implements IComponetGroup {
	protected List<IMyComponet> plugins=new ArrayList<IMyComponet>();

	@Override
	public List<String[]> getPluginInfo() {
		List<String[]> list=new ArrayList<String[]>();
		list.add(new String[]{this.getClass().getName(),getDescribtion(),String.valueOf(getStatus())});
		for(IMyComponet plugin:plugins){
			list.add(new String[]{plugin.getClass().getName(),plugin.getDescribtion(),String.valueOf(plugin.getStatus())});
		}
		return list;
	}
	
	public void regeistPlugin(IMyComponet plugin) {
		plugins.add(plugin);
		status=getStatus();
		if(status!=0){
			if(plugin.getStatus()==2){
				plugin.init();
			}
		}
	}
	

	@Override
	public List<IMyComponet> getPlugins() {
		return plugins;
	}
	@Override
	public void stop() {
		for(IMyComponet plugin:plugins){
			if(plugin.getStatus()==2){
				plugin.stop();
			}
		}
	}

	@Override
	public void start() {
		for(IMyComponet plugin:plugins){
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
		for(IMyComponet plugin:plugins){
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
		for(IMyComponet plugin:plugins){
			plugin.uninstall();
		}
	}
}
