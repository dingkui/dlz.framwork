package com.dlz.commbiz.sys.rbac.service;


import com.dlz.common.base.service.BaseService;
import com.dlz.commbiz.sys.rbac.model.RoleFunOptKey;

public interface RoleFunOptService extends BaseService<RoleFunOptKey, RoleFunOptKey>{
 
 
	/*
	 * 向角色-资源中间表插入角色绑定资源信息
	 * @param roleId
	 * @param detailId
	 * @return
	 * 
	 * 2013-8-25
	 */
	public int insertRoleFunOpt(Integer roleId,Integer[] detailId);
	
}
