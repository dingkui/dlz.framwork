package com.dlz.framework.ssme.db.model;

import java.io.Serializable;

public class RoleFunOptKey implements Serializable {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static final long serialVersionUID = 1L;

	private Integer roleId;

    private Long funOptId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Long getFunOptId() {
        return funOptId;
    }

    public void setFunOptId(Long funOptId) {
        this.funOptId = funOptId;
    }
}