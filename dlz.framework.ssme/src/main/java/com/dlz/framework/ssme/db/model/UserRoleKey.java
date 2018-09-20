package com.dlz.framework.ssme.db.model;

import java.io.Serializable;

public class UserRoleKey implements Serializable {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;

    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}