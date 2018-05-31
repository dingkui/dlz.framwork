package com.dlz.web.inf;

import com.dlz.app.uim.bean.AuthUser;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;

public interface IApiAjax {
	/**
	 * 根据传递的参数自动登录并设置自动登录结果
	 */
	public AuthUser autoLogin(String ui, JSONResult m);

	// 取得客户端用户信息
	default JSONMap getClientUserInfo(){
		return null;
	}

	// 取得渠道信息
	public String getChannel();
}
