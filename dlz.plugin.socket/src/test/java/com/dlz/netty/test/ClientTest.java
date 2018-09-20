package com.dlz.netty.test;

import java.util.Scanner;

import com.dlz.plugin.netty.NettyClientUtil;
import com.dlz.plugin.socket.interfaces.ISocketListener;

public class ClientTest {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	static int cnt=0;
	public static void main(String[] args) {
		NettyClientUtil.init("test", true, 8080, "127.0.0.1", new ISocketListener() {
			@Override
			public String deal(String reciveStr) {
				System.out.println("客户端收到消息："+reciveStr.length()+" " + reciveStr+"   "+cnt++);
				return null;
			}
		});
		
//		Scanner input = new Scanner(System.in);
//		while (true) {
//			NettyClientUtil.send(input.nextLine());
//		}
		
		Scanner input = new Scanner(System.in);
		while (true) {
			String nextLine = input.nextLine();
			//模拟100个线程向服务器发送消息
			for(int i=0;i<1;i++){
				new Thread(new Runnable() {
					@Override
					public void run() {
						//线程内发送100发送消息
						for (int j = 0; j < 100; j++) {
							try {
								NettyClientUtil.send(nextLine+"-" + j);
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
