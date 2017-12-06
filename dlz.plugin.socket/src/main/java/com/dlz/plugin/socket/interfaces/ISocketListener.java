package com.dlz.plugin.socket.interfaces;

/**
 * 服务器端回馈处理监听
 * @author dingkui
 *
 */
public interface ISocketListener {
	/**
	 * 处理传入数据，并返回信息
	 * @param postStr 传入数据
	 * @return
	 */
	public void deal(String reciveStr);
}
