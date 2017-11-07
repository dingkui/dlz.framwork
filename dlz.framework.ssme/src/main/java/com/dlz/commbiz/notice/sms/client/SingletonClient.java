package com.dlz.commbiz.notice.sms.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dlz.common.util.config.ConfigUtil;

public class SingletonClient {
	private static Logger logger = LoggerFactory.getLogger(SingletonClient.class);
	private static Client client=null;
	private SingletonClient(){
	}
	public synchronized static Client getClient(String softwareSerialNo,String key){
		if(client==null){
			try {
				client=new Client(ConfigUtil.getConfig("sms.server.softwareSerialNo"),ConfigUtil.getConfig("sms.server.key"));
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
		return client;
	}
	public synchronized static Client getClient(){
		if(client==null){
			try { 
				client=new Client(ConfigUtil.getConfig("sms.server.softwareSerialNo"),ConfigUtil.getConfig("sms.server.key"));
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
		return client;
	}
	
	public  Client getClient_(){ 
		if(client==null){
			try { 
				client=new Client(ConfigUtil.getConfig("sms.server.softwareSerialNo"),ConfigUtil.getConfig("sms.server.key"));
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
		return client;
	}
	
	
}
