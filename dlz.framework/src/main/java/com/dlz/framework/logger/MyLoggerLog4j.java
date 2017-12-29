package com.dlz.framework.logger;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MyLoggerLog4j extends MyLogger{
	private static final String FQCN = MyLoggerLog4j.class.getName();
	private Logger logger;
	
	MyLoggerLog4j(String name) {
		this.logger=Logger.getLogger(name);
	}
	
	public void debug(Object message,Throwable t,Object ...paras) {
		logger.log(FQCN, Level.DEBUG, formatMsg(message, paras), t);
	}
	
	public void error(Object message,Throwable t,Object ...paras) {
		logger.log(FQCN, Level.ERROR, formatMsg(message, paras), t);
	}
	
	public void warn(Object message,Throwable t,Object ...paras) {
		logger.log(FQCN, Level.WARN, formatMsg(message, paras), t);
	}
	
	public void info(Object message,Throwable t,Object ...paras) {
		logger.log(FQCN, Level.INFO, formatMsg(message, paras), t);
	}
	
	public void fatal(Object message,Throwable t,Object ...paras) {
		logger.log(FQCN, Level.FATAL, formatMsg(message, paras), t);
	}
	
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}
	public boolean isWarnEnabled() {
		return logger.isEnabledFor(Level.WARN);
	}
	public boolean isErrorEnabled() {
		return logger.isEnabledFor(Level.ERROR);
	}
}
