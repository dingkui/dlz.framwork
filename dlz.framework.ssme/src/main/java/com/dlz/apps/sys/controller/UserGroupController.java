package com.dlz.apps.sys.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlz.apps.ControllerConst;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.service.ICommService;
import org.slf4j.Logger;
import com.dlz.framework.ssme.db.model.Role;
import com.dlz.framework.ssme.db.model.User;
import com.dlz.framework.ssme.db.model.UserGroup;
import com.dlz.framework.ssme.db.model.UserGroupCriteria;
import com.dlz.framework.ssme.db.model.UserGroupRoleCriteria;
import com.dlz.framework.ssme.db.model.UserGroupRoleKey;
import com.dlz.framework.ssme.db.model.UserGroupUserCriteria;
import com.dlz.framework.ssme.db.model.UserGroupUserKey;
import com.dlz.framework.ssme.db.service.RbacService;
import com.dlz.framework.ssme.db.service.RoleService;
import com.dlz.framework.ssme.db.service.UserGroupRoleService;
import com.dlz.framework.ssme.db.service.UserGroupService;
import com.dlz.framework.ssme.db.service.UserGroupUserService;
import com.dlz.framework.ssme.db.service.UserService;
import com.dlz.framework.ssme.util.criterias.Criterias;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * 用户组控制器类
 * 方法上的注释为页面中Button的标题
 */
@Controller
@RequestMapping(value = ControllerConst.ADMIN+"/rbac/userGroup")
public class UserGroupController {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(UserGroupController.class);

	@Autowired
	private ICommService commService;

	@Autowired
	private UserGroupService userGroupService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserGroupRoleService userGroupRoleService;

	@Autowired
	private RbacService rbacService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserGroupUserService userGroupUserService;

