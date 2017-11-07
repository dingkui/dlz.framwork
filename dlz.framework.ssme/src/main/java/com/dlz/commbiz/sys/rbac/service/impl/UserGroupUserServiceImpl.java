package com.dlz.commbiz.sys.rbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sys.rbac.dao.UserGroupUserMapper;
import com.dlz.commbiz.sys.rbac.model.UserGroupUserCriteria;
import com.dlz.commbiz.sys.rbac.model.UserGroupUserKey;
import com.dlz.commbiz.sys.rbac.service.UserGroupUserService;


@Service
@Transactional(rollbackFor=Exception.class)
public class UserGroupUserServiceImpl extends BaseServiceImpl<UserGroupUserKey, UserGroupUserKey> implements
		UserGroupUserService {

	@Autowired
	public void setMapper(UserGroupUserMapper mapper) {
		super.mapper = mapper;
	}
	/*
	 * 绑定用户组-用户组用户
	 */
	public boolean insertUserGroupUserByUserGroup(Long userId, Long[] boundInfos) {	
		// 1、删除已绑定的用户组信息
		UserGroupUserCriteria criteria = new UserGroupUserCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId);
		mapper.deleteByExample(criteria);
		
		// 2、添加用户组用户
		if(boundInfos!=null){
			for(Long grpId : boundInfos){
				UserGroupUserKey key = new UserGroupUserKey();
				key.setGrpId(grpId);
				key.setUserId(userId);
				mapper.insertSelective(key);
			}
		}
		
		return true;
		
	}
	/*
	 * 绑定用户-用户组用户
	 */
	public boolean insertUserGroupUserByUser(Long grpId, Long[] userIds) {
		// 1、删除已绑定的用户
		UserGroupUserCriteria criteria = new UserGroupUserCriteria();
		criteria.createCriteria().andGrpIdEqualTo(grpId);
		mapper.deleteByExample(criteria);
		
		// 2、添加用户组用户
		if(userIds!=null){
			for (Long userId : userIds) {
				UserGroupUserKey key = new UserGroupUserKey();
				key.setGrpId(grpId);
				key.setUserId(userId);
				mapper.insertSelective(key);
			}
		}
		
		return true;
	}
}
