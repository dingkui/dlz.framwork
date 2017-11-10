package com.dlz.framework.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dlz.framework.db.conver.Convert;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.JacksonUtil;

/**
 * 数据库信息转换器
 * 
 * @author dingkui 2017-06-26
 * 
 */
public class DbCoverUtil {
	private static MyLogger logger = MyLogger.getLogger(DbCoverUtil.class);
	/**
	 * 从Map里取得字符串
	 * @param m
	 * @author dk 2015-04-09
	 * @return
	 */
	public static Object getFistClumn(ResultMap m){
		if(m==null){
			return null;
		}
		for(String a: m.keySet()){
			if("rownum".equals(a)){
				continue;
			}
			return m.get(a);
		}
		return null;
	}
	
	/**
	 * 将list中的Map转换成bean中对应字段的Map
	 * @param list
	 * @author dk 2015-04-09
	 * @return
	 */
	public static List<ResultMap> doMyCover(List<ResultMap> list,Convert c){
		for(ResultMap a: list){
			doMyCover(a,c);
		}
		return list;
	}
	/**
	 * 将Map转换成bean中对应字段的Map
	 * @param list
	 * @author dk 2015-04-15
	 * @return
	 */
	public static ResultMap doMyCover(ResultMap m,Convert c){
		if(m==null){
			return null;
		}
		ResultMap m2=new ResultMap();
		for(String a: m.keySet()){
			m2.put(SqlUtil.converClumnStr2Str(a), m.get(a));
		}
		m.clear();
		m.putAll(m2);
		m2=null;
		c.convertMap(m);
		return m;
	}
	
	/**
	 * 将list中的Map转换成bean中对应字段的Map
	 * @param list
	 * @author dk 2016-02-25
	 * @return
	 */
	public static <T> List<T> converList(List<ResultMap> list,Class<T> t){
		List<T> l = new ArrayList<T>();
		for(ResultMap r: list){
			try{
				Map<String,Object> m2=new HashMap<String,Object>();
				for(String a: r.keySet()){
					m2.put(SqlUtil.converClumnStr2Str(a),r.get(a));
				}
				l.add(JacksonUtil.coverObj(m2, t));
				//l.add(MapUtil.convertMap2Bean(t, m2));
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
		return l;
	}
	/**
	 * 将list中的Map转换成bean中对应字段的Map
	 * @param list
	 * @author dk 2016-02-25
	 * @return
	 */
	public static <T> T converObjWithJackson(Object o,Class<T> t){
		return JacksonUtil.readValue(JacksonUtil.getJson(o),t);
	}

}
