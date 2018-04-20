package com.dlz.commbiz.sys.rbac.service;

import java.util.List;

import com.dlz.common.base.service.BaseService;
import com.dlz.commbiz.dict.model.ComboBoxModel;
import com.dlz.commbiz.sys.rbac.model.UserGroup;

public interface UserGroupService extends BaseService<UserGroup, Long> {
	
	public UserGroup getUserGroupByUser(Long userId);
	
	/**
	 * 获取用户下拉列表数据
	 * @param groupType 用户组类型
	 * @return
	 */
	public List<ComboBoxModel> getComboBoxUser(String groupType);
}
