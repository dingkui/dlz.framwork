package com.dlz.app.uim.bean;

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
	private Long member_id;
	private Long role_id;
	public Long getMember_id() {
		return member_id;
	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
}