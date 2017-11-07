package com.dlz.commbiz.sys.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.dict.model.ComboBoxModel;
import com.dlz.commbiz.sys.rbac.dao.UserGroupMapper;
import com.dlz.commbiz.sys.rbac.dao.UserGroupUserMapper;
import com.dlz.commbiz.sys.rbac.model.UserGroup;
import com.dlz.commbiz.sys.rbac.model.UserGroupUserCriteria;
import com.dlz.commbiz.sys.rbac.model.UserGroupUserKey;
import com.dlz.commbiz.sys.rbac.service.UserGroupService;

@Service
@Transactional(rollbackFor=Exception.class)
public class UserGroupServiceImpl extends BaseServiceImpl<UserGroup, Long> implements UserGroupService {
	@Autowired
	public void setMapper(UserGroupMapper mapper) {
		super.mapper = mapper;
	}
	@Autowired
	UserGroupUserMapper userGroupUserMapper;
	
	public UserGroup getUserGroupByUser(Long userId) {
		UserGroup group = new UserGroup();
		UserGroupUserCriteria groupUserCriteria = new UserGroupUserCriteria();
		groupUserCriteria.createCriteria().andUserIdEqualTo(userId);
		
		List<UserGroupUserKey> userGroupUserList = userGroupUserMapper.selectByExample(groupUserCriteria);
		if(userGroupUserList !=null &&userGroupUserList.size() >0){
			UserGroupUserKey  ugu = userGroupUserList.get(0);
			group = mapper.selectByPrimaryKey(ugu.getGrpId());
		}
		return group;
	}
	
	public List<ComboBoxModel> getComboBoxUser(String groupType) {
		return userGroupUserMapper.getComboBoxUser(groupType);
	}
}
