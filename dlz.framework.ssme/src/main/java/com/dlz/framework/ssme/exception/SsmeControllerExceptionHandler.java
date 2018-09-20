package com.dlz.framework.ssme.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dlz.framework.db.exception.DbException;
import com.dlz.framework.exception.BaseException;
import com.dlz.framework.exception.JspException;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.JacksonUtil;

@ControllerAdvice
public class SsmeControllerExceptionHandler {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static MyLogger logger = MyLogger.getLogger(SsmeControllerExceptionHandler.class);
	/** 基于@ExceptionHandler异常处理 */
	@ExceptionHandler
	public String exp(HttpServletRequest request, Exception ex) {
		logger.error("来源:"+request.getHeader("referer"));
		logger.error("url:"+request.getRequestURI());
		logger.error("paras:"+JacksonUtil.getJson(request.getParameterMap()));
		// 根据不同错误转向不同页面
		if (ex instanceof DbException) {
			request.setAttribute("ex", ex);
			logger.error(ex.getMessage(), ex);
			return "error/error";
		}
		if (ex instanceof TypeMismatchException) {
			ex = JspException.buildJspException(ex.getMessage(), ex);
			request.setAttribute("ex", ex);
			logger.error(ex.getMessage(), ex);
			return "error/error";
		} else {
			ex = new BaseException(6001,ex);
			request.setAttribute("ex", ex);
			logger.error(ex.getMessage(), ex);
			return "error/error";
		}
	}
}