package com.dlz.plugin.socket.test.pool;

import java.io.IOException;

import com.dlz.plugin.socket.deal.DealServiceTest1;
import com.dlz.plugin.socket.interfaces.ASocketServer;
import com.dlz.plugin.socket.io.ScoketIOCompress;

/**
 * 服务器测试程序
 * @author dk
 */
public class TestServer{

	public static void main(String[] args) throws IOException {
//		SocketServerManager.startServer("test", 9999, 1, new DealServiceTest1(), new ScoketIOLine());
	//	SocketServerManager.startServer("test", 9999, 10, new DealServiceTest1(), new ScoketIOCompress());
//		SocketServerManager.startServer("test", 9999, 1, new DealServiceTest1(), new ScoketIOSizeByte());
		
		ASocketServer server = new com.dlz.plugin.socket.conn.normal.SocketServer(9999,10, new DealServiceTest1(),new ScoketIOCompress());
		server.start();
	}
}
