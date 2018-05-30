package com.dlz.common.bean;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 角色
 * 
 * @author dk 2017-06-15
 *
 */
public class Dict implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5070462418563344534L;
	private String id; // ID
	private String code;// 字典code
	private String name;// 字典名
	private String memo;// 字典名
	private String type;// 字典名
	private Integer enable;// 是否有效 1删除 0正常
	private String sqltext;//字典sql
	private Map<String,DictItem> itemMap=new LinkedHashMap<String,DictItem>();// 字典名

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, DictItem> getItemMap() {
		return itemMap;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getSqltext() {
		return sqltext;
	}

	public void setSqltext(String sqltext) {
		this.sqltext = sqltext;
	}

	public void setItemMap(Map<String, DictItem> itemMap) {
		this.itemMap = itemMap;
	}
	
}