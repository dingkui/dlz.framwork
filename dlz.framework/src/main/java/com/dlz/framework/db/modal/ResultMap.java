package com.dlz.framework.db.modal;

import java.util.Date;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.util.DateUtil;

public class ResultMap extends JSONMap{
	private static final long serialVersionUID = -7368198549742264784L;
	
	/**
	 * 取出日期类型属性为字符串
	 * yyyy-MM-dd HH:mm:ss
	 * @param key
	 * @return
	 */
	public String getDateStr(String key){
		Object o = super.get(key);
		if(o instanceof Date){
			return DateUtil.getDateTimeStr((Date)super.get(key));
		}else{
			return String.valueOf(o);
		}
	}
	/**
	 * 根据传入的格式取出日期类型属性 为字符串
	 * @param key
	 * @param format 自定义格式 如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public String getDateStr(String key,String format){
		Object o = super.get(key);
		if(o instanceof Date){
			return DateUtil.getDateStr((Date)super.get(key),format);
		}else{
			return String.valueOf(super.get(key));
		}
	}
	
	public void coverDate2Str(String dateFormat){
		for(String k:super.keySet()){
			if(super.get(k) instanceof Date){
				super.put(k, getDateStr(k,dateFormat));
			}
		}
	}
}
