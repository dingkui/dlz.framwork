package com.dlz.plugin.socket.test.normal;

import com.dlz.plugin.socket.conn.normal.SocketClient;
import com.dlz.plugin.socket.interfaces.ISocketListener;
import com.dlz.plugin.socket.io.ScoketIOSizeByte;
import com.dlz.plugin.socket.test.threads.TestThread;

/**
 * 服务器测试程序
 * @author dk
 */
public class TestClient{
	public static void main(String[] args) throws Exception {
		SocketClient client = new SocketClient("127.0.0.1", 9999, new ScoketIOSizeByte(),new ISocketListener() {
			public void deal(String postStr) {
				System.out.println("反馈信息:"+postStr);
			}
		});
		for(int i=0;i<1000;i++){
			Thread c=new TestThread(client,"123_"+i,i);
			c.start();
		}
	}
}
