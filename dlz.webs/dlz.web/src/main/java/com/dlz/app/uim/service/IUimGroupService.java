package com.dlz.app.uim.service;

import com.dlz.app.uim.bean.Group;
import com.dlz.app.uim.bean.GroupItem;
import com.dlz.comm.json.JSONMap;
import com.dlz.framework.db.modal.Page;

/**
 * 组操作相关接口
 * @author dingkui
 *
 */
public interface IUimGroupService {
	/**
	 * 添加或更新组信息
	 */
	public Group addOrUpdate(Group dept);
	/**
	 * 删除组
	 * @param id
	 * @return
	 */
	public String del(Long gId);
	/**
	 * 查询组信息
	 */
	public Page<Group> searchGroups(Page<?> page,JSONMap para);
	/**
	 * 根据id取得组
	 */
	public Group getGroupById(Long id);
	

	/**
	 * 添加数据
	 * @param m
	 * @param dId
	 * @return
	 * @throws Exception
	 */
	public String addItem(int gid,int itemid);
	/**
	 * 删除数据
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean delItems(String ids);
	/**
	 * 查询已经绑定的数据
	 */
	public Page<GroupItem> searchItems(Page<?> page,JSONMap para);
}
