package com.dlz.framework.exception;

/**
 * BaseException for SDK
 */
public class CodeException extends RuntimeException {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5345825923487658213L;
	
	public CodeException(String msg) {
		super(msg);
	}
	public CodeException(String msg, Throwable exception) {
		super(msg, exception);
	}
}
