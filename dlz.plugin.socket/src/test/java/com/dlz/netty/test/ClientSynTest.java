package com.dlz.netty.test;

import java.util.Scanner;

import com.dlz.plugin.netty.NettyClient;

public class ClientSynTest {
	public static void main(String[] args) {
		NettyClient.init(8080, "127.0.0.1",null);
		
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println(NettyClient.synSend(input.nextLine()));
		}
	}
}
