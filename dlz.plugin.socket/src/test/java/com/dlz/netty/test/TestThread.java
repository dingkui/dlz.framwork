package com.dlz.netty.test;

import java.util.Date;

import com.dlz.plugin.netty.NettyServer;

public class TestThread extends Thread {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	static int all = 0;
	static long allt = new Date().getTime();
	NettyServer client = null;
	String msg = null;
	int id = 0;

	public TestThread(NettyServer client, String msg, int id) {
		super();
		this.client = client;
		this.msg = msg;
		this.id = id;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			all++;
			try {
				client.broadMsg(msg+"-"+id+"-" + i);
			} catch (Exception e) {
				System.out.println("aaa:" + i);
				throw e;
			}
		}
	}
}