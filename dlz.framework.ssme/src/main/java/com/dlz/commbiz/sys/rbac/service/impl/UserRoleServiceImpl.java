package com.dlz.commbiz.sys.rbac.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sys.rbac.dao.UserRoleMapper;
import com.dlz.commbiz.sys.rbac.model.UserRoleCriteria;
import com.dlz.commbiz.sys.rbac.model.UserRoleKey;
import com.dlz.commbiz.sys.rbac.service.UserRoleService;

@Service
@Transactional(rollbackFor=Exception.class)
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleKey, UserRoleKey> implements
		UserRoleService {

	@Autowired
	public void setMapper(UserRoleMapper mapper) {
		super.mapper = mapper;
	}
	/*
	 * 绑定角色-用户角色
	 */
	public boolean insertUesrRole(Long userId, Set<Long> boundInfos) {	
		// 1、删除已绑定的角色
		UserRoleCriteria criteria = new UserRoleCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId);		
		mapper.deleteByExample(criteria);
		
		// 2、添加用户角色
		if(boundInfos!=null){
			for (Long roleId : boundInfos) {
				if(roleId==null){
					continue;
				}
				UserRoleKey key = new UserRoleKey();
				key.setUserId(userId);
				key.setRoleId(roleId);
				mapper.insertSelective(key);
			}
		}
		
		return true;
	}
}
