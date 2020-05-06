package com.dlz.plugin.socket.handler;

import java.net.Socket;

import com.dlz.plugin.socket.interfaces.ASocketIO;
import com.dlz.plugin.socket.interfaces.IDealService;

public abstract class ASocketHandler implements Runnable {

		public ASocketHandler(Socket socket, IDealService dealService,ASocketIO sio) {}
}