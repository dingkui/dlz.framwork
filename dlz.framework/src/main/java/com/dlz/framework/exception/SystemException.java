package com.dlz.framework.exception;

/**
 * BaseException for SDK
 */
public class SystemException extends BaseException {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5345825923487658213L;
	
	public SystemException(String msg) {
		super(msg,1);
	}

	public SystemException(String msg, Throwable exception) {
		super(msg,1,exception);
	}
}
