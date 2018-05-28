package com.dlz.app.uim.service;

import java.util.List;

import com.dlz.app.uim.bean.Role;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.modal.Page;

/**
 * 角色操作相关接口
 * @author dingkui
 */
public interface IUimRoleService {
	/**
	 * 查询角色信息
	 */
	public Page<Role> searchRoles(Page<?> page,JSONMap para);
	/**
	 * 角色管理：获取待更新记录信息
	 */
	public Role getRole(Long roleId);

	/**
	 * 角色管理：新建角色
	 */
	public Role addOrUpdate(Role role);
	/**
	 * 角色管理：删除
	 */
	public boolean delete(Long roleId);
	

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
}
