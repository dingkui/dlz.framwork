package com.dlz.plugin.socket.test.threads;

import java.util.Date;
import java.util.Random;

import com.dlz.plugin.socket.handler.SocketHandlerWithHolder;
import com.dlz.plugin.socket.interfaces.ASocketClient;

public class TestThread4 extends Thread {

	static int all = 0;
	static long allt = new Date().getTime();
	ASocketClient client = null;
	String msg = null;
	int id = 0;
	
	

	public TestThread4(int id) {
		super();
		this.id = id;
	}

	@Override
	public void run() {
		Long t=(new Random()).nextLong()%1000;
		if(t<0){
			t*=-1;
		}
		try {
			Thread.sleep(t*100);
			SocketHandlerWithHolder.notifyClients(id+"aa");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}