package com.dlz.framework.ssme.db.service;

import java.util.List;

import com.dlz.framework.ssme.base.service.BaseService;
import com.dlz.framework.ssme.db.model.ComboBoxModel;
import com.dlz.framework.ssme.db.model.UserGroup;

public interface UserGroupService extends BaseService<UserGroup, Long> {
	
	public UserGroup getUserGroupByUser(Long userId);
	
	/**
	 * 获取用户下拉列表数据
	 * @param groupType 用户组类型
	 * @return
	 */
	public List<ComboBoxModel> getComboBoxUser(String groupType);
}
