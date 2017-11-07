package com.dlz.plugin.socket.conn.asyn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.socket.conn.asyn.SocketClient.SocketHolder;
import com.dlz.plugin.socket.conn.asyn.SocketClient.SocketProxy;
import com.dlz.plugin.socket.interfaces.ASocketClient;
import com.dlz.plugin.socket.interfaces.IClientDealService;
import com.dlz.plugin.socket.interfaces.ISocketIO;


public class AsynClient extends ASocketClient{
	private static MyLogger logger = MyLogger.getLogger(AsynClient.class);
	
	private SenderDeal sender;
	SocketProxy socket =null;

	/**
	 * 构造Socket服务
	 * @param serverPort 服务端口
	 * @param poolSize 线程池大小
	 * @param charsetName 字符
	 * @param socketSevice 接口处理类 
	 * @param sio 接口读取类
	 * @throws IOException
	 */
	public AsynClient(String serverPort,int port,ISocketIO sio) throws IOException {
		super(serverPort, port, sio);
	}
	
	public void init(IClientDealService dealService) throws IOException{
		socket = SocketHolder.getSocket(server, port);
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
		
		new Thread(new ResultLisener(socket.getInputStream(), dealService, sio) {
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
	}

	protected abstract class SenderDeal implements Runnable {
		SocketProxy socket;
		ISocketIO sio;
		public SenderDeal(SocketProxy socket,ISocketIO sio) {
			this.sio=sio;
			this.socket=socket;
		}
		public abstract void send(String sendStr);
	}
	protected abstract class ResultLisener implements Runnable {
		protected InputStream socketIn;
		public ResultLisener(InputStream socketIn, IClientDealService dealService,ISocketIO sio) {this.socketIn=socketIn;}
	}

	@Override
	public String getRespose(String s) throws IOException {
		sender.send(s);
		return null;
	}
}
