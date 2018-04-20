package com.dlz.common.jetty;

import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用Jetty运行调试Web应用, 在Console输入回车快速重新加载应用.
 *
 * @author calvin
 */
public class QuickStartServer {
	private static Logger logger = LoggerFactory.getLogger(QuickStartServer.class);
	public static final int PORT = 9090;
	public static final String CONTEXT = "/alex";
	public static final String[] TLD_JAR_NAMES = new String[] { "sitemesh", "spring-webmvc", "shiro-web" };

	public static void main(String[] args) throws Exception {
		// 设定Spring的profile
		System.setProperty("spring.profiles.active", "development");

		// 启动Jetty
		long startTime = System.currentTimeMillis();
		Server server = JettyFactory.createServerInSource(PORT, CONTEXT);
		JettyFactory.setTldJarNames(server, TLD_JAR_NAMES);

		try {	
			server.start();
			
			long time = System.currentTimeMillis() - startTime;
			logger.info("Server startup in " + time + " ms, running at http://localhost:" + PORT + CONTEXT);
			logger.info("Hit Enter to reload the application quickly");
			
			// 等待用户输入回车重载应用.
			while (true) {
				char c = (char) System.in.read();
				if (c == '\n') {
					JettyFactory.reloadContext(server);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			System.exit(-1);
		}
	}
}
