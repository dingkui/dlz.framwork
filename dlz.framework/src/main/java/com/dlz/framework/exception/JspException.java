package com.dlz.framework.exception;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;


/**
 * JSP异常
 * @author dingkui
 *
 */
public class JspException extends BaseException {
	private static Logger logger = LoggerFactory.getLogger(JspException.class);
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	
	public JspException(int errorCode,String message,Throwable cause) {
		super(message,errorCode,cause);
	}
	public JspException(String message,Throwable cause) {
		super(message,9999,cause);
	}
	
	public static JspException buildJspException(String message,Throwable cause){
		JspException e = null;
		if(cause!=null && cause instanceof TypeMismatchException){
			e = new JspException(9998,message, cause);
			logger.error(e.getMessage());
		}else if(cause!=null && cause instanceof ServletException){
			e = new JspException(9997,message, cause);
			logger.error(e.getMessage(),e);
		}else{
			e = new JspException(message, cause);
			logger.error(e.getMessage(),e);
		}
		return e;
	}
}
