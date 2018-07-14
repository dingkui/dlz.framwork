package com.dlz.plugin.websocket.handler;

import java.util.Map;

public interface IWsHandler{

	default void onOpen(){
		System.out.println("连接成功");
	}

	void onMessage(String s);

	default void onError(Exception e){
		System.out.println("发生错误已关闭");
		e.printStackTrace();
	}

	default String getSessionId(){
		return null;
	}

	default Map<String, String> getHeaders(){
		return null;
	}
}

