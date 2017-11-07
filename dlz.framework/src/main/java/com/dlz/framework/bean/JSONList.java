package com.dlz.framework.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.dlz.framework.exception.SystemException;
import com.dlz.framework.util.JacksonUtil;

/**
 * JSONList
 * @author dk 2017-09-05
 *
 */
public class JSONList extends ArrayList<Object>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7554800764909179290L;
	
	public JSONList(){
		super();
	}
	@SuppressWarnings({ "rawtypes" })
	public JSONList(Object obj){
		super();
		if(obj==null){
			return;
		}
		if(obj instanceof String){
			addAll(JacksonUtil.readValue((String)obj, JSONList.class));
		}else if(obj instanceof Collection){
			for(Object ci:(Collection)obj){
				add(new JSONMap(ci));
			}
		}else if(obj instanceof Object[]){
			for(Object ci:(Object[] )obj){
				add(new JSONMap(ci));
			}
		}else if(obj instanceof Serializable){
			addAll(JacksonUtil.coverObj(obj, JSONList.class));
		}
	}
	
	public static JSONList createJsonList(Object json){
		return new JSONList(json);
	}
	
	public JSONMap getMap(int index){
		Object o=get(index);
		if(o instanceof JSONMap){
			return (JSONMap)o;
		}
		if(o instanceof Number){
			throw new SystemException("对象是简单类型【"+o.getClass().getName()+"】，不能转换为JSONMap");
		}
		if(o instanceof CharSequence){
			if(((CharSequence)o).charAt(0)=='{'){
				return JacksonUtil.readValue(o.toString(), JSONMap.class);
			}
			throw new SystemException("对象是简单类型【"+o.getClass().getName()+"】，不能转换为JSONMap");
		}
		return JacksonUtil.coverObj(o, JSONMap.class);
	}

	public String toString(){
		return JacksonUtil.getJson(this);
	}
}