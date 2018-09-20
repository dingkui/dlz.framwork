package org.jeewx.api.coupon.location.model;

import org.jeewx.api.core.annotation.ReqType;
import org.jeewx.api.core.req.model.WeixinReqParam;

@ReqType("getLocationInfo")
public class LocationInfo extends WeixinReqParam {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	// 图片地址
	private String filePathName;

	public String getFilePathName() {
		return filePathName;
	}

	public void setFilePathName(String filePathName) {
		this.filePathName = filePathName;
	}


	
}
