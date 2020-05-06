package com.dlz.plugin.weixin.pojo;

import com.dlz.comm.util.DateUtil;

/**
 * 凭证
 * 
 * @author 陈孙亮（微信）
 *
 */
public class Token {


	//接口访问凭证
	private String accessToken;
	
	//凭证有效期，单位：秒
	private int expiresIn;
	
	//凭证过期时间
	private long expiresTime;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
		expiresTime= DateUtil.getDateline()+expiresIn;
	}

	/**
	 * 是否在有效期内
	 * @return
	 */
	public boolean isUsefull() {
		return DateUtil.getDateline() < expiresTime;
	}
	
}
