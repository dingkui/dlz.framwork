package com.dlz.app.uim.service;

import java.util.List;

import com.dlz.app.uim.bean.Role;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.service.IBaseService;

/**
 * 角色操作相关接口
 * @author dingkui
 */
public interface IUimRoleService extends IBaseService<Role,String>{
	/**
	 * 启用/禁用角色
	 * @param roleId
	 * @param disabled
	 * @return 
	 */
	public int setDisabled(Long roleId,String disabled);
	/**
	 * 一个角色下添加多个用户
	 * @param updateAll 是否更新所有，true时删除角色下的所有用户，然后添加
	 */
	public boolean addRoleUsers(Long roleId,String userIds,boolean updateAll);
	/**
	 * 一个用户添加多个角色
	 * @param updateAll 是否更新所有，true时删除用户所有角色，然后添加
	 */
	public boolean addUserRoles(Long roleId,String userIds,boolean updateAll);
	
	/**
	 * 查询用户的所有角色
	 */
	public List<Role> getUserRoles(Long userId);
	
	/**
	 * 查询角色下用户ID
	 */
	public Page<Integer> searchRoleUsers(Page<?> page,Long roleId);
	/**
	 * 根据code获取角色
	 * @param data
	 * @return
	 */
	public JSONMap getRoleByCode(String code);
}
