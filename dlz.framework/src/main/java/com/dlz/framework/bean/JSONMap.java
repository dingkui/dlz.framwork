package com.dlz.framework.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.StringUtils;
import com.dlz.framework.util.ValUtil;

/**
 * JSONMap
 * @author dk 2017-06-15
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class JSONMap extends HashMap<String,Object>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7554800764909179290L;
	
	public JSONMap(){
		super();
	}
	public JSONMap(Object obj){
		super();
		if(obj==null){
			return;
		}
		if(obj instanceof String){
			putAll(JacksonUtil.readValue((String)obj, JSONMap.class));
		}else if(obj instanceof Map){
			putAll((Map)obj);
		}else if(obj instanceof Object[] || obj instanceof Collection){
			
		}else if(obj instanceof Serializable){
			putAll(JacksonUtil.coverObj(obj, JSONMap.class));
		}
	}
	public static JSONMap createJsonMap(Object json){
		return new JSONMap(json);
	}
	public BigDecimal getBigDecimal(String key,BigDecimal defaultV){
		return ValUtil.getBigDecimal(JacksonUtil.at(this,key),defaultV);
	}
	public BigDecimal getBigDecimal(String key){
		return ValUtil.getBigDecimal(JacksonUtil.at(this,key),null);
	}
	public JSONMap clearEmptyProp(){
		List<String> emputyKeys=new ArrayList<String>();
		for(Entry<String,Object> entry:this.entrySet()){
			if(StringUtils.isEmpty(entry.getValue())){
				emputyKeys.add(entry.getKey());
			}
		}
		for(String key:emputyKeys){
			this.remove(key);
		}
		return this;
	}
	
	public Double getDouble(String key){
		return ValUtil.getDouble(JacksonUtil.at(this,key),null);
	}
	public Double getDouble(String key,Double defaultV){
		return ValUtil.getDouble(JacksonUtil.at(this,key),defaultV);
	}
	public Float getFloat(String key){
		return ValUtil.getFloat(JacksonUtil.at(this,key),null);
	}
	public Float getFloat(String key,Float defaultV){
		return ValUtil.getFloat(JacksonUtil.at(this,key),defaultV);
	}
	public Integer getInt(String key){
		return ValUtil.getInt(JacksonUtil.at(this,key),null);
	}
	public Integer getInt(String key,Integer defaultV){
		return ValUtil.getInt(JacksonUtil.at(this,key),null);
	}
	public Long getLong(String key){
		return ValUtil.getLong(JacksonUtil.at(this,key),null);
	}
	public Long getLong(String key,Long defaultV){
		return ValUtil.getLong(JacksonUtil.at(this,key),defaultV);
	}
	public Object[] getArray(String key){
		return ValUtil.getArray(JacksonUtil.at(this,key),null);
	}
	public Object[] getArray(String key,Object[] defaultV){
		return ValUtil.getArray(JacksonUtil.at(this,key),defaultV);
	}
	public List getList(String key){
		return ValUtil.getList(JacksonUtil.at(this,key),null);
	}
	public List getList(String key,List defaultV){
		return ValUtil.getList(JacksonUtil.at(this,key),defaultV);
	}
	public String getStr(String key){
		return ValUtil.getStr(JacksonUtil.at(this,key),null);
	}
	public String getStr(String key,String defaultV){
		return ValUtil.getStr(JacksonUtil.at(this,key),defaultV);
	}
	public Boolean getBoolean(String key){
		return ValUtil.getBoolean(JacksonUtil.at(this,key),null);
	}
	public Boolean getBoolean(String key,Boolean defaultV){
		return ValUtil.getBoolean(JacksonUtil.at(this,key),defaultV);
	}
	public JSONMap getObj(String key){
		return getObj(key,JSONMap.class);
	}
	public <T> T getObj(String key,Class<T> classs){
		return ValUtil.getObj(JacksonUtil.at(this,key),classs);
	}
	public String toString(){
		return JacksonUtil.getJson(this);
	}
}