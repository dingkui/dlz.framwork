package com.dlz.plugin.socket.test.pressure;

import org.junit.Test;

import com.dlz.plugin.socket.deal.DealServiceTest1;
import com.dlz.plugin.socket.interfaces.impl.ScoketIOCompress;
import com.dlz.plugin.socket.interfaces.impl.ScoketIOLine;
import com.dlz.plugin.socket.interfaces.impl.ScoketIOSizeByte;
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
}
