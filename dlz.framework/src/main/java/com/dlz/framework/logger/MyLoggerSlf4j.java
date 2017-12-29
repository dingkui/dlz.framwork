package com.dlz.framework.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLoggerSlf4j extends MyLogger{
	private Logger logger;
	
	MyLoggerSlf4j(String name) {
		this.logger=LoggerFactory.getLogger(name);
	}
	
	public void debug(Object message,Throwable t,Object ...paras) {
		logger.debug(formatMsg(message, paras), t);
	}
	
	public void error(Object message,Throwable t,Object ...paras) {
		logger.error(formatMsg(message, paras), t);
	}
	
	public void warn(Object message,Throwable t,Object ...paras) {
		logger.warn(formatMsg(message, paras), t);
	}
	
	public void info(Object message,Throwable t,Object ...paras) {
		logger.info(formatMsg(message, paras), t);
	}
	
	
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}
}
