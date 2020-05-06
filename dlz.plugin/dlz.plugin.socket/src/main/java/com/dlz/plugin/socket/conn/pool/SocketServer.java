package com.dlz.plugin.socket.conn.pool;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import org.slf4j.Logger;
import com.dlz.plugin.socket.handler.ASocketHandler;
import com.dlz.plugin.socket.interfaces.ASocketIO;
import com.dlz.plugin.socket.interfaces.ASocketServer;
import com.dlz.plugin.socket.interfaces.IDealService;


 public class SocketServer extends ASocketServer{
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
				OutputStream socketOut=null;
				try {
					boolean real=true;
					while(real){
						// 得到客户端发送的信息
						socketIn = socket.getInputStream();
						
						logger.info("server read begin");
						String actionInfo = sio.read(socketIn);
						logger.info("server read end:"+actionInfo);
						
						//logger.info("收到来自【" + socket.getInetAddress() + ":" + socket.getPort() + "】的监听请求：" + actionInfo);
						if (actionInfo != null) {
							String str = dealService.getResStr(actionInfo);
							socketOut=socket.getOutputStream();
							// 返回信息给Client端
							logger.info("server write begin："+str);
							sio.write(socketOut, str);
							logger.info("server write begin");
						}else{
							socketOut=socket.getOutputStream();
							sio.write(socketOut,"bye bye!");
							real=false;
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
