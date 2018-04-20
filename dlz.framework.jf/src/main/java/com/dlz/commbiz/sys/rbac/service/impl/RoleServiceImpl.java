package com.dlz.commbiz.sys.rbac.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.dict.model.ComboBoxModel;
import com.dlz.commbiz.sys.rbac.dao.RbacMapper;
import com.dlz.commbiz.sys.rbac.dao.RoleMapper;
import com.dlz.commbiz.sys.rbac.dao.UserGroupUserMapper;
import com.dlz.commbiz.sys.rbac.dao.UserMapper;
import com.dlz.commbiz.sys.rbac.model.Role;
import com.dlz.commbiz.sys.rbac.model.User;
import com.dlz.commbiz.sys.rbac.model.UserGroupUserCriteria;
import com.dlz.commbiz.sys.rbac.model.UserGroupUserKey;
import com.dlz.commbiz.sys.rbac.service.RoleService;

@Service
@Transactional(rollbackFor=Exception.class)
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

	@Autowired
	public void setMapper(RoleMapper mapper) {
		super.mapper = mapper;
	}
	
	@Autowired
	private RbacMapper rbacMapper;
	
	@Autowired
	private UserGroupUserMapper userGroupUserMapper;
	
	@Autowired
	private UserMapper userMapper;

	public List<Role> getRolesByUserId(Long userId) {
		return rbacMapper.getRolesByUserId(userId);
	}
	
	public List<String> getRoleNameByUserId(Long userId) {
		return rbacMapper.getRoleNameByUserId(userId);
	}
	
	public List<ComboBoxModel> getUserByUserGroupId(Long userGrpId){
		List<ComboBoxModel> listCombox = new ArrayList<ComboBoxModel>();
		UserGroupUserCriteria uguc = new UserGroupUserCriteria();
		uguc.createCriteria().andGrpIdEqualTo(userGrpId);
		List<UserGroupUserKey> list = userGroupUserMapper.selectByExample(uguc);
		for (UserGroupUserKey userGroupUserKey : list) {
			ComboBoxModel model = new ComboBoxModel();
			User user = userMapper.selectByPrimaryKey(userGroupUserKey.getUserId());
			model.setId(user.getUserId().toString());
			model.setText(user.getUserName());
			listCombox.add(model);
		}
		return listCombox;
	}
}
