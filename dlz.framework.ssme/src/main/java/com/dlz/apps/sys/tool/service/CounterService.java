package com.dlz.apps.sys.tool.service;

import com.dlz.framework.ssme.base.service.BaseService;
import com.dlz.apps.sys.tool.model.Counter;

public interface CounterService extends BaseService<Counter, Long> {
	public String getNum(String str,int len) throws Exception;
}