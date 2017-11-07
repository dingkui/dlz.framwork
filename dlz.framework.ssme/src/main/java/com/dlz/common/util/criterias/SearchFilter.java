package com.dlz.common.util.criterias;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dlz.common.util.string.Collections3;
import com.dlz.common.util.string.StringUtils;

public class SearchFilter {

	public enum Operator {
		CONDITION("condition"),
		NULL("IsNull"),
		NOTNULL("IsNotNull"),
		EQ("EqualTo"), 
		NOTEQ("NotEqualTo"), 
		LIKE("Like"),
		NOTLIKE("NotLike"),
		GT("GreaterThan"), 
		LT("LessThan"), 
		GTE("GreaterThanOrEqualTo"), 
		LTE("LessThanOrEqualTo"),
		IN("In");
		
		public String value;
		
		private Operator(String value){
			this.value = value;
		}
	}
	
	public String fieldName;
	public Object value;
	public Operator operator;

	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * searchParams中key的格式为OPERATOR_FIELDNAME
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if(searchParams==null){
			return filters;
		}

		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			if(value == null) {
				continue;
			}
			if(value instanceof String) {
				if (StringUtils.isEmpty(value)) {
					continue;
				}
			} else if(value instanceof List && ((List)value).isEmpty()) {
				return null;
			}

			// 拆分operator与filedAttribute
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			String fieldName = names[1];
			Operator operator = null;
			try{
				operator=Operator.valueOf(names[0].toUpperCase());
			}catch(Exception e){
				continue;
			}
			if(operator == Operator.LIKE) {
				value = "%" + value + "%";
			}
			if(operator == Operator.IN) {
				value = Collections3.stringToList((String)value, ",");
			}
			// 创建searchFilter
			SearchFilter filter = new SearchFilter(fieldName, operator, value);
			filters.put(key, filter);
		}
		return filters;
	}
}
