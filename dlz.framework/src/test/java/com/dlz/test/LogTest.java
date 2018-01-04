package com.dlz.test;

import com.dlz.framework.logger.MyLogger;


public class LogTest {
	
//	static MyMyLogger myLogger = MyLogger.getLogger(LogTest.class);
	static MyLogger logger = MyLogger.getLogger(LogTest.class);
	static MyLogger log = MyLogger.getLogger(LogTest.class);
//	static MyMyLogger myLogger2 = MyLogger.getLogger("xccd");

	public static void main(String[] args) {
		logger.debug("123{1},{},{},{}",1,2,3,5555);
		logger.error("456");
//		myLogger.debug("m123");
//		myLogger.error("m456");
//		myLogger2.debug("2m123");
//		myLogger2.error("2m456");
	}

}
