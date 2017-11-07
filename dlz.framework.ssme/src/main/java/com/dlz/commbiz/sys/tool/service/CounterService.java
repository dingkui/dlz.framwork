package com.dlz.commbiz.sys.tool.service;

import com.dlz.common.base.service.BaseService;
import com.dlz.commbiz.sys.tool.model.Counter;

public interface CounterService extends BaseService<Counter, Long> {
	public String getNum(String str,int len) throws Exception;
}