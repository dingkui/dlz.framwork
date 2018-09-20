package com.dlz.plugin.socket.test.pressure;

import java.util.Date;

import org.junit.Test;

import com.dlz.plugin.socket.conn.normal.SocketClient;
import com.dlz.plugin.socket.io.ScoketIOCompress;
import com.dlz.plugin.socket.io.ScoketIOLine;

/**
 * 服务器测试程序
 * @author dk
 */
public class TestClient{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	@Test
	public void test1() throws Exception {
		SocketClient client=new SocketClient("127.0.0.1",9999,new ScoketIOLine());
		long d1=new Date().getTime();
		for(int i=0;i<10000;i++){
			//System.out.println(client.getRespose("send:"+i));
			try{
				client.getRespose("se:"+i);
				//Thread.sleep(1);
			}catch(Exception e){
				System.out.println("aaa:"+i);
				throw e;
			}
		}
		System.out.println(new Date().getTime()-d1);
		
		d1=new Date().getTime();
		for(int i=0;i<10000;i++){
			//System.out.println(client.getRespose("se2:"+i));
			try{
				client.getRespose("se:"+i);
				//Thread.sleep(1);
			}catch(Exception e){
				System.out.println("bbb:"+i);
				throw e;
			}
		}
		System.out.println(new Date().getTime()-d1);
	}
	@Test
	public void test2() throws Exception {
		SocketClient client = new SocketClient("127.0.0.1", 9999, new ScoketIOCompress());
		for(int i=0;i<10000;i++){
			System.out.println(client.getRespose("111"+i));
		}
	}
	
}
