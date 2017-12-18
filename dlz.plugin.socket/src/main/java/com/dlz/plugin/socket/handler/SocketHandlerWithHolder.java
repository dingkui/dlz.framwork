package com.dlz.plugin.socket.handler;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.socket.constance.SocketConstance;
import com.dlz.plugin.socket.interfaces.ASocketIO;
import com.dlz.plugin.socket.interfaces.IDealService;

public abstract class SocketHandlerWithHolder extends ASocketHandler {
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
		for(SocketHandlerWithHolder handler:holderHandlerSet){
			handler.notifyClient(recive);
		}
	}

	public void addHolder(Socket socket, ASocketIO sio) throws IOException {
		holderSocket = socket;
		holderSio = sio;
		notifyClient(SocketConstance.HOLDER);
		doHart(20000);
		holderHandlerSet.add(this);
		logger.debug("holderHandlerSet数："+holderHandlerSet.size());
	}

	// 返回信息给Client端
	private void notifyClient(String recive) throws IOException {
		try {
			if (!holderSocket.isClosed()) {
				holderSio.write(holderSocket.getOutputStream(), recive);
			}else{
				logger.debug("客户端长链接关闭：{0}:{1,number,#}" ,holderSocket.getInetAddress().getHostName(),holderSocket.getPort());
				holderHandlerSet.remove(this);
			}
		} catch (IOException e) {
			logger.error("客户端输出异常：{0}:{1,number,#},{2}" ,holderSocket.getInetAddress().getHostName(),holderSocket.getPort(),e.getMessage());
			holderHandlerSet.remove(this);
			holderSocket.getOutputStream().close();
			holderSocket.close();
			throw e;
		}
	}

	// 心跳通讯
	private void doHart(long hartTime)  {
		SocketHandlerWithHolder that=this;
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(hartTime);
//					holderSocket.sendUrgentData(0xFF);
					notifyClient(SocketConstance.DOHART+holderSocket.getPort());
					doHart(hartTime);
				} catch (Exception e) {
					logger.error("心跳异常，关闭连接：{0}:{1,number,#}" ,holderSocket.getInetAddress().getHostName(),holderSocket.getPort(),e);
					holderHandlerSet.remove(that);
					logger.debug("holderHandlerSet数："+holderHandlerSet.size());
					if(!holderSocket.isClosed()){
						try {
							holderSocket.getOutputStream().close();
						} catch (IOException e1) {
							logger.error(e1.getMessage(),e1);
						}
						try {
							holderSocket.close();
						} catch (IOException e1) {
							logger.error(e1.getMessage(),e1);
						}
					}
				}
			}
		}).start();
	}
}
