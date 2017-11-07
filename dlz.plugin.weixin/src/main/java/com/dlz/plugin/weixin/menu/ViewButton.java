package com.dlz.plugin.weixin.menu;

/**
 * view 类型的按钮
 * 
 * @author Administrator
 *
 */
public class ViewButton extends Button {

	//按钮连接，点击按钮后访问的网页连接
	private String url;
	
	//按钮类型
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
