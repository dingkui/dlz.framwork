package com.dlz.commbiz.sys.rbac.service;

import java.util.Set;

import com.dlz.common.base.service.BaseService;
import com.dlz.commbiz.sys.rbac.model.UserRoleKey;

public interface UserRoleService extends BaseService<UserRoleKey, UserRoleKey> {
	
	public abstract boolean insertUesrRole(Long userId, Set<Long> boundInfos);
	
}
