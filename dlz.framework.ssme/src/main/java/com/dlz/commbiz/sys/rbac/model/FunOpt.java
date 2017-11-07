package com.dlz.commbiz.sys.rbac.model;

import com.dlz.framework.db.modal.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class FunOpt extends BaseModel {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public String tableName = "T_P_FUN_OPT";

    @JsonIgnore
    public String tableColums = "FUN_OPT_ID,FUN_OPT_NM,URL,REMARKS,PARENT_FUN_OPT_ID,F_STATE,F_ORDER,F_CODE";

    /**
     * T_P_FUN_OPT.FUN_OPT_ID
     * 资源ID
     */
    private Long funOptId;

    /**
     * T_P_FUN_OPT.FUN_OPT_NM
     * 资源名称
     */
    private String funOptNm;

    /**
     * T_P_FUN_OPT.URL
     * 资源URL
     */
    private String url;

    /**
     * T_P_FUN_OPT.REMARKS
     * 资源描述
     */
    private String remarks;

    /**
     * T_P_FUN_OPT.PARENT_FUN_OPT_ID
     * 父资源ID
     */
    private Long parentFunOptId;

    /**
     * T_P_FUN_OPT.F_STATE
     * 状态 1有效 0无效
     */
    private Long fState;

    /**
     * T_P_FUN_OPT.F_ORDER
     * 序号
     */
    private Long fOrder;

    /**
     * T_P_FUN_OPT.F_CODE
     * 层级码
     */
    private String fCode;

    /**
     * T_P_FUN_OPT.FUN_OPT_ID
     * 资源ID
     */
    public Long getFunOptId() {
        return funOptId;
    }

    /**
     * @param funOptId the value for T_P_FUN_OPT.FUN_OPT_ID
     * 资源ID
     */
    public void setFunOptId(Long funOptId) {
        this.funOptId = funOptId;
    }

    /**
     * T_P_FUN_OPT.FUN_OPT_NM
     * 资源名称
     */
    public String getFunOptNm() {
        return funOptNm;
    }

    /**
     * @param funOptNm the value for T_P_FUN_OPT.FUN_OPT_NM
     * 资源名称
     */
    public void setFunOptNm(String funOptNm) {
        this.funOptNm = funOptNm == null ? null : funOptNm.trim();
    }

    /**
     * T_P_FUN_OPT.URL
     * 资源URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the value for T_P_FUN_OPT.URL
     * 资源URL
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * T_P_FUN_OPT.REMARKS
     * 资源描述
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the value for T_P_FUN_OPT.REMARKS
     * 资源描述
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * T_P_FUN_OPT.PARENT_FUN_OPT_ID
     * 父资源ID
     */
    public Long getParentFunOptId() {
        return parentFunOptId;
    }

    /**
     * @param parentFunOptId the value for T_P_FUN_OPT.PARENT_FUN_OPT_ID
     * 父资源ID
     */
    public void setParentFunOptId(Long parentFunOptId) {
        this.parentFunOptId = parentFunOptId;
    }

    /**
     * T_P_FUN_OPT.F_STATE
     * 状态 1有效 0无效
     */
    public Long getfState() {
        return fState;
    }

    /**
     * @param fState the value for T_P_FUN_OPT.F_STATE
     * 状态 1有效 0无效
     */
    public void setfState(Long fState) {
        this.fState = fState;
    }

    /**
     * T_P_FUN_OPT.F_ORDER
     * 序号
     */
    public Long getfOrder() {
        return fOrder;
    }

    /**
     * @param fOrder the value for T_P_FUN_OPT.F_ORDER
     * 序号
     */
    public void setfOrder(Long fOrder) {
        this.fOrder = fOrder;
    }

    /**
     * T_P_FUN_OPT.F_CODE
     * 层级码
     */
    public String getfCode() {
        return fCode;
    }

    /**
     * @param fCode the value for T_P_FUN_OPT.F_CODE
     * 层级码
     */
    public void setfCode(String fCode) {
        this.fCode = fCode == null ? null : fCode.trim();
    }
}