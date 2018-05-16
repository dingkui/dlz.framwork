package com.dlz.framework.holder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dlz.common.bean.AuthUser;
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
	private static String SESSION_AUTHUSER="curr_member";
	
	public static void setHttpRequest(HttpServletRequest request){
		HttpRequestThreadLocalHolder.set(request);
	}
	public static HttpServletRequest getHttpRequest(){
		return  HttpRequestThreadLocalHolder.get();
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
		return HttpRequestThreadLocalHolder.get().getSession();
	}
	public static <T> T getSessionAttr(String key) {
		return (T)HttpRequestThreadLocalHolder.get().getSession().getAttribute(key);
	}
	public static <T> T getReqeustAttr(String key) {
		return (T)HttpRequestThreadLocalHolder.get().getAttribute(key);
	}
	public static void setSessionAttr(String key,Object o) {
		HttpRequestThreadLocalHolder.get().getSession().setAttribute(key, o);
	}
	public static void setReqeustAttr(String key,Object o) {
		HttpRequestThreadLocalHolder.get().setAttribute(key, o);
	}
	public static void removeSessionAttr(String key) {
		HttpRequestThreadLocalHolder.get().getSession().removeAttribute(key);
	}
	public static void removeRequestAttr(String key) {
		HttpRequestThreadLocalHolder.get().removeAttribute(key);
	}
	public static <T extends AuthUser> T getAuthInfo(){
		return (T)HttpRequestThreadLocalHolder.get().getSession().getAttribute(SESSION_AUTHUSER);
	}
	public static void setAuthInfo(AuthUser user){
		HttpRequestThreadLocalHolder.get().getSession().setAttribute(SESSION_AUTHUSER, user);
	}
}
