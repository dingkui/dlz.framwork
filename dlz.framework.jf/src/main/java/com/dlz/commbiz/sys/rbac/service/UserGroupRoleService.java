package com.dlz.commbiz.sys.rbac.service;

import com.dlz.common.base.service.BaseService;
import com.dlz.commbiz.sys.rbac.model.UserGroupRoleKey;

public interface UserGroupRoleService extends BaseService<UserGroupRoleKey, UserGroupRoleKey> {
	
	public abstract boolean insertUesrGroupRole(Long grpId, Integer[] roleIds);
	
}
