package com.dlz.framework.ssme.db.model;

import java.util.Date;

import com.dlz.framework.db.modal.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class LoginDevice extends BaseModel {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public String tableName = "T_P_LOGIN_DEVICE";

    @JsonIgnore
    public String tableColums = "ID,USER_ID,PID,REG_TIME,ORDERNO";

    /**
     * T_P_LOGIN_DEVICE.ID
     * 编号
     */
    private Long id;

    /**
     * T_P_LOGIN_DEVICE.USER_ID
     * 用户ID
     */
    private Long userId;

    /**
     * T_P_LOGIN_DEVICE.PID
     * 设备号
     */
    private String pid;

    /**
     * T_P_LOGIN_DEVICE.REG_TIME
     * 登记事件
     */
    private Date regTime;

    /**
     * T_P_LOGIN_DEVICE.ORDERNO
     * 排序号
     */
    private Long orderno;

    /**
     * T_P_LOGIN_DEVICE.ID
     * 编号
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the value for T_P_LOGIN_DEVICE.ID
     * 编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * T_P_LOGIN_DEVICE.USER_ID
     * 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the value for T_P_LOGIN_DEVICE.USER_ID
     * 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * T_P_LOGIN_DEVICE.PID
     * 设备号
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid the value for T_P_LOGIN_DEVICE.PID
     * 设备号
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * T_P_LOGIN_DEVICE.REG_TIME
     * 登记事件
     */
    public Date getRegTime() {
        return regTime;
    }

    /**
     * @param regTime the value for T_P_LOGIN_DEVICE.REG_TIME
     * 登记事件
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    /**
     * T_P_LOGIN_DEVICE.ORDERNO
     * 排序号
     */
    public Long getOrderno() {
        return orderno;
    }

    /**
     * @param orderno the value for T_P_LOGIN_DEVICE.ORDERNO
     * 排序号
     */
    public void setOrderno(Long orderno) {
        this.orderno = orderno;
    }
}