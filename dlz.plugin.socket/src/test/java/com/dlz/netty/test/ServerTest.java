package com.dlz.netty.test;

import java.util.Scanner;

import com.dlz.plugin.netty.NettyServer;
import com.dlz.plugin.netty.listener.ANettyServerListener;

public class ServerTest {
	public static void main(String[] args) {
		NettyServer nettyServer = new NettyServer(8080, new ANettyServerListener() {
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
