package com.dlz.web.holder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dlz.app.uim.holder.UserHolder;
import com.dlz.framework.holder.SpringHolder;
import org.slf4j.Logger;


/**
 *  用ThreadLocal来存储request,response
 * @author dk 2017-06-15
 * @version 1.1
 */
@SuppressWarnings("unchecked")
public class ThreadHolder  {

	protected static final Logger logger = org.slf4j.LoggerFactory.getLogger(ThreadHolder.class);
	
	private static ThreadLocal<HttpServletRequest> HttpRequestThreadLocalHolder = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> HttpResponseThreadLocalHolder = new ThreadLocal<HttpServletResponse>();
	

	private static ISessionHolderDeal holder;

	public static ISessionHolderDeal getHolder() {
		if (holder == null) {
			synchronized (UserHolder.class) {
				holder = SpringHolder.getBean("sessionHolderDeal");
				if (holder == null) {
					holder = new ISessionHolderDeal() {
						private HttpSession getSession() {
							return getHttpRequest().getSession();
						}
						@Override
						public <T> T getSessionAttr(String sessionName) {
							return (T)getSession().getAttribute(sessionName);
						}
						@Override
						public void removeSessionAttr(String sessionName) {
							getSession().removeAttribute(sessionName);
						}
						@Override
						public void setSessionAttr(String sessionName, Object obj) {
							getSession().setAttribute(sessionName, obj);
						}
						@Override
						public void invalidate() {
							getSession().invalidate();
						}
						@Override
						public String getSessionId() {
							return getSession().getId();
						}
					};
				}
			}
		}
		return holder;
	}
	
	
	
	public static void setHttpRequest(HttpServletRequest request){
		HttpRequestThreadLocalHolder.set(request);
	}
	public static HttpServletRequest getHttpRequest(){
		final HttpServletRequest httpServletRequest = HttpRequestThreadLocalHolder.get();
//		if(httpServletRequest==null) {
//			throw new CodeException(1);
//		}
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


	public static <T> T getReqeustAttr(String key) {
		return (T)getHttpRequest().getAttribute(key);
	}
	public static void setReqeustAttr(String key,Object o) {
		getHttpRequest().setAttribute(key, o);
	}
	public static void removeRequestAttr(String key) {
		getHttpRequest().removeAttribute(key);
	}
	public static <T> T getSessionAttr(String key) {
		return getHolder().getSessionAttr(key);
	}
	public static void setSessionAttr(String key,Object o) {
		getHolder().setSessionAttr(key, o);
	}
	public static void removeSessionAttr(String key) {
		getHolder().removeSessionAttr(key);
	}
	public static String getSessionId() {
		return getHolder().getSessionId();
	}
	public static void sessionInvalidate() {
		getHolder().invalidate();
	}
}
