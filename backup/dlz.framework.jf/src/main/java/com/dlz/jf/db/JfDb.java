package com.dlz.jf.db;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.exception.DbException;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.logger.MyLogger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;

public class JfDb {
	private static MyLogger logger = MyLogger.getLogger(JfDb.class);
	public static List<JSONMap>  find(String sql,Object... paras){
		SqlPara sqlpara=getSqlPara(sql,paras);
		return getList(sqlpara,JSONMap.class);
	}
	public static JSONMap  findFirst(String sql, Object... paras){
		SqlPara sqlpara=getSqlPara(sql,paras);
		return getBean(sqlpara,JSONMap.class);
	}
	public static List<JSONMap> find(String sql, Map<String,Object> paras){
		SqlPara sqlpara=getSqlParaByKey(sql,paras);
		return getList(sqlpara,JSONMap.class);
	}
	public static JSONMap findFirst(String sql,Map<String,Object> paras){
		SqlPara sqlpara=getSqlParaByKey(sql,paras);
		return getBean(sqlpara,JSONMap.class);
	}
	
	public static <T> List<T>  find(String sql, Class<T> modelClass, Object... paras){
		SqlPara sqlpara=getSqlPara(sql,paras);
		return getList(sqlpara,modelClass);
	}
	public static <T> T  findFirst(String sql, Class<T> modelClass, Object... paras){
		SqlPara sqlpara=getSqlPara(sql,paras);
		return getBean(sqlpara,modelClass);
	}
	public static <T> List<T>  find(String sql, Class<T> modelClass,Map<String,Object> paras){
		SqlPara sqlpara=getSqlParaByKey(sql,paras);
		return getList(sqlpara,modelClass);
	}
	public static <T> T  findFirst(String sql, Class<T> modelClass,Map<String,Object> paras){
		SqlPara sqlpara=getSqlParaByKey(sql,paras);
		return getBean(sqlpara,modelClass);
	}
	public static int excute(String sql, Map<String,Object> paras){
		SqlPara sqlpara=getSqlParaByKey(sql,paras);
		return Db.update(sqlpara.getSql(),sqlpara.getPara());
	}
	public static int excute(String sql,Object... paras){
		return Db.update(sql,paras);
	}
	
	
	
	private static SqlPara getSqlParaByKey(String sql, Map<String,Object> paras){
		ParaMap pm=new ParaMap(sql);
		pm.getPara().putAll(paras);
		try {
			SqlUtil.dealParm(pm);
			SqlUtil.dealParmToJdbc(pm, pm.getSqlRun());
		} catch (Exception e) {
			throw new DbException(e.getMessage(),e);
		}
		
		SqlPara sqlpara=new SqlPara();
		sqlpara.setSql(pm.getSqlJdbc());
		Object[] paras2=pm.getSqlJdbcPara();
		for(Object o:paras2){
			sqlpara.addPara(o);
		}
		return sqlpara;
	}
	
	private static SqlPara getSqlPara(String sql, Object... paras){
		SqlPara sqlpara=new SqlPara();
		sqlpara.setSql(sql);
		for(Object o:paras){
			sqlpara.addPara(o);
		}
		return sqlpara;
	}
	
	private static <T> List<T> getList(SqlPara sqlPara, Class<T> modelClass){
		List<Record> rs=Db.find(sqlPara);
		return build(rs, modelClass);
	}
	
	private static <T> T getBean(SqlPara sqlPara, Class<T> modelClass){
		List<Record> rs=Db.find(sqlPara);
		if(rs.isEmpty()){
			return null;
		}
		return build(rs.get(0), modelClass);
	}

	private static <T> List<T> build(List<Record> rs, Class<T> modelClass){
		List<T> l=new ArrayList<T>();
		for(Record rc:rs){
			l.add(build(rc,modelClass));
		}
		return l;
	}
	
	public static Page<JSONMap> paginate(Integer nowPage, Integer pageSize, String sql,Object... paras) {
		return null;
//		return Db.paginate(nowPage, pageSize, select, select, sqlExceptSelect, hotel_id);
	}
	
	public static <T> T build(Record rs, Class<T> modelClass){
		try {
			return convertMap2Bean(modelClass, rs.getColumns());
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(),e);
		} catch (InstantiationException e) {
			logger.error(e.getMessage(),e);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(),e);
		} catch (IntrospectionException e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	
	
	/**
	 * 将一个 Map 对象转化为一个 JavaBean
	 * 
	 * @param type
	 *          要转化的类型
	 * @param map
	 *          包含属性值的 map
	 * @return 转化出来的 JavaBean 对象
	 * @throws IntrospectionException
	 *           如果分析类属性失败
	 * @throws IllegalAccessException
	 *           如果实例化 JavaBean 失败
	 * @throws InstantiationException
	 *           如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *           如果调用属性的 setter 方法失败
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T> T convertMap2Bean(Class<T> clazz, Map<String,Object> map) throws IntrospectionException, IllegalAccessException,
			InstantiationException, InvocationTargetException {
		if(map == null){
			return null;
		}
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz); // 获取类属性
		T obj = clazz.newInstance(); // 创建 JavaBean 对象
		
		if(obj instanceof Map ){
			((Map)obj).putAll(map);
			for(String key:map.keySet()){
				((Map)obj).put(SqlUtil.converClumnStr2Str(key),map.get(key));
			}
			return obj;
		}

		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName().toUpperCase();
			if (map.containsKey(propertyName)) {
				// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
				Object value = map.get(propertyName);
				try{
					if(value instanceof BigDecimal){
						if(descriptor.getPropertyType().equals(Long.class)){
							value=((BigDecimal)value).longValue();
						}else if(descriptor.getPropertyType().equals(Integer.class)){
							value=((BigDecimal)value).intValue();
						}else if(descriptor.getPropertyType().equals(Double.class)){
							value=((BigDecimal)value).doubleValue();
						}else if(descriptor.getPropertyType().equals(Float.class)){
							value=((BigDecimal)value).floatValue();
						}
					}
					Object[] args = new Object[1];
					args[0] = value;
					descriptor.getWriteMethod().invoke(obj, args);
				}catch(Exception e){
					logger.error("转换失败："+clazz.getName()+"."+propertyName+"["+value.getClass()+" to "+descriptor.getPropertyType()+"]");
				}
			}
		}
		return obj;
	}
}
