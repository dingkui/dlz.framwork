package com.dlz.plugin.weixin.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jeewx.api.custservice.multicustservice.JwMultiCustomerAPI;

import org.slf4j.Logger;
import com.dlz.plugin.weixin.message.resp.Article;
import com.dlz.plugin.weixin.message.resp.NewsMessage;
import com.dlz.plugin.weixin.message.resp.TextMessage;
import com.dlz.plugin.weixin.util.MessageUtil;

/**
 * 核心服务类
 */
public abstract class ACoreService {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger log = org.slf4j.LoggerFactory.getLogger(ACoreService.class);

	// 文本消息
	public abstract String dealText(String fromUserName, String toUserName, Map<String, String> requestMap);

	// 图片消息
	public abstract String dealImage(String fromUserName, String toUserName, Map<String, String> requestMap);

	// 音频消息
	public abstract String dealVoice(String fromUserName, String toUserName, Map<String, String> requestMap);

	// 视频消息
	public abstract String dealVedio(String fromUserName, String toUserName, Map<String, String> requestMap);

	// 地理位置消息
	public abstract String dealLocation(String fromUserName, String toUserName, Map<String, String> requestMap);

	// 链接消息
	public abstract String dealLink(String fromUserName, String toUserName, Map<String, String> requestMap);

	/**
	 * 关注事件 扫描带参数二维码事件:用户未关注时，进行关注后的事件推送
	 * 
	 * @param eventKey
	 * @return
	 */
	public abstract String dealEventSubscribe(String fromUserName, String toUserName, Map<String, String> requestMap,
			String eventKey);

	/**
	 * 取消关注事件 扫描带参数二维码事件:用户未关注时，进行关注后的事件推送
	 * 
	 * @param eventKey
	 * @return
	 */
	public abstract String dealEventUnsubscribe(String fromUserName, String toUserName, Map<String, String> requestMap,
			String eventKey);

	/**
	 * 扫描带参数二维码事件事件 扫描带参数二维码事件:用户已关注时的事件推送
	 * 
	 * @param eventKey
	 * @return
	 */
	public abstract String dealEventScan(String fromUserName, String toUserName, Map<String, String> requestMap,
			String eventKey);

	/**
	 * 上报地图位置
	 * 
	 * @param eventKey
	 * @return
	 */
	public abstract String dealEventLocation(String fromUserName, String toUserName, Map<String, String> requestMap,
			String eventKey);

	/**
	 * 自定义菜单点击事件
	 * 
	 * @param eventKey
	 * @return
	 */
	public abstract String dealEventClick(String fromUserName, String toUserName, Map<String, String> requestMap,
			String eventKey);

	protected String getTextMsg(String fromUserName, String toUserName, String content) {
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);
		textMessage.setContent(content);
		return MessageUtil.messageToXml(textMessage);
	}
	
	protected String getNewsMsg(String fromUserName, String toUserName, List<Article> articles) {
		NewsMessage newsMessage=new NewsMessage();
		newsMessage.setArticles(articles);
		newsMessage.setArticleCount(articles.size());
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setFuncFlag(0);
		return MessageUtil.messageToXml(newsMessage);
	}

	protected String getMultiMsg(String fromUserName, String toUserName) {
		return new JwMultiCustomerAPI().getMultiCustServcieMessage(fromUserName, toUserName);
	}

	// 事件推送
	private String dealEvent(String fromUserName, String toUserName, Map<String, String> requestMap) {
		// 事件类型
		String eventType = requestMap.get("Event");
		String eventKey = requestMap.get("EventKey");
		switch (eventType) {
		case MessageUtil.EVENT_TYPE_SUBSCRIBE:// 扫描带参数二维码事件:用户未关注时，进行关注后的事件推送
			return dealEventSubscribe(fromUserName, toUserName, requestMap, eventKey);
		case MessageUtil.EVENT_TYPE_UNSUBSCRIBE:// 取消订阅
			return dealEventUnsubscribe(fromUserName, toUserName, requestMap, eventKey);
		case MessageUtil.EVENT_TYPE_SCAN:// 扫描带参数二维码事件:用户已关注时的事件推送
			return dealEventScan(fromUserName, toUserName, requestMap, eventKey);
		case MessageUtil.EVENT_TYPE_LOCATION:// 上报地图位置
			return dealEventLocation(fromUserName, toUserName, requestMap, eventKey);
		case MessageUtil.EVENT_TYPE_CLICK:// 自定义菜单点击事件
			return dealEventClick(fromUserName, toUserName, requestMap, eventKey);
		default:
			return getTextMsg(fromUserName, toUserName, "操作未识别：eventKey=" + eventKey + " eventType=" + eventType);
		}
	}

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request) {
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			switch (msgType) {
			case MessageUtil.REQ_MESSAGE_TYPE_TEXT:// 文本消息
				return dealText(fromUserName, toUserName, requestMap);
			case MessageUtil.REQ_MESSAGE_TYPE_IMAGE:// 图片消息
				return dealImage(fromUserName, toUserName, requestMap);
			case MessageUtil.REQ_MESSAGE_TYPE_LOCATION:// 地理位置消息
				return dealLocation(fromUserName, toUserName, requestMap);
			case MessageUtil.REQ_MESSAGE_TYPE_LINK:// 链接消息
				return dealLink(fromUserName, toUserName, requestMap);
			case MessageUtil.REQ_MESSAGE_TYPE_VOICE:// 音频消息
				return dealVoice(fromUserName, toUserName, requestMap);
			case MessageUtil.REQ_MESSAGE_TYPE_VIDEO:// 视频消息
				return dealVedio(fromUserName, toUserName, requestMap);
			case MessageUtil.REQ_MESSAGE_TYPE_EVENT:// 事件推送
				return dealEvent(fromUserName, toUserName, requestMap);
			default:
				return getTextMsg(fromUserName, toUserName, "请通过菜单使用地址导航服务！");
			}
		} catch (Exception e) {
			log.error("调用异常!");
			log.error(e.getMessage(), e);
		}
		return "操作未识别";
	}
}