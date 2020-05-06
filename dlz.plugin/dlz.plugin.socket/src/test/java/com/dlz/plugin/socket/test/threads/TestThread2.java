package com.dlz.plugin.socket.test.threads;

import com.dlz.plugin.socket.interfaces.ASocketClient;

public class TestThread2 extends Thread {

	ASocketClient client = null;
	String msg = null;

	public TestThread2(ASocketClient client, String msg) {
		super();
		this.client = client;
		this.msg = msg;
	}

	@Override
	public void run() {
		try {
			// System.out.println(client.getRespose(msg));
			System.out.println(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}