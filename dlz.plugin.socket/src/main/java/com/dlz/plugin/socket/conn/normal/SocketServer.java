package com.dlz.plugin.socket.conn.normal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.socket.interfaces.ASocketServer;
import com.dlz.plugin.socket.interfaces.IDealService;
import com.dlz.plugin.socket.interfaces.ISocketIO;


public class SocketServer extends ASocketServer {
	private static MyLogger logger = MyLogger.getLogger(SocketServer.class);

	/**
	 * 构造Socket服务
	 * @param serverPort 服务端口
	 * @param poolSize 线程池大小
	 * @param charsetName 字符
	 * @param dealService 接口处理类 
	 * @param sio 接口读取类
	 * @throws IOException
	 */
	public SocketServer(int serverPort,int poolSize, IDealService dealService,ISocketIO sio) throws IOException {
		super(serverPort, poolSize, dealService, sio);
	}

	@Override
	protected ASocketHandler getHandler(Socket socket, IDealService dealService, ISocketIO sio) {
		return new ASocketHandler(socket, dealService, sio) {
			public void run() {
				InputStream socketIn=null;
				OutputStream socketOut=null;
				try {
					// 得到客户端发送的信息
					socketIn = socket.getInputStream();
					String actionInfo = sio.read(socketIn);
					//logger.info("收到来自【" + socket.getInetAddress() + ":" + socket.getPort() + "】的监听请求：" + actionInfo);
					if (actionInfo != null) {
						String str = dealService.getResStr(actionInfo);
						socketOut=socket.getOutputStream();
						// 返回信息给Client端
						sio.write(socketOut, str);
					}else{
						socketOut=socket.getOutputStream();
						logger.error("socket 非法访问:"+socket.getInetAddress()+":" + socket.getPort());
						socketOut.write("error!".getBytes());
//						sio.write(socketOut, "bye bye!");
					}
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
						if(socketOut!=null){
							socketOut.close();
							socketOut=null;
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

}
