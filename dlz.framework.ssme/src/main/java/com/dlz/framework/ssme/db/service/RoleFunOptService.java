package com.dlz.framework.ssme.db.service;


import com.dlz.framework.ssme.base.service.BaseService;
import com.dlz.framework.ssme.db.model.RoleFunOptKey;

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
