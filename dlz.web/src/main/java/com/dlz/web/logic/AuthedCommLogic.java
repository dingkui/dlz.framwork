package com.dlz.web.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.app.uim.annotation.AnnoAuth;
import com.dlz.app.uim.bean.AuthUser;
import com.dlz.app.uim.holder.UserHolder;
import com.dlz.framework.db.service.ICommService;
import com.dlz.web.holder.ThreadHolder;

@AnnoAuth
public class AuthedCommLogic{
	@Autowired
	protected ICommService commService;
	
	public boolean needAuth() {
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends AuthUser> T getMember(){
		return (T)UserHolder.getAuthInfo();
	}
	
	/**
	 * 是否登录
	 * @return
	 */
	protected  boolean hasAuth(){
		return UserHolder.getAuthInfo()!=null;
	}
	
	public HttpServletRequest getRequest(){
		return ThreadHolder.getHttpRequest();
	}
	public HttpSession getSession(){
		return ThreadHolder.getSession();
	}
}
