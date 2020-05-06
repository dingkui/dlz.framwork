package com.dlz.web.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dlz.comm.json.JSONMap;
import com.dlz.framework.db.modal.Page;
import org.slf4j.Logger;
import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.StringUtils;
import com.dlz.comm.util.ValUtil;
import com.dlz.web.holder.ThreadHolder;

@SuppressWarnings("unchecked")
public class WebUtil {

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(WebUtil.class);
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (!isnull(ip) && ip.contains(",")) {
			String[] ips = ip.split(",");
			ip = ips[ips.length - 1];
		}
		return ip;
	}
	/**
	 * str空判断
	 * 
	 * @param str
	 * @return
	 * @author guoyx
	 */
	private static boolean isnull(String str) {
		if (null == str || str.equalsIgnoreCase("null") || str.equals("")) {
			return true;
		} else
			return false;
	}

	public static String getServer(HttpServletRequest request) {
		String scheme = request.getHeader("x-real-scheme");
		if (scheme == null || scheme.length() == 0 || "unknown".equalsIgnoreCase(scheme)) {
			scheme = request.getScheme();
		}
		String port = request.getHeader("x-real-port");
		if (port == null || port.length() == 0 || "unknown".equalsIgnoreCase(port)) {
			port = String.valueOf(request.getServerPort());
		}
		return scheme + "://" + request.getServerName() + ":" + port;
	}
	/**
	 * Stores an attribute in this request
	 * 
	 * @param name
	 *            a String specifying the name of the attribute
	 * @param value
	 *            the Object to be stored
	 */
	public static void setAttr(String name, Object value) {
		getRequest().setAttribute(name, value);

	}

	/**
	 * Removes an attribute from this request
	 * 
	 * @param name
	 *            a String specifying the name of the attribute to remove
	 */
	public static void removeAttr(String name) {
		getRequest().removeAttribute(name);

	}

	/**
	 * Stores attributes in this request, key of the map as attribute name and
	 * value of the map as attribute value
	 * 
	 * @param attrMap
	 *            key and value as attribute of the map to be stored
	 */
	public static void setAttrs(Map<String, Object> attrMap) {
		for (Map.Entry<String, Object> entry : attrMap.entrySet())
			getRequest().setAttribute(entry.getKey(), entry.getValue());

	}

	/**
	 * Returns the value of a request parameter as a String, or null if the
	 * parameter does not exist.
	 * <p>
	 * You should only use this method when you are sure the parameter has only
	 * one value. If the parameter might have more than one value, use
	 * getParaValues(java.lang.String).
	 * <p>
	 * If you use this method with a multivalued parameter, the value returned
	 * is equal to the first value in the array returned by getParameterValues.
	 * 
	 * @param name
	 *            a String specifying the name of the parameter
	 * @return a String representing the single value of the parameter
	 */
	public static String getPara(String name) {
		return getRequest().getParameter(name);
	}

	/**
	 * Returns the value of a request parameter as a String, or default value if
	 * the parameter does not exist.
	 * 
	 * @param name
	 *            a String specifying the name of the parameter
	 * @param defaultValue
	 *            a String value be returned when the value of parameter is null
	 * @return a String representing the single value of the parameter
	 */
	public static String getPara(String name, String defaultValue) {
		String result = getRequest().getParameter(name);
		return result != null && !"".equals(result) ? result : defaultValue;
	}

	/**
	 * Returns the values of the request parameters as a Map.
	 * 
	 * @return a Map contains all the parameters name and value
	 */
	public Map<String, String[]> getParaMap() {
		return getRequest().getParameterMap();
	}

	/**
	 * Returns an Enumeration of String objects containing the names of the
	 * parameters contained in this getRequest(). If the request has no
	 * parameters, the method returns an empty Enumeration.
	 * 
	 * @return an Enumeration of String objects, each String containing the name
	 *         of a request parameter; or an empty Enumeration if the request
	 *         has no parameters
	 */
	public Enumeration<String> getParaNames() {
		return getRequest().getParameterNames();
	}

	/**
	 * Returns an array of String objects containing all of the values the given
	 * request parameter has, or null if the parameter does not exist. If the
	 * parameter has a single value, the array has a length of 1.
	 * 
	 * @param name
	 *            a String containing the name of the parameter whose value is
	 *            requested
	 * @return an array of String objects containing the parameter's values
	 */
	public static String[] getParaValues(String name) {
		return getRequest().getParameterValues(name);
	}

	/**
	 * Returns an array of Integer objects containing all of the values the
	 * given request parameter has, or null if the parameter does not exist. If
	 * the parameter has a single value, the array has a length of 1.
	 * 
	 * @param name
	 *            a String containing the name of the parameter whose value is
	 *            requested
	 * @return an array of Integer objects containing the parameter's values
	 */
	public static Integer[] getParaValuesToInt(String name) {
		String[] values = getRequest().getParameterValues(name);
		if (values == null)
			return null;
		Integer[] result = new Integer[values.length];
		for (int i = 0; i < result.length; i++)
			result[i] = Integer.parseInt(values[i]);
		return result;
	}

	public static Long[] getParaValuesToLong(String name) {
		String[] values = getRequest().getParameterValues(name);
		if (values == null)
			return null;
		Long[] result = new Long[values.length];
		for (int i = 0; i < result.length; i++)
			result[i] = Long.parseLong(values[i]);
		return result;
	}

	/**
	 * Returns an Enumeration containing the names of the attributes available
	 * to this getRequest(). This method returns an empty Enumeration if the
	 * request has no attributes available to it.
	 * 
	 * @return an Enumeration of strings containing the names of the request's
	 *         attributes
	 */
	public Enumeration<String> getAttrNames() {
		return getRequest().getAttributeNames();
	}

	/**
	 * Returns the value of the named attribute as an Object, or null if no
	 * attribute of the given name exists.
	 * 
	 * @param name
	 *            a String specifying the name of the attribute
	 * @return an Object containing the value of the attribute, or null if the
	 *         attribute does not exist
	 */
	public <T> T getAttr(String name) {
		return (T) getRequest().getAttribute(name);
	}

	/**
	 * Returns the value of the named attribute as an Object, or null if no
	 * attribute of the given name exists.
	 * 
	 * @param name
	 *            a String specifying the name of the attribute
	 * @return an String Object containing the value of the attribute, or null
	 *         if the attribute does not exist
	 */
	public static String getAttrForStr(String name) {
		return (String) getRequest().getAttribute(name);
	}

	/**
	 * Returns the value of the named attribute as an Object, or null if no
	 * attribute of the given name exists.
	 * 
	 * @param name
	 *            a String specifying the name of the attribute
	 * @return an Integer Object containing the value of the attribute, or null
	 *         if the attribute does not exist
	 */
	public static Integer getAttrForInt(String name) {
		return (Integer) getRequest().getAttribute(name);
	}

	/**
	 * Returns the value of the specified request header as a String.
	 */
	public static String getHeader(String name) {
		return getRequest().getHeader(name);
	}

	/**
	 * Returns the value of a request parameter and convert to Integer.
	 * 
	 * @param name
	 *            a String specifying the name of the parameter
	 * @return a Integer representing the single value of the parameter
	 */
	public static Integer getParaToInt(String name) {
		return ValUtil.getInt(getRequest().getParameter(name), null);
	}

	/**
	 * Returns the value of a request parameter and convert to Integer with a
	 * default value if it is null.
	 * 
	 * @param name
	 *            a String specifying the name of the parameter
	 * @return a Integer representing the single value of the parameter
	 */
	public static Integer getParaToInt(String name, Integer defaultValue) {
		return ValUtil.getInt(getRequest().getParameter(name), defaultValue);
	}

	/**
	 * Returns the value of a request parameter and convert to Long.
	 * 
	 * @param name
	 *            a String specifying the name of the parameter
	 * @return a Integer representing the single value of the parameter
	 */
	public static Long getParaToLong(String name) {
		return ValUtil.getLong(getRequest().getParameter(name), null);
	}

	/**
	 * Returns the value of a request parameter and convert to Long with a
	 * default value if it is null.
	 * 
	 * @param name
	 *            a String specifying the name of the parameter
	 * @return a Integer representing the single value of the parameter
	 */
	public static Long getParaToLong(String name, Long defaultValue) {
		return ValUtil.getLong(getRequest().getParameter(name), defaultValue);
	}

	/**
	 * Returns the value of a request parameter and convert to Boolean.
	 * 
	 * @param name
	 *            a String specifying the name of the parameter
	 * @return true if the value of the parameter is "true" or "1", false if it
	 *         is "false" or "0", null if parameter is not exists
	 */
	public Boolean getParaToBoolean(String name) {
		return ValUtil.getBoolean(getRequest().getParameter(name), null);
	}

	/**
	 * Returns the value of a request parameter and convert to Boolean with a
	 * default value if it is null.
	 * 
	 * @param name
	 *            a String specifying the name of the parameter
	 * @return true if the value of the parameter is "true" or "1", false if it
	 *         is "false" or "0", default value if it is null
	 */
	public Boolean getParaToBoolean(String name, Boolean defaultValue) {
		return ValUtil.getBoolean(getRequest().getParameter(name), defaultValue);
	}

	/**
	 * Returns the value of a request parameter and convert to Date.
	 * 
	 * @param name
	 *            a String specifying the name of the parameter
	 * @return a Date representing the single value of the parameter
	 */
	public Date getParaToDate(String name) {
		return getParaToDate(name, null);
	}

	/**
	 * Returns the value of a request parameter and convert to Date with a
	 * default value if it is null.
	 * 
	 * @param name
	 *            a String specifying the name of the parameter
	 * @return a Date representing the single value of the parameter
	 */
	public Date getParaToDate(String name, Date defaultValue) {
		Date date = ValUtil.getDate(getRequest().getParameter(name));
		if (date == null)
			return defaultValue;
		return date;
	}

	/**
	 * Return HttpServletgetRequest(). Do not use HttpServletRequest Object in
	 * constructor of void
	 */
	public static HttpServletRequest getRequest() {
		return ThreadHolder.getHttpRequest();
	}

	/**
	 * Return HttpServletResponse. Do not use HttpServletResponse Object in
	 * constructor of void
	 */
	public static HttpServletResponse getResponse() {
		return ThreadHolder.getHttpResponse();
	}

	/**
	 * Return HttpSession.
	 */
	public HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * Return HttpSession.
	 * 
	 * @param create
	 *            a boolean specifying create HttpSession if it not exists
	 */
	public HttpSession getSession(boolean create) {
		return getRequest().getSession(create);
	}

	/**
	 * Return a Object from session.
	 * 
	 * @param key
	 *            a String specifying the key of the Object stored in session
	 */
	public <T> T getSessionAttr(String key) {
		HttpSession session = getRequest().getSession(false);
		return session != null ? (T) session.getAttribute(key) : null;
	}

	/**
	 * Store Object to session.
	 * 
	 * @param key
	 *            a String specifying the key of the Object stored in session
	 * @param value
	 *            a Object specifying the value stored in session
	 */
	public static void setSessionAttr(String key, Object value) {
		getRequest().getSession(true).setAttribute(key, value);
	}

	/**
	 * Remove Object in session.
	 * 
	 * @param key
	 *            a String specifying the key of the Object stored in session
	 */
	public static void removeSessionAttr(String key) {
		HttpSession session = getRequest().getSession(false);
		if (session != null)
			session.removeAttribute(key);
	}

	/**
	 * Get cookie value by cookie name.
	 */
	public static String getCookie(String name, String defaultValue) {
		Cookie cookie = getCookieObject(name);
		return cookie != null ? cookie.getValue() : defaultValue;
	}

	/**
	 * Get cookie value by cookie name.
	 */
	public static String getCookie(String name) {
		return getCookie(name, null);
	}

	/**
	 * Get cookie value by cookie name and convert to Integer.
	 */
	public static Integer getCookieToInt(String name) {
		String result = getCookie(name);
		return result != null ? Integer.parseInt(result) : null;
	}

	/**
	 * Get cookie value by cookie name and convert to Integer.
	 */
	public static Integer getCookieToInt(String name, Integer defaultValue) {
		String result = getCookie(name);
		return result != null ? Integer.parseInt(result) : defaultValue;
	}

	/**
	 * Get cookie value by cookie name and convert to Long.
	 */
	public static Long getCookieToLong(String name) {
		String result = getCookie(name);
		return result != null ? Long.parseLong(result) : null;
	}

	/**
	 * Get cookie value by cookie name and convert to Long.
	 */
	public static Long getCookieToLong(String name, Long defaultValue) {
		String result = getCookie(name);
		return result != null ? Long.parseLong(result) : defaultValue;
	}

	/**
	 * Get cookie object by cookie name.
	 */
	public static Cookie getCookieObject(String name) {
		Cookie[] cookies = getRequest().getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies)
				if (cookie.getName().equals(name))
					return cookie;
		return null;
	}

	/**
	 * Get all cookie objects.
	 */
	public static Cookie[] getCookieObjects() {
		Cookie[] result = getRequest().getCookies();
		return result != null ? result : new Cookie[0];
	}

	/**
	 * Set Cookie.
	 * 
	 * @param name
	 *            cookie name
	 * @param value
	 *            cookie value
	 * @param maxAgeInSeconds
	 *            -1: clear cookie when close browser. 0: clear cookie
	 *            immediately. n>0 : max age in n seconds.
	 * @param isHttpOnly
	 *            true if this cookie is to be marked as HttpOnly, false
	 *            otherwise
	 */
	public static void setCookie(String name, String value, int maxAgeInSeconds, boolean isHttpOnly) {
		doSetCookie(name, value, maxAgeInSeconds, null, null, isHttpOnly);
	}

	/**
	 * Set Cookie.
	 * 
	 * @param name
	 *            cookie name
	 * @param value
	 *            cookie value
	 * @param maxAgeInSeconds
	 *            -1: clear cookie when close browser. 0: clear cookie
	 *            immediately. n>0 : max age in n seconds.
	 */
	public static void setCookie(String name, String value, int maxAgeInSeconds) {
		doSetCookie(name, value, maxAgeInSeconds, null, null, null);
	}

	/**
	 * Set Cookie to response.
	 */
	public static void setCookie(Cookie cookie) {
		getResponse().addCookie(cookie);
	}

	/**
	 * Set Cookie to response.
	 * 
	 * @param name
	 *            cookie name
	 * @param value
	 *            cookie value
	 * @param maxAgeInSeconds
	 *            -1: clear cookie when close browser. 0: clear cookie
	 *            immediately. n>0 : max age in n seconds.
	 * @param path
	 *            see Cookie.setPath(String)
	 * @param isHttpOnly
	 *            true if this cookie is to be marked as HttpOnly, false
	 *            otherwise
	 */
	public static void setCookie(String name, String value, int maxAgeInSeconds, String path, boolean isHttpOnly) {
		doSetCookie(name, value, maxAgeInSeconds, path, null, isHttpOnly);
	}

	/**
	 * Set Cookie to response.
	 * 
	 * @param name
	 *            cookie name
	 * @param value
	 *            cookie value
	 * @param maxAgeInSeconds
	 *            -1: clear cookie when close browser. 0: clear cookie
	 *            immediately. n>0 : max age in n seconds.
	 * @param path
	 *            see Cookie.setPath(String)
	 */
	public static void setCookie(String name, String value, int maxAgeInSeconds, String path) {
		doSetCookie(name, value, maxAgeInSeconds, path, null, null);
	}

	/**
	 * Set Cookie to response.
	 * 
	 * @param name
	 *            cookie name
	 * @param value
	 *            cookie value
	 * @param maxAgeInSeconds
	 *            -1: clear cookie when close browser. 0: clear cookie
	 *            immediately. n>0 : max age in n seconds.
	 * @param path
	 *            see Cookie.setPath(String)
	 * @param domain
	 *            the domain name within which this cookie is visible; form is
	 *            according to RFC 2109
	 * @param isHttpOnly
	 *            true if this cookie is to be marked as HttpOnly, false
	 *            otherwise
	 */
	public static void setCookie(String name, String value, int maxAgeInSeconds, String path, String domain, boolean isHttpOnly) {
		doSetCookie(name, value, maxAgeInSeconds, path, domain, isHttpOnly);
	}

	/**
	 * Remove Cookie.
	 */
	public static void removeCookie(String name) {
		doSetCookie(name, null, 0, null, null, null);
	}

	/**
	 * Remove Cookie.
	 */
	public static void removeCookie(String name, String path) {
		doSetCookie(name, null, 0, path, null, null);
	}

	/**
	 * Remove Cookie.
	 */
	public static void removeCookie(String name, String path, String domain) {
		doSetCookie(name, null, 0, path, domain, null);
	}

	private static void doSetCookie(String name, String value, int maxAgeInSeconds, String path, String domain, Boolean isHttpOnly) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAgeInSeconds);
		// set the default path value to "/"
		if (path == null) {
			path = "/";
		}
		cookie.setPath(path);

		if (domain != null) {
			cookie.setDomain(domain);
		}
		if (isHttpOnly != null) {
			cookie.setHttpOnly(isHttpOnly);
		}
		getResponse().addCookie(cookie);
	}

	public <T> T getBean(Class<T> beanClass, String beanName) {
		String parameter = getRequest().getParameter(beanName);
		if (parameter == null) {
			return null;
		}
		return (T) JacksonUtil.coverObj(parameter, beanClass);
	}

	/**
	 * Keep all parameter's value except model value
	 */
	public static void keepPara() {
		Map<String, String[]> map = getRequest().getParameterMap();
		for (Entry<String, String[]> e : map.entrySet()) {
			String[] values = e.getValue();
			if (values.length == 1)
				getRequest().setAttribute(e.getKey(), values[0]);
			else
				getRequest().setAttribute(e.getKey(), values);
		}

	}

	/**
	 * Return true if the para value is blank otherwise return false
	 */
	public boolean isParaBlank(String paraName) {
		String value = getRequest().getParameter(paraName);
		return value == null || value.trim().length() == 0;
	}

	/**
	 * Return true if the para exists otherwise return false
	 */
	public boolean isParaExists(String paraName) {
		return getRequest().getParameterMap().containsKey(paraName);
	}
	
	public static JSONMap getParaMatersFromRequest(ServletRequest request){
		JSONMap datas=new JSONMap();
		Map<String,String[]> paraMeterMap=request.getParameterMap();
		for(Map.Entry<String,String[]> entry:paraMeterMap.entrySet()){
			if(entry.getValue().length==1) {
				datas.put(entry.getKey(), entry.getValue()[0]);
			}else{
				datas.put(entry.getKey(), entry.getValue());
			}
		}
		return datas;
	}

	public static Page<?> getPage(boolean fromRequest) {
		Page<?> pi = new Page<>();
		if(fromRequest){
			HttpServletRequest request=getRequest();
			String parameter = request.getParameter("pageSize");
			if (StringUtils.isNotEmpty(parameter)) {
				pi.setPageSize(Integer.parseInt(parameter));
			}
			String parameter2 = request.getParameter("pageIndex");
			if (StringUtils.isNotEmpty(parameter2)) {
				pi.setPageIndex(Integer.parseInt(parameter2));
			}
			String parameter3 = request.getParameter("sortField");
			if (StringUtils.isNotEmpty(parameter3)) {
				pi.setSortField(parameter3);
			}
			String parameter4 = request.getParameter("sortOrder");
			if (StringUtils.isNotEmpty(parameter4)) {
				pi.setSortOrder(parameter4);
			}
		}
		return pi;
	}

	public static Page<?> getPage(JSONMap data,boolean fromRequest) {
		Page<?> pi = getPage(fromRequest);
		if(data.containsKey("pageSize")){
			pi.setPageSize(data.getInt("pageSize"));
		}
		if(data.containsKey("pageIndex")){
			pi.setPageIndex(data.getInt("pageIndex"));
		}
		if(data.containsKey("sortField")){
			pi.setSortField(data.getStr("sortField"));
		}
		if(data.containsKey("sortOrder")){
			pi.setSortOrder(data.getStr("sortOrder"));
		}
		return pi;
	}
	
	protected static final String contentType = "text/html; charset=UTF-8";
	public static String renderErr(int statu,Object service, String methodName, String msg) {
		HttpServletResponse response=getResponse();
		msg=service.getClass() + "." + methodName+msg;
		String err="访问异常：statu="+statu+" msg="+msg;
		response.setStatus(statu);
		PrintWriter writer = null;
		try {
			response.setContentType(contentType);
			writer = response.getWriter();
			writer.write(msg);
			writer.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (writer != null)
				writer.close();
		}
		return err;
	}
}
