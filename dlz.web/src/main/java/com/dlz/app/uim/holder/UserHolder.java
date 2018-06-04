package com.dlz.app.uim.holder;

import com.dlz.app.uim.bean.AuthUser;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.web.holder.ThreadHolder;

/**
 * 用ThreadLocal来存储用户登录信息
 * 
 * @author dk 2017-06-15
 * @version 1.1
 */
@SuppressWarnings("unchecked")
public class UserHolder {
	private static String SESSION_AUTHUSER = "curr_member";

	private static ISessionDeal holder;

	public static ISessionDeal getHolder() {
		if (holder == null) {
			synchronized (UserHolder.class) {
				holder = SpringHolder.getBean("sessionDeal");
				if (holder == null) {
					holder = new ISessionDeal() {
						@Override
						public <T> T getSessionAttr(String sessionName) {
							return (T) ThreadHolder.getSessionAttr(sessionName);
						}
						@Override
						public void setSessionAttr(String sessionName, Object user) {
							ThreadHolder.setSessionAttr(sessionName, user);
						}
						@Override
						public void removeSessionAttr(String sessionName) {
							ThreadHolder.removeSessionAttr(sessionName);
						}
						@Override
						public <T extends AuthUser> T getAuthInfo(String sessionName) {
							return (T) getSessionAttr(sessionName);
						}
						@Override
						public void setAuthInfo(String sessionName, AuthUser user) {
							setSessionAttr(sessionName, user);
						}
					};
				}
			}
		}
		return holder;
	}

	public static <T extends AuthUser> T getAuthInfo() {
		return holder.getAuthInfo(SESSION_AUTHUSER);
	}

	public static void setAuthInfo(AuthUser user) {
		holder.setAuthInfo(SESSION_AUTHUSER, user);
	}

	public static <T extends AuthUser> T getAuthInfo(String sessionName) {
		return (T) holder.getAuthInfo(sessionName);
	}

	public static void setAuthInfo(String sessionName, AuthUser user) {
		holder.setAuthInfo(sessionName, user);
	}
}
