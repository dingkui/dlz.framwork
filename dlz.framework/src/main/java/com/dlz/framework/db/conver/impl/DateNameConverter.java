package com.dlz.framework.db.conver.impl;



import java.util.Date;

import com.dlz.framework.db.conver.ANameConverter;
import com.dlz.framework.db.enums.DateFormatEnum;
import com.dlz.framework.util.DateUtil;

public class DateNameConverter extends ANameConverter<Date,String,DateFormatEnum> {
	public DateNameConverter(String name,DateFormatEnum para) {
		super(name, para);
	}
	@Override
	public String conver2Str(Date o) {
		return DateUtil.getDateStr(o,getPara().getFormatStr());
	}
	@Override
	public Date conver2Db(String o) {
		return DateUtil.parseData(o,getPara().getFormatStr());
	}
}
