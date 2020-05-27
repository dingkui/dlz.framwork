package com.dlz.framework.db.convertor;

import com.dlz.comm.util.JacksonUtil;
import com.dlz.framework.db.convertor.clumnname.ColumnNameCamel;
import com.dlz.framework.db.convertor.clumnname.AColumnNameConvertor;
import com.dlz.framework.db.convertor.dbtype.ATableCloumnMapper;
import com.dlz.framework.db.convertor.result.Convert;
import com.dlz.comm.exception.DbException;
import com.dlz.framework.db.modal.ResultMap;

/**
 * 数据库信息转换器
 * 
 * @author dingkui 2017-06-26
 * 
 */
public class ConvertUtil {
	static ATableCloumnMapper tableCloumnMapper= new ATableCloumnMapper(){
		@Override
		public Object converObj4Db(String tableName, String clumnName, Object value) {
			return value;
		}
		@Override
		public boolean isClumnExists(String tableName, String clumnName) {
			return true;
		}
	};
	public static void setTableCloumnCache(ATableCloumnMapper paraCover){
		ConvertUtil.tableCloumnMapper =paraCover;
	}
	/**
	 * 把传入的参数转换成数据库识别的参数
	 * 主要用于postgresql类似的强制类型
	 * @param tableName
	 * @param clumnName
	 * @param value
	 * @author dk 2018-09-28
	 * @return
	 */
	public static Object getVal4Db(String tableName,String clumnName,Object value) {
		return tableCloumnMapper.converObj4Db(tableName, clumnName, value);
	}
	/**
	 * 判断字段是否存在
	 * @param tableName
	 * @param clumnName
	 * @author dk 2018-09-28
	 * @return
	 */
	public static boolean isClumnExists(String tableName,String clumnName) {
		return tableCloumnMapper.isClumnExists(tableName, clumnName);
	}
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
			m2.put(clumn2Str(a), m.get(a));
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
		try{
			return getConveredMap(m, c);
		}catch (Exception e) {
			if(e instanceof DbException) {
				throw e;
			}
			throw new DbException("转换异常："+m.toString(),1004,e);
		}
		
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

	public static AColumnNameConvertor columnMapper=new ColumnNameCamel();
	public static String clumn2Str(String dbKey) {
		return columnMapper.clumn2Str(dbKey);
	}
	public static String str2Clumn(String beanKey) {
		return columnMapper.str2Clumn(beanKey);
	}
}
