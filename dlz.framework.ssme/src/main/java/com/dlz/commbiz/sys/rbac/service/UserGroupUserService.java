package com.dlz.commbiz.sys.rbac.service;

import com.dlz.common.base.service.BaseService;
import com.dlz.commbiz.sys.rbac.model.UserGroupUserKey;

public interface UserGroupUserService extends BaseService<UserGroupUserKey, UserGroupUserKey> {
	
	public abstract boolean insertUserGroupUserByUserGroup(Long userId, Long[] boundInfos);
	
	public abstract boolean insertUserGroupUserByUser(Long grpId, Long[] userIds);
}
