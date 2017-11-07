package com.dlz.plugin.weixin.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.weixin.pojo.SNSUserInfo;
import com.dlz.plugin.weixin.pojo.WeixinOauth2Token;
import com.dlz.plugin.weixin.util.AdvancedUtil;

/**
 * 授权后的回到请求处理
 * 
 * @author 陈孙亮（微信）
 *
 */
public class OAuthServlet extends HttpServlet{
	private static MyLogger logger = MyLogger.getLogger(OAuthServlet.class);
	public void doGet(HttpServletRequest request,HttpServletResponse response
			) throws ServletException, IOException{
		request.setCharacterEncoding("gb2312");
		request.setCharacterEncoding("gb2312");
		
		//用户同事授权后，能获取到code
		String code = request.getParameter("code");
		logger.debug(code);
//		用户同意授权
		if(!"authdeny".equals(code)){
			//获取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken("APPID", "APPSECRET", code);
			//网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();
			//用户标识
			String openId = weixinOauth2Token.getOpenId();
			//获取用户信息
			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId); 
			//设置要触底的参数
			request.setAttribute("snsUserInfo", snsUserInfo);
		}
		request.getRequestDispatcher("pages/snsUser.jsp").forward(request, response);
	}
	

}
