package com.dlz.web.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.exception.HttpException;
import com.dlz.framework.exception.LogicException;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.StringUtils;

/**
 * HTTP相关的操作
 * 
 * @author Dawei
 * 
 */

public class HttpUtil {
	private static MyLogger logger = MyLogger.getLogger(HttpUtil.class);
	
	private final static String CHARSET_UTF8="UTF-8";

	/**
	 * 
	 * @param request
	 * @param url
	 * @param para
	 * @param entity
	 * @param headers
	 * @param charsetNameSend
	 * @param charsetNamere
	 * @param showLog
	 * @param returnType
	 * @return
	 * @throws Exception
	 */
	public static Object doHttp(HttpRequestBase request,
			String url, 
			Map<String, Object> para, 
			HttpEntity entity, 
			Map<String, String> headers,
			String charsetNameSend, 
			String charsetNamere, 
			boolean showLog,
			int returnType){
		return doHttp(request, url, para, entity, headers, charsetNameSend, charsetNamere, showLog, returnType,null);
	}
	/**
	 * 
	 * @param request
	 * @param url
	 * @param para
	 * @param entity
	 * @param headers
	 * @param charsetNameSend
	 * @param charsetNamere
	 * @param showLog
	 * @param returnType
	 * @return
	 * @throws Exception
	 */
	public static Object doHttp(HttpRequestBase request,
			String url, 
			Map<String, Object> para, 
			HttpEntity entity, 
			Map<String, String> headers,
			String charsetNameSend, 
			String charsetNamere, 
			boolean showLog,
			int returnType,
			HttpClientContext localContext/*,
			CookieStore cookieStore*/){
		HttpClient httpClient = HttpConnUtil.wrapClient(url);
		
		if(localContext==null){
			localContext = new HttpClientContext();
		}
//		if(cookieStore==null){
//			cookieStore = new BasicCookieStore();
//		}
//		localContext.setCookieStore(cookieStore);
		
		if(headers!=null && !headers.isEmpty()){
			for (Map.Entry<String, String> e : headers.entrySet()) {
				request.addHeader(e.getKey(), e.getValue());
			}
		}
		
		Object result = null;
		try {
			if (para != null && !para.isEmpty()) {
				if(request instanceof HttpEntityEnclosingRequestBase) {
					List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
					for (Map.Entry<String, Object> e : para.entrySet()) {
						nameValuePairList.add(new BasicNameValuePair(e.getKey(), JacksonUtil.cover2String(e.getValue())));
					}
					UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, charsetNameSend);
					formEntity.setContentType("application/x-www-form-urlencoded; charset="+charsetNameSend);
					((HttpEntityEnclosingRequestBase)request).setEntity(formEntity);
				}else{
					request.setURI(new URI(buildUrl(url, null, para)));
				}
			}else if(entity!=null){
				if(request instanceof HttpEntityEnclosingRequestBase) {
					((HttpEntityEnclosingRequestBase)request).setEntity(entity);
				}
			}
			HttpResponse execute = httpClient.execute(request,localContext);
			int statusCode = execute.getStatusLine().getStatusCode();
			switch(statusCode){
			case HttpStatus.SC_OK:
				result=getResult(execute.getEntity().getContent(), returnType);
//				if(returnType==1){
//					BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
//					String temp = in.readLine();
//					StringBuffer sb=new StringBuffer();
//					/* 连接成一个字符串 */
//					while (temp != null) {
//						sb.append(temp);
//						temp = in.readLine();
//					}
//					result=sb.toString();
//				}else if(returnType==2){
//					//获取输入流
//					result=new SAXReader().read(inputStream);
//				}
				return result;
			case HttpStatus.SC_NOT_FOUND:
				throw new HttpException("地址无效:"+request.getURI(),statusCode);
			case HttpStatus.SC_UNAUTHORIZED:
			case HttpStatus.SC_FORBIDDEN:
				throw new HttpException("无访问权限:"+request.getURI(),statusCode);
			default :
				if(statusCode>3000&&statusCode<3100){
					throw new LogicException(statusCode, (String)getResult(execute.getEntity().getContent(), 1), null);
				}else{
					throw new HttpException("访问异常:"+request.getURI()+" 返回码:"+statusCode,statusCode);
				}
			}
		} catch (LogicException e) {
			throw e;
		} catch (HttpException e) {
			throw e;
		} catch (Exception e) {
			logger.error("doHttp "+request.getMethod()+" Exception:" + e.getMessage()+" url:" + request.getURI(),e);
			throw new RuntimeException(e);
		} finally {
			if(showLog && logger.isDebugEnabled()){
				if(result instanceof Document) {
					logger.debug("doHttp "+request.getMethod()+" url:" + request.getURI() + " para:" + para + " re:" + ((Document)result).asXML());
				}else{
					logger.debug("doHttp "+request.getMethod()+" url:" + request.getURI() + " para:" + para + " re:" + result);
				}
			}
			request.releaseConnection();
		}
	}
	
	private static Object getResult(InputStream inputStream,int returnType) throws Exception{
		if(returnType==1){
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
			String temp = in.readLine();
			StringBuffer sb=new StringBuffer();
			/* 连接成一个字符串 */
			while (temp != null) {
				sb.append(temp);
				temp = in.readLine();
			}
			return sb.toString();
		}else if(returnType==2){
			//获取输入流
			return new SAXReader().read(inputStream);
		}
		return null;
	}
	/**
	 * 
	 * @param request
	 * @param url
	 * @param para
	 * @param headers
	 * @param charsetNameSend
	 * @param charsetNamere
	 * @return
	 * @throws Exception
	 */
	public static String doHttp(HttpRequestBase request,String url, Map<String, Object> para,Map<String, String> headers,  String charsetNameSend,String charsetNamere) {
		return (String)doHttp(request, url, para,null, headers, charsetNameSend, charsetNamere, true,1);
	}
	
	public static class HttpPostUtil{
		public static String post(String url, Map<String, Object> querys,Map<String, String> headers,  String charsetNameSend,String charsetNamere,HttpClientContext localContext){    	
			return (String)doHttp(new HttpPost(url), url, querys,null, headers, charsetNameSend, charsetNamere, true,1,localContext);
		}
		public static String post(String url, Map<String, Object> para,Map<String, String> headers,  String charsetNameSend,String charsetNamere) throws Exception {
			return post(url, para, headers, charsetNameSend, charsetNamere,null);
		}
		public static String post(String url, Map<String, Object> para,HttpClientContext localContext) throws Exception {
			return post(url, para, null, CHARSET_UTF8, CHARSET_UTF8,localContext);
		}
		public static String post(String url, Map<String, Object> para, String charsetName) throws Exception {
			return post(url, para, null, charsetName, charsetName);
		}
		public static String post(String url, Map<String, Object> para,Map<String, String> headers)
				throws Exception {    	
			return post(url, para, headers, CHARSET_UTF8, CHARSET_UTF8);
		}
		public static String post(String url, Map<String, Object> para)
				throws Exception {    	
			return post(url, para, null, CHARSET_UTF8, CHARSET_UTF8);
		}
		public static String post(String url) throws Exception {
			return post(url, CHARSET_UTF8);
		}
		public static String post(String url, String charsetName) throws Exception {
			return doHttp(new HttpPost(url), url, null, null, charsetName, charsetName);
		}
		
		
		public static String post(String url, HttpEntity para,Map<String, String> headers,  String charsetNameSend,String charsetNamere) throws Exception {
			return (String)doHttp(new HttpPost(url), url, null,para, headers, charsetNameSend, charsetNamere,true,1);
		}
		public static String post(String url, HttpEntity para, String charsetName) throws Exception {
			return post(url, para, null, charsetName, charsetName);
		}
		public static String post(String url, HttpEntity para,Map<String, String> headers)
				throws Exception {    	
			return post(url, para, headers, CHARSET_UTF8, CHARSET_UTF8);
		}
		public static String post(String url, HttpEntity para)
				throws Exception {    	
			return post(url, para, null, CHARSET_UTF8, CHARSET_UTF8);
		}
	}
	
	public static class HttpGetUtil{
		public static String get(String url, Map<String, Object> querys,Map<String, String> headers,  String charsetNameSend,String charsetNamere,HttpClientContext localContext){    	
			return (String)doHttp(new HttpGet(url), url, querys,null, headers, charsetNameSend, charsetNamere, true,1,localContext);
		}
		public static String get(String url, Map<String, Object> querys,Map<String, String> headers,  String charsetNameSend,String charsetNamere){    	
			return get(url, querys, headers, charsetNameSend, charsetNamere, null);
		}
		public static String get(String url, Map<String, Object> querys, String charsetName) throws Exception {
			return get(url, querys, null, charsetName, charsetName);
		}
		public static String get(String url, Map<String, Object> querys,Map<String, String> headers)
				throws Exception {    	
			return get(url, querys, headers, CHARSET_UTF8, CHARSET_UTF8);
		}
		public static String get(String url, Map<String, Object> querys)
				throws Exception {    	
			return get(url, querys, null, CHARSET_UTF8, CHARSET_UTF8);
		}
		public static String get(String url,HttpClientContext localContext)  {
			return get(url, null,null, CHARSET_UTF8, CHARSET_UTF8,localContext);
		}
		public static String get(String url,Map<String, Object> querys,HttpClientContext localContext)  {
			return get(url, querys,null, CHARSET_UTF8, CHARSET_UTF8,localContext);
		}
		public static String get(String url)  {
			return get(url, null,null, CHARSET_UTF8, CHARSET_UTF8);
		}
		public static String get(String url, String charsetName)  {
			return get(url, null,null, charsetName, charsetName);
		}
		
		public static JSONMap get4JSON(String url, Map<String, Object> querys) {
			try {
				return new JSONMap(get(url,querys));
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return null;
		}
		public static JSONMap get4JSON(String url) {
			return get4JSON(url,null);
		}
		
		public static Document get4Dom(String url, Map<String, Object> querys) {
			try {
				return (Document)doHttp(new HttpGet(url), url, querys,null, null, CHARSET_UTF8, CHARSET_UTF8,true,2);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return null;
		}
		public static Document get4Dom(String url) {
			return get4Dom(url,null);
		}
	}
	
	
	private static class  HttpConnUtil {
		private static final String HTTP = "http";
		private static final String HTTPS = "https";
		private static SSLConnectionSocketFactory sslsf = null;
		private static PoolingHttpClientConnectionManager cm = null;
		private static SSLContextBuilder builder = null;
		static {
			try {
				builder = new SSLContextBuilder();
				// 全部信任 不做身份鉴定
				builder.loadTrustMaterial(null, new TrustStrategy() {
					@Override
					public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
						return true;
					}
				});
				sslsf = new SSLConnectionSocketFactory(builder.build(), new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2" }, null,
						NoopHostnameVerifier.INSTANCE);
				Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register(HTTP, new PlainConnectionSocketFactory())
						.register(HTTPS, sslsf).build();
				cm = new PoolingHttpClientConnectionManager(registry);
				cm.setMaxTotal(200);// max connection
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		private static HttpClient wrapClient(String host) {
			HttpClientBuilder setConnectionManagerShared = HttpClientBuilder.create().setConnectionManager(cm).setConnectionManagerShared(true);
			if (host.startsWith("https://")) {
				return setConnectionManagerShared.setSSLSocketFactory(sslsf).build();
			}
			return setConnectionManagerShared.build();
		}
	}
	
	
	private static String buildUrl(String host, String path, Map<String, Object> querys) throws UnsupportedEncodingException {
    	StringBuilder sbUrl = new StringBuilder();
    	sbUrl.append(host);
    	if (!StringUtils.isEmpty(path)) {
    		sbUrl.append(path);
        }
    	if (null != querys) {
    		StringBuilder sbQuery = new StringBuilder();
        	for (Map.Entry<String, Object> query : querys.entrySet()) {
        		if (0 < sbQuery.length()) {
        			sbQuery.append("&");
        		}
        		if (StringUtils.isEmpty(query.getKey()) && !StringUtils.isEmpty(query.getValue())) {
        			sbQuery.append(query.getValue());
                }
        		if (!StringUtils.isEmpty(query.getKey())) {
        			sbQuery.append(query.getKey());
        			if (!StringUtils.isEmpty(query.getValue())) {
        				sbQuery.append("=");
        				sbQuery.append(URLEncoder.encode(JacksonUtil.cover2String(query.getValue()), "utf-8"));
        			}        			
                }
        	}
        	if (0 < sbQuery.length()) {
        		sbUrl.append(sbUrl.indexOf("?")>-1?"&":"?").append(sbQuery);
        	}
        }
    	return sbUrl.toString();
    }


	
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
}
