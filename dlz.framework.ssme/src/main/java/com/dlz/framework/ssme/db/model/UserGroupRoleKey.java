package com.dlz.framework.ssme.db.model;

import java.io.Serializable;

public class UserGroupRoleKey implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long grpId;

    private Integer roleId;

    public Long getGrpId() {
        return grpId;
    }

    public void setGrpId(Long grpId) {
        this.grpId = grpId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}