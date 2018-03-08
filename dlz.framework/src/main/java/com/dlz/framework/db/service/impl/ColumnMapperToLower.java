package com.dlz.framework.db.service.impl;

import com.dlz.framework.db.service.IColumnMapperService;
public class ColumnMapperToLower implements IColumnMapperService {

	@Override
	public String converClumnStr2Str(String dbKey) {
		return dbKey.toLowerCase();
	}

	@Override
	public String converStr2ClumnStr(String beanKey) {
		return beanKey;
	}
}
