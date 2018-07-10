package com.dlz.app.uim.service;

import java.util.List;

import com.dlz.app.uim.enums.PwdTypeEnum;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;

/**
 * 用户信息相关接口
 * @author dingkui
 *
 */
public interface IUimInfoService {
	/**
	 * 保存密码 
	 * @param id
	 * @param pwdType 密码类型 1 登录密码 2支付密码
	 * @param pwd
	 * @return
	 */
	public boolean savePwd(int id, PwdTypeEnum pwdType, String pwd);

	public boolean checkPwd(int id, PwdTypeEnum pwdType, String pwd);

	public boolean resetPwd(int id, PwdTypeEnum pwdType);

	/**
	 * 保存用户角色信息
	 * @param id
	 * @param roleId
	 * @param info
	 * @return
	 */
	public boolean saveRoleInfo(int id, int roleId, JSONMap info);

	/**
	 * 保存用户扩展信息
	 * @param id
	 * @param extType
	 * @param info
	 * @return
	 */
	public boolean saveExtInfo(int id, String extType, JSONMap info);

	/**
	 * 取得用户扩展信息
	 * @param id
	 * @param extType
	 * @return
	 */
	public JSONMap getExtInfo(int id, String extType);

	/**
	 * 取得用户角色对应的信息
	 * @param id
	 * @param extType
	 * @return
	 */
	public JSONMap getRoleInfo(int id, int roleId);
	
	/**
	 * 取得用户的关系链
	 * @param id
	 * @return
	 */
	public List<Integer> getRelations(int id);
	
	/**
	 * 批量查询用户扩展信息
	 * @param id
	 * @param extType
	 * @return
	 */
	public List<ResultMap> getExtInfos(List<Integer> id, String extType);

	/**
	 * 批量查询用户角色信息
	 * @param id
	 * @param roleId
	 * @return
	 */
	public List<ResultMap> getRoleInfos(List<Integer> id, int roleId);
	
	/**
	 * 查询用户信息
	 * @param id
	 * @param roleId
	 * @return
	 */
	public Page<JSONMap> searchUsers(Page<?> page, JSONMap para);
}