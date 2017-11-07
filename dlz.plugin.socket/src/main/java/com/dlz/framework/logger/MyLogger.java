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
	
	public void debug(Object message) {
		debug(message, null);
	}
	public void debug(String message,Object ...paras) {
		debug(formatMsg(message, paras),null);
	}
	public void debug(Object message, Throwable t) {
		logger.log(FQCN, Level.DEBUG, message, t);
	}
	
	
	public void error(Object message) {
		error(message,null);
	}
	public void error(String message,Object ...paras) {
		error(formatMsg(message, paras));
	}
	public void error(String message,Throwable t,Object ...paras) {
		error(formatMsg(message, paras),t);
	}
	public void error(Object message, Throwable t) {
		logger.log(FQCN, Level.ERROR, message, t);
	}
	
	
	public void warn(Object message) {
		warn(message,null);
	}
	public void warn(String message,Object ...paras) {
		warn(formatMsg(message, paras));
	}
	public void warn(String message,Throwable t,Object ...paras) {
		warn(formatMsg(message, paras),t);
	}
	public void warn(Object message, Throwable t) {
		logger.log(FQCN, Level.WARN, message, t);
	}
	
	public void info(Object message) {
		info(message,null);
	}
	public void info(String message,Object ...paras) {
		info(formatMsg(message, paras));
	}
	public void info(String message,Throwable t,Object ...paras) {
		info(formatMsg(message, paras),t);
	}
	public void info(Object message, Throwable t) {
		logger.log(FQCN, Level.INFO, message, t);
	}
	
	public void fatal(Object message) {
		fatal(message,null);
	}
	public void fatal(String message,Object ...paras) {
		fatal(formatMsg(message, paras));
	}
	public void fatal(String message,Throwable t,Object ...paras) {
		fatal(formatMsg(message, paras),t);
	}
	public void fatal(Object message, Throwable t) {
		logger.log(FQCN, Level.FATAL, message, t);
	}
	
	private Object formatMsg(String message,Object ...paras){
		return MessageFormat.format(message, paras);
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
