package com.dlz.framework.logger;

import java.text.MessageFormat;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MyLogger{
	private static final String FQCN = MyLogger.class.getName();
	private Logger logger;
	private MyLogger(Logger logger) {
		this.logger=logger;
	}
	public Logger getLogger() {
		return logger;
	}
	static public MyLogger getLogger(Class<?> clazz) {
		return getLogger(clazz.getName());
	}
	static public MyLogger getLogger(String name) {
		return new MyLogger(Logger.getLogger(name));
	}
	
	public void debug(Object message,Object ...paras) {
		debug(message,null,paras);
	}
	public void debug(Object message,Throwable t,Object ...paras) {
		logger.log(FQCN, Level.DEBUG, formatMsg(message, paras), t);
	}
	
	public void error(Object message,Object ...paras) {
		error(message,null,paras);
	}
	public void error(Object message,Throwable t,Object ...paras) {
		logger.log(FQCN, Level.ERROR, formatMsg(message, paras), t);
	}
	
	public void warn(Object message,Object ...paras) {
		warn(formatMsg(message, paras));
	}
	public void warn(Object message,Throwable t,Object ...paras) {
		logger.log(FQCN, Level.WARN, formatMsg(message, paras), t);
	}
	
	public void info(Object message,Object ...paras) {
		info(message,null,paras);
	}
	public void info(Object message,Throwable t,Object ...paras) {
		logger.log(FQCN, Level.INFO, formatMsg(message, paras), t);
	}
	
	public void fatal(Object message,Object ...paras) {
		fatal(formatMsg(message, paras));
	}
	public void fatal(Object message,Throwable t,Object ...paras) {
		logger.log(FQCN, Level.FATAL, formatMsg(message, paras), t);
	}
	
	private String formatMsg(Object message,Object ...paras){
		return MessageFormat.format(message.toString(), paras);
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
