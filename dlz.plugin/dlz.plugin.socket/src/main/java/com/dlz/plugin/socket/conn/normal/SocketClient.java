package com.dlz.plugin.socket.conn.normal;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

import org.slf4j.Logger;
import com.dlz.plugin.socket.constance.SocketConstance;
import com.dlz.plugin.socket.interfaces.ASocketClient;
import com.dlz.plugin.socket.interfaces.ASocketIO;
import com.dlz.plugin.socket.interfaces.ISocketListener;

public class SocketClient  extends ASocketClient{

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(SocketClient.class);

	public SocketClient(String server,int port,ASocketIO sio) {
		super(server, port, sio);
	}
	public SocketClient(String server,int port,ASocketIO sio,ISocketListener listener){
		super(server, port, sio);
		if(listener!=null){
			addMessageListener(listener,"创建监听("+server+":"+port+")");
		}
	}
	public void setMax(int max) {
		MAX = max<1?1:max;
	}

	private int MAX=20;
	private int running=0;
	

	private int listenTimes=0;
	private int retryTime=5000;
	
	public void addMessageListener(ISocketListener listener,String msg){
		new Thread(new Runnable() {
			@Override
			public void run() {
				Socket socket=null;
				try{
					socket=new Socket(server, port);
					sio.write(socket.getOutputStream(), SocketConstance.HOLDER);
					String recive=sio.read(socket.getInputStream());
					if(SocketConstance.HOLDER.equals(recive)){
						logger.debug(msg+"连接成功!");
						listenTimes=1;
						while(recive!=null){
							recive=sio.read(socket.getInputStream());
							if(recive!=null && !recive.startsWith(SocketConstance.DOHART)){
								listener.deal(recive);
							}
						}
						logger.error("服务器监听("+server+":"+port+")失败，关闭连接！");
					}
				}catch(ConnectException e){
					logger.error(msg+ "连接失败:"+e.getMessage());
				}catch(SocketException e){
					logger.error(msg+ "通讯失败:"+e.getMessage());
				}catch(Exception e){
					logger.error(e.getMessage(),e);
				}finally{
					if(socket!=null){
						try {
							socket.close();
						} catch (IOException e) {
							logger.error(e.getMessage(),e);
						}
					}
					socket=null;
					try {
						long waitTime=retryTime*listenTimes;
						if(waitTime>60000){
							waitTime=60000;
						}
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						logger.error(e.getMessage(),e);
					}
					if(listenTimes++<100){
						addMessageListener(listener,"连接重试第"+listenTimes+"次("+server+":"+port+")");
					}else{
						logger.error(msg+"连接超时，关闭重连!");
					}
				}
			}
		}).start();
	}
	
	private synchronized boolean getLock(int i) throws IOException {
		if(i==0){
			return running>MAX;
		}
		running+=i;
		return true;
	}

	/**
	 * 一次读取一行取得信息
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public String getRespose(String s) throws IOException {
		if(getLock(0)){
			try {
				Thread.sleep(500);
				return getRespose(s);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(),e);
			}
		}
		getLock(1);
		Socket socket = null;
		try{
			socket=new Socket(server, port);
			sio.write(socket.getOutputStream(), s);
			return sio.read(socket.getInputStream());
		}catch(Exception e){
			logger.error("running="+running,e);
			throw e;
		}finally{
			if(socket!=null){
				socket.close();
			}
			getLock(-1);
		}
	}
}
