package com.dlz.framework.db.conver.impl;



import java.util.Date;

import com.dlz.framework.db.conver.AClassConverter;
import com.dlz.framework.db.enums.DateFormatEnum;
import com.dlz.framework.util.DateUtil;

public class DateConverter extends AClassConverter<Date,String,DateFormatEnum> {
	private static String COVERCLASS_TIME="java.sql.Timestamp";
	public DateConverter(DateFormatEnum para) {
		super(para);
	}

	@Override
	public String conver2Str(Date o) {
		return DateUtil.getDateStr(o,para.getFormatStr());
	}

	@Override
	public String getCoverClass() {
		return COVERCLASS_TIME;
	}
	
	@Override
	public Date conver2Db(String o) {
		return DateUtil.parseData(o,para.getFormatStr());
	}
}
