package com.dlz.framework.ssme.db.service;

import java.util.List;

import com.dlz.framework.ssme.base.service.BaseService;
import com.dlz.framework.ssme.db.model.ComboBoxModel;
import com.dlz.framework.ssme.db.model.Role;

public interface RoleService extends BaseService<Role, Long> {
	
	/*
	 * 根基用户Id获取角色
	 * @param userId
	 * @return
	 * 
	 * 2013-8-25
	 */
	public abstract List<Role> getRolesByUserId(Long userId);

	/*
	 * 根基用户Id获取角色名称
	 * @param userId
	 * @return
	 * 
	 * 2013-8-25
	 */
	public abstract List<String> getRoleNameByUserId(Long userId);
	
	/*
	 * 根据用户组ID获取用户
	 * @param userGrpId
	 * @return
	 * @2014-3-22 
	 */
	public List<ComboBoxModel> getUserByUserGroupId(Long userGrpId);
	
}
