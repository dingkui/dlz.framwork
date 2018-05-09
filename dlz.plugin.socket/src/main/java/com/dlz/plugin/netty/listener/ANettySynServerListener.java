package com.dlz.plugin.netty.listener;

/**
 * 服务器端回馈处理监听
 * @author dingkui
 *
 */
public abstract class ANettySynServerListener implements INettyListener {
	/**
	 * 客户端收到传入数据
	 * @param postStr 传入数据
	 * @return
	 */
	public void recive(String reciveStr){
	};
}
