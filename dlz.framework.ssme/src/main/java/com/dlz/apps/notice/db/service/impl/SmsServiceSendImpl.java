package com.dlz.apps.notice.db.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.dlz.apps.notice.db.service.SmsServiceSend;

@Service("smsService")
public class SmsServiceSendImpl implements SmsServiceSend {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
//	private final String SITE_NAME = "s_sitename";;
//	private final String CUSTOMER = "s_phone";// 客服电话
//	private final String SYSTEM = "SYSTEM";// 客服电话
	public boolean smsSchoolReg() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean smsTrainReg() {
		// TODO Auto-generated method stub
		return false;
	}
	public Map<String, Object> smsMemberLogin(String mobile, String code) {
		// TODO Auto-generated method stub
		return null;
	}
	public Map<String, Object> smsMemberReg(String mobile, String code) {
		// TODO Auto-generated method stub
		return null;
	}
	
}