package com.dlz.framework.ssme.shiro;

import java.util.ArrayList;
import java.util.List;

import com.dlz.common.bean.AuthUser;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
public class UserInfo extends AuthUser {
	private static final long serialVersionUID = 1L;

	private Long pType;

	private String logo;

	private String pRelation;

	private String phone;

	private List<Long> groups = new ArrayList<Long>();

	/**
	 * 获取groups
	 * 
	 * @return groups groups
	 */
	public List<Long> getGroups() {
		return groups;
	}

	/**
	 * 设置groups
	 * 
	 * @param groups
	 *          groups
	 */
	public void setGroups(List<Long> groups) {
		this.groups = groups;
	}

	/**
	 * 获取pType
	 * 
	 * @return pType pType
	 */
	public Long getpType() {
		return pType;
	}

	/**
	 * 设置pType
	 * 
	 * @param pType
	 *          pType
	 */
	public void setpType(Long pType) {
		this.pType = pType;
	}

	/**
	 * 获取logo
	 * 
	 * @return logo logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * 设置logo
	 * 
	 * @param logo
	 *          logo
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * 获取pRelation
	 * 
	 * @return pRelation pRelation
	 */
	public String getpRelation() {
		return pRelation;
	}

	/**
	 * 设置pRelation
	 * 
	 * @param pRelation
	 *          pRelation
	 */
	public void setpRelation(String pRelation) {
		this.pRelation = pRelation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}