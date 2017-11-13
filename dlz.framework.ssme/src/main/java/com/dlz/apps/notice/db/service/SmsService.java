package com.dlz.apps.notice.db.service;

import java.util.Map;

import com.dlz.apps.notice.db.model.Sms;
import com.dlz.framework.ssme.base.service.BaseService;

public interface SmsService extends BaseService<Sms, Long> {
	public Boolean sendMsg(String templateCode, String phone, Map<String, String> params);
}