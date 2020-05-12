package com.dlz.framework.db.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;


/**
 * 从数据库中取得单条map类型数据：{adEnddate=2015-04-08 13:47:12.0}
 * sql语句，可以带参数如：select AD_ENDDATE from JOB_AD t where ad_id=#{ad_id}
 * paraMap ：Map<String,Object> m=new HashMap<String,Object>();m.put("ad_id", "47");
 * @return
 * @throws Exception
 */
public interface ICommService {
	/**
	 * 更新或插入数据库
	 * sql语句，可以带参数如：update JOB_AD set AD_text=#{adText} where ad_id in (${ad_id})
	 * @param paraMap ：Map<String,Object> m=new HashMap<String,Object>();m.put("ad_id", "47");
	 * @return
	 * @throws Exception
	 */
	int excuteSql(BaseParaMap paraMap);
	
	/**
	 * 从数据库中取得单个字段数据
	 */
	Object getColum(BaseParaMap paraMap);
	String getStr(BaseParaMap paraMap);
	BigDecimal getBigDecimal(BaseParaMap paraMap);
	Float getFloat(BaseParaMap paraMap);
	Integer getInt(BaseParaMap paraMap);
	Long getLong(BaseParaMap paraMap);
	
	List<Object> getColumList(BaseParaMap paraMap);
	List<String> getStrList(BaseParaMap paraMap);
	List<BigDecimal> getBigDecimalList(BaseParaMap paraMap);
	List<Float> getFloatList(BaseParaMap paraMap);
	List<Integer> getIntList(BaseParaMap paraMap);
	List<Long> getLongList(BaseParaMap paraMap);
	
	/**
	 * 从数据库中取得集合
	 */
	default ResultMap getMap(BaseParaMap paraMap){
		return getMap(paraMap,true);
	}
	ResultMap getMap(BaseParaMap paraMap, boolean throwEx);
	List<ResultMap> getMapList(BaseParaMap paraMap);
	
	
	<T> T getBean(BaseParaMap paraMap, Class<T> t, boolean throwEx);
	default <T> T getBean(BaseParaMap paraMap, Class<T> t){
		return getBean(paraMap, t, true);
	}
	<T> List<T> getBeanList(BaseParaMap paraMap, Class<T> t);
	
	/**
	 * 取得分页数据
	 * @return
	 * @throws Exception
	 */
	Page<ResultMap> getPage(BaseParaMap paraMap);
	<T> Page<T> getPage(BaseParaMap paraMap, Class<T> t);
	int getCnt( BaseParaMap paraMap);
	
	
	
	
	/**
	 * 新的一套操作api,用于比较简单的sql,直接用问号传参
	 * @param sql sql语句，可以用问号传参数如：update JOB_AD set AD_text=? where ad_id = ? 
	 * @param para ：参数数组
	 */
	int excuteSql(String sql, Object ... para);
	Object getColum(String sql, Object ... para);
	String getStr(String sql, Object ... para);
	BigDecimal getBigDecimal(String sql, Object ... para);
	Float getFloat(String sql, Object ... para);
	Integer getInt(String sql, Object ... para);
	Long getLong(String sql, Object ... para);
	List<Object> getColumList(String sql, Object ... para);
	List<String> getStrList(String sql, Object ... para);
	List<BigDecimal> getBigDecimalList(String sql, Object ... para);
	List<Float> getFloatList(String sql, Object ... para);
	List<Integer> getIntList(String sql, Object ... para);
	List<Long> getLongList(String sql, Object ... para);
	ResultMap getMap(String sql, boolean throwEx, Object ... para);
	default ResultMap getMap(String sql, Object ... para){
		return getMap(sql,true ,para);
	}
	List<ResultMap> getMapList(String sql, Object ... para);
	<T> T getBean(String sql, Class<T> t, Object ... para);
	<T> List<T> getBeanList(String sql, Class<T> t, Object ... para);
	int getCnt(String sql, Object ... para);
	<T> Page<T> getPage(String sql, Class<T> t,int pageSize,int pageIndex, Object ... para);
	Page<ResultMap> getPage(String sql, int pageSize,int pageIndex, Object ... para);
	
	long getSeq(String seqName);
	long getSeq(Class<?> clazz);

	int update(String tableName, Object bean, String where);
	int insert(String tableName, Object bean);
}
