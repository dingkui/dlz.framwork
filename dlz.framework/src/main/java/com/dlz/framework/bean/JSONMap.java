package com.dlz.framework.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

	public BigDecimal getBigDecimal(String key){
		return  getBigDecimal(key,null);
	}
	public BigDecimal getBigDecimal(String key,BigDecimal defaultV){
		return ValUtil.getBigDecimal(JacksonUtil.at(this,key),defaultV);
	}
	public Double getDouble(String key){
		return getDouble(key,null);
	}
	public Double getDouble(String key,Double defaultV){
		return ValUtil.getDouble(JacksonUtil.at(this,key),defaultV);
	}
	public Float getFloat(String key){
		return  getFloat(key,null);
	}
	public Float getFloat(String key,Float defaultV){
		return ValUtil.getFloat(JacksonUtil.at(this,key),defaultV);
	}
	public Integer getInt(String key){
		return  getInt(key,null);
	}
	public Integer getInt(String key,Integer defaultV){
		return ValUtil.getInt(JacksonUtil.at(this,key),defaultV);
	}
	public Long getLong(String key){
		return  getLong(key,null);
	}
	public Long getLong(String key,Long defaultV){
		return ValUtil.getLong(JacksonUtil.at(this,key),defaultV);
	}
	public Object[] getArray(String key){
		return  getArray(key,null);
	}
	public Object[] getArray(String key,Object[] defaultV){
		return ValUtil.getArray(JacksonUtil.at(this,key),defaultV);
	}
	public List getList(String key){
		return  getList(key,null);
	}
	public List getList(String key,List defaultV){
		return ValUtil.getList(JacksonUtil.at(this,key),defaultV);
	}
	public String getStr(String key){
		return  getStr(key,null);
	}
	public String getStr(String key,String defaultV){
		return ValUtil.getStr(JacksonUtil.at(this,key),defaultV);
	}
	public Boolean getBoolean(String key){
		return getBoolean(key,null);
	}
	public Boolean getBoolean(String key,Boolean defaultV){
		return ValUtil.getBoolean(JacksonUtil.at(this,key),defaultV);
	}
	public Date getDate(String key){
		return ValUtil.getDate(JacksonUtil.at(this,key));
	}
	public Date getDate(String key,String format){
		return ValUtil.getDate(JacksonUtil.at(this,key),format);
	}
	public String getDateStr(String key){
		return ValUtil.getDateStr(JacksonUtil.at(this,key));
	}
	public String getDateStr(String key,String format){
		return ValUtil.getDateStr(JacksonUtil.at(this,key),format);
	}
	public JSONMap getObj(String key){
		return getObj(key,JSONMap.class);
	}
	public <T> T getObj(String key,Class<T> classs){
		return ValUtil.getObj(JacksonUtil.at(this,key),classs);
	}
	public <T> T as(String key,Class<T> classs){
		return JacksonUtil.coverObj(this, classs);
	}
	public String toString(){
		return JacksonUtil.getJson(this);
	}
}