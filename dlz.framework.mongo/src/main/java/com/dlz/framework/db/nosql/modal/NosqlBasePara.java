package com.dlz.framework.db.nosql.modal;

import java.io.Serializable;

public class NosqlBasePara implements Serializable{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static final long serialVersionUID = 8374167270612933157L;
	private String key;
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public NosqlBasePara(String key){
		this.key=key;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
