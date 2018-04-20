package com.dlz.framework.db.service;

import java.math.BigDecimal;
import java.util.List;

import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;


/**
 * 从数据库中取得单条map类型数据：{adEnddate=2015-04-08 13:47:12.0}
 * @param sql sql语句，可以带参数如：select AD_ENDDATE from JOB_AD t where ad_id=#{ad_id}
 * @param paraMap ：Map<String,Object> m=new HashMap<String,Object>();m.put("ad_id", "47");
 * @return
 * @throws Exception
 */
public interface ICommService {
	/**
	 * 更新或插入数据库
	 * @param sql sql语句，可以带参数如：update JOB_AD set AD_text=#{adText} where ad_id in (${ad_id})
	 * @param paraMap ：Map<String,Object> m=new HashMap<String,Object>();m.put("ad_id", "47");
	 * @return
	 * @throws Exception
	 */
	int excuteSql(BaseParaMap paraMap);
	
	/**
	 * 从数据库中取得单个字段数据
	 */
	public Object getColum(BaseParaMap paraMap);
	public String getStr(BaseParaMap paraMap);
	public BigDecimal getBigDecimal(BaseParaMap paraMap);
	public Float getFloat(BaseParaMap paraMap);
	public Integer getInt(BaseParaMap paraMap);
	public Long getLong(BaseParaMap paraMap);
	
	public List<Object> getColumList(BaseParaMap paraMap);
	public List<String> getStrList(BaseParaMap paraMap);
	public List<BigDecimal> getBigDecimalList(BaseParaMap paraMap);
	public List<Float> getFloatList(BaseParaMap paraMap);
	public List<Integer> getIntList(BaseParaMap paraMap);
	public List<Long> getLongList(BaseParaMap paraMap);
	
	/**
	 * 从数据库中取得集合
	 */
	public ResultMap getMap(BaseParaMap paraMap);
	public List<ResultMap> getMapList(BaseParaMap paraMap);
	
	
	public <T> T getBean(BaseParaMap paraMap, Class<T> t);
	public <T> List<T> getBeanList(BaseParaMap paraMap, Class<T> t);
	
	/**
	 * 取得分页数据
	 * @return
	 * @throws Exception
	 */
	Page<ResultMap> getPage(BaseParaMap paraMap);
	<T> Page<T> getPage(BaseParaMap paraMap, Class<T> t);
	
	int getCnt( BaseParaMap paraMap);
	
	long getSeqWithTime(String seqName);
	long getSeq(String seqName);
	long getSeq(Class<?> clazz); 
}
