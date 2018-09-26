package com.dlz.framework.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.dlz.framework.bean.JSONList;
import com.dlz.framework.exception.CodeException;
import org.slf4j.Logger;

/**
 * 对象转换工具类
 * @author dk 2017-11-03
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ValUtil{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger logger=org.slf4j.LoggerFactory.getLogger(ValUtil.class);
	
	public static BigDecimal getBigDecimal(Object input,BigDecimal defaultV){
		Number o=getNumber(input,null);
		if(o==null){
			return defaultV;
		}
		if(o instanceof BigDecimal) {
			return (BigDecimal)o;
		}else if (o instanceof Float) {
			return new BigDecimal(((Float)o).doubleValue());
		}else if (o instanceof Double) {
			return new BigDecimal(((Double)o).doubleValue());
		}else if (o instanceof Integer) {
			return new BigDecimal(((Integer)o).intValue());
		}else if (o instanceof Long) {
			return new BigDecimal(((Long)o).longValue());
		}
		return new BigDecimal(o.toString());
	}
	public static BigDecimal getBigDecimal(Object input){
		return getBigDecimal(input,null);
	}
	
	public static Double getDouble(Object input){
		return getDouble(input, null);
	}
	public static Double getDouble(Object input,Double defaultV){
		Number o=getNumber(input,null);
		if(o==null){
			return defaultV;
		}
		return o.doubleValue();
	}
	
	public static Float getFloat(Object input){
		return getFloat(input, null);
	}
	public static Float getFloat(Object input,Float defaultV){
		Number o=getNumber(input,null);
		if(o==null){
			return defaultV;
		}
		return o.floatValue();
	}
	
	public static Integer getInt(Object input){
		return getInt(input,null);
	}
	public static Integer getInt(Object input,Integer defaultV){
		Number o=getNumber(input,null);
		if(o==null){
			return defaultV;
		}
		return o.intValue();
	}
	public static Long getLong(Object input){
		return getLong(input, null);
	}
	public static Long getLong(Object input,Long defaultV){
		Number o=getNumber(input,null);
		if(o==null){
			return defaultV;
		}
		return o.longValue();
	}
	

	public static Object[] getArray(Object input){
		return getArray(input, null);
	}
	public static JSONList getList(Object input){
		return getList(input, null);
	}
	
	public static String getStr(Object input){
		return getStr(input, null);
	}
	
	public static Boolean getBoolean(Object input){
		return getBoolean(input,false);
	}
	
	
	public static String getStr(Object input,String defaultV){
		if(input==null){
			return defaultV;
		}
		if (input instanceof CharSequence) {
			return input.toString();
		}
		return input.toString();
	}
	public static Boolean getBoolean(Object input,Boolean defaultV){
		if(input==null){
			return defaultV;
		}
		if (input instanceof Boolean) {
			return (Boolean)input;
		}
		String r=input.toString();
		
		return !"false".equalsIgnoreCase(r)&&!"0".equals(r)&&!"".equals(r);
	}
	private static Number getNumber(Object input,Number defaultV){
		if(input==null || "".equals(input)){
			return defaultV;
		}
		if (input instanceof Number) {
			return (Number)input;
		}
		return new BigDecimal(input.toString());
	}
	public static JSONList getList(Object input,List defaultV){
		if(input==null){
			return new JSONList(defaultV);
		}
		if (input instanceof JSONList) {
			return (JSONList)input;
		}
		try{
			return new JSONList(input);
		}catch(CodeException e){
			logger.warn(e.getMessage());
		}
		return new JSONList(defaultV);
	}
	
	public static Date getDate(Object input){
		return getDate(input, null);
	}
	public static Date getDate(Object input,String format){
		if(input==null){
			return null;
		}
		if (input instanceof Date) {
			return (Date)input;
		}
		String  value = getStr(input,"").replaceAll("/", "-").replaceAll("\"", "");
		if (value.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.*")) {
			return DateUtil.parseDateTime(value.substring(0, 18));
		} else if (value.matches("^\\d{4}年\\d{2}月\\d{2}日 \\d{2}时\\d{2}分\\d{2}秒")) {
			return DateUtil.parseDateTimeC(value);
		} else if (value.matches("^\\d{4}-\\d{2}-\\d{2}")) {
			return DateUtil.parseDate(value);
		}
		if(format==null){
			return null;
		}
		return DateUtil.parseDate(value,format);
	}
	public static String getDateStr(Object input,String format){
		if(input==null){
			return null;
		}
		input=getDate(input);
		if(input==null){
			return "";
		}
		if(format==null){
			return DateUtil.getDateTimeStr((Date)input);
		}
		return DateUtil.getDateStr((Date)input,format);
	}
	public static String getDateStr(Object input){
		return getDateStr(input, null);
	}
	public static Object[] getArray(Object input,Object[] defaultV){
		if(input==null){
			return defaultV;
		}
		if(input instanceof Object[]) {
			return (Object[])input;
		}else if (input instanceof Collection) {
			return ((Collection)input).toArray();
		}
		try{
			return new JSONList(input).toArray();
		}catch(CodeException e){
			logger.warn(e.getMessage());
		}
		return defaultV;
	}
	public static <T> T getObj(Object input,Class<T> classs){
		if(input==null){
			return null;
		}
		if(classs==String.class){
			return (T) getStr(input);
		}
		if(classs==Integer.class){
			return (T) getInt(input);
		}
		if(classs==Long.class){
			return (T) getLong(input);
		}
		if(classs==Date.class){
			return (T) getDate(input);
		}
		if(classs==BigDecimal.class){
			return (T) getBigDecimal(input);
		}
		if(classs==Float.class){
			return (T) getFloat(input);
		}
		if(classs==Double.class){
			return (T) getDouble(input);
		}
		if(classs==Boolean.class){
			return (T) getBoolean(input);
		}
		if(classs==JSONList.class){
			return (T) getList(input);
		}
		return JacksonUtil.coverObj(input, classs);
	}

}