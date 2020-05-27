package com.dlz.framework.db.convertor.result;

import java.util.Map;

/**
 * 根据名称转换多个值
 * @author dingkui
 *
 */
public abstract class AGroupConverter<DB,OBJ,PARA>{

	/**
	 * 转换成显示对象
	 * @param o
	 * @return
	 */
	public abstract OBJ conver2Str(DB o,Map<String,Object> map);
	/**
	 * 转换成数据库对象
	 * @param o
	 * @return
	 */
	public abstract DB conver2Db(OBJ o,Map<String,Object> map);
	
	String name;
	PARA para;
	public String getName(){
		return name;
	}
	public PARA getPara() {
		return para;
	}
	public AGroupConverter(String name,PARA para){
		this.name=name;
		this.para=para;
	}
}
