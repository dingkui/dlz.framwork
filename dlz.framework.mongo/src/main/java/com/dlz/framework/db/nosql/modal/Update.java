package com.dlz.framework.db.nosql.modal;

import java.util.Map;

import com.dlz.framework.bean.JSONMap;

public class Update extends NosqlFilterPara{
	private static final long serialVersionUID = 8374167270612933157L;
	private JSONMap data = new JSONMap();
	public Update addDatas(Map<String, Object> map) {
		for (String key : map.keySet()) {
			Object v=map.get(key);
			if(v instanceof String[]){
				String[] paras=(String[])map.get(key);
				if(paras.length==1){
					data.put(key, paras[0]);
				}else{
					data.put(key, paras);
				}
			}else{
				data.put(key, v);
			}
		}
		return this;
	}
	/**
	 * 添加参数
	 * @param key
	 * @param value
	 * @return
	 */
	public Update addData(String key,Object value){
		data.put(key, value==null?"":value);
		return this;
	}
	
	public JSONMap getData() {
		return data;
	}
	public Update(String key) {
		super(key);
	}
}
