package com.dlz.app.uim.holder;

import com.dlz.app.uim.bean.AuthUser;

/**
 *  用ThreadLocal来存储用户登录信息
 * @author dk 2017-06-15
 * @version 1.1
 */
public interface ISessionDeal  {
	public  <T> T getSessionAttr(String sessionName);
	public  void removeSessionAttr(String sessionName);
	public void setSessionAttr(String sessionName,Object obj);
	
	public <T extends AuthUser> T getAuthInfo(String sessionName);
	public void setAuthInfo(String sessionName, AuthUser user);
}
