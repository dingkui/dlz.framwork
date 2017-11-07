package com.dlz.plugin.weixin.message.req;


/**
 * 链接消息
 * 
 * @author 陈孙亮（微信）
 * @date 2014-06-29
 *
 */
public class LinkMessage {

	// 消息标题
	private String Title;
	
	// 消息描述
	private String Description;
	
	// 消息链接
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
	
	
}
