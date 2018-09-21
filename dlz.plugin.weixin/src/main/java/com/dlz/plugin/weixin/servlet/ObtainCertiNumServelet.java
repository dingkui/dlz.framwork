package com.dlz.plugin.weixin.servlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
/**
 * 获取手机验证码与发送短信
 * @author Administrator
 *
 */
public class ObtainCertiNumServelet extends HttpServlet {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static final long serialVersionUID = -5794207647541551424L;
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(ObtainCertiNumServelet.class);
	public ObtainCertiNumServelet() {
        super();
    }
	
    public void destroy() {
        super.destroy(); 
    }
    
    public void init() throws ServletException {
    }
    
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		long certiNum = Math.round((Math.random() * 8999 +1000)); //获取随机四位验证码
		HttpSession session=request.getSession();
		session.setAttribute("certiNum",certiNum );
		String message="当前验证码为："+certiNum+",请输入完成验证,谢谢！【格林森健康家】";
		String phoneNum=request.getParameter("phoneNum");
		messageSendAndFind(message,phoneNum);
		
		
	}
	
	public static String messageSendAndFind(String msg,String phoneNum ) throws UnsupportedEncodingException {
		String lines = "";
		String newMsg= URLEncoder.encode(msg, "utf-8");	
		logger.debug(newMsg);
		String getURL = "";
		// 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码
		
		getURL = "http://203.93.96.226:8003/Api/smssend.aspx?user=zykji&pass=zykji_123&telno=1&msg="+newMsg+"&mobiles="+phoneNum;
		
		HttpURLConnection connection;
		// 取得输入流，并使用Reader读取
		BufferedReader reader;
		try {
			URL getUrl = new URL(getURL);
			 connection = (HttpURLConnection) getUrl
			         .openConnection();
			 // 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
			// 服务器
			connection.connect();
			 reader = new BufferedReader(new InputStreamReader(
			         connection.getInputStream()));
			 
			  while ((lines = reader.readLine()) != null) {
		             logger.debug(lines);
		             break;
		         }
		         reader.close();
		         // 断开连接
		        connection.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
		}
		return lines;
	}

}
