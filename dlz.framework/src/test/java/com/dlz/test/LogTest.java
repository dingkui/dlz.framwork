package com.dlz.test;

import org.slf4j.Logger;


public class LogTest {
	
//	static MyLogger Logger = org.slf4j.LoggerFactory.getLogger(LogTest.class);
	static Logger logger = org.slf4j.LoggerFactory.getLogger(LogTest.class);
	static Logger log = org.slf4j.LoggerFactory.getLogger(LogTest.class);
//	static MyLogger myLogger2 = org.slf4j.LoggerFactory.getLogger("xccd");

	public static void main(String[] args) {
		logger.debug("123{1},{},{},{}",1,2,3,5555);
		logger.error("456");
//		myLogger.debug("m123");
//		myLogger.error("m456");
//		myLogger2.debug("2m123");
//		myLogger2.error("2m456");
	}

}
