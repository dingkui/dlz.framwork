package com.dlz.framework.ssme.db.service;

import com.dlz.framework.ssme.base.service.BaseService;
import com.dlz.framework.ssme.db.model.UserGroupUserKey;

public interface UserGroupUserService extends BaseService<UserGroupUserKey, UserGroupUserKey> {
	
	public abstract boolean insertUserGroupUserByUserGroup(Long userId, Long[] boundInfos);
	
	public abstract boolean insertUserGroupUserByUser(Long grpId, Long[] userIds);
}
