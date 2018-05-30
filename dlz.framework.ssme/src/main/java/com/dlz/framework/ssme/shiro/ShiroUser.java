package com.dlz.framework.ssme.shiro;

import java.io.Serializable;

import com.google.common.base.Objects;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
public class ShiroUser extends UserInfos implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * 用户编号
     */
    private Long userId;
    /**
     * 登录名
     */
    private String loginId;
    /**
     * 真实姓名
     */
    private String userName;
    
    public Long getUserId() {
		return userId;
	}
	public String getLoginId() {
		return loginId;
	}
	public String getUserName() {
		return userName;
	}

	public ShiroUser(Long userId, String loginId, String userName,int priceLevel) {
    	this.userId=userId;
    	this.loginId=loginId;
    	this.userName=userName;
    	super.setId(userId.intValue());
    	super.setL_id(loginId);
    	super.setName(userName);
    	super.setPriceLevel(priceLevel);
	}
	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return loginId;
	}
	/**
	 * 重载hashCode,只计算loginName;
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(loginId);
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