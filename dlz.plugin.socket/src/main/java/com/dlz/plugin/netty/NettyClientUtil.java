package com.dlz.plugin.netty;

import java.util.HashMap;
import java.util.Map;

import com.dlz.plugin.netty.codec.ICoder;
import com.dlz.plugin.socket.interfaces.ISocketListener;

public class NettyClientUtil {
	private static Map<String,NettyClient> clientMap=new HashMap<>();
	private static Map<String,NettySynClient> clientSynMap=new HashMap<>();
	
	private static String defaultClient;
	
	/**
	 * 客户端初始化
	 * @param serverName 服务器名称
	 * @param isDefualt 是否默认服务器
	 * @param port 端口
	 * @param host 
	 * @param lisner 异步消息监听（服务器推送回来的消息或者异步消息处理）
	 * 默认编码器未
	 */
	public static void init(String serverName,boolean isDefualt, int port, String host, ISocketListener lisner) {
		if(isDefualt){
			defaultClient=serverName;
		}
		if(lisner!=null && !clientMap.containsKey(serverName)){
			clientMap.put(serverName, new NettyClient(port, host, lisner));
		}
		if(!clientSynMap.containsKey(serverName)){
			clientSynMap.put(serverName, new NettySynClient(port, host));
		}
	}
	/**
	 * 客户端初始化
	 * @param serverName 服务器名称
	 * @param isDefualt 是否默认服务器
	 * @param port 端口
	 * @param host 
	 * @param lisner 异步消息监听（服务器推送回来的消息或者异步消息处理）
	 * @param coder
	 */
	public static void init(String serverName,boolean isDefualt, int port, String host, ISocketListener lisner,ICoder coder) {
		if(isDefualt){
			defaultClient=serverName;
		}
		if(lisner!=null && !clientMap.containsKey(serverName)){
			clientMap.put(serverName, new NettyClient(port, host, lisner,coder));
		}
		if(!clientSynMap.containsKey(serverName)){
			clientSynMap.put(serverName, new NettySynClient(port, host,coder));
		}
	}
	public static void send(String serverNam,String msg){
		NettyClient client=clientMap.get(serverNam);
		if(client==null){
			throw new RuntimeException("客户端["+serverNam+"]未初始化！");
		}
		client.send(msg);
	}
	public static void send(String msg){
		send(defaultClient, msg);
	}
	public static String synSend(String serverNam,String msg){
		NettySynClient client=clientSynMap.get(serverNam);
		if(client==null){
			throw new RuntimeException("客户端["+serverNam+"]未初始化！");
		}
		return client.send(msg);
	}
	public static String synSend(String msg){
		return synSend(defaultClient, msg);
	}
}