package com.dlz.plugin.socket.conn.asyn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import com.dlz.plugin.socket.handler.ASocketHandler;
import com.dlz.plugin.socket.interfaces.ASocketIO;
import com.dlz.plugin.socket.interfaces.ASocketServer;
import com.dlz.plugin.socket.interfaces.IDealService;


public class SocketServer extends ASocketServer{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(SocketServer.class);
	/**
	 * 构造Socket服务
	 * @param serverPort 服务端口
	 * @param poolSize 线程池大小
	 * @param charsetName 字符
	 * @param socketSevice 接口处理类 
	 * @param sio 接口读取类
	 * @throws IOException
	 */
	public SocketServer(int serverPort,int poolSize, IDealService socketSevice,ASocketIO sio) throws IOException {
		super(serverPort, poolSize, socketSevice, sio);
	}
	@Override
	protected Class<?> getClientClass() {
		return SocketClient.class;
	}
	@Override
	protected ASocketHandler getHandler(Socket socket, IDealService dealService, ASocketIO sio) {
		return new ASocketHandler(socket, dealService, sio) {
			public void run() {
				InputStream socketIn=null;
				try {
					boolean real=true;
					while(real){
						// 得到客户端发送的信息
						socketIn = socket.getInputStream();
						logger.info("server {0}read begin",socket.getPort());
						String actionInfo = sio.read(socketIn);
						logger.info("server {0}read end:"+actionInfo,socket.getPort());
						
						//logger.info("收到来自【" + socket.getInetAddress() + ":" + socket.getPort() + "】的监听请求：" + actionInfo);
						if (actionInfo != null) {
							new Thread(new ASocketDeal(socket, actionInfo, dealService, sio) {
								@Override
								public void run() {
									OutputStream socketOut=null;
									try{
										String str = dealService.getResStr(actionInfo);
										socketOut=socket.getOutputStream();
										// 返回信息给Client端
										logger.info("server write begin："+str);
										sio.write(socketOut, str);
										logger.info("server write end");
									} catch (SocketException e) {
										logger.warn(socket.getInetAddress()+" "+e.getMessage());
									} catch (IOException e) {
										logger.error(e.getMessage(),e);
									}
								}
							}).start();
						}
					}
				} catch (SocketException e) {
					logger.warn(socket.getInetAddress()+" "+e.getMessage());
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				} finally {
					try {
						if(socketIn!=null){
							socketIn.close();
						}
					} catch (IOException e) {
						logger.error(e.getMessage(),e);
					}
					try {
						if (socket != null){
							socket.close();
						}
					} catch (Exception e) {
						logger.error(e.getMessage(),e);
					}
				}
			}
		};
	}
	
	protected abstract class SenderDeal implements Runnable {
		Socket socket;
		ASocketIO sio;
		public SenderDeal(Socket socket,ASocketIO sio) {
			this.sio=sio;
			this.socket=socket;
		}
		public abstract void send(String sendStr);
	}
	
	protected abstract class ASocketDeal implements Runnable {
		private SenderDeal sender;
		public ASocketDeal(Socket socket,String input, IDealService dealService,ASocketIO sio) {
			sender=new SenderDeal(socket,sio) {
				private Queue<String> sendS= new LinkedBlockingQueue<String>();
				@Override
				public void run() {
					OutputStream socketOut=null;
					boolean send=true;
					try{
						socketOut=socket.getOutputStream();
						while(send){
							String str=sendS.poll();
							if(str!=null){
								sio.write(socketOut, str);
								System.out.println(sendS.size());
							}else{
								Thread.sleep(1000);
								System.out.println("doing is null");
							}
						}
					} catch (SocketException e) {
						logger.warn(socket.getInetAddress()+" "+e.getMessage());
					} catch (IOException e) {
						logger.error(e.getMessage(),e);
					} catch (InterruptedException e) {
						logger.error(e.getMessage(),e);
					}
				}
				public void send(String sendStr) {
					sendS.add(sendStr);
				}
			};
			new Thread(sender).start();
		}
	}
}
