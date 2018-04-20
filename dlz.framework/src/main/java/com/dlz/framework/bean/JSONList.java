package com.dlz.framework.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.dlz.framework.exception.CodeException;
import com.dlz.framework.exception.SystemException;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.ValUtil;

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
				addAll(JacksonUtil.readValue(string, JSONList.class));
			}else{
				throw new CodeException("参数不能转换成JSONMap:"+string);
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

	public <T> T at(String key,Class<T> classs){
		return JacksonUtil.at(this, key,classs);
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
	@SuppressWarnings("rawtypes")
	public List getList(String key){
		return  getList(key,null);
	}
	@SuppressWarnings("rawtypes")
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
	
	
	
	
	public BigDecimal getBigDecimal(int index){
		return  getBigDecimal(index,null);
	}
	public BigDecimal getBigDecimal(int index,BigDecimal defaultV){
		return ValUtil.getBigDecimal(get(index),defaultV);
	}
	public Double getDouble(int index){
		return getDouble(index,null);
	}
	public Double getDouble(int index,Double defaultV){
		return ValUtil.getDouble(get(index),defaultV);
	}
	public Float getFloat(int index){
		return  getFloat(index,null);
	}
	public Float getFloat(int index,Float defaultV){
		return ValUtil.getFloat(get(index),defaultV);
	}
	public Integer getInt(int index){
		return  getInt(index,null);
	}
	public Integer getInt(int index,Integer defaultV){
		return ValUtil.getInt(get(index),defaultV);
	}
	public Long getLong(int index){
		return  getLong(index,null);
	}
	public Long getLong(int index,Long defaultV){
		return ValUtil.getLong(get(index),defaultV);
	}
	public Object[] getArray(int index){
		return  getArray(index,null);
	}
	public Object[] getArray(int index,Object[] defaultV){
		return ValUtil.getArray(get(index),defaultV);
	}
	@SuppressWarnings("rawtypes")
	public List getList(int index){
		return  getList(index,null);
	}
	@SuppressWarnings("rawtypes")
	public List getList(int index,List defaultV){
		return ValUtil.getList(get(index),defaultV);
	}
	public String getStr(int index){
		return  getStr(index,null);
	}
	public String getStr(int index,String defaultV){
		return ValUtil.getStr(get(index),defaultV);
	}
	public Boolean getBoolean(int index){
		return getBoolean(index,null);
	}
	public Boolean getBoolean(int index,Boolean defaultV){
		return ValUtil.getBoolean(get(index),defaultV);
	}
	public Date getDate(int index){
		return ValUtil.getDate(get(index));
	}
	public Date getDate(int index,String format){
		return ValUtil.getDate(get(index),format);
	}
	public String getDateStr(int index){
		return ValUtil.getDateStr(get(index));
	}
	public String getDateStr(int index,String format){
		return ValUtil.getDateStr(get(index),format);
	}
	public JSONMap getObj(int index){
		return getObj(index,JSONMap.class);
	}
	public <T> T getObj(int index,Class<T> classs){
		return ValUtil.getObj(get(index),classs);
	}

	public String toString(){
		return JacksonUtil.getJson(this);
	}
}