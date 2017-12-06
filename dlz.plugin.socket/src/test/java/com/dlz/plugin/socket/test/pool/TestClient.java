package com.dlz.plugin.socket.test.pool;

import java.io.IOException;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.socket.conn.asyn.AsynClient;
import com.dlz.plugin.socket.interfaces.ISocketListener;
import com.dlz.plugin.socket.io.ScoketIOLine;

/**
 * 服务器测试程序
 * @author dk
 */
public class TestClient{
	private static MyLogger logger = MyLogger.getLogger(TestClient.class);
	public static void main(String[] args) throws IOException {
//		SocketClient client = new SocketClient("127.0.0.1", 9999, new ScoketIOLine());
		AsynClient client = new com.dlz.plugin.socket.conn.asyn.AsynClient("127.0.0.1", 9999, new ScoketIOLine());
		client.init(new ISocketListener() {
			@Override
			public void deal(String postStr) {
				logger.info("client deal:"+postStr);
			}
		});
//		SocketClient client = new SocketClient("127.0.0.1", 9999, new ScoketIOSizeByte());
		for(int i=0;i<10;i++){
			client.getRespose(""+i);
		}
	}
//	public static void main(String[] args) {
////		SocketClient client = new SocketClient("127.0.0.1", 9999, new ScoketIOLine());
////		SocketClient client = new SocketClient("127.0.0.1", 9999, new ScoketIOCompress());
//		SocketClient client = new SocketClient("127.0.0.1", 9999, new ScoketIOSizeByte());
//		for(int i=0;i<1;i++){
//			Thread c=new TestThread(client,"123",i);
//			c.start();
//		}
//	}
}
