package com.dlz.apps.sys.db.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.framework.ssme.util.config.ConfigUtil;
import com.dlz.framework.util.StringUtils;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
@Transactional(rollbackFor = Exception.class)
public class PushUtil {
	private static String appId = null;
	private static String appKey = null;
	private static String masterSecret = null;
	private static String host = null;
	private static IGtPush push = null;

	public static void initPush(){
		if(push!=null){
			return;
		}
		synchronized (PushUtil.class) {
			if(push!=null){
				return;
			}
			// 配置返回每个用户返回用户状态，可选
			System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
			appId=ConfigUtil.getConfig("app.push.appID");
			appKey=ConfigUtil.getConfig("app.push.appKey");
			masterSecret=ConfigUtil.getConfig("app.push.masterSecret");
			host=ConfigUtil.getConfig("app.push.host");
			push = new IGtPush(host, appKey, masterSecret);
			return;
		}
	}
	
	private static class Template{
		static Gson gson = new Gson();
		/**
		 * 在通知栏显示一条含图标、标题等的通知，用户点击后激活应用。 （激活后，打开应用的首页，如果只要求点击通知唤起应用，不要求到哪个指定页面就可以用此功能)
		 * 
		 * @param title
		 * @param text
		 * @param transJsonObj
		 * @return
		 */
		private static NotificationTemplate notificationTemplate(String title, String text, JsonObject transJsonObj) {
			NotificationTemplate template = new NotificationTemplate();
			// 设置APPID与APPKEY
			template.setAppId(appId);
			template.setAppkey(appKey);
			// 设置通知栏标题与内容
			template.setTitle(title);
			template.setText(text);
			// 配置通知栏图标
			template.setLogo("icon.png");
			// 配置通知栏网络图标
			template.setLogoUrl("");
			// 设置通知是否响铃，震动，或者可清除
			template.setIsRing(true);
			template.setIsVibrate(true);
			template.setIsClearable(true);
			// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
			template.setTransmissionType(1);
			template.setTransmissionContent(gson.toJson(transJsonObj));
			return template;
		}
		
		private static TransmissionTemplate getTransmissionTemplateWithApn(String title, String text, JsonObject transJsonObj) {
			TransmissionTemplate template = new TransmissionTemplate();
			template.setAppId(appId);
			template.setAppkey(appKey);
			template.setTransmissionType(2);
			APNPayload payload = new APNPayload();
			payload.setContentAvailable(1);
			payload.setSound("default");
	    payload.setCategory(gson.toJson(transJsonObj));
	    payload.setAutoBadge("1");
//		payload.setCategory("1111");
      APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
      alertMsg.setBody(text);
      alertMsg.setTitle(title);
      //alertMsg.setLocKey(gson.toJson(transJsonObj));
			// 简单模式APNPayload.SimpleMsg
			payload.setAlertMsg(alertMsg);
			template.setAPNInfo(payload);
			return template;
		}
	}



	/**
	 * 点击通知透传模板启动应用-多用户
	 * 
	 * @param cids
	 * @param title
	 * @param content
	 * @param json
	 * @param callBackUrl
	 */
	public static String listNotificationMsg(List<String> cids, String title, String content, JsonObject json) {
		if(StringUtils.isEmpty(cids)) {
			return "";
		}
		initPush();
		// 通知透传模板
		NotificationTemplate notificationTemplate = Template.notificationTemplate(title, content, json);
		ListMessage message = new ListMessage();
		message.setData(notificationTemplate);
		message.setOffline(true);// 设置消息离线，并设置离线时间
		message.setOfflineExpireTime(24 * 1000 * 3600);// 离线有效时间，单位为毫秒，可选
		// 配置推送目标
		List<Target> targets = new ArrayList<Target>();
		for (String cid : cids) {
			Target target1 = new Target();
			target1.setAppId(appId);
			target1.setClientId(cid);
			targets.add(target1);
		}
		String taskId = push.getContentId(message);// taskId用于在推送时去查找对应的message
		IPushResult ret = push.pushMessageToList(taskId, targets);
		return ret.getResponse().toString();
	}

	public static String apnPush(String token, String title, String content, JsonObject json) throws Exception {
		if(StringUtils.isEmpty(token)) {
			return "";
		}
		initPush();
		TransmissionTemplate tr = Template.getTransmissionTemplateWithApn(title, content, json);
		SingleMessage sm = new SingleMessage();
		sm.setData(tr);
		IPushResult ret0 = push.pushAPNMessageToSingle(appId, token, sm);
		return ret0.getResponse().toString();
	}
}