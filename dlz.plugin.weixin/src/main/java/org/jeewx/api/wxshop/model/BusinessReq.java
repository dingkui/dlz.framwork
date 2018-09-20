package org.jeewx.api.wxshop.model;

public class BusinessReq {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	// 开始位置
	private String begin;
	// 返回数据条数
	private String limit;
	
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}

	
}
