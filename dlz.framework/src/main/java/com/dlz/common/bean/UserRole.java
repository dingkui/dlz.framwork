package com.dlz.common.bean;

/**
 * 用户角色
 * @author dk 2017-06-15
 *
 */
public class UserRole implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5070462418563344534L;
	private Integer member_id         ;	//ID
	private Integer role_id;	//ID
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
}