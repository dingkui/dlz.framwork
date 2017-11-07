package com.dlz.plugin.weixin.pojo;

import org.jeewx.api.wxsendmsg.JwSendMessageAPI;
import org.jeewx.api.wxsendmsg.model.WxMedia;
import org.jeewx.api.wxuser.user.model.Wxuser;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.weixin.custom.CustomMessage;
 

public class SendMsgThread  implements Runnable{ 
	private static MyLogger logger = MyLogger.getLogger(SendMsgThread.class);
	
	
	private String accesstoken;
	private Wxuser wxuser = null;
	private WxMedia wxMedia = null;
	private String type = null;

	/**
	 * 获取accesstoken
	 * 
	 * @return accesstoken accesstoken
	 */
	public String getAccesstoken() {
		return accesstoken;
	}

	/**
	 * 设置accesstoken
	 * 
	 * @param accesstoken
	 *          accesstoken
	 */
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}

	/**
	 * 获取wxuser
	 * 
	 * @return wxuser wxuser
	 */
	public Wxuser getWxuser() {
		return wxuser;
	}

	/**
	 * 设置wxuser
	 * 
	 * @param wxuser
	 *          wxuser
	 */
	public void setWxuser(Wxuser wxuser) {
		this.wxuser = wxuser;
	}

	/**
	 * 获取wxMedia
	 * 
	 * @return wxMedia wxMedia
	 */
	public WxMedia getWxMedia() {
		return wxMedia;
	}

	/**
	 * 设置wxMedia
	 * 
	 * @param wxMedia
	 *          wxMedia
	 */
	public void setWxMedia(WxMedia wxMedia) {
		this.wxMedia = wxMedia;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(8*1000);
			CustomMessage.sendCustomMessage(accesstoken, CustomMessage.makeTextCustomMessage(wxuser.getOpenid(),"生成专属推荐码成功, 内容为......"));
			JwSendMessageAPI.messagePrivate(accesstoken,wxuser.getOpenid(), wxMedia);
			logger.debug((Thread.currentThread().getName()));
		} catch (Exception e) {
			logger.error(e.getMessage(), e); 
		}
	}
 

}
