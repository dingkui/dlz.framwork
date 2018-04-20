package com.dlz.framework.db.enums;


public enum DateFormatEnum {
	
	DateStr("yyyy-MM-dd"),
	DateTimeStr("yyyy-MM-dd HH:mm:ss"),
	DateTimeStrShort("MM-dd HH:mm"),
	DateTimeStr2("yyyy-MM-dd HH:mm");
	private String formatStr;
	
	private DateFormatEnum(String formatStr) {
		this.formatStr = formatStr;
	}
	/**  
	 * 获取formatStr
	 * @return formatStr  
	 */
	public String getFormatStr() {
		return formatStr;
	}
}
