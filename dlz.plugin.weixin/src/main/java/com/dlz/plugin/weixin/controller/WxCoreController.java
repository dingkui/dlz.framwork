package com.dlz.plugin.weixin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import com.dlz.plugin.weixin.service.ACoreService;
import com.dlz.plugin.weixin.util.SignUtil;

/**
 * 核心请求处理类
 * @author dk
 * @date 2017-08-24
 * 
 */
@Controller
@RequestMapping("/wxCore")
public class WxCoreController {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	@Autowired
	ACoreService coreService;
	
	private static Logger log = org.slf4j.LoggerFactory.getLogger(WxCoreController.class);

	/**
	 * 确认（处理）请求来自微信服务器
	 */
	@RequestMapping("")
	public void responseMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String method = request.getMethod();
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		
		boolean checkSignature=SignUtil.checkSignature(signature, timestamp, nonce);
		
		if(checkSignature){
			//通过检验signature对请求进行校验
			if (method.equals("GET")) {// 响应微信发送的Token验证
				//原样返回echostr，表示接入成功，否则接入失败
				String echostr = request.getParameter("echostr");
				out.print(echostr);
			} else if (method.equals("POST")) {// 响应用户发送的微信消息
				//响应消息 调用response.getWriter().write()方法将消息的处理结果返回给用户
				String respMessage = coreService.processRequest(request);
				out.print(respMessage);
			}
		}else{
			log.error("微信请求校验失败：method={1},signature={2},timestamp={3},nonce={4}", method,signature,timestamp,nonce);
		}
		out.close();
		out = null;
	}
}
