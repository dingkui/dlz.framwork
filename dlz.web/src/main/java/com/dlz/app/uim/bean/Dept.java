package com.dlz.app.uim.bean;

public class Dept implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * T_P_DEPT.D_ID
     * 组织机构ID
     */
    private Long dId;

    /**
     * T_P_DEPT.D_CODE
     * 组织机构编码 层级码一层为2位数字（大于99时使用16进制）
     */
    private String dCode;

    /**
     * T_P_DEPT.D_NAME
     * 组织机构名称
     */
    private String dName;
    
    /**
     * 组织机构名称+did
     */
    private String dIdName;

    /**
     * T_P_DEPT.D_FID
     * 父节点ID
     */
    private Long dFid;

    /**
     * T_P_DEPT.D_SC_FLG
     * 删除flag 1：正常 0：删除
     */
    private String dScFlg;

    /**
     * T_P_DEPT.D_MANAGER_ID
     * 主管ID
     */
    private Long dManagerId;

    /**
     * T_P_DEPT.D_MANAGER_NAME
     * 主管姓名
     */
    private String dManagerName;

    /**
     * T_P_DEPT.D_TYPE
     * 部门类型
     */
    private String dType;
    
    public String getdIdName() {
		return dIdName;
	}

	public void setdIdName(String dIdName) {
		this.dIdName = dIdName;
	}

	/**
     * T_P_DEPT.D_ID
     * 组织机构ID
     */
    public Long getdId() {
        return dId;
    }

    /**
     * @param dId the value for T_P_DEPT.D_ID
     * 组织机构ID
     */
    public void setdId(Long dId) {
        this.dId = dId;
    }

    /**
     * T_P_DEPT.D_CODE
     * 组织机构编码 层级码一层为2位数字（大于99时使用16进制）
     */
    public String getdCode() {
        return dCode;
    }

    /**
     * @param dCode the value for T_P_DEPT.D_CODE
     * 组织机构编码 层级码一层为2位数字（大于99时使用16进制）
     */
    public void setdCode(String dCode) {
        this.dCode = dCode == null ? null : dCode.trim();
    }

    /**
     * T_P_DEPT.D_NAME
     * 组织机构名称
     */
    public String getdName() {
        return dName;
    }

    /**
     * @param dName the value for T_P_DEPT.D_NAME
     * 组织机构名称
     */
    public void setdName(String dName) {
        this.dName = dName == null ? null : dName.trim();
    }

    /**
     * T_P_DEPT.D_FID
     * 父节点ID
     */
    public Long getdFid() {
        return dFid;
    }

    /**
     * @param dFid the value for T_P_DEPT.D_FID
     * 父节点ID
     */
    public void setdFid(Long dFid) {
        this.dFid = dFid;
    }

    /**
     * T_P_DEPT.D_SC_FLG
     * 删除flag 1：正常 0：删除
     */
    public String getdScFlg() {
        return dScFlg;
    }

    /**
     * @param dScFlg the value for T_P_DEPT.D_SC_FLG
     * 删除flag 1：正常 0：删除
     */
    public void setdScFlg(String dScFlg) {
        this.dScFlg = dScFlg == null ? null : dScFlg.trim();
    }

    /**
     * T_P_DEPT.D_MANAGER_ID
     * 主管ID
     */
    public Long getdManagerId() {
        return dManagerId;
    }

    /**
     * @param dManagerId the value for T_P_DEPT.D_MANAGER_ID
     * 主管ID
     */
    public void setdManagerId(Long dManagerId) {
        this.dManagerId = dManagerId;
    }

    /**
     * T_P_DEPT.D_MANAGER_NAME
     * 主管姓名
     */
    public String getdManagerName() {
        return dManagerName;
    }

    /**
     * @param dManagerName the value for T_P_DEPT.D_MANAGER_NAME
     * 主管姓名
     */
    public void setdManagerName(String dManagerName) {
        this.dManagerName = dManagerName == null ? null : dManagerName.trim();
    }

    /**
     * T_P_DEPT.D_TYPE
     * 部门类型
     */
    public String getdType() {
        return dType;
    }

    /**
     * @param dType the value for T_P_DEPT.D_TYPE
     * 部门类型
     */
    public void setdType(String dType) {
        this.dType = dType == null ? null : dType.trim();
    }
}