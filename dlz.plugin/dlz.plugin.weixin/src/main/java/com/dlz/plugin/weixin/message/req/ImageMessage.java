package com.dlz.plugin.weixin.message.req;

/**
 * 图片消息
 * @author liufeng 
 * @date 2014-06-29
 *
 */

public class ImageMessage extends BaseMessage {


	// 图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	
	
}
