package com.dlz.framework.db.conver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.conver.impl.DateConverter;
import com.dlz.framework.db.conver.impl.DateNameConverter;
import com.dlz.framework.db.conver.impl.DictConverter;
import com.dlz.framework.db.enums.DateFormatEnum;
import com.dlz.framework.db.modal.ResultMap;
import org.slf4j.Logger;


/**
 * 查询结果转换工具
 * @author dingkui
 *
 */
@SuppressWarnings("rawtypes")
public class Convert {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	public static Logger logger = org.slf4j.LoggerFactory.getLogger(Convert.class);
	Map<String,AClassConverter> classMap = new HashMap<String,AClassConverter>();
	Map<String,ANameConverter> nameMap = new HashMap<String,ANameConverter>();
	Map<String,AGroupConverter> groupMap = new HashMap<String,AGroupConverter>();
	
	/**
	 * 添加转换器(按类型添加)
	 * @param converter
	 */
	public Convert addClassConvert(AClassConverter c){
		classMap.put(c.getCoverClass(),c);
		return this;
	}
	/**
	 * 添加转换器(按类型添加)
	 * @param converter
	 */
	public Convert addNameConvert(ANameConverter c){
		nameMap.put(c.getName(),c);
		return this;
	}
	/**
	 * 添加转换器(按类型添加)
	 * @param converter
	 */
	public Convert addGroupConvert(AGroupConverter c){
		groupMap.put(c.getName(),c);
		return this;
	}
	
	/**
	 * 添加字典转换器
	 * @param name 字段名
	 * @param dictCode 字典名称
	 * @return
	 */
	public Convert addDictConvert(String name, String dictCode){
		name=SqlUtil.converClumnStr2Str(name);
		nameMap.put(name,new DictConverter(name, dictCode,"dictConverterLogicServer"));
		return this;
	}
	/**
	 * 转换数据
	 * @param o
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ResultMap convertMap(ResultMap map){
		ResultMap nM=new ResultMap();
		for(String k:map.keySet()){
			Object o=map.get(k);
			if(o==null){
				continue;
			}
			if(groupMap.containsKey(k)){
				groupMap.get(k).conver2Str(o,map);
			}else if(nameMap.containsKey(k)){
				nM.put(k+"_text", nameMap.get(k).conver2Str(o));
			}else if(classMap.containsKey(o.getClass().getCanonicalName())){
				map.put(k, classMap.get(o.getClass().getCanonicalName()).conver2Str(o));
			} 
		}
		map.putAll(nM);
		return map;
	}
	/**
	 * 转换数据
	 * @param o
	 * @return
	 */
	public void convertListMap(List<ResultMap> list){
		for(ResultMap map:list){
			convertMap(map);
		}
	}
	public void addDateConvert(DateFormatEnum datetimestr) {
		addClassConvert(new DateConverter(datetimestr));
	}
	public void addDateConvert(String name,DateFormatEnum datetimestr) {
		name=SqlUtil.converClumnStr2Str(name);
		nameMap.put(name,new DateNameConverter(name, datetimestr));
	}
	
}
