package com.dlz.plugin.weixin.custom;

import java.util.List;

import com.dlz.framework.bean.JSONMap;
import com.dlz.plugin.weixin.message.req.Article;
import com.dlz.plugin.weixin.util.CommonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 封装客户消息
 * 
 * @author Administrator
 *
 */
public class CustomMessage {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	
	/**
	 * 组装文本客服消息
	 * 
	 * @param openId 消息发送对象
	 * @param content 文本消息内容
	 * @return
	 */
	public static String makeTextCustomMessage(String openId , String content){
		//对消息内容中的双引号进行转义
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\"," +
				"\"text\":{\"content\":\"%s\"}}";
		return String.format(jsonMsg, openId,content);
	}
	
	
	/**
	 * 组装图片客服消息
	 * 
	 * @param openId
	 * @param mediaId
	 * @return
	 */
	public static String makeImageCustomMessage(String openId, String mediaId){
		String  jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"image\"," +
				"\"image\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	
	/**
	 * 组装语音客服消息
	 * 
	 * @param openId
	 * @param medisId
	 * @return
	 */
	public static String makeVolceCustomMessge(String openId,String medisId){
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"voice\"," +
				" \"voice\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg , openId , medisId);
	}
	
	/**
	 * 组装视频客服消息
	 * 
	 * @param openId
	 * @param medisId
	 * @return
	 */
	public static String makeVideoCustomMessage(String openId, String medisId ,String thumbMediaId){
		String jsonMsg = "{\"touser\"：\"%s\",\"msgtype\" : \"video\",\"video\": " +
				"{\"media_id\":\"%s\",\"thumb_media_id\":\"%s\" }}";
		return String.format(jsonMsg , openId , medisId , thumbMediaId);
	}
	
	/**
	 * 组装音乐客服消息
	 * 
	 * @param openId
	 * @param medisId
	 * @return
	 */
	public static String makeMusicCustomMessage(String openId, String music){
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"music\":\"music\":%s}";
		jsonMsg = String.format(jsonMsg, openId, JSONObject.fromObject(music).toString());
		//参考名称替换
		jsonMsg = jsonMsg.replace("musicUrl", "musicurl");
		jsonMsg = jsonMsg.replace("HQMusicUrl", "hqmusicurl");
		jsonMsg = jsonMsg.replace("thumbMediaId", "thumb_media_id");
		return jsonMsg;
	}
	
	/**
	 * 组装图文客服信息
	 * 
	 * @param openId
	 * @param articleList
	 * @return
	 */
	public static String makeNewsCustomMessage(String openId, List<Article> articleList){
		String jsonMsg = "{\"touser\":\"%s\", \"msgtype\":\"news\",\"news\":{\"articles\":%s}}";
		jsonMsg = String.format(jsonMsg,openId,JSONArray.fromObject(articleList).toString().replace("\"","\\\""));
		//将jsonMsg 中的picUrl 替换为picurl
		jsonMsg = jsonMsg.replace("picUrl", "picurl");
		return jsonMsg;
	}
	
	/**
	 * 发送客服消息
	 * 
	 * @param accessToken
	 * @param jsonMsg
	 * @return
	 */
	public static boolean sendCustomMessage(String accessToken, String jsonMsg){
		boolean result = false;
		//拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		
		System.out.println("--=------------------------------------");
		System.out.println(jsonMsg);
		System.out.println("--=------------------------------------");
		//发送客服消息
		JSONMap jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);
		
		if(jsonObject != null){
			int errorCode = jsonObject.getInt("errcode");
//			String errorMsg = jsonObject.getStr("errmsg");
			if(0 == errorCode){
				result = true;
			}
		}
		
		
		return result;
	}
}
