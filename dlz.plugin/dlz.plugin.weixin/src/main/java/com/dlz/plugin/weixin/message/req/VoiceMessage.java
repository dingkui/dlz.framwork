package com.dlz.plugin.weixin.message.req;

/**
 * 音频消息
 * 
 * @author 陈孙亮（微信）
 * @date 2014-06-26
 *
 */
public class VoiceMessage {


	// 媒体ID   
	private String MediaId;
	
	// 语音格式
	private String Format;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}
	
	
}
