package com.dlz.netty.test;

import java.util.Scanner;

import com.dlz.plugin.netty.NettyClient;
import com.dlz.plugin.netty.listener.ANettyClientListener;

public class ClientSynTest {
	public static void main(String[] args) {
		NettyClient.init(8080, "127.0.0.1", new ANettyClientListener() {
			@Override
			public void recive(String reciveStr) {
				System.out.println("我是客户端，收到服务器消息：" + reciveStr);
			}
		});
		
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println(NettyClient.synSend(input.nextLine()));
		}
	}
}
