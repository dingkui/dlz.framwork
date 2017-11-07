package com.dlz.plugin.socket.conn.normal;

import java.io.IOException;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.dlz.plugin.socket.interfaces.ASocketClient;
import com.dlz.plugin.socket.interfaces.ISocketIO;

public class SocketClient  extends ASocketClient{
	private static Logger logger = Logger.getLogger(SocketClient.class);

	public SocketClient(String server,int port,ISocketIO sio) {
		super(server, port, sio);
	}

	/**
	 * 一次读取一行取得信息
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public String getRespose(String s) throws IOException {
		Socket socket = null;
		try{
			socket=new Socket(server, port);
			sio.write(socket.getOutputStream(), s);
			return sio.read(socket.getInputStream());
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			throw e;
		}finally{
			if(socket!=null){
				socket.close();
			}
		}
	}
}
