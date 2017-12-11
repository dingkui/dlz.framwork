package com.dlz.framework.db.modal;

import java.util.Date;

import com.dlz.framework.bean.JSONMap;

public class ResultMap extends JSONMap{
	private static final long serialVersionUID = -7368198549742264784L;
	public void coverDate2Str(String dateFormat){
		for(String k:super.keySet()){
			if(super.get(k) instanceof Date){
				super.put(k, getDateStr(k,dateFormat));
			}
		}
	}
}
