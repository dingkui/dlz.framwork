package com.dlz.plugin.socket.test.threads;

import java.io.IOException;
import java.util.Date;

import com.dlz.plugin.socket.interfaces.ASocketClient;

public class TestThread extends Thread {
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
			for (int i = 0; i < 10; i++) {
				try {
					String r=client.getRespose(msg);
				} catch (Exception e) {
					System.out.println("aaa:" + i);
					throw e;
				}
				//System.out.println(id + " all:" + (all++) + ":" + (new Date().getTime() - allt));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}