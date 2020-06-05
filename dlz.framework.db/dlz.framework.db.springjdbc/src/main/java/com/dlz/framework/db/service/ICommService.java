package com.dlz.framework.db.service;

import com.dlz.comm.exception.DbException;
import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.ValUtil;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.convertor.ConvertUtil;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
	 * 从数据库中取得map类型列表如：[{AD_ENDDATE=2015-04-08 13:47:12.0}]
	 * sql语句，可以带参数如：select AD_ENDDATE from JOB_AD t where ad_id=#{ad_id}
	 * @param paraMap ：Map<String,Object> m=new HashMap<String,Object>();m.put("ad_id", "47");
	 * @return
	 * @throws Exception
	 */
	List<ResultMap> getMapList(BaseParaMap paraMap);

	int getCnt(BaseParaMap paraMap);
	
	/**
	 * 从数据库中取得单个字段数据
	 */
	default Object getColum(BaseParaMap paraMap){
		List<ResultMap> list = getMapList(paraMap);
		if(list.size()==0){
			return null;
		}else if(list.size()>1){
			throw new DbException("查询结果为多条",1004);
		}else{
			return ConvertUtil.getFistClumn(list.get(0));
		}
	}
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


	default List<Object> getColumList(BaseParaMap paraMap){
		return getColumList(paraMap,null);
	}
	default <T> List<T> getColumList(BaseParaMap paraMap, Class<T> classs){
		List<ResultMap> r = getMapList(paraMap);
		List<T> l = new ArrayList<>();
		if (classs == null) {
			for(ResultMap m : r){
				l.add((T)ConvertUtil.getFistClumn(m));
			}
		} else if (classs == String.class) {
			for(ResultMap m : r){
				l.add((T)ValUtil.getStr(ConvertUtil.getFistClumn(m)));
			}
		} else if (classs == Integer.class) {
			for(ResultMap m : r){
				l.add((T)ValUtil.getInt(ConvertUtil.getFistClumn(m)));
			}
		} else if (classs == Long.class) {
			for(ResultMap m : r){
				l.add((T)ValUtil.getLong(ConvertUtil.getFistClumn(m)));
			}
		} else if (classs == BigDecimal.class) {
			for(ResultMap m : r){
				l.add((T)ValUtil.getBigDecimal(ConvertUtil.getFistClumn(m)));
			}
		} else if (classs == Float.class) {
			for(ResultMap m : r){
				l.add((T)ValUtil.getFloat(ConvertUtil.getFistClumn(m)));
			}
		} else if (classs == Double.class) {
			for(ResultMap m : r){
				l.add((T)ValUtil.getDouble(ConvertUtil.getFistClumn(m)));
			}
		}else{
			for(ResultMap m : r){
				l.add(ValUtil.getObj(ConvertUtil.getFistClumn(m),classs));
			}
		}
		return l;
	}
	default List<String> getStrList(BaseParaMap paraMap){
		return getColumList(paraMap,String.class);
	}
	default List<BigDecimal> getBigDecimalList(BaseParaMap paraMap) {
		return getColumList(paraMap,BigDecimal.class);
	}
	default List<Float> getFloatList(BaseParaMap paraMap) {
		return getColumList(paraMap,Float.class);
	}
	default List<Integer> getIntList(BaseParaMap paraMap) {
		return getColumList(paraMap,Integer.class);
	}
	default List<Long> getLongList(BaseParaMap paraMap) {
		return getColumList(paraMap,Long.class);
	}
	default List<Double> getDoubleList(BaseParaMap paraMap) {
		return getColumList(paraMap,Double.class);
	}
	
	/**
	 * 从数据库中取得集合
	 */
	default ResultMap getMap(BaseParaMap paraMap){
		return getMap(paraMap,true);
	}
	default ResultMap getMap(BaseParaMap paraMap, boolean throwEx){
		List<ResultMap> list = getMapList(paraMap);
		if(list.size()==0){
			return null;
		}else if(list.size()>1 && throwEx){
			throw new DbException("查询结果为多条",1004);
		}else{
			return list.get(0);
		}
	}

	default <T> T getBean(BaseParaMap paraMap, Class<T> t, boolean throwEx){
		try {
			return JacksonUtil.coverObj(getMap(paraMap,throwEx), t);
		} catch (Exception e) {
			if(e instanceof DbException){
				throw e;
			}
			throw new DbException(e.getMessage(),1005,e);
		}
	}
	default <T> T getBean(BaseParaMap paraMap, Class<T> t){
		return getBean(paraMap, t, true);
	}
	default <T> List<T> getBeanList(BaseParaMap paraMap, Class<T> t){
		List<ResultMap> list = getMapList(paraMap);
		List<T> l = new ArrayList<T>();
		for(ResultMap r: list){
			try{
				l.add(JacksonUtil.coverObj(r, t));
			}catch(Exception e){
				throw new DbException(e.getMessage(),1005,e);
			}
		}
		return l;
	}
	
	/**
	 * 取得分页数据
	 * @return
	 * @throws Exception
	 */
	default Page<ResultMap> getPage(BaseParaMap paraMap){
		return getPage(paraMap, ResultMap.class);
	}
	default <T> Page<T> getPage(BaseParaMap paraMap, Class<T> t){
		Page cache = paraMap.getCacheItem().getCache("page", paraMap);
		if(cache!=null){
			return cache;
		}

		Page<T> page= paraMap.getPage();
		//是否需要查询列表（需要统计条数并且条数是0的情况不查询，直接返回空列表）
		boolean needList=true;

		page.setCount(getCnt(paraMap));
		if(page.getCount()==0){
			needList=false;
		}

		if(needList){
			if(t==ResultMap.class){
				page.setData((List<T>) getMapList(paraMap));
			}else{
				page.setData(getBeanList(paraMap,t));
			}
		}else{
			page.setData(new ArrayList<T>());
		}
		paraMap.getCacheItem().saveCache(page);

		return page;
	}




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
}
