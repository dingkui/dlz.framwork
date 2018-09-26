package com.dlz.plugin.socket.interfaces;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import com.dlz.plugin.socket.handler.ASocketHandler;

public abstract class ASocketServer  extends Thread{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(ASocketServer.class);

	private ServerSocket serverSocket;
	private ExecutorService executorService;// 线程池
	private IDealService dealService = null;//处理业务数据的类
	private ASocketIO sio = null;//socket数据读取
	private boolean run = true;
	private String serverInfo="端口[{0,number,#}],线程池[{1,number,#}],客户端[{2}],处理类[{3}],读取类[{4}]";
	
	protected abstract Class<?> getClientClass();

	/**
	 * 构造Socket服务
	 * @param serverPort 服务端口
	 * @param poolSize 线程池大小
	 * @param charsetName 字符
	 * @param dealService 接口处理类 
	 * @param sio 接口读取类
	 * @throws IOException
	 */
	public ASocketServer(int serverPort,int poolSize, IDealService dealService,ASocketIO sio) throws IOException {
		this.dealService = dealService;
		this.sio=sio;
		try {
			serverSocket = new ServerSocket(serverPort);
			executorService = Executors.newFixedThreadPool(poolSize);
			serverInfo=MessageFormat.format(serverInfo,serverPort,poolSize,getClientClass().getName(),dealService.getClass().getName(),sio.getClass().getName());
		} catch (IOException e) {
			String s = "监听服务器启动 失败，可能是端口" + serverPort + "已被占用，请重置端口" + serverPort + "后重试";
			logger.error(e.getMessage() + " " + s);
			throw new IOException(s, e);
		}
	}

	public void stopServer() {
		run = false;
		try {
			if (serverSocket != null) {
				serverSocket.close();
				executorService.shutdown();
				logger.info("监听服务器停止成功：" + serverInfo);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}

	public void run() {
		logger.info("监听服务器启动成功：" + serverInfo);
		try {
			while (run) {
				Socket clientSocket = null;
				try {
					// 接收客户连接,只要客户进行了连接,就会触发accept();从而建立连接
					clientSocket = serverSocket.accept();
					//logger.info("客户端接入：{0}:{1,number,#}" ,clientSocket.getInetAddress().getHostName(),clientSocket.getPort());
					executorService.execute(getHandler(clientSocket, dealService,sio));
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (Exception ex1) {
					logger.error(ex1.getMessage(),ex1);
				}
			}
		}
	}
	
	/**
	 * 取得服务器端处理程序（取得输入，相应处理并输出）
	 * @param socket
	 * @param ss  信息业务处理
	 * @param sio 数据读写接口
	 * @return
	 */
	protected abstract ASocketHandler getHandler(Socket socket, IDealService ss,ASocketIO sio);
}