package com.dlz.framework.ssme.db.service;

import java.util.Set;

import com.dlz.framework.ssme.base.service.BaseService;
import com.dlz.framework.ssme.db.model.UserRoleKey;

public interface UserRoleService extends BaseService<UserRoleKey, UserRoleKey> {
	
	public abstract boolean insertUesrRole(Long userId, Set<Long> boundInfos);
	
}
