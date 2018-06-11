package com.dlz.framework.ssme.shiro;

import org.apache.shiro.SecurityUtils;

import com.dlz.app.uim.bean.AuthUser;
import com.dlz.app.uim.holder.ISessionDeal;
import com.dlz.web.holder.ThreadHolder;

@SuppressWarnings("unchecked")
public class SessionDeal implements ISessionDeal {

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
		return (T) SecurityUtils.getSubject().getPrincipal();
	}
	@Override
	public void setAuthInfo(String sessionName, AuthUser user) {
		setSessionAttr(sessionName, user);
	}
}