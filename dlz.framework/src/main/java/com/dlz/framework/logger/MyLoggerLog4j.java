package com.dlz.framework.logger;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

import com.dlz.framework.util.StringUtils;

public class MyLoggerLog4j extends MyLogger {
	private Logger logger;

	MyLoggerLog4j(String name) {
		this.logger = Logger.getLogger(name);
	}

	private void Logging(Level level, Object msg, Throwable t, Object[] params) {
		if (!logger.isEnabledFor(level)) {
			return;
		}
		logger.callAppenders(new LoggingEvent(FQCN, logger, level, StringUtils.formatMsg(msg, params), t));
	}

	public void debug(Object message, Object... paras) {
		Throwable t=null;
		Logging(Level.DEBUG, message, t, paras);
	}

	public void error(Object message, Throwable t, Object... paras) {
		Logging(Level.ERROR, message, t, paras);
	}

	public void warn(Object message, Throwable t, Object... paras) {
		Logging(Level.WARN, message, t, paras);
	}

	public void info(Object message, Throwable t, Object... paras) {
		Logging(Level.INFO, message, t, paras);
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
