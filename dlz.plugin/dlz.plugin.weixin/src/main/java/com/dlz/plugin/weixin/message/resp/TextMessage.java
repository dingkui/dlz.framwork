package com.dlz.plugin.weixin.message.resp;


/**
 * 文本消息 
 * 
 * @author 陈孙亮（微信）
 * @date 2014-06-26
 *
 */
public class TextMessage extends BaseMessage {


	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
	
}
