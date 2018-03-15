package com.dlz.framework.db.nosql.modal;

import com.dlz.framework.bean.JSONList;
import com.dlz.framework.bean.JSONMap;

public class Insert extends NosqlBasePara{
	private static final long serialVersionUID = 8374167270612933157L;
	private String dataBson;
	private JSONList datas=new JSONList();
	public Insert(String key, JSONList datas) {
		super(key);
		this.datas=datas;
	}
	public Insert(String key) {
		super(key);
	}
	public JSONList getDatas() {
		return datas;
	}
	public Insert addData(JSONMap data) {
		datas.add(data);
		return this;
	}
	public String getDataBson() {
		return dataBson;
	}
	public void createDataBson() {
		this.dataBson = datas.toString();
	}
}
