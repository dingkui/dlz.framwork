package com.dlz.framework.ssme.shiro;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import com.dlz.app.uim.bean.AuthUser;
import com.dlz.app.uim.holder.IUserHolderDeal;
import com.dlz.web.holder.ThreadHolder;

@SuppressWarnings("unchecked")
@Component
public class UserHolderDeal implements IUserHolderDeal {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	@Override
	public <T extends AuthUser> T getAuthInfo(String sessionName) {
		return (T) SecurityUtils.getSubject().getPrincipal();
	}
	@Override
	public void setAuthInfo(String sessionName, AuthUser user) {
		ThreadHolder.setSessionAttr(sessionName, user);
	}
}
