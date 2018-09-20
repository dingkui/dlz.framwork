package com.dlz.framework.db.modal;

import java.util.HashMap;
import java.util.Map;

import com.dlz.framework.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * POJO基类
 */
public class BaseModel implements java.io.Serializable {
	void doNothing1(){new java.util.ArrayList<>().forEach(a->{});}
	private static final long serialVersionUID = -5972679257248081155L;
	@JsonIgnore
	protected String tableName;
	@JsonIgnore
	protected String tableColums;

	protected Map<String, Object> m_i = new HashMap<String, Object>();

	public Map<String, Object> getM_i() {
		return m_i;
	}

	public void put(String key, Object value) {
		m_i.put(key, value);
	}

	public void putAll(Map<String, Object> m) {
		m_i.putAll(m);
	}

	public Object get(String key) {
		return m_i.get(key);
	}

	public <T> T get(String key, Class<T> t) {
		return StringUtils.coverObj(m_i.get(key), t);
	}

	public String getTableName() {
		return tableName;
	}

}
