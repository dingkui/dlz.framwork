package com.dlz.apps.sys.db.service;

import com.dlz.framework.ssme.base.service.BaseService;
import com.dlz.apps.sys.db.model.Counter;

public interface CounterService extends BaseService<Counter, Long> {
	public String getNum(String str,int len) throws Exception;
}