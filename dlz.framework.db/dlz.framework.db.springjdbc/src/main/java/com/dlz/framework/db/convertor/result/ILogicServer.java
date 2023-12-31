package com.dlz.framework.db.convertor.result;

/**
 * 业务逻辑转换接口
 * @author dingkui
 *
 */
public interface ILogicServer<T,P>{
	/**
	 * 转换成显示对象
	 * @param o
	 * @return
	 */
	Object conver2Str(T o, P para);
	/**
	 * 转换成数据库对象
	 * @param o
	 * @return
	 */
	Object conver2Db(T o, P para);
}
