package com.dlz.plugin.weixin.message.event;

/**
 * 时间基类
 * 
 * @author 陈孙亮（微信）
 * @date 20140-06-27
 *
 */
public class BaseEvent {


	//开发者微信号
	private String ToUserName;
	
	//发送方账号(一个OpenID)
	private String FromUserName;
	
	//消息创建时间 （整形）
	private long CreateTime;
	
	//消息类型
	private String MsgType;
	
	//事件类型
	private String Event;
	
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	
	
}
