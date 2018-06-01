package com.dlz.app.uim.holder;

/**
 *  用ThreadLocal来存储用户登录信息
 * @author dk 2017-06-15
 * @version 1.1
 */
public interface ISessionDeal  {
	public  <T> T getSessionAttr(String sessionName);
	public  void removeSessionAttr(String sessionName);
	public void setSessionAttr(String sessionName,Object user);
}
