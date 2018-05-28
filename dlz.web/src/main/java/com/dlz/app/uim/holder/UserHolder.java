package com.dlz.app.uim.holder;

import com.dlz.app.uim.bean.AuthUser;
import com.dlz.web.holder.ThreadHolder;


/**
 *  用ThreadLocal来存储用户登录信息
 * @author dk 2017-06-15
 * @version 1.1
 */
@SuppressWarnings("unchecked")
public class UserHolder  {
	private static String SESSION_AUTHUSER="curr_member";
	
	public static <T extends AuthUser> T getAuthInfo(){
		return getAuthInfo(SESSION_AUTHUSER);
	}
	public static void setAuthInfo(AuthUser user){
		setAuthInfo(SESSION_AUTHUSER, user);
	}
	
	public static <T extends AuthUser> T getAuthInfo(String sessionName){
		return (T)ThreadHolder.getSessionAttr(sessionName);
	}
	public static void setAuthInfo(String sessionName,AuthUser user){
		ThreadHolder.setSessionAttr(sessionName, user);
	}
}
