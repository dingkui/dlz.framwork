package com.dlz.plugin.weixin.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;


/**
 * 测试获取凭证（HTTPS GET 请求）
 * 
 * @author Administrator
 *
 */
public class TokenTest {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(TokenTest.class);
	public static void main(String[] args) {
		//获取凭证接口地址： appid 及 secret需要获取
		String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?" +
		"grant_type=client_credential&appid=wx5d0abadb6f4ddf2b&secret=91f27f6f6cf80e19c8240b60e0eb5950";
		
		try {
			//建立连接
			URL url = new URL(tokenUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			//使用自定义的信任管理器
			TrustManager[] tm = { new Myx509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
			sslContext.init(null, tm, new SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			conn.setSSLSocketFactory(ssf);
			conn.setDoInput(true);
			//设置请求方式
			conn.setRequestMethod("GET");
			//取得输入流
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			//读取相应内容
			StringBuffer buffer= new StringBuffer();
			String str = null;
			while((str = bufferedReader.readLine()) != null){
				buffer.append(str);
			}
			//关闭并释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			conn.disconnect();
			//输出返回结果
			logger.debug(buffer.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
