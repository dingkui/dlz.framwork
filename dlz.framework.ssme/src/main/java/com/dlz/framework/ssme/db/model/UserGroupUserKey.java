package com.dlz.framework.ssme.db.model;

import java.io.Serializable;

public class UserGroupUserKey implements Serializable {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long grpId;

    private Long userId;

    public Long getGrpId() {
        return grpId;
    }

    public void setGrpId(Long grpId) {
        this.grpId = grpId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}