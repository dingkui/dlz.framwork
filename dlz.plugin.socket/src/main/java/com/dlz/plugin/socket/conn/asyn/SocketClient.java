package com.dlz.plugin.socket.conn.asyn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.dlz.plugin.socket.interfaces.ASocketClient;
import com.dlz.plugin.socket.interfaces.IClientDealService;
import com.dlz.plugin.socket.interfaces.ISocketIO;

/**
 * 线程池客户端
 * @author dingkui
 *
 */
public class SocketClient extends ASocketClient{
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
	public void send(String s,IClientDealService dealService) throws IOException {
		SocketProxy socket =null;
		InputStream socketIn=null;
		OutputStream socketOut=null;
		try{
			socket = SocketHolder.getSocket(server, port);
			socketOut=socket.getOutputStream();
			socketIn=socket.getInputStream();
			sio.write(socketOut, s);
			new Thread(new ResultLisener(socketIn, dealService, sio) {
				@Override
				public void run() {
					try {
						String r=sio.read(socketIn);
						if(r!=null){
							dealService.deal(r);
						}
					} catch (IOException e) {
						logger.error(e.getMessage(),e);
					}
				}
			}).start();
		}catch(Exception e1){
			logger.error(e1.getMessage(),e1);
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
	
	protected abstract class ResultLisener implements Runnable {
		protected InputStream socketIn;
		public ResultLisener(InputStream socketIn, IClientDealService dealService,ISocketIO sio) {this.socketIn=socketIn;}
	}
	
	static class SocketHolder {
		private static volatile byte[] syn = new byte[0];
		private static int i= 0;
		private static Map<String,Set<SocketProxy>> serverSocket =new HashMap<String,Set<SocketProxy>>();
		
		static SocketProxy getSocket(String server,int port) throws UnknownHostException, IOException {
			synchronized (syn) {
				
				Set<SocketProxy> usefull=serverSocket.get(server+port+"usefull");
				Set<SocketProxy> useing=serverSocket.get(server+port+"useing");
				if(usefull==null){
					usefull=new HashSet<SocketProxy>();
					useing=new HashSet<SocketProxy>();
					serverSocket.put(server+port+"usefull", usefull);
					serverSocket.put(server+port+"useing", useing);
				}
				
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
				return socket;
			}
		}
		static void closeSocket(SocketProxy socket){
			synchronized (syn) {
				serverSocket.get(socket.getHostKey()+"usefull").add(socket);
				serverSocket.get(socket.getHostKey()+"useing").remove(socket);
				syn.notifyAll();
			}
		}
		static void destroySocket(SocketProxy socket){
			synchronized (syn) {
				serverSocket.get(socket.getHostKey()+"usefull").remove(socket);
				serverSocket.get(socket.getHostKey()+"useing").remove(socket);
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

	@Override
	public String getRespose(String s) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
}
