package com.dlz.apps.sys.db.service;

import com.dlz.apps.sys.db.model.Counter;
import com.dlz.framework.ssme.base.service.BaseService;

public interface CounterService extends BaseService<Counter, Long> {
	public String getNum(String str,int len) throws Exception;
}