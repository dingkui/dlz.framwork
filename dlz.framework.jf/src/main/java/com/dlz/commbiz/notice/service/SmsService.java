package com.dlz.commbiz.notice.service;

import java.util.Map;

import com.dlz.commbiz.notice.model.Sms;
import com.dlz.common.base.service.BaseService;

public interface SmsService extends BaseService<Sms, Long> {
	public Boolean sendMsg(String templateCode, String phone, Map<String, String> params);
}