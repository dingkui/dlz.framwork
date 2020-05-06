package com.dlz.plugin.socket.conn.asyn;

import com.dlz.plugin.socket.conn.asyn.SocketClient.SocketProxy;
import com.dlz.plugin.socket.interfaces.ASocketIO;

abstract class SenderDeal implements Runnable {
	SocketProxy socket;
	ASocketIO sio;
	public SenderDeal(SocketProxy socket,ASocketIO sio) {
		this.sio=sio;
		this.socket=socket;
	}
	public abstract void send(String sendStr);
}