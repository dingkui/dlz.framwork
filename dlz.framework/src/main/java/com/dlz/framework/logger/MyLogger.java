package com.dlz.framework.logger;

import java.net.URL;

public abstract class MyLogger {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	protected static final String FQCN = MyLogger.class.getName();
	private static int logType = -1;

	static public MyLogger getLogger(Class<?> clazz) {
		return getLogger(clazz.getName());
	}

	static public MyLogger getLogger(String name) {
		if (logType == -1) {
			URL resource = MyLogger.class.getClassLoader().getResource("logback.xml");
			if (resource != null) {
				logType = 1;
			} else {
				URL resource2 = MyLogger.class.getClassLoader().getResource("log4j.properties");
				if (resource2 != null) {
					logType = 2;
				}else{
					logType = 0;
				}
			}
		}
		switch (logType) {
		case 1:
			return new MyLoggerLogback(name);
		case 2:
			return new MyLoggerLog4j(name);
		}
		return new MyLoggerSlf4j(name);
	}
	public void debug(Object message) {
		Object[] p=null;
		debug(message,p);
	}	
	public abstract void debug(Object message, Object... paras);

	public void error(Object message, Object... paras) {
		error(message, null, paras);
	}
	public abstract void error(Object message, Throwable t, Object... paras);

	public void warn(Object message, Object... paras) {
		warn(message, null, paras);
	}
	public abstract void warn(Object message, Throwable t, Object... paras);

	public void info(Object message, Object... paras) {
		info(message, null, paras);
	}
	public abstract void info(Object message, Throwable t, Object... paras);

	public abstract boolean isDebugEnabled();
	
	public abstract boolean isInfoEnabled();

	public abstract boolean isWarnEnabled();

	public abstract boolean isErrorEnabled();
}
