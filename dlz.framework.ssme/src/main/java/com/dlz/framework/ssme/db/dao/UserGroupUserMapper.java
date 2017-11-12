package com.dlz.framework.ssme.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dlz.framework.ssme.base.dao.BaseMapper;
import com.dlz.framework.ssme.db.model.ComboBoxModel;
import com.dlz.framework.ssme.db.model.UserGroupUserKey;

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