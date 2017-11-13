package com.dlz.framework.ssme.db.model;

import com.dlz.framework.db.modal.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

public class DeptUser extends BaseModel {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public String tableName = "T_P_DEPT_USER";

    @JsonIgnore
    public String tableColums = "DU_ID,DU_D_ID,DU_U_ID,DU_DUTY_DES,DU_ADD_TIME,DU_ADD_USER_ID,DU_DUTY";

    /**
     * T_P_DEPT_USER.DU_ID
     * 机构用户ID
     */
    private Long duId;

    /**
     * T_P_DEPT_USER.DU_D_ID
     * 组织机构ID
     */
    private Long duDId;

    /**
     * T_P_DEPT_USER.DU_U_ID
     * 用户ID
     */
    private Long duUId;

    /**
     * T_P_DEPT_USER.DU_DUTY_DES
     * 职位描述 可能多个职位，用逗号分隔
     */
    private String duDutyDes;

    /**
     * T_P_DEPT_USER.DU_ADD_TIME
     * 添加时间
     */
    private Date duAddTime;

    /**
     * T_P_DEPT_USER.DU_ADD_USER_ID
     * 添加人ID
     */
    private Long duAddUserId;

    /**
     * T_P_DEPT_USER.DU_DUTY
     * 职位
     */
    private String duDuty;

    /**
     * T_P_DEPT_USER.DU_ID
     * 机构用户ID
     */
    public Long getDuId() {
        return duId;
    }

    /**
     * @param duId the value for T_P_DEPT_USER.DU_ID
     * 机构用户ID
     */
    public void setDuId(Long duId) {
        this.duId = duId;
    }

    /**
     * T_P_DEPT_USER.DU_D_ID
     * 组织机构ID
     */
    public Long getDuDId() {
        return duDId;
    }

    /**
     * @param duDId the value for T_P_DEPT_USER.DU_D_ID
     * 组织机构ID
     */
    public void setDuDId(Long duDId) {
        this.duDId = duDId;
    }

    /**
     * T_P_DEPT_USER.DU_U_ID
     * 用户ID
     */
    public Long getDuUId() {
        return duUId;
    }

    /**
     * @param duUId the value for T_P_DEPT_USER.DU_U_ID
     * 用户ID
     */
    public void setDuUId(Long duUId) {
        this.duUId = duUId;
    }

    /**
     * T_P_DEPT_USER.DU_DUTY_DES
     * 职位描述 可能多个职位，用逗号分隔
     */
    public String getDuDutyDes() {
        return duDutyDes;
    }

    /**
     * @param duDutyDes the value for T_P_DEPT_USER.DU_DUTY_DES
     * 职位描述 可能多个职位，用逗号分隔
     */
    public void setDuDutyDes(String duDutyDes) {
        this.duDutyDes = duDutyDes == null ? null : duDutyDes.trim();
    }

    /**
     * T_P_DEPT_USER.DU_ADD_TIME
     * 添加时间
     */
    public Date getDuAddTime() {
        return duAddTime;
    }

    /**
     * @param duAddTime the value for T_P_DEPT_USER.DU_ADD_TIME
     * 添加时间
     */
    public void setDuAddTime(Date duAddTime) {
        this.duAddTime = duAddTime;
    }

    /**
     * T_P_DEPT_USER.DU_ADD_USER_ID
     * 添加人ID
     */
    public Long getDuAddUserId() {
        return duAddUserId;
    }

    /**
     * @param duAddUserId the value for T_P_DEPT_USER.DU_ADD_USER_ID
     * 添加人ID
     */
    public void setDuAddUserId(Long duAddUserId) {
        this.duAddUserId = duAddUserId;
    }

    /**
     * T_P_DEPT_USER.DU_DUTY
     * 职位
     */
    public String getDuDuty() {
        return duDuty;
    }

    /**
     * @param duDuty the value for T_P_DEPT_USER.DU_DUTY
     * 职位
     */
    public void setDuDuty(String duDuty) {
        this.duDuty = duDuty == null ? null : duDuty.trim();
    }
}