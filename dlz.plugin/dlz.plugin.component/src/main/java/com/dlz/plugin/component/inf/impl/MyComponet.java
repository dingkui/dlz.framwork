package com.dlz.plugin.component.inf.impl;

import com.dlz.plugin.component.inf.IMyComponet;
import com.dlz.plugin.component.inf.IComponetGroup;
import com.dlz.plugin.component.inf.IComponetPile;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class MyComponet<T extends IComponetPile, T2 extends IComponetGroup> extends ComponetBase implements IMyComponet {

	@Autowired
	T pile;
	
	T2 group;
	@Autowired
	public void setGroup(T2 group) {
		this.group = group;
		group.regeistPlugin(this);
	}
	@Override
	public Integer getPluginIndex(){
		return null;
	}
	
	/**
	 * 启用本插件
	 */
	public void start(){
		status=getStatus();
		if(status==1){
			pile.addPlugin2Pile(this,getPluginIndex());
			status=2;
			setStatus(status);
		}
	}
	@Override
	public void init() {
		if(status==2){
			pile.addPlugin2Pile(this,getPluginIndex());
		}
	}

	/**
	 * 停用本插件
	 */
	public void stop(){
		status=getStatus();
		if(status==2){
			pile.removePluginsFromPile(this);
			status=1;
			setStatus(status);
		}
	}
	
	/**
	 * 未安装的执行安装
	 */
	@Override
	public void install() {
		status=getStatus();
		if(status==-1||status==0){
			doInstall();
			status=1;
			setStatus(status);
		}
		start();
	}
	
	public void doInstall(){
		System.out.println(this.getClass()+" doInstall: do nothing");
	};
	public void doUnInstall(){
		System.out.println(this.getClass()+" doUnInstall: do nothing");
	};

	@Override
	public void uninstall() {
		stop();
		if(status==1){
			doUnInstall();
			status=-1;
			setStatus(status);
		}
	}
}
