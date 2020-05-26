package com.dlz.framework.db.enums;


public enum CharsetNameEnum {
	
	UTF8("UTF-8"),
	GBK("GBK");
	private String charsetName;
	
	private CharsetNameEnum(String charsetName) {
		this.charsetName = charsetName;
	}

	public String getCharsetName() {
		return charsetName;
	}
}
