package com.dlz.framework.bean;

import com.dlz.comm.json.IUniversalVals;
import com.dlz.comm.json.JSONMap;
import com.dlz.comm.util.JacksonUtil;

import java.io.Serializable;

/**
 * JSONMap
 * @author dk 2017-06-15
 *
 */
public class RestPara implements IUniversalVals,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7554800764909179290L;
	private JSONMap m;
	
	public JSONMap getJSONMap(){
		return m;
	}
	
	public RestPara(){
		m=new JSONMap();
	}
	public RestPara(Object obj){
		m=new JSONMap(obj);
	}
	public static RestPara createJsonMap(Object json){
		return new RestPara(json);
	}
	public RestPara clearEmptyProp(){
		m.clearEmptyProp();
		return this;
	}
	/**
	 * 
	 * @param key
	 * @param obj
	 * @param joinMethod 
	 * 		0:替换原有信息
	 * 		1：加入到原有数组中
	 * 		2：合并到原有数组中
	 * 		3：把原有数据跟新数据构造新数组 
	 * @return
	 */
	public RestPara add(String key,Object obj,int joinMethod){
		m.add(key, obj, joinMethod);
		return this;
	}
	public RestPara add(String key,Object obj){
		return add(key, obj, 3);
	}
	
	public RestPara getRestObj(String key){
		return getObj(key,RestPara.class);
	}
	@Override
	public String toString(){
		return JacksonUtil.getJson(m);
	}
	@Override
	public Object getInfoObject() {
		return m;
	}
}