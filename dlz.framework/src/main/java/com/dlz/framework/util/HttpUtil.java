package com.dlz.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.dlz.framework.holder.ThreadHolder;
import com.dlz.framework.logger.MyLogger;

/**
 * HTTP相关的操作
 * 
 * @author Dawei
 * 
 */

public class HttpUtil {
	private static MyLogger logger = MyLogger.getLogger(HttpUtil.class);

	public static String getParameter(String name) {
		return ThreadHolder.getHttpRequest().getParameter(name);
	}

	public static Integer getIntPara(String name) {
		try {
			return Integer.parseInt(getParameter(name));
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue, int time) {
	    addCookie(response, null, "/", cookieName, cookieValue, time);
	}

	public static void addCookie(HttpServletResponse response, String domain, String path, String cookieName,
			String cookieValue, int time) {
		try {
			if (cookieValue != null) {
				cookieValue = URLEncoder.encode(cookieValue, "UTF-8");
			}
		} catch (Exception ex) {
		}
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(time);
		if (!StringUtils.isEmpty(domain)) {
			cookie.setDomain(domain);
		}
		cookie.setPath(path);
		response.addCookie(cookie);
	}

	public static void addCookie1(HttpServletResponse response, String cookieName, String cookieValue, int time) {

		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(time);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public static String getCookieValue(HttpServletRequest request, String cookieName, String domain, String path) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (domain.equals(cookies[i].getDomain()) && path.equals(cookies[i].getPath())
						&& cookieName.equals(cookies[i].getName())) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 根据cookie名称取得cookie的值
	 * 
	 * @param HttpServletRequest
	 *            request request对象
	 * @param name
	 *            cookie名称
	 * @return string cookie的值 当取不到cookie的值时返回null
	 */
	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {

				if (cookieName.equals(cookies[i].getName())) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}

	static TrustManager[] myX509TrustManagers = new TrustManager[] { new Myx509TrustManager() };

	public static HttpURLConnection getHttpConn(String url) {
		try {
			URL requestUrl = new URL(url);
			HttpURLConnection httpConn = (HttpURLConnection) requestUrl.openConnection();
			if (httpConn instanceof HttpsURLConnection) {
				SSLContext sslcontext = SSLContext.getInstance("TLS");
				sslcontext.init(null, myX509TrustManagers, null);
				HttpsURLConnection httpsConn = (HttpsURLConnection) httpConn;
				httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());
			}
			return httpConn;
		} catch (Exception e) {
			return null;
		}
	}

	public static String sendHttpsPOST(String url, String data) {
		String result = null;
		try {
			// 设置SSLContext
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, myX509TrustManagers, null);

			// 打开连接
			// 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
			URL requestUrl = new URL(url);
			HttpsURLConnection httpsConn = (HttpsURLConnection) requestUrl.openConnection();

			// 设置套接工厂
			httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

			// 加入数据
			httpsConn.setRequestMethod("POST");
			httpsConn.setDoOutput(true);
			OutputStream out = httpsConn.getOutputStream();

			if (data != null)
				out.write(data.getBytes("UTF-8"));
			out.flush();
			out.close();

			// 获取输入流
			BufferedReader in = new BufferedReader(new InputStreamReader(httpsConn.getInputStream()));
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

			logger.debug("return:" + result);
		} catch (KeyManagementException e) {
			logger.error(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(), e);
		} catch (ProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.debug("HttpPost url:" + url + " para:" + data + " re:" + result);
		}
		return result;
	}

	public static String sendHttpsGET(String url) {
		return sendHttpsGET(url, "UTF-8");
	}

	public static String sendHttpsGET(String url, String charsetName) {
		String result = null;
		try {
			logger.debug("sendHttpsGET:" + url);
			// 设置SSLContext
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, myX509TrustManagers, null);
			// 打开连接
			// 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
			URL requestUrl = new URL(url);
			HttpsURLConnection httpsConn = (HttpsURLConnection) requestUrl.openConnection();

			// 设置套接工厂
			httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

			// 加入数据
			httpsConn.setRequestMethod("GET");
			// httpsConn.setDoOutput(true);
			// 获取输入流
			BufferedReader in = new BufferedReader(new InputStreamReader(httpsConn.getInputStream(), charsetName));
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
			logger.debug("result:" + result);
		} catch (KeyManagementException e) {
			logger.error(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(), e);
		} catch (ProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.debug("HttpsGET url:" + url + " re:" + result);
		}

		return result;
	}

	public static String sendHttpPost(String url) throws Exception {
		return sendHttpPost(url, null, "UTF-8", "UTF-8");
	}

	public static String sendHttpPost(String url, String charsetName) throws Exception {
		return sendHttpPost(url, null, charsetName, charsetName);
	}

	public static String sendHttpPost(String url, Map<String, String> para) throws Exception {
		return sendHttpPost(url, para, "UTF-8", "UTF-8");
	}

	public static String sendHttpPost(String url, Map<String, String> para, String charsetName) throws Exception {
		return sendHttpPost(url, para, charsetName, charsetName);
	}

	public static String sendHttpPost(String url, Map<String, String> para, String charsetNameSend,
			String charsetNamere) throws Exception {
		String result = null;
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		try {
			if (para != null) {
				NameValuePair[] nameValuePairs = new NameValuePair[para.size()];
				int i = 0;
				for (String strNm : para.keySet()) {
					nameValuePairs[i++] = new NameValuePair(strNm, para.get(strNm));
				}
				postMethod.setRequestBody(nameValuePairs);
			}

			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charsetNameSend);
			int statusCode = httpClient.executeMethod(postMethod);

			if (statusCode != HttpStatus.SC_OK) {
				return result;
			}
			StringBuffer sb = new StringBuffer();
			InputStream in = postMethod.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in, charsetNamere));
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			result = sb.toString();
			if (br != null) {
				br.close();
			}
		} catch (Exception e) {
			logger.debug("HttpPost Exception:" + e.getMessage(), e);
			throw e;
		} finally {
			logger.debug("HttpPost url:" + url + " para:" + para + " re:" + result);
			postMethod.releaseConnection();
		}
		return result;
	}

	public static String sendHttpGet(String url) throws Exception {
		return sendHttpGet(url, "UTF-8");
	}

	public static String sendHttpGet(String url, String charsetName) throws Exception {
		HttpClient httpClient = new HttpClient();
		GetMethod postMethod = new GetMethod(url);
		String result = null;
		try {
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				return result;
			}
			StringBuffer sb = new StringBuffer();
			InputStream in = postMethod.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in, charsetName));
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			result = sb.toString();
			if (br != null) {
				br.close();
			}
			return result;
		} catch (Exception e) {
			logger.debug("HttpGet Exception:" + e.getMessage(), e);
			throw e;
		} finally {
			logger.debug("HttpGet url:" + url + " re:" + result);
			postMethod.releaseConnection();
		}
	}
}
