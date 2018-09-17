package com.dlz.framework.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.dlz.framework.exception.CodeException;
import com.dlz.framework.exception.SystemException;
import com.dlz.framework.util.JacksonUtil;

/**
 * JSONList
 * @author dk 2017-09-05
 *
 */
public class JSONList extends ArrayList<Object> implements IUniversalVals,IUniversalVals4List{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7554800764909179290L;
	
	public JSONList(){
		super();
	}
	public JSONList(Object obj){
		this(obj,JSONMap.class);
	}
	@SuppressWarnings({ "rawtypes" })
	public <T> JSONList(Object obj,Class<T> objectClass){
		super();
		if(obj==null){
			return;
		}
		if(obj instanceof Collection){
			for(Object ci:(Collection)obj){
				add(ci);
			}
		}else if(obj instanceof Object[]){
			for(Object ci:(Object[])obj){
				add(ci);
			}
		}else {
			String string=null;
			if(obj instanceof CharSequence){
				string=obj.toString().trim();
			}else{
				string=JacksonUtil.getJson(obj);
			}
			if(string==null){
				return;
			}
			if(string.startsWith("[") && string.endsWith("]")){
				addAll(JacksonUtil.readListValue(string, objectClass));
			}else{
				throw new CodeException("参数不能转换成JSONList:"+string);
			}
		}
	}
	
	public JSONList adds(Object obj){
		add(obj);
		return this;
	}
	
	public static JSONList createJsonList(Object json){
		return new JSONList(json);
	}
	
	@SuppressWarnings({"unchecked" })
	public <T> List<T> asList(Class<T> objectClass){
		return (List<T>)(Object)this;
	}
	@SuppressWarnings({"unchecked" })
	public List<JSONMap> asList(){
		return (List<JSONMap>)(Object)this;
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
		return new JSONMap(o);
	}

	public JSONMap getObj(int index){
		return getObj(index,JSONMap.class);
	}

	public String toString(){
		return JacksonUtil.getJson(this);
	}
	@Override
	public Object getIndexObject(int index) {
		return get(index);
	}
	@Override
	public Object getInfoObject() {
		return this;
	}
}