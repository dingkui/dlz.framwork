package com.dlz.plugin.socket.handler;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.socket.constance.SocketConstance;
import com.dlz.plugin.socket.interfaces.ASocketIO;
import com.dlz.plugin.socket.interfaces.IDealService;


public abstract class SocketHandlerWithHolder extends ASocketHandler {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static MyLogger logger = MyLogger.getLogger(SocketHandlerWithHolder.class);
	public SocketHandlerWithHolder(Socket socket, IDealService dealService, ASocketIO sio) {
		super(socket, dealService, sio);
	}

	protected Socket holderSocket;
	private ASocketIO holderSio;
	// 长链接请求标示
	private static Set<SocketHandlerWithHolder> holderHandlerSet = new HashSet<SocketHandlerWithHolder>();
	
	// 返回信息给Client端
	public static void notifyClients(String recive) throws IOException {
		synchronized (SocketHandlerWithHolder.class) {
			for(SocketHandlerWithHolder handler:holderHandlerSet){
				handler.notifyClient(recive);
			}
		}
	}
	
	// 清除出错的Client端
	public static void removeClient(SocketHandlerWithHolder handler) {
		synchronized (SocketHandlerWithHolder.class) {
			holderHandlerSet.remove(handler);
			logger.debug("减少客户端，当前客户端数："+holderHandlerSet.size());
		}
	}

	public void addHolder(Socket socket, ASocketIO sio) throws IOException {
		holderSocket = socket;
		holderSio = sio;
		notifyClient(SocketConstance.HOLDER);
		doHart(20000);
		holderHandlerSet.add(this);
		logger.debug("增加客户端，当前客户端数："+holderHandlerSet.size());
	}
	
	boolean isDestroy=false;
	// 返回信息给Client端
	private synchronized void notifyClient(String recive){
		if(isDestroy){
			return;
		}
		try {
			if (holderSocket==null || holderSocket.isClosed()) {
				clientNotifySevDetroy();
				return;
			}
			holderSio.write(holderSocket.getOutputStream(), recive);
		} catch (IOException e) {
			logger.error("客户端输出异常：{0}:{1,number,#},{2}" ,holderSocket.getInetAddress().getHostName(),holderSocket.getPort(),e.getMessage());
			clientNotifySevDetroy();
		}
	}
	
	public void clientNotifySevDetroy(){
		isDestroy=true;
		if (holderSocket!=null && !holderSocket.isClosed()) {
			try{
				holderSocket.getOutputStream().close();
			}catch(IOException e){
				logger.error("关闭失败" ,e);
			}
			try{
				holderSocket.close();
			}catch(IOException e){
				logger.error("关闭失败" ,e);
			}
		}
		holderSocket = null;
		holderSio = null;
		removeClient(this);
	}
	
	Timer timer = new Timer();
	// 心跳通讯
	private void doHart(long hartTime)  {
		if(isDestroy){
			return;
		}
		timer.schedule(new TimerTask() {
			public void run() {
				if(isDestroy){
					return;
				}
//				holderSocket.sendUrgentData(0xFF);
				notifyClient(SocketConstance.DOHART);
				doHart(hartTime);
			}
		}, hartTime);
	}
}
