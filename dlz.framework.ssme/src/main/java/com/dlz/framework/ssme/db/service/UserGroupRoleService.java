package com.dlz.framework.ssme.db.service;

import com.dlz.framework.ssme.base.service.BaseService;
import com.dlz.framework.ssme.db.model.UserGroupRoleKey;

public interface UserGroupRoleService extends BaseService<UserGroupRoleKey, UserGroupRoleKey> {
	
	public abstract boolean insertUesrGroupRole(Long grpId, Integer[] roleIds);
	
}
