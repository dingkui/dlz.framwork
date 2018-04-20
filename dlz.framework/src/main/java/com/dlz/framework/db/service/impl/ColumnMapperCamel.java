package com.dlz.framework.db.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dlz.framework.db.service.IColumnMapperService;
public class ColumnMapperCamel implements IColumnMapperService {
	@Override
	public String converClumnStr2Str(String dbKey) {
		if (dbKey == null) {
			return "";
		}
		dbKey = dbKey.toLowerCase();
		Matcher mat = Pattern.compile("_([a-z])").matcher(dbKey);
		while (mat.find()) {
			dbKey = dbKey.replace("_" + mat.group(1), mat.group(1).toUpperCase());
		}
		return dbKey.replaceAll("_", "");
	}

	/**
	 * 字段转换成数据库键名 aaBbCc→AA_BB_CC<br>
	 * 如果参数含有_则不做转换
	 * @param str
	 * @author dk 2015-04-10
	 * @return
	 */
	@Override
	public String converStr2ClumnStr(String beanKey) {
		if(beanKey==null){
			return null;
		}
		if(beanKey.indexOf("_")>-1){
			return beanKey;
		}
		if(beanKey.equals(beanKey.toUpperCase())){
			return beanKey;
		}
		return beanKey.replaceAll(" (?i)desc", " desc")
  			.replaceAll(" (?i)asc", " asc")
//  			.replaceAll("^(?i)select ", "select ")
//  			.replaceAll(" (?i)from ", " from ")
//  			.replaceAll("^(?i)update ", " update ")
//  			.replaceAll(" (?i)set ", " set ")
//  			.replaceAll(" (?i)where ", " where ")
//  			.replaceAll(" (?i)and ", " and ")
//  			.replaceAll(" (?i)exists ", " exists ")
//  			.replaceAll(" (?i)join ", " join ")
  			.replaceAll("([A-Z])", "_$1").toUpperCase();
	}

}
