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
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static String SESSION_AUTHUSER = "curr_member";

	private static IUserHolderDeal holder;

	public static IUserHolderDeal getUserHolder() {
		if (holder == null) {
			synchronized (UserHolder.class) {
				holder = SpringHolder.getBean("userHolderDeal");
				if (holder == null) {
					holder = new IUserHolderDeal() {
						@Override
						public <T extends AuthUser> T getAuthInfo(String sessionName) {
							return (T) ThreadHolder.getSessionAttr(sessionName);
						}
						@Override
						public void setAuthInfo(String sessionName, AuthUser user) {
							ThreadHolder.setSessionAttr(sessionName, user);
						}
					};
				}
			}
		}
		return holder;
	}

	public static <T extends AuthUser> T getAuthInfo() {
		return getUserHolder().getAuthInfo(SESSION_AUTHUSER);
	}
	public static Long getUserId() {
		return getAuthInfo().getId();
	}

	public static void setAuthInfo(AuthUser user) {
		getUserHolder().setAuthInfo(SESSION_AUTHUSER, user);
	}
	public static void removeUser() {
		ThreadHolder.removeSessionAttr(SESSION_AUTHUSER);
	}
}
