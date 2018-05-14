package com.dlz.framework.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.ValUtil;

/**
 * JSONMap
 * @author dk 2017-06-15
 *
 */
@SuppressWarnings({"rawtypes"})
public class RestPara implements Serializable{
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
	public BigDecimal getBigDecimal(String key){
		return  getBigDecimal(key,null);
	}
	public BigDecimal getBigDecimal(String key,BigDecimal defaultV){
		return ValUtil.getBigDecimal(JacksonUtil.at(m,key),defaultV);
	}
	public Double getDouble(String key){
		return getDouble(key,null);
	}
	public Double getDouble(String key,Double defaultV){
		return ValUtil.getDouble(JacksonUtil.at(m,key),defaultV);
	}
	public Float getFloat(String key){
		return  getFloat(key,null);
	}
	public Float getFloat(String key,Float defaultV){
		return ValUtil.getFloat(JacksonUtil.at(m,key),defaultV);
	}
	public Integer getInt(String key){
		return  getInt(key,null);
	}
	public Integer getInt(String key,Integer defaultV){
		return ValUtil.getInt(JacksonUtil.at(m,key),defaultV);
	}
	public Long getLong(String key){
		return  getLong(key,null);
	}
	public Long getLong(String key,Long defaultV){
		return ValUtil.getLong(JacksonUtil.at(m,key),defaultV);
	}
	public Object[] getArray(String key){
		return  getArray(key,null);
	}
	public Object[] getArray(String key,Object[] defaultV){
		return ValUtil.getArray(JacksonUtil.at(m,key),defaultV);
	}
	public List getList(String key){
		return  getList(key,null);
	}
	public List getList(String key,List defaultV){
		return ValUtil.getList(JacksonUtil.at(m,key),defaultV);
	}
	public String getStr(String key){
		return  getStr(key,null);
	}
	public String getStr(String key,String defaultV){
		return ValUtil.getStr(JacksonUtil.at(m,key),defaultV);
	}
	public Boolean getBoolean(String key){
		return getBoolean(key,null);
	}
	public Boolean getBoolean(String key,Boolean defaultV){
		return ValUtil.getBoolean(JacksonUtil.at(m,key),defaultV);
	}
	public Date getDate(String key){
		return ValUtil.getDate(JacksonUtil.at(m,key));
	}
	public Date getDate(String key,String format){
		return ValUtil.getDate(JacksonUtil.at(m,key),format);
	}
	public String getDateStr(String key){
		return ValUtil.getDateStr(JacksonUtil.at(m,key));
	}
	public String getDateStr(String key,String format){
		return ValUtil.getDateStr(JacksonUtil.at(m,key),format);
	}
	public RestPara getObj(String key){
		return getObj(key,RestPara.class);
	}
	public <T> T getObj(String key,Class<T> classs){
		return ValUtil.getObj(JacksonUtil.at(m,key),classs);
	}
	public <T> T as(Class<T> classs){
		return JacksonUtil.readValue(JacksonUtil.getJson(this),classs);
	}
	public String toString(){
		return JacksonUtil.getJson(m);
	}
}