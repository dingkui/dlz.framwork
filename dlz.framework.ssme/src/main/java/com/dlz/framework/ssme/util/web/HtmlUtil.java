package com.dlz.framework.ssme.util.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class HtmlUtil {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	public static String delHTMLTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[//s//S]*?<///script>"; // 定义script的正则表达式
		String regEx_style = "<style[^>]*?>[//s//S]*?<///style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
		Pattern p_script = Pattern.compile(regEx_script,
				Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签
		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		return htmlStr.trim(); // 返回文本字符串
	}

	public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getHeader("x-forward-for");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
	
	public static String getServer(HttpServletRequest request) {
		String scheme = request.getHeader("x-real-scheme");
		if(scheme == null || scheme.length() == 0 || "unknown".equalsIgnoreCase(scheme)) {
			scheme = request.getScheme();
		}
		String port = request.getHeader("x-real-port");
		if(port == null || port.length() == 0 || "unknown".equalsIgnoreCase(port)) {
			port = String.valueOf(request.getServerPort());
		}
		return scheme+"://"+request.getServerName()+":"+port;
	}
}
