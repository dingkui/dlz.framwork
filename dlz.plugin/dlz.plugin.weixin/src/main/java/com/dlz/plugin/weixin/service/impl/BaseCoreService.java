package com.dlz.plugin.weixin.service.impl;

import java.util.Map;

import com.dlz.plugin.weixin.service.ACoreService;

/**
 * 核心服务类
 */
@SuppressWarnings("unused")
public class BaseCoreService extends ACoreService{

	//文本消息
	public String dealText(String fromUserName,String toUserName,Map<String, String> requestMap){
		String txtContent = requestMap.get("Content");
		if(txtContent.endsWith("w")||txtContent.endsWith("W")){
			String content="Hello，我是客服小妹，您想知道什么？更多问题请拨打客服热线：400-008-2019我们将竭诚为您服务~~";
			return getTextMsg(fromUserName, toUserName, content);
		}else {
			return "";
		}
	}
	
	//图片消息
	public String dealImage(String fromUserName,String toUserName,Map<String, String> requestMap){
		return getMultiMsg(fromUserName, toUserName);
	}
	//音频消息
	public String dealVoice(String fromUserName,String toUserName,Map<String, String> requestMap){
		return getMultiMsg(fromUserName, toUserName);
	}
	//视频消息
	public String dealVedio(String fromUserName,String toUserName,Map<String, String> requestMap){
		return getMultiMsg(fromUserName, toUserName);
	}
	//地理位置消息
	public String dealLocation(String fromUserName,String toUserName,Map<String, String> requestMap){
		String content = "您发送的是地理位置消息!";
		// 地理位置纬度
		String lat = requestMap.get("Location_X");
		// 地理位置经度
		String lng = requestMap.get("Location_Y");
		// 地理位置信息
		String label = requestMap.get("Label");
		// 地图缩放大小
		String scale = requestMap.get("Scale");
		return getTextMsg(fromUserName, toUserName, content);
	}
	//链接消息
	public String dealLink(String fromUserName,String toUserName,Map<String, String> requestMap){
		String content = "您发送的是链接消息!";
		// 消息标题
		String title = requestMap.get("Title");
		// 消息描述
		String description = requestMap.get("Description");
		// 消息连接
		String url = requestMap.get("Url");
		return getTextMsg(fromUserName, toUserName, content);
	}
	
	/**
	 * 关注事件
	 *  扫描带参数二维码事件:用户未关注时，进行关注后的事件推送
	 * @param eventKey
	 * @return
	 */
	public String dealEventSubscribe(String fromUserName,String toUserName,Map<String, String> requestMap,String eventKey){
		String content = "欢迎关注";
		return getTextMsg(fromUserName, toUserName, content);
	}
	/**
	 * 取消关注事件
	 *  扫描带参数二维码事件:用户未关注时，进行关注后的事件推送
	 * @param eventKey
	 * @return
	 */
	public String dealEventUnsubscribe(String fromUserName,String toUserName,Map<String, String> requestMap,String eventKey){
		String content = "您好！若您有任何疑问、意见或者建议，可随时拨打格林森健康家客服热线：400-008-2019 格林森健康家客服中心竭诚为您服务！";
		return getTextMsg(fromUserName, toUserName, content);
	}
	
	/**
	 * 扫描带参数二维码事件事件
	 *  扫描带参数二维码事件:用户已关注时的事件推送
	 * @param eventKey
	 * @return
	 */
	public String dealEventScan(String fromUserName,String toUserName,Map<String, String> requestMap,String eventKey){
		String content = "取消关注";
		return getTextMsg(fromUserName, toUserName, content);
	}
	/**
	 * 上报地图位置
	 * @param eventKey
	 * @return
	 */
	public String dealEventLocation(String fromUserName,String toUserName,Map<String, String> requestMap,String eventKey){
		String content = "上报地图位置";
		return getTextMsg(fromUserName, toUserName, content);
	}
	/**
	 *  自定义菜单点击事件
	 * @param eventKey
	 * @return
	 */
	public String dealEventClick(String fromUserName,String toUserName,Map<String, String> requestMap,String eventKey){
		String content = "菜单点击"+eventKey;
		return getTextMsg(fromUserName, toUserName, content);
	}
}