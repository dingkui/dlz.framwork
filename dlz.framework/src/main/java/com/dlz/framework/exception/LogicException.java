package com.dlz.framework.exception;

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
		super(DEFUALT_ERROR_CODE, message, cause);
	}

	public LogicException(String message) {
		super(DEFUALT_ERROR_CODE, message, null);
	}
}
