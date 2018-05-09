package com.dlz.plugin.netty.listener;

/**
 * 服务器端回馈处理监听
 * @author dingkui
 *
 */
public abstract class ANettyServerListener implements INettyListener {
	/**
	 * 服务器端处理传入数据，并返回信息
	 * @param postStr 传入数据
	 * @return
	 */
	public abstract String deal(String reciveStr);
	
	/**
	 * 客户端处理收到数据
	 * @param postStr 传入数据
	 * @return
	 */
	public  void recive(String reciveStr){
		
	}

//	
//	/**
//	 * 服务器异步处理客户端请求
//	 * @param info
//	 * @return
//	 */
//	public String dealAsyn(String info);
//
//
//	/**
//	 * 服务器同步处理客户端请求
//	 * @param info
//	 * @return
//	 */
//	public String dealSyn(String info);
}
