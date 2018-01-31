package com.dlz.framework.db.service;

import java.math.BigDecimal;
import java.util.List;

import com.dlz.framework.db.modal.NosqlParaMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;


/**
 * 从数据库中取得单条map类型数据：{adEnddate=2015-04-08 13:47:12.0}
 * @param sql sql语句，可以带参数如：select AD_ENDDATE from JOB_AD t where ad_id=#{ad_id}
 * @param paraMap ：Map<String,Object> m=new HashMap<String,Object>();m.put("ad_id", "47");
 * @return
 * @throws Exception
 */
public interface INosqlService {
	/**
	 * 更新或插入数据库
	 * @param sql sql语句，可以带参数如：update JOB_AD set AD_text=#{adText} where ad_id in (${ad_id})
	 * @param paraMap ：Map<String,Object> m=new HashMap<String,Object>();m.put("ad_id", "47");
	 * @return
	 * @throws Exception
	 */
	int excuteSql(NosqlParaMap paraMap);
	
	/**
	 * 从数据库中取得单个字段数据
	 */
	public Object getColum(NosqlParaMap paraMap);
	public String getStr(NosqlParaMap paraMap);
	public BigDecimal getBigDecimal(NosqlParaMap paraMap);
	public Float getFloat(NosqlParaMap paraMap);
	public Integer getInt(NosqlParaMap paraMap);
	public Long getLong(NosqlParaMap paraMap);
	
	public List<Object> getColumList(NosqlParaMap paraMap);
	public List<String> getStrList(NosqlParaMap paraMap);
	public List<BigDecimal> getBigDecimalList(NosqlParaMap paraMap);
	public List<Float> getFloatList(NosqlParaMap paraMap);
	public List<Integer> getIntList(NosqlParaMap paraMap);
	public List<Long> getLongList(NosqlParaMap paraMap);
	
	/**
	 * 从数据库中取得集合
	 */
	public ResultMap getMap(NosqlParaMap paraMap);
	public List<ResultMap> getMapList(NosqlParaMap paraMap);
	
	
	public <T> T getBean(NosqlParaMap paraMap, Class<T> t);
	public <T> List<T> getBeanList(NosqlParaMap paraMap, Class<T> t);
	
	/**
	 * 取得分页数据
	 * @return
	 * @throws Exception
	 */
	Page<ResultMap> getPage(NosqlParaMap paraMap);
	<T> Page<T> getPage(NosqlParaMap paraMap, Class<T> t);
	
	int getCnt(NosqlParaMap paraMap);
	
	long getSeq(String seqName);
}
