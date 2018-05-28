package com.dlz.app.uim.enums;

/**
 * 组类型
 * @author dingkui
 *
 */
public enum GroupTypeEnum {
	/** 角色组 */
	role(1),
	/** 用户组 */
	user(2),
	/** 部门组 */
	dept(3)
	;
	private int type;
	
	GroupTypeEnum(int type) {
		this.type = type;
	}

	public int getPwdType() {
		return type;
	}
}
