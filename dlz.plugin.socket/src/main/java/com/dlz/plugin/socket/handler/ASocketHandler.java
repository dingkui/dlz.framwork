package com.dlz.plugin.socket.handler;

import java.net.Socket;

import com.dlz.plugin.socket.interfaces.ASocketIO;
import com.dlz.plugin.socket.interfaces.IDealService;

public abstract class ASocketHandler implements Runnable {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
		public ASocketHandler(Socket socket, IDealService dealService,ASocketIO sio) {}
}