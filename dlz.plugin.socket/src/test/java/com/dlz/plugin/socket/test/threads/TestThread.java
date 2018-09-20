package com.dlz.plugin.socket.test.threads;

import java.io.IOException;
import java.util.Date;

import com.dlz.plugin.socket.interfaces.ASocketClient;

public class TestThread extends Thread {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	static int all = 0;
	static long allt = new Date().getTime();
	ASocketClient client = null;
	String msg = null;
	int id = 0;

	public TestThread(ASocketClient client, String msg, int id) {
		super();
		this.client = client;
		this.msg = msg;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 100; i++) {
				all++;
				try {
					@SuppressWarnings("unused")
					String r=client.getRespose(msg);
				} catch (Exception e) {
					System.out.println("aaa:" + i);
					throw e;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}