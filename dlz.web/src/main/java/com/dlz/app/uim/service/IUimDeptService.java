package com.dlz.app.uim.service;

import com.dlz.app.uim.bean.Dept;
import com.dlz.app.uim.bean.DeptUser;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.service.IBaseService;

/**
 * 用户部门操作相关接口
 * @author dingkui
 *
 */
public interface IUimDeptService extends IBaseService<Dept,String>{
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
}
