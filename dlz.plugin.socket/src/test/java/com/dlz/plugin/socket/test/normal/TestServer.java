package com.dlz.plugin.socket.test.normal;

import java.io.IOException;

import com.dlz.plugin.socket.conn.normal.SocketServer;
import com.dlz.plugin.socket.deal.DealServiceTest1;
import com.dlz.plugin.socket.interfaces.ASocketServer;
import com.dlz.plugin.socket.io.ScoketIOSizeByte;

/**
 * 服务器测试程序
 * @author dk
 */
public class TestServer{
	public static void main(String[] args) throws IOException {
		ASocketServer server = new SocketServer(9999,1000, new DealServiceTest1(),new ScoketIOSizeByte());
		server.start();
	}
}
