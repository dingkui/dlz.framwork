package com.dlz.framework.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.plugin.base.BasePluginGroup;

public abstract class PluginInfo{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	public static List<List<String[]>> getPluginInfos(){
		List<List<String[]>> list=new ArrayList<List<String[]>>();
		Map<String, BasePluginGroup> beans = SpringHolder.getBeans(BasePluginGroup.class);
		for(BasePluginGroup g:beans.values()){
			list.add(g.getPluginInfo());
		}
		return list;
	}
}
