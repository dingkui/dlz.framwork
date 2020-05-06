package com.dlz.framework.db.nosql.modal;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.dlz.comm.json.JSONMap;

public class Update extends NosqlFilterPara{

	private static final long serialVersionUID = 8374167270612933157L;
	private JSONMap data = new JSONMap();
	public Update addDatas(Map<String, Object> map) {
		if(map!=null){
			Set<Entry<String, Object>> entrySet = map.entrySet();
			for(Entry<String, Object> e:entrySet){
				addData(e.getKey(),e.getValue());
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
