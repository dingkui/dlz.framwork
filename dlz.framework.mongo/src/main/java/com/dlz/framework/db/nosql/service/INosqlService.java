package com.dlz.framework.db.nosql.service;

import java.math.BigDecimal;
import java.util.List;

import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.nosql.modal.Delete;
import com.dlz.framework.db.nosql.modal.Find;
import com.dlz.framework.db.nosql.modal.Insert;
import com.dlz.framework.db.nosql.modal.Update;

/**
 * 从数据库中取得单条map类型数据：{adEnddate=2015-04-08 13:47:12.0}
 * @param sql sql语句，可以带参数如：select AD_ENDDATE from JOB_AD t where ad_id=#{ad_id}
 * @param paraMap ：Map<String,Object> m=new HashMap<String,Object>();m.put("ad_id", "47");
 * @return
 * @throws Exception
 */
public interface INosqlService {
	public int insert(Insert insert);
	public int update(Update paraMap);
	public int del(Delete paraMap);
	
	/**
	 * 从数据库中取得单个字段数据
	 */
	public Object getColum(Find paraMap);
	public String getStr(Find paraMap);
	public BigDecimal getBigDecimal(Find paraMap);
	public Float getFloat(Find paraMap);
	public Integer getInt(Find paraMap);
	public Long getLong(Find paraMap);
	
	public List<Object> getColumList(Find paraMap);
	public List<String> getStrList(Find paraMap);
	public List<BigDecimal> getBigDecimalList(Find paraMap);
	public List<Float> getFloatList(Find paraMap);
	public List<Integer> getIntList(Find paraMap);
	public List<Long> getLongList(Find paraMap);
	
	/**
	 * 从数据库中取得集合
	 */
	public ResultMap getMap(Find paraMap);
	public List<ResultMap> getMapList(Find paraMap);
	
	
	public <T> T getBean(Find paraMap, Class<T> t);
	public <T> List<T> getBeanList(Find paraMap, Class<T> t);
	
	/**
	 * 取得分页数据
	 * @return
	 * @throws Exception
	 */
	Page<ResultMap> getPage(Find paraMap);
	<T> Page<T> getPage(Find paraMap, Class<T> t);
	
	public int getCnt(Find paraMap);
}
