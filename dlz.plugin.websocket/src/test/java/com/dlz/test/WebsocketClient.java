package com.dlz.test;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by jack on 2017/10/25.
 */
public class WebsocketClient {
	public static WebSocketClient client;
	
	public static void main(String[] args) {
		try {
//			client = new WebSocketClient(new URI("ws://192.168.1.213:8080/cas/websocket"), new Draft_6455()) {
			client = new WebSocketClient(new URI("ws://127.0.0.1:2222/vcenter/websocket"), new Draft_6455()) 
			
			{
				@Override
				public void onOpen(ServerHandshake serverHandshake) {
					System.out.println("打开链接");
				}

				@Override
				public void onMessage(String s) {
					System.out.println("收到消息" + s);
				}

				@Override
				public void onClose(int i, String s, boolean b) {
					System.out.println("链接已关闭");
					
				}

				@Override
				public void onError(Exception e) {
					e.printStackTrace();
					System.out.println("发生错误已关闭");
				}
			};
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		client.connect();
//		System.out.println(client.getDraft());
//		while (!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
//			System.out.println("还没有打开");
//		}
//		System.out.println("打开了");
//		try {
//			send("hello world".getBytes("utf-8"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		client.send("hello world");
		//client.close();
	}

	public static void send(byte[] bytes) {
		client.send(bytes);
	}

}