	@RequestMapping()
	public String init() {
		return "sys/rbac/userGroup";
	}
	/*
	 * 显示-用户组列表
	 */
	@ResponseBody
	@RequestMapping(value = "list")
	public Page list(HttpServletRequest request) {
		try {
			UserGroupCriteria uc = Criterias.buildCriteria(UserGroupCriteria.class,request);
			return userGroupService.pageByExample(uc);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	/*
	 * 检查-用户组信息是否合法
	 */
	@ResponseBody
	@RequestMapping(value = "check")
	public boolean check(String grpNm) {
		try {
			UserGroupCriteria criteria = new UserGroupCriteria();
			criteria.createCriteria().andGrpNmEqualTo(grpNm);
			return userGroupService.selectByExample(criteria).isEmpty();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}
	
	/*
	 * 添加用户组
	 */
	@ResponseBody
	@RequestMapping(value = "create")
	public boolean create(@RequestBody UserGroup userGroup) {
		try {
			userGroup.setGrpId(commService.getSeq(UserGroup.class));
			userGroupService.insertSelective(userGroup);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}
	/*
	 * 修改用户组（之前）-查询用户组信息
	 */
	@ResponseBody
	@RequestMapping(value = "{grpId}")
	public UserGroup get(@PathVariable("grpId") Long grpId) {
		try {
			UserGroup u = userGroupService.selectByPrimaryKey(grpId);
			return u;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	/*
	 * 修改用户组
	 */
	@ResponseBody
	@RequestMapping(value = "update")
	public boolean update(@RequestBody UserGroup userGroup) {
		try {
			userGroupService.updateByPrimaryKeySelective(userGroup);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}
	/*
	 * 删除用户组-根据用户组编号删除用户组
	 */
	@ResponseBody
	@RequestMapping(value = "delete/{grpId}")
	public boolean delete(@PathVariable("grpId") Long grpId) {
		try {
			//1、判断该用户组中是否已绑定有用户
			UserGroupUserCriteria keyCriteria = new UserGroupUserCriteria();
			keyCriteria.createCriteria().andGrpIdEqualTo(grpId);
			if(userGroupUserService.selectByExample(keyCriteria).size() > 0){
				return false;
			}		
			//2、删除未绑定用户的用户组
			userGroupService.deleteByPrimaryKey(grpId);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}
	/*
	 * 查看用户（之前）-检查该用户组中是否有关联的用户信息
	 */
	@ResponseBody
	@RequestMapping(value ="checkExits/{grpId}")
	public boolean checkExits(@PathVariable("grpId") Long grpId){
		try {
			UserGroupUserCriteria uguc = new UserGroupUserCriteria();
			uguc.createCriteria().andGrpIdEqualTo(grpId);
			List<UserGroupUserKey> uguList = userGroupUserService.selectByExample(uguc);
			return uguList.isEmpty();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}
	/*
	 * 查看用户-根据用户组编号查询该用户组下的所有用户登录名称
	 */
	@ResponseBody
	@RequestMapping(value = "getUserNameByGrpId/{grpId}")
	public List<Map<String, Object>> getUserNameByGrpId(@PathVariable("grpId") String grpId) {
		// 1、查询出所有的用户登录名称和用户状态
		List<User> userList = rbacService.getLoginIdByGrpId(grpId);
		
		// 2、初始化treeList
		List<Map<String, Object>> treeList = Lists.newArrayList();
		
		// 3、将用户分为有有效和无效的保存到treeList中
		for (User user : userList) {
			Map<String, Object> map = Maps.newHashMap();
			if("1".equals(user.getUserStatus())){ 
				map.put("text", user.getLoginId());
			}else{	//无效
				map.put("text", user.getUserName()+"(无效)");
			}			
			treeList.add(map);
		}
		
		// 4、返回treeList
		return treeList;
	}
	/*
	 * 绑定用户（之前）-查出所有用户并将该用户组已有用户默认选中
	 */
	@ResponseBody
	@RequestMapping(value = "getAllUser/{grpId}")
	public List<Map<String, Object>> getAllUser(@PathVariable("grpId") Long grpId){
		try {
			// 1、查询出所有用户
			List<User> userList = userService.selectByExample(null);
			
			// 2、找出与用户组用户关联的信息
			UserGroupUserCriteria keyCriteria = new UserGroupUserCriteria();
			keyCriteria.createCriteria().andGrpIdEqualTo(grpId);
			List<UserGroupUserKey> keyList = userGroupUserService.selectByExample(keyCriteria);
			
			// 3、将用户组用户关联的用户编号保存到Set集合中
			Set<Long> userIdSet = Sets.newHashSet();
			for (UserGroupUserKey key : keyList) {
				userIdSet.add(key.getUserId());
			}
			
			// 4、初始化treeList
			List<Map<String, Object>> treeList = Lists.newArrayList();
			
			// 5、将与用户组用户关联的用户默认选中并添加到treeList中
			for (User user : userList) {
				if("1".equals(user.getUserStatus())){ //有效用户
					Map<String, Object> map = Maps.newHashMap();
					map.put("id", user.getUserId());
					map.put("loginId", user.getLoginId());
					map.put("userName", user.getUserName());
					if(userIdSet.contains(user.getUserId())){
						map.put("checked", true);
					}
					treeList.add(map);
				}			
			}
			
			return treeList;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	/*
	 * 绑定用户
	 */
	@ResponseBody
	@RequestMapping(value = "insertUesrGroupUser/{grpId}")
	public boolean insertUesrGroupUser(@PathVariable("grpId") Long grpId, Long[] userIds) {		
		return userGroupUserService.insertUserGroupUserByUser(grpId, userIds);
	}
	/*
	 * 绑定角色（之前）-查出所有角色并将该用户组已有角色默认选中
	 */
	@ResponseBody
	@RequestMapping(value = "getAllRole/{grpId}")
	public List<Map<String, Object>> getAllRole(@PathVariable("grpId") Long grpId) {
		try {
			// 1、查询出所有角色
			List<Role> roleList = roleService.selectByExample(null);
			
			// 2、找出与用户组角色关联的信息
			UserGroupRoleCriteria keyCriteria = new UserGroupRoleCriteria();
			keyCriteria.createCriteria().andGrpIdEqualTo(grpId);
			List<UserGroupRoleKey> keyList = userGroupRoleService.selectByExample(keyCriteria);
			
			// 3、将与用户组角色关联的角色编号保存到Set集合中
			Set<Integer> roleIdSet = Sets.newHashSet();
			for (UserGroupRoleKey key : keyList) {
				roleIdSet.add(key.getRoleId());
			}
			
			// 4、初始化treeList
			List<Map<String, Object>> treeList = Lists.newArrayList();
			
			// 5、将与用户组角色关联的角色默认选中并添加到treeList中
			for (Role role : roleList) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", role.getRoleId());
				map.put("text", role.getRoleNm());
				if (roleIdSet.contains(role.getRoleId())) {
					map.put("checked", true);
				}
				treeList.add(map);
			}

			return treeList;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	/*
	 * 绑定角色
	 */
	@ResponseBody
	@RequestMapping(value = "insertUesrGroupRole/{grpId}")
	public boolean insertUesrGroupRole(@PathVariable("grpId") Long grpId, Integer[] roleIds) {		
		// 添加用户组角色
		userGroupRoleService.insertUesrGroupRole(grpId, roleIds);	
		
		return true;
	}
}
