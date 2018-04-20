package com.dlz.common.util.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dlz.framework.util.JacksonUtil;
 
/**
 * 这个Https协议工具类，采用HttpsURLConnection实现。
 * 提供get和post两种请求静态方法
 * 
 * @author marker
 * @date 2014年8月30日
 * @version 1.0
 */
public class HttpUtil {
	
	private  static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	public static TrustManager myX509TrustManager = new X509TrustManager() {

		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException { 
		}

		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException { 
		}

		public X509Certificate[] getAcceptedIssuers() { 
			return null;
		}
	};
	
	public static HttpURLConnection getHttpConn(String url){
		try {
		URL requestUrl = new URL(url);
		HttpURLConnection httpConn = (HttpURLConnection) requestUrl.openConnection();
		if(httpConn instanceof HttpsURLConnection){
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { myX509TrustManager },
					null);
			HttpsURLConnection httpsConn=(HttpsURLConnection)httpConn;
			httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());
		}
		return httpConn;
		}catch(Exception e){
			return null;
		}
	}

	
	public static String sendHttpsPOST(String url, String data) {
		String result = null;
		try {
			// 设置SSLContext
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { myX509TrustManager },
					null);

			// 打开连接
			// 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
			URL requestUrl = new URL(url);
			HttpsURLConnection httpsConn = (HttpsURLConnection) requestUrl
					.openConnection();

			// 设置套接工厂
			httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

			// 加入数据
			httpsConn.setRequestMethod("POST");
			httpsConn.setDoOutput(true);
			OutputStream out = httpsConn.getOutputStream() ;
			 
			if (data != null)
				out.write(data.getBytes("UTF-8")); 
			out.flush();
			out.close();

			// 获取输入流
			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpsConn.getInputStream()));
			int code = httpsConn.getResponseCode();
			if (HttpsURLConnection.HTTP_OK == code) {
				String temp = in.readLine();
				/* 连接成一个字符串 */
				while (temp != null) {
					if (result != null)
						result += temp;
					else
						result = temp;
					temp = in.readLine();
				}
			}
		} catch (KeyManagementException e) {
			logger.error(e.getMessage(),e);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(),e);
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(),e);
		} catch (ProtocolException e) {
			logger.error(e.getMessage(),e);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		return result;
	}
	
	
	
	public static String sendHttpsGET(String url) {
		return sendHttpsGET(url, "UTF-8");
	}
	public static String sendHttpsGET(String url,String charsetName) {
		String result = null;
		try {
			// 设置SSLContext
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { myX509TrustManager },
					null);

			// 打开连接
			// 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
			URL requestUrl = new URL(url);
			HttpsURLConnection httpsConn = (HttpsURLConnection) requestUrl
					.openConnection();

			// 设置套接工厂
			httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

			// 加入数据
			httpsConn.setRequestMethod("GET");
//			httpsConn.setDoOutput(true);
			// 获取输入流
			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpsConn.getInputStream(), charsetName));
			int code = httpsConn.getResponseCode();
			if (HttpsURLConnection.HTTP_OK == code) {
				String temp = in.readLine();
				/* 连接成一个字符串 */
				while (temp != null) {
					if (result != null)
						result += temp;
					else
						result = temp;
					temp = in.readLine();
				}
			}
		} catch (KeyManagementException e) {
			logger.error(e.getMessage(),e);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(),e);
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(),e);
		} catch (ProtocolException e) {
			logger.error(e.getMessage(),e);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}

		return result;
	}
	
	public static String sendHttpPost(String url, Map<String, String> para) throws Exception {
		String result = null;
		String action="["+Thread.currentThread().getName()+"]HttpPost";
		logger.debug(action, "req:url="+url+" para="+JacksonUtil.writeValueAsString(para));
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		try {
			NameValuePair[] nameValuePairs = new NameValuePair[para.size()];
			int i=0;
			for (String strNm : para.keySet()) {
				nameValuePairs[i++]=new NameValuePair(strNm, para.get(strNm));
			}

			postMethod.setRequestBody(nameValuePairs);
			int statusCode = httpClient.executeMethod(postMethod);

			if (statusCode != HttpStatus.SC_OK) {
				logger.debug(action, "response status error" + statusCode);
				return result;
			}

			StringBuffer sb = new StringBuffer();
			InputStream in = postMethod.getResponseBodyAsStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			result = sb.toString();
			if (br != null) {
				br.close();
			}
		}catch(Exception e){
			logger.error(action, e.getMessage());
			throw e;
		} finally {
			logger.debug(action, "res:" + result);
			postMethod.releaseConnection();
		}
		return result;
	}
}
