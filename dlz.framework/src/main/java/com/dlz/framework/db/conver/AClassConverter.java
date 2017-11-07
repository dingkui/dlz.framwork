package com.dlz.framework.db.conver;

/**
 * 根据类型转换器
 * @author dingkui
 *
 */
public abstract class AClassConverter<DB,OBJ,PARA>{
	public abstract String getCoverClass();
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
	
	protected PARA para;
	public AClassConverter(PARA para){
		this.para=para;
	}
}
