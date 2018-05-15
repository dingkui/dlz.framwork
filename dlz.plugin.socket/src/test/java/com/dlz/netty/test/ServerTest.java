package com.dlz.netty.test;

import java.util.Scanner;

import com.dlz.plugin.netty.NettyServer;
import com.dlz.plugin.socket.interfaces.ISocketListener;

public class ServerTest {
	public static void main(String[] args) {
		NettyServer nettyServer = new NettyServer(8080, new ISocketListener() {
			@Override
			public String deal(String reciveStr) {
				return "我是服务器，你发给我的消息是：" + reciveStr;
			}
		});
		Scanner input = new Scanner(System.in);
		while (true) {
			nettyServer.broadMsg(input.nextLine());
		}
	}
}
 