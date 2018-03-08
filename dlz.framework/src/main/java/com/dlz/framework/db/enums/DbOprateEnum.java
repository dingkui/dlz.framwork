package com.dlz.framework.db.enums;

public enum DbOprateEnum {
	EQ(1,"=","dbn=#{key}"),
	L_LIKE(1,"like","dbn like #{key}||'%'"),
	R_LIKE(1,"like","dbn like '%'||#{key}"),
	O_LIKE(1,"like","dbn like ${likestr}"),
	LIKE(2,"like","dbn like '%'||#{key}||'%'"),
	BETWEEN(2,"between","dbn like #{key1} and #{key2}"),
	IN(1,"in","dbn in (${values})");
	private int paras;
	private String oprate;
	private String condition;
	
	private DbOprateEnum(int paras,String oprate,String condition) {
		this.paras = paras;
		this.oprate = oprate;
		this.condition = condition;
	}

	public int getParas() {
		return paras;
	}

	public String getOprate() {
		return oprate;
	}

	public void setOprate(String oprate) {
		this.oprate = oprate;
	}

	public void setParas(int paras) {
		this.paras = paras;
	}


	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getConditionStr(String dbn,String key,Object o) {
		return condition.replace("dbn", dbn).replaceAll("key", key);
	}
	
}
