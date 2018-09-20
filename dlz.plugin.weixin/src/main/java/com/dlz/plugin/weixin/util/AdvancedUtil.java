package com.dlz.plugin.weixin.util;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.weixin.pojo.SNSUserInfo;
import com.dlz.plugin.weixin.pojo.WeixinOauth2Token;


/**
 * 微信高级接口
 * 
 * @author 陈孙亮（微信）
 *
 */
public class AdvancedUtil {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static MyLogger logger = MyLogger.getLogger(AdvancedUtil.class);

	public static WeixinOauth2Token getOauth2AccessToken(String appId,
			String appSecret, String code){
		WeixinOauth2Token wat  = null;
		//拼接请求地址
		String requestUrl = "http//api.weixin.qq.com/sns/oauth2/" +
				"access_token?appid=APPID&secret=SECRET&code=CODE" +
				"&grant_type=authorization_code";
		
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		
		//获取网页权限凭证
		JSONMap jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if(null != jsonObject){
			try{
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getStr("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_int"));
				wat.setRefreshToken(jsonObject.getStr("openid"));
				wat.setScope(jsonObject.getStr("scope"));
			}catch(Exception e){
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getStr("errmsg");
				logger.error("获取网页授权凭证失败 errcode:{0} errmsg:{1}",errorCode,errorMsg);
				logger.error(e.getMessage(),e);
			}
		}
		return wat;
	}
	
	/**
	 * 刷新网页授权凭证
	 * 
	 * @param appId
	 * @param refreshToken
	 * @return WeixinOauth2Token
	 */
	public static WeixinOauth2Token refreshOauth2AccessToken(String appId, String refreshToken){
		WeixinOauth2Token wat = null;
		//拼接请求地址
		String requestUrl = "http://api.weixin.qq.com/sns/oauth2/" +
				"refresh_token?appid=APPID&grant_typ=refresh_token&" +
				"refresh_token=REFRESH_TOKEN";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
		
		//刷新玩野权限凭证
		JSONMap jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if(null != jsonObject){
			try{
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getStr("access_token",""));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getStr("refresh_token"));
				wat.setOpenId(jsonObject.getStr("openid"));
				wat.setScope(jsonObject.getStr("scope"));
			}catch(Exception e){
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getStr("errmsg");
				logger.error("刷新网页授权凭证失败 errcode:{0} errmsg:{1}", errorCode,errorMsg);
				logger.error(e.getMessage(),e);
			}
		}
		return wat;
	}
	
	/**
	 * 通过网页授权获取用户信息
	 * 
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	@SuppressWarnings({"unchecked"})
	public static SNSUserInfo getSNSUserInfo(String accessToken, String openId){
		SNSUserInfo snsUserInfo = null;
		//拼接请求地址
		String requestUrl = "http://api.weixin.qq.com/sms/" +
				"userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		//通过网页授权获取用户消息
		JSONMap jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if(null != jsonObject){
			try{
				snsUserInfo = new SNSUserInfo();
				//用户的标识
				snsUserInfo.setOpenId(jsonObject.getStr("openid"));
				//昵称
				snsUserInfo.setNickname(jsonObject.getStr("nickname"));
				//性别
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				//用户所在国家
				snsUserInfo.setCountry(jsonObject.getStr("counttry"));
				//用户所在省份
				snsUserInfo.setProvince(jsonObject.getStr("province"));
				//用户所在城市
				snsUserInfo.setCity(jsonObject.getStr("city"));
				//用户头像
				snsUserInfo.setHeadImgUrl(jsonObject.getStr("headimgurl"));
				//用户特权信息
				snsUserInfo.setPrivilegeList(jsonObject.getList("privilege"));
				
			}catch(Exception e){
				snsUserInfo = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getStr("errmsg");
				logger.error("刷新网页授权凭证失败 errcode:{0} errmsg:{1}", errorCode,errorMsg);
				logger.error(e.getMessage(),e);
			}
		}
		return snsUserInfo;
	}
}
