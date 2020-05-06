package com.dlz.web.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import com.dlz.comm.json.JSONMap;
import com.dlz.framework.exception.HttpException;
import com.dlz.framework.exception.LogicException;
import org.slf4j.Logger;
import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.StringUtils;

/**
 * HTTP相关的操作
 * 
 * @author Dawei
 * 
 */

public class HttpUtil {

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(HttpUtil.class);
	
	private final static String CHARSET_UTF8="UTF-8";
	
	public static HttpUtilEnum getHttpUtil(String method){
		return HttpUtilEnum.valueOf(method.toUpperCase());
	}
	
	public enum HttpUtilEnum{
		PUT,
		DELETE,
		UPDATEL,
		POST,
		TRACE,
		GET;
		private HttpRequestBase getRequest(String url){
			switch (this.toString()) {
				case "GET":
					return new HttpGet(url);
				case "POST":
					return new HttpPost(url);
				case "DELETE":
					return new HttpDelete(url);
				case "TRACE":
					return new HttpTrace(url);
				case "OPTIONS":
					return new HttpOptions(url);
				case "PUT":
					return new HttpPut(url);
				default:
					return new HttpPost(url);
			}
		}
		
		public String send(String url, Map<String, Object> para, Map<String, String> headers, String charsetNameSend, String charsetNamere,HttpClientContext localContext) {
			return (String) doHttp(getRequest(url), url, para, null, headers, charsetNameSend, charsetNamere, true, 1, localContext);
		}
		public String send(String url, Map<String, Object> para,HttpClientContext localContext){
			return (String) doHttp(getRequest(url), url, para, null, null, CHARSET_UTF8, CHARSET_UTF8, true, 1,localContext);
		}
		public String send(String url, Map<String, Object> para, Map<String, String> headers){
			return (String) doHttp(getRequest(url), url, para, null, headers, CHARSET_UTF8, CHARSET_UTF8, true, 1,null);
		}
		public String sendWithEntry(String url, Map<String, Object> para, Map<String, String> headers){
			return (String) doHttp(getRequest(url), url, null, para==null?null:new StringEntity(JacksonUtil.getJson(para),CHARSET_UTF8), headers, CHARSET_UTF8, CHARSET_UTF8, true, 1,null);
		}
		public String send(String url, Map<String, Object> para){
			return (String) doHttp(getRequest(url), url, para, null, null, CHARSET_UTF8, CHARSET_UTF8, true, 1,null);
		}
		public String send(String url){
			return (String) doHttp(getRequest(url), url, null, null, null, CHARSET_UTF8, CHARSET_UTF8, true, 1,null);
		}
		public String send(String url, HttpEntity para, Map<String, String> headers, String charsetNameSend, String charsetNamere,HttpClientContext localContext) {
			return (String) doHttp(getRequest(url), url, null, para, headers, charsetNameSend, charsetNamere, true, 1, localContext);
		}
		public String send(String url, HttpEntity para) {
			return (String) doHttp(getRequest(url), url, null, para, null, CHARSET_UTF8, CHARSET_UTF8, true, 1, null);
		}
		public JSONMap send4JSON(String url, Map<String, Object> para, Map<String, String> headers, String charsetNameSend, String charsetNamere,HttpClientContext localContext) {
			try {
				return new JSONMap(doHttp(getRequest(url), url, para, null, headers, charsetNameSend, charsetNamere, true, 1, localContext));
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return null;
		}
		public JSONMap send4JSON(String url, Map<String, Object> para) {
			return send4JSON(url, para, null, CHARSET_UTF8, CHARSET_UTF8, null);
		}
		public JSONMap send4JSON(String url, Map<String, Object> para,Map<String, String> headers) {
			return send4JSON(url, para, headers, CHARSET_UTF8, CHARSET_UTF8, null);
		}
		public JSONMap send4JSON(String url) {
			return send4JSON(url,null);
		}
		public Document send4Dom(String url, Map<String, Object> para, Map<String, String> headers, String charsetNameSend, String charsetNamere,HttpClientContext localContext) {
			try {
				return (Document)doHttp(getRequest(url), url, para, null, headers, charsetNameSend, charsetNamere, true, 2, localContext);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return null;
		}
		public Document send4Dom(String url, Map<String, Object> para) {
			return send4Dom(url, para, null, CHARSET_UTF8, CHARSET_UTF8, null);
		}
		public Document send4Dom(String url) {
			return send4Dom(url,null);
		}
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
			HttpClientContext localContext){
		HttpClient httpClient = HttpConnUtil.wrapClient(url);
		
		if(localContext==null){
			localContext = new HttpClientContext();
		}
		
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
						nameValuePairList.add(new BasicNameValuePair(e.getKey(), JacksonUtil.getJson(e.getValue())));
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
				result=getResult(execute.getEntity().getContent(), returnType,charsetNamere);
				return result;
			case HttpStatus.SC_NOT_FOUND:
				throw new HttpException("地址无效:"+request.getURI(),statusCode);
			case HttpStatus.SC_UNAUTHORIZED:
			case HttpStatus.SC_FORBIDDEN:
				throw new HttpException("无访问权限:"+request.getURI(),statusCode);
			default :
				if(statusCode>3000&&statusCode<3100){
					throw new LogicException(statusCode, (String)getResult(execute.getEntity().getContent(), 1,charsetNamere), null);
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
	
	private static Object getResult(InputStream inputStream,int returnType,String charsetNamere) throws Exception{
		if(returnType==1){
			StringBuffer sb=new StringBuffer();
			byte[] buffer = new byte[4096];
			int read = 0;
			while (read != -1) {
				read = inputStream.read(buffer);
				if (read > 0) {
					sb.append(new String(buffer,0,read,charsetNamere));
				}
			}
			
//			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
//			String temp = new String(in.readLine().getBytes(charsetNamere));
//			StringBuffer sb=new StringBuffer();
//			/* 连接成一个字符串 */
//			while (temp != null) {
//				sb.append(temp);
//				temp = in.readLine();
//			}
			return sb.toString();
		}else if(returnType==2){
			//获取输入流
			return new SAXReader().read(inputStream);
		}
		return null;
	}
	
	public static class  HttpConnUtil {
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
		public static HttpClient wrapClient(String host) {
			HttpClientBuilder setConnectionManagerShared = HttpClientBuilder.create().setConnectionManager(cm).setConnectionManagerShared(true);
			if (host.startsWith("https://")) {
				return setConnectionManagerShared.setSSLSocketFactory(sslsf).build();
			}
			return setConnectionManagerShared.build();
		}
	}
	
	
	public static String buildUrl(String host, String path, Map<String, Object> querys) throws UnsupportedEncodingException {
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
        				sbQuery.append(URLEncoder.encode(JacksonUtil.getJson(query.getValue()), "utf-8"));
        			}        			
                }
        	}
        	if (0 < sbQuery.length()) {
        		sbUrl.append(sbUrl.indexOf("?")>-1?"&":"?").append(sbQuery);
        	}
        }
    	return sbUrl.toString();
    }
}
