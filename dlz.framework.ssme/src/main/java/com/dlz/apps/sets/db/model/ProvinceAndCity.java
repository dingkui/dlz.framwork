package com.dlz.apps.sets.db.model;

import com.dlz.framework.db.modal.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProvinceAndCity extends BaseModel {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public String tableName = "S_SET_PROVINCE_AND_CITY";

    @JsonIgnore
    public String tableColums = "P_ID,P_NAME,P_ENNAME,P_FID,P_ORDER,P_CODE,P_FCODE,P_FLAG";

    /**
     * S_SET_PROVINCE_AND_CITY.P_ID
     * ID
     */
    private Long pId;

    /**
     * S_SET_PROVINCE_AND_CITY.P_NAME
     * 名称
     */
    private String pName;

    /**
     * S_SET_PROVINCE_AND_CITY.P_ENNAME
     * 英文名称
     */
    private String pEnname;

    /**
     * S_SET_PROVINCE_AND_CITY.P_FID
     * 父节点ID
     */
    private Long pFid;

    /**
     * S_SET_PROVINCE_AND_CITY.P_ORDER
     * 规则
     */
    private Long pOrder;

    /**
     * S_SET_PROVINCE_AND_CITY.P_CODE
     * 层级码
     */
    private String pCode;

    /**
     * S_SET_PROVINCE_AND_CITY.P_FCODE
     * 父节点CODE
     */
    private String pFcode;

    /**
     * S_SET_PROVINCE_AND_CITY.P_FLAG
     * 是否有效
     */
    private Long pFlag;

    /**
     * S_SET_PROVINCE_AND_CITY.P_ID
     * ID
     */
    public Long getpId() {
        return pId;
    }

    /**
     * @param pId the value for S_SET_PROVINCE_AND_CITY.P_ID
     * ID
     */
    public void setpId(Long pId) {
        this.pId = pId;
    }

    /**
     * S_SET_PROVINCE_AND_CITY.P_NAME
     * 名称
     */
    public String getpName() {
        return pName;
    }

    /**
     * @param pName the value for S_SET_PROVINCE_AND_CITY.P_NAME
     * 名称
     */
    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    /**
     * S_SET_PROVINCE_AND_CITY.P_ENNAME
     * 英文名称
     */
    public String getpEnname() {
        return pEnname;
    }

    /**
     * @param pEnname the value for S_SET_PROVINCE_AND_CITY.P_ENNAME
     * 英文名称
     */
    public void setpEnname(String pEnname) {
        this.pEnname = pEnname == null ? null : pEnname.trim();
    }

    /**
     * S_SET_PROVINCE_AND_CITY.P_FID
     * 父节点ID
     */
    public Long getpFid() {
        return pFid;
    }

    /**
     * @param pFid the value for S_SET_PROVINCE_AND_CITY.P_FID
     * 父节点ID
     */
    public void setpFid(Long pFid) {
        this.pFid = pFid;
    }

    /**
     * S_SET_PROVINCE_AND_CITY.P_ORDER
     * 规则
     */
    public Long getpOrder() {
        return pOrder;
    }

    /**
     * @param pOrder the value for S_SET_PROVINCE_AND_CITY.P_ORDER
     * 规则
     */
    public void setpOrder(Long pOrder) {
        this.pOrder = pOrder;
    }

    /**
     * S_SET_PROVINCE_AND_CITY.P_CODE
     * 层级码
     */
    public String getpCode() {
        return pCode;
    }

    /**
     * @param pCode the value for S_SET_PROVINCE_AND_CITY.P_CODE
     * 层级码
     */
    public void setpCode(String pCode) {
        this.pCode = pCode == null ? null : pCode.trim();
    }

    /**
     * S_SET_PROVINCE_AND_CITY.P_FCODE
     * 父节点CODE
     */
    public String getpFcode() {
        return pFcode;
    }

    /**
     * @param pFcode the value for S_SET_PROVINCE_AND_CITY.P_FCODE
     * 父节点CODE
     */
    public void setpFcode(String pFcode) {
        this.pFcode = pFcode == null ? null : pFcode.trim();
    }

    /**
     * S_SET_PROVINCE_AND_CITY.P_FLAG
     * 是否有效
     */
    public Long getpFlag() {
        return pFlag;
    }

    /**
     * @param pFlag the value for S_SET_PROVINCE_AND_CITY.P_FLAG
     * 是否有效
     */
    public void setpFlag(Long pFlag) {
        this.pFlag = pFlag;
    }
}