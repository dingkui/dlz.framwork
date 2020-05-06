package com.dlz.plugin.socket.test.pressure;

import org.junit.Test;

import com.dlz.plugin.socket.deal.DealServiceTest1;
import com.dlz.plugin.socket.io.ScoketIOCompress;
import com.dlz.plugin.socket.io.ScoketIOLine;
import com.dlz.plugin.socket.io.ScoketIOSizeByte;
import com.dlz.plugin.socket.server.SocketServerManager;

/**
 * 服务器测试程序
 * @author dk
 */
public class TestServer{

	@Test
	public void testScoketIOLine() throws Exception {
		SocketServerManager.startServer("test", 9999, 10, new DealServiceTest1(), new ScoketIOLine());
	}
	@Test
	public void testScoketIOSizeByte() throws Exception {
		SocketServerManager.startServer("test", 9999, 10, new DealServiceTest1(), new ScoketIOSizeByte());
	}
	@Test
	public void testScoketIOCompress() throws Exception {
		SocketServerManager.startServer("test", 9999, 10, new DealServiceTest1(), new ScoketIOCompress());
	}
	@Test
	public void testScoketstartServerPool() throws Exception {
		SocketServerManager.startServer("test1", 9991, 10, new DealServiceTest1(), new ScoketIOCompress());
		SocketServerManager.startServerPool("test2", 9999, 10, new DealServiceTest1(), new ScoketIOCompress());
	}
}
