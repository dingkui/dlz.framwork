package com.dlz.plugin.weixin.util;

import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;



/**
 * 信任管理器
 * 
 * @author 陈孙亮（微信）
 * @date 2014-7-1
 *
 */
public class Myx509TrustManager implements X509TrustManager{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(Myx509TrustManager.class);
	/**
	 * 检查客户端证书
	 * 
	 * @param chain
	 * @param authType
	 * @throws CertificateException
	 */
	@Override
	public void checkClientTrusted(X509Certificate[] chain,
			String authType) throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 检查服务器证书
	 * 
	 * @param chain
	 * @param authType
	 * @throws CertificateException
	 */
	@Override
	public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
			String authType) throws CertificateException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 返回收信的X509证书数组
	 * 
	 */
	@Override
	public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public static void main(String[] args){
		String requestUrl = "";
		
		//创建 SSLContext 对象，并使用我们制定的信任管理器初始化
		TrustManager[] tm = { new Myx509TrustManager() };
		try {
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			//从上述SSLContext 对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
		} 
	}
}
