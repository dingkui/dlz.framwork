package com.dlz.plugin.socket.conn.pool;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.socket.interfaces.ASocketClient;
import com.dlz.plugin.socket.interfaces.ASocketIO;

/**
 * 线程池客户端
 * @author dingkui
 *
 */
 class SocketClient extends ASocketClient{
	 private static MyLogger logger = MyLogger.getLogger(SocketClient.class);
	
	public SocketClient(String server,int port,ASocketIO sio) {
		super(server, port, sio);
	}

	/**
	 * 一次读取一行取得信息
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public String getRespose(String s) throws IOException {
		SocketProxy socket =null;
		InputStream socketIn=null;
		OutputStream socketOut=null;
		try{
			socket = SocketHolder.getSocket(server, port);
			socketIn=socket.getInputStream();
			socketOut=socket.getOutputStream();
			return sio.client(socketOut, s, socketIn);
		}catch(Exception e1){
			logger.error(e1.getMessage(),e1);
			try {
				if(socketIn!=null){
					socketIn.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
			try {
				if(socketOut!=null){
					socketOut.close();
					socketOut=null;
				}
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
			if(socket!=null){
				socket.destroy();
			}
			throw e1;
		}finally{
			if(socket!=null){
				socket.close();
			}
		}
	}
	
	static class SocketHolder {
		private static volatile byte[] syn = new byte[0];
		private static int i= 0;
		private static Set<SocketProxy> usefull=new HashSet<SocketProxy>();
		private static Set<SocketProxy> useing=new HashSet<SocketProxy>();
		static SocketProxy getSocket(String server,int port) throws UnknownHostException, IOException {
			synchronized (syn) {
				if(usefull.isEmpty()){
					SocketProxy socket = new SocketProxy(server, port,++i);
					useing.add(socket);
					syn.notifyAll();
					return socket;
				}
				SocketProxy socket=usefull.iterator().next();
				usefull.remove(socket);
				useing.add(socket);
				syn.notifyAll();
				System.out.println("socket编号："+socket.getIndex());
				return socket;
			}
		}
		static void closeSocket(SocketProxy socket){
			synchronized (syn) {
				usefull.add(socket);
				useing.remove(socket);
				syn.notifyAll();
			}
		}
		static void destroySocket(SocketProxy socket){
			synchronized (syn) {
				usefull.remove(socket);
				useing.remove(socket);
				syn.notifyAll();
			}
		}
	}
	
	static class SocketProxy extends Socket {
		private String hostKey;
		private int index;
		public SocketProxy(String server,int port,int index) throws UnknownHostException, IOException {
			super(server, port);
			this.hostKey=server+port;
			this.index=index;
		}
		public int getIndex() {
			return index;
		}
		String getHostKey() {
			return hostKey;
		}
		@Override
		public void close() throws IOException {
			SocketHolder.closeSocket(this);
		}
		void destroy() throws IOException {
			this.close();
			SocketHolder.destroySocket(this);
		}
	}
}
