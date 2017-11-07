package com.dlz.plugin.weixin.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.logger.MyLogger;

import net.sf.json.JSONObject;


/**
 * 通用工具类
 * 
 * @author 陈孙亮（微信）
 *
 */
public class CommonUtil {

	private static MyLogger logger = MyLogger.getLogger(CommonUtil.class);
	/**
	 * 发送 https 请求
	 * 
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	public static JSONMap httpsRequest(String requestUrl, String requestMethod,String outputStr){
		JSONMap jsonObject = null;
		try{
			//创建SSLContext 对象，并使用我们制定的信任管理器初始化
			TrustManager[] tm = { new Myx509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
			sslContext.init(null, tm, new SecureRandom());
			//从上述 SSLContext 对象中得到 SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
			conn.setSSLSocketFactory(ssf);
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			
			//设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			
			//当outputStr 部位Null 时，想输入流写数据
			if(null != outputStr){
				OutputStream outputStream = conn.getOutputStream();
				//注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			
			//从输入六读取放回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader); 
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while((str = bufferedReader.readLine()) != null ){
				buffer.append(str);
			}
			
			//释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			logger.debug(buffer.toString());
			jsonObject = JSONMap.createJsonMap(buffer.toString());
			
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			logger.error("https请求异常：{0}",e);
		}
		
		return jsonObject;
	}
}
