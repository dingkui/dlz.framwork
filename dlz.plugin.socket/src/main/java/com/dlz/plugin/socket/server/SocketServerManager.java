package com.dlz.plugin.socket.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.socket.interfaces.ASocketServer;
import com.dlz.plugin.socket.interfaces.IDealService;
import com.dlz.plugin.socket.interfaces.ASocketIO;

public class SocketServerManager {
	
	private static MyLogger logger=MyLogger.getLogger(SocketServerManager.class);
	private static Map<String,ASocketServer> SOCKETSERVERS=new HashMap<String,ASocketServer>();
	

	public static void startServer(String serverName,int port,int poolsize, IDealService socketService,ASocketIO socketReader) throws IOException {
		if(serverName==null){
			logger.error("服务器启动失败，无serverName");
			return;
		}
		if(SOCKETSERVERS.containsKey(serverName)){
			logger.warn("服务器[{0}]运行中，不需要启动",serverName);
			return;
		}
		ASocketServer server = new com.dlz.plugin.socket.conn.normal.SocketServer(port,poolsize,socketService,socketReader);
		server.start();
		SOCKETSERVERS.put(serverName, server);
	}
	public static void startServerPool(String serverName,int port,int poolsize, IDealService socketService,ASocketIO socketReader) throws IOException {
		if(serverName==null){
			logger.error("服务器启动失败，无serverName");
			return;
		}
		if(SOCKETSERVERS.containsKey(serverName)){
			logger.warn("服务器[{0}]运行中，不需要启动",serverName);
			return;
		}
		ASocketServer server = new com.dlz.plugin.socket.conn.pool.SocketServer(port,poolsize,socketService,socketReader);
		server.start();
		SOCKETSERVERS.put(serverName, server);
	}
	
	
	public static void stopServer(String serverName) throws IOException {
		if(serverName==null){
			logger.error("服务器停止失败，无serverName");
			return;
		}
		if(!SOCKETSERVERS.containsKey(serverName)){
			logger.warn("服务器[{0}]未运行，不需要停止",serverName);
			return;
		}
		ASocketServer server = SOCKETSERVERS.get(serverName);
		server.stopServer();
		SOCKETSERVERS.remove(serverName);
		logger.info("服务器[{0}]停止成功",serverName);
	}
}