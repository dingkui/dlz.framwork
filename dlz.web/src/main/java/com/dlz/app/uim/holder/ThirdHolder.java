package com.dlz.app.uim.holder;

import com.dlz.framework.holder.TokenHolder.TokenInfo;
import com.dlz.web.holder.ThreadHolder;

/**
 * 第三方信息保管器
 * 
 * @author dk 2017-08-24
 *
 */
public class ThirdHolder {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private final static String MEMBER_SESSION_THIRDINFO="member_session_thirdInfo";
	private final static String SESSION_WX_ACCESSTOKEN = "session_wx_accessToken";
	
	public static ThirdInfo getThirdInfo(){
		ThirdInfo thirdInfo= ThreadHolder.getSessionAttr(MEMBER_SESSION_THIRDINFO);
		if(thirdInfo==null){
			thirdInfo=new ThirdInfo();
			ThreadHolder.setSessionAttr(MEMBER_SESSION_THIRDINFO, thirdInfo);
		}
		return thirdInfo;
	}
	public static void removeThirdInfo(){
		ThreadHolder.removeSessionAttr(MEMBER_SESSION_THIRDINFO);
	}
	public static TokenInfo getWxMemberAccesToken(){
		TokenInfo token = ThreadHolder.getSessionAttr(SESSION_WX_ACCESSTOKEN);
		if (token == null) {
			token=new TokenInfo();
			ThreadHolder.setSessionAttr(SESSION_WX_ACCESSTOKEN, token);
		}
		return token;
	}
	
	public static class ThirdInfo{
		private boolean fromQq=false;
		private boolean fromWx=false;
		private String qq_openid;
		private String wx_openid;
		private String app_wx_openid;
		private String wx_xcx_openid;
		private String wx_unionid;
		private String sex;
		private String face;
		private String nickname;
		
		
		public boolean isFromQq() {
			return fromQq;
		}
		public void setFromQq(boolean fromQq) {
			this.fromQq = fromQq;
		}
		public boolean isFromWx() {
			return fromWx;
		}
		public void setFromWx(boolean fromWx) {
			this.fromWx = fromWx;
		}
		public String getQq_openid() {
			return qq_openid;
		}
		public void setQq_openid(String qq_openid) {
			this.qq_openid = qq_openid;
		}
		public String getWx_openid() {
			return wx_openid;
		}
		public void setWx_openid(String wx_openid) {
			this.wx_openid = wx_openid;
		}
		public String getApp_wx_openid() {
			return app_wx_openid;
		}
		public void setApp_wx_openid(String app_wx_openid) {
			this.app_wx_openid = app_wx_openid;
		}
		
		public String getWx_xcx_openid() {
			return wx_xcx_openid;
		}
		public void setWx_xcx_openid(String wx_xcx_openid) {
			this.wx_xcx_openid = wx_xcx_openid;
		}
		public String getWx_unionid() {
			return wx_unionid;
		}
		public void setWx_unionid(String wx_unionid) {
			this.wx_unionid = wx_unionid;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getFace() {
			return face;
		}
		public void setFace(String face) {
			this.face = face;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
	}
}
