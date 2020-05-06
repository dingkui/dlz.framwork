package com.dlz.app.uim.enums;

/**
 * 密码类型
 * @author dingkui
 *
 */
public enum PwdTypeEnum {
	/** 登录密码 */
	login(1),
	/** 支付密码 */
	pay(2);
	private int type;
	
	PwdTypeEnum(int type) {
		this.type = type;
	}

	public int getPwdType() {
		return type;
	}
}
