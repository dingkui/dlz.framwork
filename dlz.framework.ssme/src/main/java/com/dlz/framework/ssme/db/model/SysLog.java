package com.dlz.framework.ssme.db.model;

import java.io.Serializable;
import java.util.Date;

public class SysLog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long logId;

    private Integer userId;

    private Date operTime;

    private String deviceCode;

    private Integer funOptId;

    private String url;

    private String param;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public Integer getFunOptId() {
        return funOptId;
    }

    public void setFunOptId(Integer funOptId) {
        this.funOptId = funOptId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }
}