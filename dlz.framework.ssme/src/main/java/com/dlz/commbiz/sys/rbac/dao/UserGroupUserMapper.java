package com.dlz.commbiz.sys.rbac.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dlz.common.base.dao.BaseMapper;
import com.dlz.commbiz.dict.model.ComboBoxModel;
import com.dlz.commbiz.sys.rbac.model.UserGroupUserKey;

public interface UserGroupUserMapper extends BaseMapper<UserGroupUserKey, UserGroupUserKey> {
	/**
	 * 获取用户下拉列表数据
	 * 
	 * @param groupType
	 *          用户组类型
	 * @return
	 */
	public List<ComboBoxModel> getComboBoxUser(@Param("groupType") String groupType);

}