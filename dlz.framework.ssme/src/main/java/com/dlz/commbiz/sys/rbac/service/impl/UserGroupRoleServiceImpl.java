package com.dlz.commbiz.sys.rbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sys.rbac.dao.UserGroupRoleMapper;
import com.dlz.commbiz.sys.rbac.model.UserGroupRoleCriteria;
import com.dlz.commbiz.sys.rbac.model.UserGroupRoleKey;
import com.dlz.commbiz.sys.rbac.service.UserGroupRoleService;

@Service
@Transactional(rollbackFor=Exception.class)
public class UserGroupRoleServiceImpl extends BaseServiceImpl<UserGroupRoleKey, UserGroupRoleKey> implements
		UserGroupRoleService {

	@Autowired
	protected void setMapper(UserGroupRoleMapper mapper) {
		super.mapper = mapper;
	}
	/*
	 * 绑定角色-用户组角色
	 */
	public boolean insertUesrGroupRole(Long grpId, Integer[] roleIds) {	
		// 1、删除已绑定的角色
		UserGroupRoleCriteria criteria = new UserGroupRoleCriteria();
		criteria.createCriteria().andGrpIdEqualTo(grpId);
		mapper.deleteByExample(criteria);
		
		// 2、添加用户组角色
		if(roleIds!=null){
			for(Integer roleId : roleIds) {
				UserGroupRoleKey key = new UserGroupRoleKey();
				key.setGrpId(grpId);
				key.setRoleId(roleId);
				mapper.insertSelective(key);
			}
		}
		
		return true;
	}
}
