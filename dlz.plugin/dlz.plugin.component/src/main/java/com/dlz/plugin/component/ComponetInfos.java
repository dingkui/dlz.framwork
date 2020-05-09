package com.dlz.plugin.component;

import com.dlz.framework.holder.SpringHolder;
import com.dlz.plugin.component.inf.impl.ComponetGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ComponetInfos {
	public static List<List<String[]>> getPluginInfos(){
		List<List<String[]>> list=new ArrayList<List<String[]>>();
		Map<String, ComponetGroup> beans = SpringHolder.getBeans(ComponetGroup.class);
		for(ComponetGroup g:beans.values()){
			list.add(g.getPluginInfo());
		}
		return list;
	}
}
