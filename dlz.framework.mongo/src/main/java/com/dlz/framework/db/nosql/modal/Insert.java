package com.dlz.framework.db.nosql.modal;

import java.util.ArrayList;
import java.util.List;

import com.dlz.framework.bean.JSONMap;

public class Insert extends NosqlBasePara{
	private static final long serialVersionUID = 8374167270612933157L;
	private List<JSONMap> datas=new ArrayList<JSONMap>();
	public Insert(String key) {
		super(key);
	}
	public List<JSONMap> getDatas() {
		return datas;
	}
	public Insert addData(JSONMap data) {
		datas.add(data);
		return this;
	}
	public Insert addDatas(List<JSONMap> data) {
		datas.addAll(data);
		return this;
	}
}
