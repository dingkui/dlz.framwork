package com.dlz.framework.db.conver;

/**
 * 根据名称转换
 * @author dingkui
 *
 */
public abstract class ANameConverter<DB,OBJ,PARA>{
	/**
	 * 转换成显示对象
	 * @param o
	 * @return
	 */
	public abstract OBJ conver2Str(DB o);
	/**
	 * 转换成数据库对象
	 * @param o
	 * @return
	 */
	public abstract DB conver2Db(OBJ o);
	
	String name;
	PARA para;
	public String getName(){
		return name;
	}
	public PARA getPara() {
		return para;
	}
	public ANameConverter(String name,PARA para){
		this.name=name;
		this.para=para;
	}
}
