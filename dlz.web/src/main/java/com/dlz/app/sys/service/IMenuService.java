package com.dlz.app.sys.service;


import java.util.List;

import com.dlz.framework.db.service.IBaseService;

public interface IMenuService extends IBaseService{
	/**
	 * 资源管理：资源授权
	 * 处理逻辑为先删除该角色的所有权限，然后添加
	 */
	public boolean editMenuRigths(Long roleid,List<Integer> menuids);
}

