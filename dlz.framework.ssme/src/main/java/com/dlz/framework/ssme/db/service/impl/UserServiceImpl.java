package com.dlz.framework.ssme.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.framework.ssme.db.dao.UserMapper;
import com.dlz.framework.ssme.db.model.User;
import com.dlz.framework.ssme.db.service.UserService;

@Service
@Transactional(rollbackFor=Exception.class)
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

	@Autowired
	ICommService commService;
	
	@Autowired
	public void setMapper(UserMapper mapper) {
		super.mapper = mapper;
	}

	public boolean hasRole(Long userId, Long roleId) throws Exception {
		String sql="select 1 from t_p_user_role where user_id=#{userId} and role_id=#{roleId}";
		ParaMap pm = new ParaMap(sql);
		pm.addPara("userId",userId);
		pm.addPara("roleId",roleId);
		
		return commService.getCnt(pm)>0;
	}
}
