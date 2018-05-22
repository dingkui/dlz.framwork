package com.dlz.netty.test;

import java.util.Scanner;

import com.dlz.plugin.netty.NettyClientUtil;
import com.dlz.plugin.socket.interfaces.ISocketListener;

public class ClientTest {
	static int cnt=0;
	public static void main(String[] args) {
		NettyClientUtil.init("test", true, 8080, "127.0.0.1", new ISocketListener() {
			@Override
			public String deal(String reciveStr) {
				System.out.println("客户端收到消息：" + reciveStr+"   "+cnt++);
				return null;
			}
		});
		
		Scanner input = new Scanner(System.in);
		while (true) {
			NettyClientUtil.send(input.nextLine());
		}
	}
}
