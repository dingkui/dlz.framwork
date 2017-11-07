package com.dlz.common.enums;

/**
 * 第三方登录
 * @author dingkui
 *
 */
public enum ThirdAuthEnum {
	QQopenid("QQopenid"),
	WxUnionid("WxUnionid"),
	AppWxUnionid("AppWxUnionid"),
	WapWxopenid("WapWxopenid");
	private String mame;
	
	ThirdAuthEnum(String mame) {
		this.mame = mame;
	}

	public String getThirdName() {
		return mame;
	}
}
