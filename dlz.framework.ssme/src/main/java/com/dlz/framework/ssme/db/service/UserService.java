package com.dlz.framework.ssme.db.service;

import com.dlz.framework.ssme.base.service.BaseService;
import com.dlz.framework.ssme.db.model.User;

public interface UserService extends BaseService<User, Long>{	
	
	public boolean hasRole(Long userId,Long roleId)throws Exception;
	
}
