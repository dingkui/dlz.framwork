package com.dlz.app.uim.bean;

/**
 * 角色
 * @author dk 2017-06-15
 *
 */
public class Role implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5070462418563344534L;
	private Integer id;	//ID
	private String role_name         ;//会员名
	private String code              ;//会员名
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}