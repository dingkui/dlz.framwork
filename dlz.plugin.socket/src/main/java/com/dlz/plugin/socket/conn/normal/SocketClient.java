package com.dlz.plugin.socket.conn.normal;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.socket.constance.SocketConstance;
import com.dlz.plugin.socket.interfaces.ASocketClient;
import com.dlz.plugin.socket.interfaces.ASocketIO;
import com.dlz.plugin.socket.interfaces.ISocketListener;

public class SocketClient  extends ASocketClient{
	private static MyLogger logger = MyLogger.getLogger(SocketClient.class);

	public SocketClient(String server,int port,ASocketIO sio) {
		super(server, port, sio);
	}
	public SocketClient(String server,int port,ASocketIO sio,ISocketListener listener){
		super(server, port, sio);
		if(listener!=null){
			addMessageListener(listener);
		}
	}
	public static void setMax(int max) {
		MAX = max<1?1:max;
	}

	private static int MAX=100;
	private static int running=0;
	

	private static int listenTimes=0;
	private static int retryTime=5000;
	
	public void addMessageListener(ISocketListener listener){
		new Thread(new Runnable() {
			@Override
			public void run() {
				Socket socket=null;
				try{
					socket=new Socket(server, port);
					sio.write(socket.getOutputStream(), SocketConstance.HOLDER);
					String recive=sio.read(socket.getInputStream());
					if(SocketConstance.HOLDER.equals(recive)){
						logger.debug("socket监听成功："+server+":"+port);
						listenTimes=1;
						while(recive!=null){
							recive=sio.read(socket.getInputStream());
							if(recive!=null && !recive.startsWith(SocketConstance.DOHART)){
								listener.deal(recive);
							}
						}
						logger.error("socket监听数据为空");
					}
				}catch(ConnectException e){
					logger.error("socket连接失败:"+e.getMessage());
					logger.error("监听中断："+server+":"+port);
				}catch(SocketException e){
					logger.error("socket通讯失败:"+e.getMessage());
					logger.error("监听中断："+server+":"+port);
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
						logger.warn("socket重新监听，第"+listenTimes+"次");
						addMessageListener(listener);
					}else{
						logger.error("socket连接超时，关闭监听!");
					}
				}
			}
		}).start();
	}

	/**
	 * 一次读取一行取得信息
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public String getRespose(String s) throws IOException {
		if(running>=MAX){
			try {
				Thread.sleep(1000);
				return getRespose(s);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(),e);
			}
		}
		running++;
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
			running--;
		}
	}
}
