package com.dlz.framework.logger;

/**
 * 方法马上删除，修改成Slf4j
 * @author dingkui
 *
 */
@Deprecated
public abstract class MyLogger {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	protected static final String FQCN = MyLogger.class.getName();

	static public MyLogger getLogger(Class<?> clazz) {
		return getLogger(clazz.getName());
	}

	static public MyLogger getLogger(String name) {
		System.out.println("MyLogger 已经不支持，请使用Slf4j");
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
