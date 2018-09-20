package com.dlz.plugin.weixin.menu;

/**
 * click 类型的按钮
 * 
 * @author 陈孙亮（微信）
 *
 */
public class ClickButton extends Button {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	
	//按钮键值，开发者通过他来判断用户点击的按钮
	private String Key;
	
	//按钮类型
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return Key;
	}

	public void setKey(String key) {
		Key = key;
	}
	
	
	
	
}
