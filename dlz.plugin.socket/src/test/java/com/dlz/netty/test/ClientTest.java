package com.dlz.netty.test;

import java.util.Scanner;

import com.dlz.plugin.netty.NettyClient;
import com.dlz.plugin.socket.interfaces.ISocketListener;

public class ClientTest {
	public static void main(String[] args) {
		NettyClient.init(8080, "127.0.0.1", new ISocketListener() {
			@Override
			public String deal(String reciveStr) {
				System.out.println("我是客户端，收到服务器消息：" + reciveStr);
				return null;
			}
		});
		
		Scanner input = new Scanner(System.in);
		while (true) {
			NettyClient.send(input.nextLine());
		}
	}
}
