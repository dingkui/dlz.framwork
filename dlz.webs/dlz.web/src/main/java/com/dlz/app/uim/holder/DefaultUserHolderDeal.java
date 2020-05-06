package com.dlz.app.uim.holder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.dlz.app.uim.bean.AuthUser;
import com.dlz.app.uim.cache.AuthUserCache;
import com.dlz.comm.util.ValUtil;
import com.dlz.web.holder.ThreadHolder;

@Component
@Lazy
public class DefaultUserHolderDeal implements IUserHolderDeal {
	@Autowired
	private AuthUserCache membercache;

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AuthUser> T getAuthInfo(String sessionName) {
		Long memberid = ValUtil.getLong(ThreadHolder.getSessionAttr(sessionName));
		if (memberid != null) {
			return (T)membercache.get(memberid);
		}
		return null;
	}

	@Override
	public void setAuthInfo(String sessionName, AuthUser user) {
		ThreadHolder.setSessionAttr(sessionName, user.getId());
	}
}
