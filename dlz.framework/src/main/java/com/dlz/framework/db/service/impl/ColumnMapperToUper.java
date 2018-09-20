package com.dlz.framework.db.service.impl;

import com.dlz.framework.db.service.IColumnMapperService;

public class ColumnMapperToUper implements IColumnMapperService {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	@Override
	public String converClumnStr2Str(String dbKey) {
		return dbKey.toUpperCase();
	}

	@Override
	public String converStr2ClumnStr(String beanKey) {
		return beanKey;
	}
}
