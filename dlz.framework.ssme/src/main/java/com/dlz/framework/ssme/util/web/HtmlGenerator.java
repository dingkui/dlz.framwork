package com.dlz.framework.ssme.util.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.dlz.framework.logger.MyLogger;
/**
 * 静态页面引擎技术（突乱了乱码问题UTF-8）
 */
public class HtmlGenerator {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static MyLogger logger = MyLogger.getLogger(HtmlGenerator.class);
	HttpClient httpClient = null; // HttpClient实例
	GetMethod getMethod = null; // GetMethod实例
	BufferedWriter fw = null;
	String page = null;
	String webappname = null;
	BufferedReader br = null;
	InputStream in = null;
	StringBuffer sb = null;
	String line = null;

	// 构造方法
	public HtmlGenerator(String webappname) {
		this.webappname = webappname;

	}
	
	public  void createHtmlPage(String url, String htmlFileName) {
		if(url.toLowerCase().startsWith("https")){
			createHttpsHtml(url, htmlFileName);
		}else{
			createHttpHtml(url, htmlFileName);
		}
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "resource" })
	public  boolean createHttpsHtml(String url, String htmlFileName) {
		boolean status = false;
		HttpGet httpget=null;
		try {
			org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
			// Secure Protocol implementation.
			SSLContext ctx = SSLContext.getInstance("SSL");
			// Implementation of a trust manager for X509 certificates
			X509TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
				}
				public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
				}
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx);
			ClientConnectionManager ccm = httpclient.getConnectionManager();
			// register https protocol in httpclient's scheme registry
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", 443, ssf));
			 httpget = new HttpGet(url);
//			HttpParams params = httpclient.getParams();
//			params.setParameter("param1", "paramValue1");
//			httpget.setParams(params);
			System.out.println("REQUEST:" + httpget.getURI());
			ResponseHandler responseHandler = new BasicResponseHandler();
			page = (String) httpclient.execute(httpget, responseHandler);
			// 将解析结果写入指定的静态HTML文件中，实现静态HTML生成
			writeHtml(htmlFileName, page);
			status = true;
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
		} finally {
			// 释放http连接
			httpget.releaseConnection();
		}
		return status;
	}

	/** 根据模版及参数产生静态页面 */
	public  boolean createHttpHtml(String url, String htmlFileName) {
		boolean status = false;
		int statusCode = 0;
		try {
			// 创建一个HttpClient实例充当模拟浏览器
			httpClient = new HttpClient();
			// 设置httpclient读取内容时使用的字符集
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			// 创建GET方法的实例
			getMethod = new GetMethod(url);
			// 使用系统提供的默认的恢复策略，在发生异常时候将自动重试3次
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
			// 设置Get方法提交参数时使用的字符集,以支持中文参数的正常传递
			getMethod.addRequestHeader("Content-Type", "text/html;charset=UTF-8");

			System.out.println("REQUEST:" + getMethod.getURI());
			// 执行Get方法并取得返回状态码，200表示正常，其它代码为异常
			statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != 200) {
				System.out.println("REQUEST ERROR:" + statusCode);
			} else {
				// 读取解析结果
				sb = new StringBuffer();
				in = getMethod.getResponseBodyAsStream();
				// br = new BufferedReader(new
				// InputStreamReader(in));//此方法默认会乱码，经过长时期的摸索，下面的方法才可以
				br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				if (br != null)
					br.close();
				page = sb.toString();
				writeHtml("c:/c1.html", page);
				// 将页面中的相对路径替换成绝对路径，以确保页面资源正常访问
				page = formatPage(page);
				// 将解析结果写入指定的静态HTML文件中，实现静态HTML生成
				writeHtml(htmlFileName, page);
				status = true;
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
		} finally {
			// 释放http连接
			getMethod.releaseConnection();
		}
		return status;
	}

	// 将解析结果写入指定的静态HTML文件中
	private synchronized void writeHtml(String htmlFileName, String content) throws Exception {
		fw = new BufferedWriter(new FileWriter(htmlFileName));
		OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(htmlFileName), "UTF-8");
		fw.write(page);
		if (fw != null)
			fw.close();
	}

	// 将页面中的相对路径替换成绝对路径，以确保页面资源正常访问
	private String formatPage(String page) {
		page = page.replaceAll("\\.\\./\\.\\./\\.\\./", webappname + "/");
		page = page.replaceAll("\\.\\./\\.\\./", webappname + "/");
		page = page.replaceAll("\\.\\./", webappname + "/");
		page = page.replaceAll("href=\"", "href=\""+webappname );
		page = page.replaceAll("src=\"", "src=\""+webappname );
		return page;
	}

	// 测试方法
	public static void main(String[] args) {
		HtmlGenerator h = new HtmlGenerator("webappname");
		h.createHtmlPage("http://www.sina.com.cn", "c:/c.html");
		System.out.println("静态页面已经生成到c:/c.html");
	}

}