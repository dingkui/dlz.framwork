package com.dlz.framework.db;

import com.dlz.framework.db.conver.Convert;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.util.JacksonUtil;

/**
 * 数据库信息转换器
 * 
 * @author dingkui 2017-06-26
 * 
 */
public class DbCoverUtil {
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
			if("ROWNUM_".equals(a)||"rownum".equals(a)){
				continue;
			}
			return m.get(a);
		}
		return null;
	}
	
	/**
	 * 将Map转换成bean中对应字段的Map
	 * @param list
	 * @author dk 2015-04-15
	 * @return
	 */
	public static ResultMap getConveredMap(ResultMap m,Convert c){
		if(m==null){
			return null;
		}
		ResultMap m2=new ResultMap();
		for(String a: m.keySet()){
			m2.put(SqlUtil.converClumnStr2Str(a), m.get(a));
		}
		return c.convertMap(m2);
	}
	
	/**
	 * 将Map转换成bean中对应字段的Map
	 * @param list
	 * @author dk 2015-04-15
	 * @return
	 */
	public static ResultMap converResultMap(ResultMap m,Convert c){
		if(m==null){
			return null;
		}
		return getConveredMap(m, c);
	}
	
	/**
	 * 将Map转换成bean
	 * @param list
	 * @author dk 2018-01-19
	 * @return
	 */
	public static <T> T conver(ResultMap m,Convert c,Class<T> t){
		if(m==null){
			return null;
		}
		return JacksonUtil.coverObj(getConveredMap(m, c), t);
	}
}
