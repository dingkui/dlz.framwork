package com.dlz.plugin.weixin.message.req;

/**
 * 文本消息
 * 
 * @author 陈孙亮（微信）
 * @date 2014-06-27
 * 
 *
 */
public class TextMessage extends BaseMessage {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	
	//消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
	
}
