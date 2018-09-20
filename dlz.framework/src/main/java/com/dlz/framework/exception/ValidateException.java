package com.dlz.framework.exception;

/**
 * BaseException for SDK
 */
public class ValidateException extends LogicException {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5345825923487658213L;
	private static int DEFUALT_ERROR_CODE = 3003;

	public ValidateException(String message, Throwable cause) {
		super(DEFUALT_ERROR_CODE, message, cause);
	}

	public ValidateException(String message) {
		super(DEFUALT_ERROR_CODE, message, null);
	}
}
