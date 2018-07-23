package com.dlz.app.uim.service;

import java.util.List;

import com.dlz.app.uim.bean.DeptUser;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.service.IBaseService;

/**
 * 用户部门操作相关接口
 * @author dingkui
 *
 */
public interface IUimDeptService extends IBaseService{
	/**
	 * 添加机构用户
	 * @param m
	 * @param dId
	 * @return
	 * @throws Exception
	 */
	public String addUsers(DeptUser[] items);
	/**
	 * 编辑部门用户
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean editUsers(String items);
	/**
	 * 删除机构用户
	 * @param request
	 * @param dIds
	 * @return
	 * @throws Exception
	 */
	public boolean delUsers(String dIds);
	/**
	 * 查询部门用户
	 * @param dCode
	 * @return
	 */	
	public Page<DeptUser> searchDeptUsers(Page<?> page,JSONMap para);
	/**
	 * 取得用户部门
	 * @param userId
	 * @param dCode
	 * @return
	 */
	List<ResultMap> getUserDepts(long userId, String dCode);
	/**
	 * 保存用户部门
	 * @param deptUser
	 * @return
	 */
	int saveUserDept(DeptUser deptUser);
}
