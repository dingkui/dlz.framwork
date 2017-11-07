package com.dlz.framework.exception;


public class BusinessException extends Exception {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 错误码 见Errors
	 */
	private String code;

	public String message;
	
	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException() {
		super();
	}

	public BusinessException(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}

}
