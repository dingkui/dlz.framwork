package com.dlz.framework.db.service;

import com.dlz.comm.util.ValUtil;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;

import java.math.BigDecimal;
import java.util.List;


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
	default String getStr(BaseParaMap paraMap){
		return ValUtil.getStr(getColum( paraMap));
	}
	default BigDecimal getBigDecimal(BaseParaMap paraMap){
		return ValUtil.getBigDecimal(getColum( paraMap));
	}
	default Float getFloat(BaseParaMap paraMap){
		return ValUtil.getFloat(getColum( paraMap));
	}
	default Integer getInt(BaseParaMap paraMap){
		return ValUtil.getInt(getColum( paraMap));
	}
	default Long getLong(BaseParaMap paraMap){
		return ValUtil.getLong(getColum( paraMap));
	}
	
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
	int getCnt(BaseParaMap paraMap);




	/**
	 * 新的一套操作api,用于比较简单的sql,直接用问号传参
	 * @param sql sql语句，可以用问号传参数如：update JOB_AD set AD_text=? where ad_id = ?
	 * @param para ：参数数组
	 */
	default int excuteSql(String sql, Object... para){
		return excuteSql(SqlUtil.getParmMap(sql, para));
	}
	default Object getColum(String sql, Object... para){
		return getColum(SqlUtil.getParmMap(sql, para));
	}
	default String getStr(String sql, Object... para){
		return getStr(SqlUtil.getParmMap(sql, para));
	}
	default BigDecimal getBigDecimal(String sql, Object... para){
		return getBigDecimal(SqlUtil.getParmMap(sql, para));
	}
	default Float getFloat(String sql, Object... para){
		return getFloat(SqlUtil.getParmMap(sql, para));
	}
	default Integer getInt(String sql, Object... para){
		return getInt(SqlUtil.getParmMap(sql, para));
	}
	default Long getLong(String sql, Object... para){
		return getLong(SqlUtil.getParmMap(sql, para));
	}
	default List<Object> getColumList(String sql, Object... para){
		return getColumList(SqlUtil.getParmMap(sql, para));
	}
	default List<String> getStrList(String sql, Object... para){
		return getStrList(SqlUtil.getParmMap(sql, para));
	}
	default List<BigDecimal> getBigDecimalList(String sql, Object... para){
		return getBigDecimalList(SqlUtil.getParmMap(sql, para));
	}
	default List<Float> getFloatList(String sql, Object... para){
		return getFloatList(SqlUtil.getParmMap(sql, para));
	}
	default List<Integer> getIntList(String sql, Object... para){
		return getIntList(SqlUtil.getParmMap(sql, para));
	}
	default List<Long> getLongList(String sql, Object... para){
		return getLongList(SqlUtil.getParmMap(sql, para));
	}
	default ResultMap getMap(String sql, Boolean throwEx, Object... para){
		return getMap(SqlUtil.getParmMap(sql, para),throwEx);
	}
	default ResultMap getMap(String sql, Object... para){
		return getMap(SqlUtil.getParmMap(sql, para),true);
	}
	default List<ResultMap> getMapList(String sql, Object... para){
		return getMapList(SqlUtil.getParmMap(sql, para));
	}
	default <T> T getBean(String sql, Class<T> t, Object... para){
		return getBean(SqlUtil.getParmMap(sql, para),t);
	}
	default <T> List<T> getBeanList(String sql, Class<T> t, Object... para){
		return getBeanList(SqlUtil.getParmMap(sql, para),t);
	}

	long getSeq(String seqName);
	long getSeq(Class<?> clazz);
	int update(String tableName, Object bean, String where);
	int insert(String tableName, Object bean);
}
