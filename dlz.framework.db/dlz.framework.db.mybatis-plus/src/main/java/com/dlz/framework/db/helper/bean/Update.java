package com.dlz.framework.db.helper.bean;

import java.util.HashMap;
import java.util.Map;

public class Update {

	Map<String, Object> sets=new HashMap<String, Object>();;

	public Update set(String key, Object value) {
		sets.put(key, value);
		return this;
	}

	public Map<String, Object> getSets() {
		return sets;
	}


}
