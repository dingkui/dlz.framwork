package com.dlz.commbiz.sys.rbac.service;

import com.dlz.common.base.service.BaseService;
import com.dlz.commbiz.sys.rbac.model.User;

public interface UserService extends BaseService<User, Long>{	
	
	public boolean hasRole(Long userId,Long roleId)throws Exception;
	
}
