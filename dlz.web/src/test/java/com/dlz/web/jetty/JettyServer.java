package com.dlz.web.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 创建Jetty Server
 * 2018-09-21
 * @author dingkui
 */
public class JettyServer {
	private static Logger logger = LoggerFactory.getLogger(JettyServer.class);
	private static final String DEFAULT_WEBAPP_PATH = "src/main/webapp";
	public static final int PORT = 8080;
	public static final String CONTEXT = "/shop-pf";

	public static void main(String[] args) throws Exception {
		// 设定Spring的profile
		//System.setProperty("spring.profiles.active", "development");
		// 启动Jetty
		long startTime = System.currentTimeMillis();
		
		Server server = new Server();
		//设置在JVM退出时关闭Jetty的钩子。
		server.setStopAtShutdown(true);
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(PORT);
		connector.close();
		//解决Windows下重复启动Jetty居然不报告端口冲突的问题.
		connector.setReuseAddress(false);
		server.setConnectors(new Connector[] { connector });
		WebAppContext webContext = new WebAppContext(DEFAULT_WEBAPP_PATH,CONTEXT);
		server.setHandler(webContext);
		try {	
			logger.info("Server startup...");
			server.start();
			long time = System.currentTimeMillis() - startTime;
			logger.info("Server startup in " + time + " ms, running at http://localhost:" + PORT + CONTEXT);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			System.exit(-1);
		}
	}
}
