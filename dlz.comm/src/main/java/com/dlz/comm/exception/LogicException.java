package com.dlz.comm.exception;

/**
 * BaseException for SDK
 */
public class LogicException extends BaseException {
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5345825923487658213L;
	private static int DEFUALT_ERROR_CODE = 3001;

	public LogicException(String message, Throwable cause) {
		this(DEFUALT_ERROR_CODE, message, cause);
	}

	public LogicException(String message) {
		this(DEFUALT_ERROR_CODE, message, null);
	}
	
	public LogicException(int errorCode,String message, Throwable cause) {
		super(errorCode, message, cause);
	}
}
