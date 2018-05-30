package com.dlz.app.uim.bean;

public class Group  implements java.io.Serializable  {
    private static final long serialVersionUID = 1L;

    /**
     * T_P_USER_GRP.GRP_ID
     * 用户组ID
     */
    private Long grpId;

    /**
     * T_P_USER_GRP.GRP_NM
     * 用户组名称
     */
    private String grpNm;

    /**
     * T_P_USER_GRP.GRP_DESC
     * 用户组描述
     */
    private String grpDesc;

    /**
     * T_P_USER_GRP.GRP_TYPE
     * 用户组类型 数据权限的时候使用
     */
    private String grpType;
    
    /**
     * 用户组类型 :1角色组 2用户组 3组织机构组
     */
    private int type;

    /**
     * T_P_USER_GRP.GRP_ID
     * 用户组ID
     */
    public Long getGrpId() {
        return grpId;
    }

    /**
     * @param grpId the value for T_P_USER_GRP.GRP_ID
     * 用户组ID
     */
    public void setGrpId(Long grpId) {
        this.grpId = grpId;
    }

    /**
     * T_P_USER_GRP.GRP_NM
     * 用户组名称
     */
    public String getGrpNm() {
        return grpNm;
    }

    /**
     * @param grpNm the value for T_P_USER_GRP.GRP_NM
     * 用户组名称
     */
    public void setGrpNm(String grpNm) {
        this.grpNm = grpNm == null ? null : grpNm.trim();
    }

    /**
     * T_P_USER_GRP.GRP_DESC
     * 用户组描述
     */
    public String getGrpDesc() {
        return grpDesc;
    }

    /**
     * @param grpDesc the value for T_P_USER_GRP.GRP_DESC
     * 用户组描述
     */
    public void setGrpDesc(String grpDesc) {
        this.grpDesc = grpDesc == null ? null : grpDesc.trim();
    }

    /**
     * T_P_USER_GRP.GRP_TYPE
     * 用户组类型 数据权限的时候使用
     */
    public String getGrpType() {
        return grpType;
    }

    /**
     * @param grpType the value for T_P_USER_GRP.GRP_TYPE
     * 用户组类型 数据权限的时候使用
     */
    public void setGrpType(String grpType) {
        this.grpType = grpType == null ? null : grpType.trim();
    }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}