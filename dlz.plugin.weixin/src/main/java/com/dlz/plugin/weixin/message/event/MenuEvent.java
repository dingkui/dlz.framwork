package com.dlz.plugin.weixin.message.event;

/**
 *  自定义菜单事件
 *  
 * @author Administrator
 *
 */
public class MenuEvent extends BaseEvent {

	//事件Key值，与自定义菜单接口中KEY值对应
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	
	
}
