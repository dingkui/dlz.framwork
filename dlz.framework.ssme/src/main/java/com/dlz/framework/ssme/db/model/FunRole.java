package com.dlz.framework.ssme.db.model;

import java.io.Serializable;

public class FunRole implements Serializable{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	/**
	 * 自动生成的序列号
	 */
	private static final long serialVersionUID = -4139597380890299182L;
	
    /**
     * 资源url
     */
    private String url;
    
    /**
     * 角色ID
     */
    private Integer roleId;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}