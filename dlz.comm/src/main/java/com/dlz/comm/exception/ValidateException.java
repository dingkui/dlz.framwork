package com.dlz.comm.exception;

/**
 * BaseException for SDK
 */
public class ValidateException extends BaseException {
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5345825923487658213L;
	private static int DEFUALT_ERROR_CODE = 3003;

	public ValidateException(String message) {
		super(DEFUALT_ERROR_CODE, message);
	}
}
