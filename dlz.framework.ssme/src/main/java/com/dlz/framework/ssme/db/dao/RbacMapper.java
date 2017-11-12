package com.dlz.framework.ssme.db.dao;

import java.util.List;
import java.util.Map;

import com.dlz.framework.ssme.db.model.Role;
import com.dlz.framework.ssme.db.model.User;
@SuppressWarnings("rawtypes")
public interface RbacMapper {

	/**
	 * 查看用户-根据用户组编号查询所有用户登录名称
	 */
	public abstract List<User> getLoginIdByGrpId(String grpId);
	
	/**
	 * 查询用户及用户所在用户组角色合集
	 * @param loginName
	 * @return
	 */
	public abstract List<Role> getRolesByUserId(Long userId);
	
	/**
	 * 查询用户及用户所在用户组角色合集
	 * @param loginName
	 * @return
	 */
	public abstract List<String> getRoleNameByUserId(Long userId);

	/**
	 * 查询用户及用户所在用户组角色合集包含的URL
	 * @param userId
	 * @return
	 */
	public abstract List<String> getFunOptUrlByUserId(Long userId);

	/**
	 * 查询用户及用户组能够访问的菜单
	 * @param userId
	 * @return
	 */
	public abstract List<Map<String, Object>> getMenueMapByUserId(Map map);
	
	/**
	 * 查询用户及用户组能够访问的菜单
	 * @param userId
	 * @return
	 */
	public abstract List<Map<String, Object>> getSubMenusByUserId(Map map);
	/*public abstract List<Map<String, Object>> getParMenueMapById(int menueId);
	public abstract List<Map<String, Object>> getSubMenueMapById(int menueId);
	public abstract List<Map<String, Object>> getSubMenue(int pMenueId);*/
	
	/**
	 * 查询能够访问的root菜单
	 * @param userId
	 * @return
	 */
	public abstract List<Map<String, Object>> getRootMenueByUserId(Long userId);
	/**
	 * 查询能够访问的root菜单
	 * @param userId
	 * @return
	 */
	public abstract List<Map<String, Object>> getOptsByRoles(Map map);
	
}
