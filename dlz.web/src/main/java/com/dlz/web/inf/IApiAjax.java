package com.dlz.web.inf;

import com.dlz.app.sys.bean.AuthUser;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.logger.MyLogger;
import com.dlz.web.holder.ThreadHolder;
import com.dlz.web.util.AjaxApiUtil;

public interface IApiAjax {
	/**
	 * 取得日志，用于不同的功能日志输出
	 * 
	 * @param member
	 * @param para
	 * @param uimap
	 * @param m
	 * @return
	 */
	public MyLogger getLogger();
	
	/**
	 * 根据传递的参数自动登录
	 * 
	 * @param member
	 * @param para
	 * @param uimap
	 * @param m
	 * @return
	 */
	public AuthUser autoLogin(String ui, JSONResult m);

	// 取得用户登录信息
	default <T extends AuthUser> T getAuthInfo() {
		return ThreadHolder.getAuthInfo();
	}

	// 取得客户端用户信息
	default JSONMap getClientUserInfo(){
		return null;
	}

	// 取得渠道信息
	public String getChannel();
	
	default JSONResult doAjax(String data, String ui, String aType) {
		return AjaxApiUtil.doAjax(data, ui, aType, this);
	}

	default JSONResult doAjaxs(String data, String ui) {
		return AjaxApiUtil.doAjaxs(data, ui, this);
	}
}
