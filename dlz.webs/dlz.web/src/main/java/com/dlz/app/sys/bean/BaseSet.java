package com.dlz.app.sys.bean;

import java.util.Date;

public class BaseSet implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * T_B_BASE_SET.BASE_CODE
     * 编码
     */
    private String baseCode;

    /**
     * T_B_BASE_SET.BASE_VALUE
     * 值
     */
    private String baseValue;

    /**
     * T_B_BASE_SET.STATUS
     * 状态
     */
    private String status;

    /**
     * T_B_BASE_SET.CREATE_TIME
     * 创建时间
     */
    private Date createTime;

    /**
     * T_B_BASE_SET.CREATE_ID
     * 创建人ID
     */
    private Long createId;

    /**
     * T_B_BASE_SET.CREATE_NAME
     * 创建人姓名
     */
    private String createName;

    /**
     * T_B_BASE_SET.REMARKS
     * 描述
     */
    private String remarks;

    /**
     * T_B_BASE_SET.UPDATE_TIME
     * 更新时间
     */
    private Date updateTime;

    /**
     * T_B_BASE_SET.UPDATE_ID
     * 更新人ID
     */
    private Long updateId;

    /**
     * T_B_BASE_SET.UPDATE_NAME
     * 更新人姓名
     */
    private String updateName;

    /**
     * T_B_BASE_SET.BASE_CODE
     * 编码
     */
    public String getBaseCode() {
        return baseCode;
    }

    /**
     * @param baseCode the value for T_B_BASE_SET.BASE_CODE
     * 编码
     */
    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode == null ? null : baseCode.trim();
    }

    /**
     * T_B_BASE_SET.BASE_VALUE
     * 值
     */
    public String getBaseValue() {
        return baseValue;
    }

    /**
     * @param baseValue the value for T_B_BASE_SET.BASE_VALUE
     * 值
     */
    public void setBaseValue(String baseValue) {
        this.baseValue = baseValue == null ? null : baseValue.trim();
    }

    /**
     * T_B_BASE_SET.STATUS
     * 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the value for T_B_BASE_SET.STATUS
     * 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * T_B_BASE_SET.CREATE_TIME
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the value for T_B_BASE_SET.CREATE_TIME
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * T_B_BASE_SET.CREATE_ID
     * 创建人ID
     */
    public Long getCreateId() {
        return createId;
    }

    /**
     * @param createId the value for T_B_BASE_SET.CREATE_ID
     * 创建人ID
     */
    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    /**
     * T_B_BASE_SET.CREATE_NAME
     * 创建人姓名
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * @param createName the value for T_B_BASE_SET.CREATE_NAME
     * 创建人姓名
     */
    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    /**
     * T_B_BASE_SET.REMARKS
     * 描述
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the value for T_B_BASE_SET.REMARKS
     * 描述
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * T_B_BASE_SET.UPDATE_TIME
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the value for T_B_BASE_SET.UPDATE_TIME
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * T_B_BASE_SET.UPDATE_ID
     * 更新人ID
     */
    public Long getUpdateId() {
        return updateId;
    }

    /**
     * @param updateId the value for T_B_BASE_SET.UPDATE_ID
     * 更新人ID
     */
    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    /**
     * T_B_BASE_SET.UPDATE_NAME
     * 更新人姓名
     */
    public String getUpdateName() {
        return updateName;
    }

    /**
     * @param updateName the value for T_B_BASE_SET.UPDATE_NAME
     * 更新人姓名
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }
}