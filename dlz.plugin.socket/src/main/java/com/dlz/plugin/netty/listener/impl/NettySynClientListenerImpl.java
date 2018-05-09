package com.dlz.plugin.netty.listener.impl;

import com.dlz.plugin.netty.listener.INettyListener;

/**
 * 客户端同步请求回调处理监听
 * @author dingkui
 *
 */
public class NettySynClientListenerImpl implements INettyListener {
	private String result;
	public String getResult(){
		return result;
	}
	/**
	 * 客户端收到传入数据
	 * （同步模式下，将处理结果保存到监听器中，在后续操作中取得）
	 * @param postStr 传入数据
	 * @return
	 */
	public void recive(String reciveStr){
		result=reciveStr;
	};
	/**
	 * 客户端收到传入数据
	 * （同步模式下，将处理结果保存到监听器中，在后续操作中取得）
	 * @param postStr 传入数据
	 * @return
	 */
	public String deal(String reciveStr){
		return null;
	};
}
