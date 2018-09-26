package com.dlz.framework.holder;

import java.util.HashMap;
import java.util.Map;

import com.dlz.framework.util.DateUtilSecond;

/**
 * 凭证保管器
 * 
 * @author dk 2017-08-24
 *
 */
public class TokenHolder {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Map<String,TokenInfo> holder=new HashMap<String,TokenInfo>();
	
	public static TokenInfo getToken(String key){
		TokenInfo token=holder.get(key);
		if(token==null){
			token=new TokenHolder.TokenInfo();
			holder.put(key, token);
		}
		return token;
	}
	
	public static class TokenInfo{
		//接口访问凭证
		private String tokenStr;
		
		//凭证有效期，单位：秒
		private int expiresIn=0;
		
		//凭证过期时间
		private long expiresTime=0;
		
		
		public String getTokenStr() {
			return tokenStr;
		}
		
		public int getExpiresIn() {
			return expiresIn;
		}
		public void setExpiresIn(int expiresIn,String tokenStr) {
			this.expiresIn = expiresIn;
			if(expiresIn==0){
				expiresTime=0;
			}else{
				expiresTime=DateUtilSecond.getDateline()+expiresIn;
			}
			this.tokenStr=tokenStr;
		}
		/**
		 * 是否在有效期内
		 * @return
		 */
		public boolean isUsefull() {
			return DateUtilSecond.getDateline() < expiresTime;
		}
	}
	
}
