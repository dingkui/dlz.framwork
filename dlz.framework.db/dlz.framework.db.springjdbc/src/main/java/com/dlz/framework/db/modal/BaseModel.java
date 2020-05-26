package com.dlz.framework.db.modal;

import com.dlz.comm.util.ValUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * POJO基类
 */
public class BaseModel implements java.io.Serializable {

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
		return ValUtil.getObj(m_i.get(key), t);
	}

	public String getTableName() {
		return tableName;
	}

}
