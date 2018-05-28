package com.dlz.app.sys.service;


import java.util.List;

import com.dlz.app.sys.bean.Menu;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.modal.Page;

public interface IMenuService {
	/**
	 * 资源管理：菜单信息查询
	 */
	public Page<Menu> searchMenus(Page<?> page,JSONMap para);

	/**
	 * 添加或更新（单条）
	 */
	public Menu addOrUpdate(Menu menu);

	/**
	 * 资源管理：获取更新待对象信息
	 */
	public Menu getMenu( Long funOptId);

	/**
	 * 资源管理：删除之前验证所选记录是否存在子节点
	 */
	public boolean delMenu(Long menuid);
	
	/**
	 * 资源管理：资源授权
	 * 处理逻辑为先删除该角色的所有权限，然后添加
	 */
	public boolean editMenuRigths(Long roleid,List<Integer> menuids);
}

