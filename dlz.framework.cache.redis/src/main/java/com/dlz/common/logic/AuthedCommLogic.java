package com.dlz.common.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.common.bean.AuthUser;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.holder.ThreadHolder;

public class AuthedCommLogic{
	@Autowired
	protected ICommService commService;
	
	public boolean needAuth() {
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends AuthUser> T getMember(){
		return (T)ThreadHolder.getAuthInfo();
	}
	
	/**
	 * 是否登录
	 * @return
	 */
	protected  boolean hasAuth(){
		return ThreadHolder.getAuthInfo()!=null;
	}
	
	public HttpServletRequest getRequest(){
		return ThreadHolder.getHttpRequest();
	}
	public HttpSession getSession(){
		return ThreadHolder.getSession();
	}
}
