package com.dlz.plugin.weixin.message.req;

/**
 * 视频消息
 * 
 * @author Administrator
 * @date 2014-06-27
 *
 */
public class VideoMessage extends BaseMessage {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	//媒体文件  ID
	private String MediaId;
	//缩略图的媒体 ID
	private String ThumbMediaId;
	
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
}
