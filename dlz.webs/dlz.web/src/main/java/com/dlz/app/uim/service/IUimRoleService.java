package com.dlz.app.uim.service;

import java.util.List;

import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.service.IBaseService;

/**
 * 角色操作相关接口
 * @author dingkui
 */
public interface IUimRoleService extends IBaseService{
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
	 * 获取用户角色列表
	 * @param id
	 * @return
	 */
	public List<Long> getUserRoleIds(Long userId);
	/**
	 * 查询用户的所有角色
	 */
	public List<ResultMap> getUserRoles(Long userId);
	/**
	 * 删除用户所有角色
	 * @param id
	 */
	public void deleteUserRoles(Long id);
	/**
	 * 删除用户某一角色
	 * @param id
	 */
	public void deleteUserRole(Long id,Long roleId);
	/**
	 * 查询角色下用户
	 */
	Page<ResultMap> getRoleUsers(Page<?> page, Long roleId);
}
