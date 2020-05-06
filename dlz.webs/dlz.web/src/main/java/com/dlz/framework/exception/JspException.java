package com.dlz.framework.exception;

import javax.servlet.ServletException;

import org.springframework.beans.TypeMismatchException;


/**
 * JSP异常
 * @author dingkui
 *
 */
public class JspException extends BaseException {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	
	private static int DEFUALT_ERROR_CODE = 9999;

	private JspException(int errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	public JspException(String message, Throwable cause) {
		super(DEFUALT_ERROR_CODE, message, cause);
	}

	public JspException(String message) {
		super(DEFUALT_ERROR_CODE, message, null);
	}
	public JspException(Throwable cause) {
		super(DEFUALT_ERROR_CODE, cause.getMessage(), cause);
	}
	
	public static JspException buildJspException(String message,Throwable cause){
		JspException e = null;
		if(cause!=null && cause instanceof TypeMismatchException){
			e = new JspException(9998,message, cause);
		}else if(cause!=null && cause instanceof ServletException){
			e = new JspException(9997,message, cause);
		}else{
			e = new JspException(message, cause);
		}
		return e;
	}
}
