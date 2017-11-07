package com.dlz.push.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dlz.common.util.config.ConfigUtil;
import com.dlz.framework.util.StringUtils;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.db.service.impl.CommServiceImpl;
import com.dlz.framework.holder.SpringHolder;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.NotyPopLoadTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Component
public class GTPushUtil {

	private static Logger logger = LoggerFactory.getLogger(GTPushUtil.class);

	private static String appId = ConfigUtil.getConfig("app.push.appId");
	private static String appKey = ConfigUtil.getConfig("app.push.appKey");
	private static String masterSecret = ConfigUtil.getConfig("app.push.masterSecret");
	private static String host = ConfigUtil.getConfig("app.push.host");

	/**
	 * 发送消息打开网页链接
	 * 
	 * @param cid
	 *          clienId
	 * @param title
	 *          通知标题
	 * @param content
	 *          内容
	 * @param callBackUrl
	 *          回调URL
	 * @return
	 */
	public static void singleLinkMsg(String cid, String title, String content, String callBackUrl) {
		IGtPush push = new IGtPush(host, appKey, masterSecret);
		LinkTemplate template = linkTemplate(title, content, callBackUrl);
		SingleMessage message = new SingleMessage();
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选 默认1h，3600*1000
		message.setOfflineExpireTime(2 * 3600 * 1000);
		message.setData(template);
		// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
		message.setPushNetWorkType(0);
		Target target = new Target();
		target.setAppId(appId);
		target.setClientId(cid);
		// target.setAlias(Alias);
		IPushResult ret = null;
		try {
			ret = push.pushMessageToSingle(message, target);
		} catch (RequestException e) {
			logger.error(e.getMessage(), e);
			ret = push.pushMessageToSingle(message, target, e.getRequestId());
		}
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
		} else {
			logger.error("-----------------------------");
			logger.error("服务器异常");
			logger.error("-----------------------------");
		}
	}

	/**
	 * 点击通知透传模板启动应用
	 * 
	 * @param cid
	 *          clienId
	 * @param title
	 *          通知标题
	 * @param content
	 *          内容
	 * @param callBackUrl
	 *          回调URL
	 * @return
	 */
	public void singleNotificationMsg(String cid, String title, String content, String callBackUrl) {
		IGtPush push = new IGtPush(host, appKey, masterSecret);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("url", callBackUrl);

		NotificationTemplate notificationTemplate = notificationTemplate(title, content, jsonObject);

		SingleMessage message = new SingleMessage();
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选 默认1h，3600*1000
		message.setOfflineExpireTime(2 * 3600 * 1000);
		message.setData(notificationTemplate);
		// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
		message.setPushNetWorkType(0);
		Target target = new Target();
		target.setAppId(appId);
		target.setClientId(cid);
		// target.setAlias(Alias);
		IPushResult ret = null;
		try {
			ret = push.pushMessageToSingle(message, target);
		} catch (RequestException e) {
			logger.error(e.getMessage(), e);
			ret = push.pushMessageToSingle(message, target, e.getRequestId());
		}
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
		} else {
			logger.error("-----------------------------");
			logger.error("服务器异常");
			logger.error("-----------------------------");
		}
	}

	/**
	 * 透传消息
	 * 
	 * @param cid
	 *          clienId
	 * @param title
	 *          通知标题
	 * @param content
	 *          内容
	 * @param callBackUrl
	 *          回调URL
	 * @return
	 */
	public static void singleTransmissionTemplateMsg(String cid, String title, String content, String callBackUrl) {
		IGtPush push = new IGtPush(host, appKey, masterSecret);

		Gson g = new Gson();
		Map<String, String> payLoadObj = new HashMap<String, String>();
		payLoadObj.put("url", callBackUrl);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("title", title);
		jsonObject.addProperty("content", content);
		jsonObject.addProperty("payload", g.toJson(payLoadObj));

		TransmissionTemplate transmissionTemplate = transmissionTemplate(jsonObject);

		SingleMessage message = new SingleMessage();
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选 默认1h，3600*1000
		message.setOfflineExpireTime(2 * 3600 * 1000);
		message.setData(transmissionTemplate);
		// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
		message.setPushNetWorkType(0);
		Target target = new Target();
		target.setAppId(appId);
		target.setClientId(cid);
		// target.setAlias(Alias);
		IPushResult ret = null;
		try {
			ret = push.pushMessageToSingle(message, target);
		} catch (RequestException e) {
			logger.error(e.getMessage(), e);
			ret = push.pushMessageToSingle(message, target, e.getRequestId());
		}
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
		} else {
			logger.error("-----------------------------");
			logger.error("服务器异常");
			logger.error("-----------------------------");
		}
	}

	/**
	 * 下载更新通知
	 * 
	 * @param cid
	 *          clienId
	 * @param title
	 *          通知标题
	 * @param content
	 *          内容
	 * @param callBackUrl
	 *          回调URL
	 * @return
	 */
	public void singleNotyPopLoadTemplateMsg(String cid, String title, String content, String dialogTitle, String dialogContent,
			String downloadTitle, String downloadUrl) {
		IGtPush push = new IGtPush(host, appKey, masterSecret);
		NotyPopLoadTemplate template = notyPopLoadTemplate(title, content, dialogTitle, dialogContent, downloadTitle, downloadUrl);
		SingleMessage message = new SingleMessage();
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选 默认1h，3600*1000
		message.setOfflineExpireTime(2 * 3600 * 1000);
		message.setData(template);
		// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
		message.setPushNetWorkType(0);
		Target target = new Target();
		target.setAppId(appId);
		target.setClientId(cid);
		// target.setAlias(Alias);
		IPushResult ret = null;
		try {
			ret = push.pushMessageToSingle(message, target);
		} catch (RequestException e) {
			logger.error(e.getMessage(), e);
			ret = push.pushMessageToSingle(message, target, e.getRequestId());
		}
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
		} else {
			logger.error("-----------------------------");
			logger.error("服务器异常");
			logger.error("-----------------------------");
		}
	}

	/**
	 * 发送消息打开网页链接-多用户
	 * 
	 * @param cids
	 * @param title
	 * @param content
	 * @param json
	 * @param callBackUrl
	 */
	public void listLinkMsg(List<String> cids, String title, String content, JsonObject json, String callBackUrl) {
		// 配置返回每个用户返回用户状态，可选
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		IGtPush push = new IGtPush(host, appKey, masterSecret);
		LinkTemplate template = linkTemplate(title, content, callBackUrl);
		ListMessage message = new ListMessage();
		message.setData(template);
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 1000 * 3600);
		// 配置推送目标
		List<Target> targets = new ArrayList<Target>();
		for (String cid : cids) {
			Target target1 = new Target();
			target1.setAppId(appId);
			target1.setClientId(cid);
			// target1.setAlias(Alias1);
		}
		// taskId用于在推送时去查找对应的message
		String taskId = push.getContentId(message);
		IPushResult ret = push.pushMessageToList(taskId, targets);
		System.out.println(ret.getResponse().toString());
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
	public static void listNotificationMsg(List<String> cids, String title, String content, JsonObject json, String callBackUrl) {
		// 配置返回每个用户返回用户状态，可选
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		IGtPush push = new IGtPush(host, appKey, masterSecret);
		// 通知透传模板
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("url", "callBackUrl");
		NotificationTemplate notificationTemplate = notificationTemplate(title, content, jsonObject);

		ListMessage message = new ListMessage();
		message.setData(notificationTemplate);
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 1000 * 3600);
		// 配置推送目标
		List<Target> targets = new ArrayList<Target>();
		for (String cid : cids) {
			Target target1 = new Target();
			target1.setAppId(appId);
			target1.setClientId(cid);
			targets.add(target1);
			// target1.setAlias(Alias1);
		}
		// taskId用于在推送时去查找对应的message
		String taskId = push.getContentId(message);
		IPushResult ret = push.pushMessageToList(taskId, targets);
		System.out.println(ret.getResponse().toString());
	}

	/**
	 * 透传-多用户
	 * 
	 * @param cids
	 * @param title
	 * @param content
	 * @param json
	 * @param callBackUrl
	 */
	public void listTransmissionTemplateMsg(List<String> cids, String title, String content, JsonObject json, String callBackUrl) {
		// 配置返回每个用户返回用户状态，可选
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		IGtPush push = new IGtPush(host, appKey, masterSecret);
		Gson g = new Gson();
		Map<String, String> payLoadObj = new HashMap<String, String>();
		payLoadObj.put("url", callBackUrl);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("title", title);
		jsonObject.addProperty("content", content);
		jsonObject.addProperty("payload", g.toJson(payLoadObj));

		TransmissionTemplate transmissionTemplate = transmissionTemplate(jsonObject);

		ListMessage message = new ListMessage();
		message.setData(transmissionTemplate);
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 1000 * 3600);
		// 配置推送目标
		List<Target> targets = new ArrayList<Target>();
		for (String cid : cids) {
			Target target1 = new Target();
			target1.setAppId(appId);
			target1.setClientId(cid);
			// target1.setAlias(Alias1);
		}
		// taskId用于在推送时去查找对应的message
		String taskId = push.getContentId(message);
		IPushResult ret = push.pushMessageToList(taskId, targets);
		System.out.println(ret.getResponse().toString());
	}

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
		Gson gson = new Gson();
		template.setTransmissionContent(gson.toJson(transJsonObj));
		return template;
	}

	/**
	 * 在通知栏显示一条含图标、标题等的通知，用户点击可打开您指定的网页。
	 * 
	 * @param appId
	 * @param appKey
	 * @return
	 */
	private static LinkTemplate linkTemplate(String title, String content, String url) {
		LinkTemplate template = new LinkTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appId);
		template.setAppkey(appKey);
		// 设置通知栏标题与内容
		template.setTitle(title);
		template.setText(content);
		// 配置通知栏图标
		template.setLogo("icon.png");
		// 配置通知栏网络图标
		template.setLogoUrl("");
		// 设置通知是否响铃，震动，或者可清除
		template.setIsRing(true);
		template.setIsVibrate(true);
		template.setIsClearable(true);
		// 设置打开的网址地址
		template.setUrl(url);
		// 设置定时展示时间
		// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
		return template;
	}

	/**
	 * 消息以弹框的形式展现，点击弹框内容可启动下载任务。 （iOS不支持该模板，有独立的模板）
	 * 
	 * @param appId
	 * @param appKey
	 * @return
	 */
	public static NotyPopLoadTemplate notyPopLoadTemplate(String title, String content, String dialogTitle, String dialogContent,
			String downloadTitle, String downloadUrl) {
		NotyPopLoadTemplate template = new NotyPopLoadTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appId);
		template.setAppkey(appKey);
		// 设置通知栏标题与内容
		template.setNotyTitle(title);
		template.setNotyContent(content);
		// 配置通知栏图标
		template.setNotyIcon("icon.png");
		// 设置通知是否响铃，震动，或者可清除
		template.setBelled(true);
		template.setVibrationed(true);
		template.setCleared(true);
		// 设置弹框标题与内容
		template.setPopTitle(dialogTitle);
		template.setPopContent(dialogContent);
		// 设置弹框显示的图片
		template.setPopImage("");
		template.setPopButton1("下载");
		template.setPopButton2("取消");
		// 设置下载标题
		template.setLoadTitle(downloadTitle);
		template.setLoadIcon("file://icon.png");
		// 设置下载地址
		// template.setLoadUrl("http://gdown.baidu.com/data/wisegame/80bab73f82cc29bf/shoujibaidu_16788496.apk");
		template.setLoadUrl(downloadUrl);
		// 设置定时展示时间
		// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
		return template;
	}

	/**
	 * 透传消息是指消息传递到客户端只有消息内容，展现形式由客户端自行定义。客户端可自定义通知的展现形式，也可自定义通知到达之后的动作，或者不做任何展现。
	 * 
	 * @return
	 */
	public static TransmissionTemplate transmissionTemplate(JsonObject transJsonObj) {
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appKey);
		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		template.setTransmissionType(2);
		Gson gson = new Gson();
		template.setTransmissionContent(gson.toJson(transJsonObj));
		// 设置定时展示时间
		// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
		return template;
	}

	public static void main(String[] args) {
		Gson g = new Gson();
		Map<String, String> payLoadObj = new HashMap<String, String>();
		payLoadObj.put("url", "http://www.baidu.com");

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("title", "恭喜中奖了");
		jsonObject.addProperty("content", "中了一车玩哈哈");
		jsonObject.addProperty("payload", g.toJson(payLoadObj));
		System.out.println(g.toJson(jsonObject));
//		singleLinkMsg("f519d8412f346d130e86a8636a11945b", "1", g.toJson(payLoadObj), "http://www.baidu.com");
//		singleTransmissionTemplateMsg("f519d8412f346d130e86a8636a11945b", "2", g.toJson(payLoadObj), "http://www.baidu.com");
		List<String> l=new ArrayList<String>();
		l.add("3c7058e75bca3c75085c1abd74c3ab99");
		listNotificationMsg(l, "3", g.toJson(payLoadObj),jsonObject, "http://www.baidu.com");
	}
	
	/**
	 * 透传推送
	 * @param userIds 接收消息的用户
	 * @param title 消息标题
	 * @param text 消息内容
	 * @param type 消息类型
	 * 					appPg 打开页面  
	 * 					evalJs 制定页面执行脚本
	 * 					runJs 首页执行脚本  
	 * 					update app更新  
	 * @param para
	 * 					{u,...} type=appPg u:打开页面的路径，...:页面参数
	 * 					{id,fn,...} type=evalJs id:页面的id，fn:页面执行的方法，...:方法参数
	 * 					{js} type=runJs js:执行的js
	 * 					{iosV,androidV,title,androidUrl} iosV:ios的新版本号（应用商店）,title:ios更新时的提示信息   （打开应用商店安装）
	 * 																					 androidV：安卓新版本号(发布的新版本号),androidUrl:安卓新版本的下载地址（自动下载安装）
	 * @throws Exception 
	 */
	public static void push(List<Long> userIds,String title,String text,String type,Map para) throws Exception{
		if(userIds.size()==0){
			logger.warn("推送发送对象为空，返回。title:{},text:{},type:{},para:{}",title,text,type,para);
			return;
		}
		Gson g = new Gson();
		Map<String, String> payLoadObj = new HashMap<String, String>();
		payLoadObj.put("tp", type);
		payLoadObj.putAll(para);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("title", title);
		jsonObject.addProperty("content", text);
		jsonObject.addProperty("payload", g.toJson(payLoadObj));
		ICommService cs=SpringHolder.getBean(CommServiceImpl.class);
		String sql="select other_Id from t_p_user_ids where user_id in (${userId}) and tp=1";
		ParaMap pm=new ParaMap(sql);
		pm.addPara("userId", StringUtils.join(userIds.toArray(), ","));
		List<Object> list =cs.getColumList(pm);
		List<String> l=new ArrayList<String>();
		for(Object r:list){
			l.add(String.valueOf(r));
		}
		if(l.size()==0){
			logger.warn("推送对象未登记：userIds:{},title:{},text:{},type:{},para:{}",userIds,title,text,type,para);
			return;
		}
		listNotificationMsg(l, title, g.toJson(payLoadObj),jsonObject, "http://www.baidu.com");
	}
}
