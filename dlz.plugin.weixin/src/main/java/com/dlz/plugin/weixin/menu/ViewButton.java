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
	
	//小程序Appid
	private String appid;
	
	//小程序页面路径
	private String pagepath;
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPagepath() {
		return pagepath;
	}

	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}

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
