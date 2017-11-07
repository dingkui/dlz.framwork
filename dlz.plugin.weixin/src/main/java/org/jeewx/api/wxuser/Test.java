package org.jeewx.api.wxuser;

import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.wxuser.user.JwUserAPI;

public class Test {

	public static void main(String[] args) {
		try {
			String accesstoken = "?";
			String user_openid = "o8QKAuAyDxxfyuBZ9ugSMR4SR5XQ";
			JwUserAPI.getWxuser(accesstoken, user_openid);
		} catch (WexinReqException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
