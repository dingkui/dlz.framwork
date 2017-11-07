package com.dlz.common.shiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String loginId;
	private String phone;
	private Long pType;
	private String logo;
	private String pRelation;
	private List<Long> roles=new ArrayList<Long>();
	private List<Long> groups=new ArrayList<Long>();
	/**  
	 * 获取roles
	 * @return roles roles  
	 */
	public List<Long> getRoles() {
		return roles;
	}
	/** 
	 * 设置roles 
	 * @param roles roles 
	 */
	public void setRoles(List<Long> roles) {
		this.roles = roles;
	}
	/**  
	 * 获取groups
	 * @return groups groups  
	 */
	public List<Long> getGroups() {
		return groups;
	}
	/** 
	 * 设置groups 
	 * @param groups groups 
	 */
	public void setGroups(List<Long> groups) {
		this.groups = groups;
	}
	/**  
	 * 获取id
	 * @return id id  
	 */
	public Long getId() {
		return id;
	}
	/** 
	 * 设置id 
	 * @param id id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**  
	 * 获取name
	 * @return name name  
	 */
	public String getName() {
		return name;
	}
	/** 
	 * 设置name 
	 * @param name name 
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**  
	 * 获取loginId
	 * @return loginId loginId  
	 */
	public String getLoginId() {
		return loginId;
	}
	/** 
	 * 设置loginId 
	 * @param loginId loginId 
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	/**  
	 * 获取phone
	 * @return phone phone  
	 */
	public String getPhone() {
		return phone;
	}
	/** 
	 * 设置phone 
	 * @param phone phone 
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**  
	 * 获取pType
	 * @return pType pType  
	 */
	public Long getpType() {
		return pType;
	}
	/** 
	 * 设置pType 
	 * @param pType pType 
	 */
	public void setpType(Long pType) {
		this.pType = pType;
	}
	/**  
	 * 获取logo
	 * @return logo logo  
	 */
	public String getLogo() {
		return logo;
	}
	/** 
	 * 设置logo 
	 * @param logo logo 
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	/**  
	 * 获取pRelation
	 * @return pRelation pRelation  
	 */
	public String getpRelation() {
		return pRelation;
	}
	/** 
	 * 设置pRelation 
	 * @param pRelation pRelation 
	 */
	public void setpRelation(String pRelation) {
		this.pRelation = pRelation;
	}
}