package com.dlz.framework.db.convertor.clumnname;

import com.dlz.framework.db.convertor.ConvertUtil;

public abstract class AColumnNameConvertor {
	AColumnNameConvertor(){
		ConvertUtil.columnMapper=this;
	}
	public abstract String clumn2Str(String dbKey);
	public abstract String str2Clumn(String beanKey);
}
