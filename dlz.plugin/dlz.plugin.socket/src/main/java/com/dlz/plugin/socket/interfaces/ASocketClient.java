package com.dlz.plugin.socket.interfaces;

import java.io.IOException;

public abstract class ASocketClient{

	protected ASocketIO sio;
	protected String server;
	protected int port;

	public ASocketClient(String server,int port,ASocketIO sio) {
		this.server=server;
		this.port=port;
		this.sio=sio;
	}

	/**
	 * 从服务器端请求到信息（输入再取得参数）
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public abstract String getRespose(String s) throws IOException;
}