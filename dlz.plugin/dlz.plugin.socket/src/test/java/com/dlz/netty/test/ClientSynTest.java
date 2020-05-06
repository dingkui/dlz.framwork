package com.dlz.netty.test;

import java.util.Scanner;

import com.dlz.plugin.netty.NettyClientUtil;

public class ClientSynTest {

	public static void main(String[] args) {
		NettyClientUtil.init("test", true, 8080, "127.0.0.1", null);
		
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println(NettyClientUtil.synSend(input.nextLine()));
		}
	}
}
