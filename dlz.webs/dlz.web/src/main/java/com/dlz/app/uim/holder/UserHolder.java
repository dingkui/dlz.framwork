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
public class UserHolder {

	private static String SESSION_AUTHUSER = "curr_member";
	private static IUserHolderDeal holder;

	public static IUserHolderDeal getUserHolder() {
		if (holder == null) {
			synchronized (UserHolder.class) {
				holder = SpringHolder.getBean("userHolderDeal");
				if (holder == null) {
					holder=SpringHolder.getBean("defaultUserHolderDeal");
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
