package com.dlz.framework.db.modal;

import java.util.Map;

import com.dlz.framework.util.StringUtils;

public class ParaMap extends BaseParaMap{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static final long serialVersionUID = 8374167270612933157L;
	
	@SuppressWarnings("rawtypes")
	public ParaMap(String sql,Page page){
		super(sql, page);
	}
	public ParaMap(String sql){
		super(sql);
	}
	
	public ParaMap setLikePara(String paraName) {
		if(!StringUtils.isEmpty(super.getPara().get(paraName))){
			super.addPara(paraName, "%"+super.getPara().get(paraName)+"%");
		}
		return this;
	}
	public ParaMap addDefaultPara(String paraName,String defaultValue) {
		if(StringUtils.isEmpty(super.getPara().get(paraName))){
			super.addPara(paraName, defaultValue);
		}
		return this;
	}
	public ParaMap addRequestPara(Map<String,?> requestParaMap){
		for(String key:requestParaMap.keySet()){
			if(key.startsWith("search_")){
				String[] paras=(String[])requestParaMap.get(key);
				if(paras.length==1){
					addPara(key.substring("search_".length()), paras[0]);
				}else{
					addPara(key.substring("search_".length()), paras);
				}
			}
		}
		return this;
	}
}
