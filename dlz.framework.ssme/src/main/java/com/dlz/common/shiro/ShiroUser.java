package com.dlz.common.shiro;

import java.io.Serializable;
import java.util.Map;

import com.dlz.commbiz.sys.rbac.model.User;
import com.dlz.commbiz.sys.rbac.model.UserGroup;
import com.google.common.base.Objects;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
@SuppressWarnings("rawtypes")
public class ShiroUser extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	private Map menuData;
	private UserGroup userGroup;
	private UserInfo userInfo;

	/**  
	 * 获取userInfo
	 * @return userInfo userInfo  
	 */
	public UserInfo getUserInfo() {
		return userInfo;
	}

	/** 
	 * 设置userInfo 
	 * @param userInfo userInfo 
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public ShiroUser(Long userId, String loginId, String userName) {
		super.setUserId(userId);
		super.setLoginId(loginId);
		super.setUserName(userName);
	}

	/**
	 * @return the menuData
	 */
	public Map getMenuData() {
		return menuData;
	}

	/**
	 * @param menuData
	 *            the menuData to set
	 */
	public void setMenuData(Map menuData) {
		this.menuData = menuData;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return super.getLoginId();
	}
	
	

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	/**
	 * 重载hashCode,只计算loginName;
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(super.getLoginId());
	}

	/**
	 * 重载equals,只计算loginName;
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShiroUser other = (ShiroUser) obj;
		if (getLoginId() == null) {
			if (other.getLoginId() != null)
				return false;
		} else if (!getLoginId().equals(other.getLoginId()))
			return false;
		return true;
	}
}