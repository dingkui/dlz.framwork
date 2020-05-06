package com.dlz.plugin.socket.conn.asyn;

import java.io.InputStream;

import com.dlz.plugin.socket.interfaces.ASocketIO;
import com.dlz.plugin.socket.interfaces.ISocketListener;

abstract class ResultLisener implements Runnable {
	protected InputStream socketIn;
	public ResultLisener(InputStream socketIn, ISocketListener dealService,ASocketIO sio) {this.socketIn=socketIn;}
}