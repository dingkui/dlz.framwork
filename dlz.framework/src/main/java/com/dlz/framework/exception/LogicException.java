package com.dlz.framework.exception;

/**
 * BaseException for SDK
 */
public class LogicException extends BaseException {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5345825923487658213L;
	
	public LogicException(String msg) {
		super(msg,0);
	}

	public LogicException(String msg, Throwable exception) {
		super(msg,0,exception);
	}
	
}
