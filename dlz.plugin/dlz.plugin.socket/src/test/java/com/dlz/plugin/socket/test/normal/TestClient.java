package com.dlz.plugin.socket.test.normal;

import java.util.Date;

import com.dlz.plugin.socket.conn.normal.SocketClient;
import com.dlz.plugin.socket.interfaces.ISocketListener;
import com.dlz.plugin.socket.io.ScoketIOLine;

/**
 * 服务器测试程序
 * @author dk
 */
public class TestClient{

	public static void main(String[] args) throws Exception {
		SocketClient client = new SocketClient("127.0.0.1", 9999, new ScoketIOLine(),new ISocketListener() {
			int i=0;
			long allt = new Date().getTime();
			public String deal(String postStr) {
				if(i++%10000==0){
					System.out.println("客户端收到："+i+" 时间:"+(new Date().getTime()-allt));
					allt = new Date().getTime();
				}
				return null;
			}
		});
//		for(int i=0;i<1000;i++){
//			Thread c=new TestThread(client,"123_"+i,i);
//			c.start();
//		}
	}
}
