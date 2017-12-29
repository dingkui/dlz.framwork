package com.dlz.framework.logger;

import java.text.MessageFormat;

public abstract class MyLogger {
	private static int logType = 0;

	static public MyLogger getLogger(Class<?> clazz) {
		return getLogger(clazz.getName());
	}

	static public MyLogger getLogger(String name) {
		if (logType == 0) {
			if (MyLogger.class.getClassLoader().getResource("logback.xml") != null) {
				logType = 1;
			} else if (MyLogger.class.getClassLoader().getResource("log4j.properties") != null) {
				logType = 2;
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

	public void debug(Object message, Object... paras) {
		debug(message, null, paras);
	}

	public abstract void debug(Object message, Throwable t, Object... paras);

	public void error(Object message, Object... paras) {
		error(message, null, paras);
	}

	public abstract void error(Object message, Throwable t, Object... paras);

	public void warn(Object message, Object... paras) {
		warn(formatMsg(message, paras));
	}

	public abstract void warn(Object message, Throwable t, Object... paras);

	public void info(Object message, Object... paras) {
		info(message, null, paras);
	}

	public abstract void info(Object message, Throwable t, Object... paras);

	protected String formatMsg(Object message, Object... paras) {
		return MessageFormat.format(message.toString(), paras);
	}

	public abstract boolean isDebugEnabled();

	public abstract boolean isWarnEnabled();

	public abstract boolean isErrorEnabled();
}
