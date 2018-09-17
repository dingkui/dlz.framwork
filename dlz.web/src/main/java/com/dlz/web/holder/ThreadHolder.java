package com.dlz.web.holder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dlz.framework.exception.CodeException;
import com.dlz.framework.logger.MyLogger;


/**
 *  用ThreadLocal来存储request,response
 * @author dk 2017-06-15
 * @version 1.1
 */
@SuppressWarnings("unchecked")
public class ThreadHolder  {
	protected static final MyLogger logger = MyLogger.getLogger(ThreadHolder.class);
	
	private static ThreadLocal<HttpServletRequest> HttpRequestThreadLocalHolder = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> HttpResponseThreadLocalHolder = new ThreadLocal<HttpServletResponse>();
	
	public static void setHttpRequest(HttpServletRequest request){
		HttpRequestThreadLocalHolder.set(request);
	}
	public static HttpServletRequest getHttpRequest(){
		final HttpServletRequest httpServletRequest = HttpRequestThreadLocalHolder.get();
		if(httpServletRequest==null) {
			throw new CodeException(1);
		}
		return httpServletRequest;
	}
	public static void remove(){
		HttpRequestThreadLocalHolder.remove();
		HttpResponseThreadLocalHolder.remove();
	}
	public static void setHttpResponse(HttpServletResponse response){
		HttpResponseThreadLocalHolder.set(response);	
	}
	public static HttpServletResponse getHttpResponse(){
		return HttpResponseThreadLocalHolder.get();
	}
	public static HttpSession getSession() {
		return getHttpRequest().getSession();
	}
	public static <T> T getSessionAttr(String key) {
		return (T)getHttpRequest().getSession().getAttribute(key);
	}
	public static <T> T getReqeustAttr(String key) {
		return (T)getHttpRequest().getAttribute(key);
	}
	public static void setSessionAttr(String key,Object o) {
		getSession().setAttribute(key, o);
	}
	public static void setReqeustAttr(String key,Object o) {
		getHttpRequest().setAttribute(key, o);
	}
	public static void removeSessionAttr(String key) {
		getSession().removeAttribute(key);
	}
	public static void removeRequestAttr(String key) {
		getHttpRequest().removeAttribute(key);
	}
}
