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
	@SuppressWarnings("unchecked")
	public static <T extends AuthUser> T getAuthInfo(){
		return (T)HttpRequestThreadLocalHolder.get().getSession().getAttribute(SESSION_AUTHUSER);
	}
	public static void setAuthInfo(AuthUser user){
		HttpRequestThreadLocalHolder.get().getSession().setAttribute(SESSION_AUTHUSER, user);
	}
	
}
