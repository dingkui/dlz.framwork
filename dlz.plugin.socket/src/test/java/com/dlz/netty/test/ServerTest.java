package com.dlz.netty.test;

import java.util.Scanner;

import com.dlz.plugin.netty.NettyServer;
import com.dlz.plugin.socket.interfaces.ISocketListener;

public class ServerTest {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	public static void main(String[] args) {
		NettyServer nettyServer = new NettyServer(8080, new ISocketListener() {
			@Override
			public String deal(String reciveStr) {
				return "我是服务器，你发给我的消息是：" + reciveStr;
			}
		});
		
		Scanner input = new Scanner(System.in);
		while (true) {
			String nextLine = input.nextLine();
			//模拟100个线程向客户端推送消息
			for(int i=0;i<1;i++){
				new Thread(new Runnable() {
					@Override
					public void run() {
						String nextLine2=nextLine;
						nextLine2+=nextLine2;
						nextLine2+=nextLine2;
						nextLine2+=nextLine2;
						nextLine2+=nextLine2;
						nextLine2+=nextLine2;
						nextLine2+=nextLine2;
						nextLine2+=nextLine2;
						nextLine2+=nextLine2;
						nextLine2+=nextLine2;
						nextLine2+=nextLine2;
						//线程内发送100次推送
						for (int j = 0; j < 10; j++) {
							try {
								nettyServer.broadMsg(nextLine2+"-" + j);
							} catch (Exception e) {
								throw e;
							}
						}
					}
				}).start();
			}
		}
	}
}
 