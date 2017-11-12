package com.dlz.framework.ssme.db.model;

import com.dlz.framework.db.modal.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Role extends BaseModel {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public String tableName = "T_P_ROLE";

    @JsonIgnore
    public String tableColums = "ROLE_ID,ROLE_NM,ROLE_DESC,ROLE_TYPE";

    /**
     * T_P_ROLE.ROLE_ID
     * 角色ID
     */
    private Long roleId;

    /**
     * T_P_ROLE.ROLE_NM
     * 角色名称
     */
    private String roleNm;

    /**
     * T_P_ROLE.ROLE_DESC
     * 角色描述
     */
    private String roleDesc;

    /**
     * T_P_ROLE.ROLE_TYPE
     * 角色类型
     */
    private String roleType;

    /**
     * T_P_ROLE.ROLE_ID
     * 角色ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the value for T_P_ROLE.ROLE_ID
     * 角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * T_P_ROLE.ROLE_NM
     * 角色名称
     */
    public String getRoleNm() {
        return roleNm;
    }

    /**
     * @param roleNm the value for T_P_ROLE.ROLE_NM
     * 角色名称
     */
    public void setRoleNm(String roleNm) {
        this.roleNm = roleNm == null ? null : roleNm.trim();
    }

    /**
     * T_P_ROLE.ROLE_DESC
     * 角色描述
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * @param roleDesc the value for T_P_ROLE.ROLE_DESC
     * 角色描述
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    /**
     * T_P_ROLE.ROLE_TYPE
     * 角色类型
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * @param roleType the value for T_P_ROLE.ROLE_TYPE
     * 角色类型
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
    }
}