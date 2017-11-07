package com.dlz.framework.db.conver.impl;

import com.dlz.framework.db.conver.ILogicServer;

/**
 * 业务逻辑转换接口
 * @author dingkui
 *
 */
interface ILogicConverter<T,P>{
	public ILogicServer<T,P> getLogicServ();
}
