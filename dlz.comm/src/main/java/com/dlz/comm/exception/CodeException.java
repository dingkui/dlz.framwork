package com.dlz.comm.exception;

/**
 * BaseException for SDK
 */
public class CodeException extends BaseException {
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5345825923487658213L;
	private static int DEFUALT_ERROR_CODE = 3002;
	public CodeException(String message) {
		this(message,null);
	}
	public CodeException(String message, Throwable cause) {
		super(DEFUALT_ERROR_CODE, message, cause);
	}
}
