package com.dlz.apps.sys.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlz.apps.ControllerConst;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.ssme.base.controller.BaseController;
import com.dlz.framework.ssme.constants.Constants;
import com.dlz.framework.ssme.db.model.Role;
import com.dlz.framework.ssme.db.model.User;
import com.dlz.framework.ssme.db.model.UserCriteria;
import com.dlz.framework.ssme.db.model.UserGroup;
import com.dlz.framework.ssme.db.model.UserGroupUserCriteria;
import com.dlz.framework.ssme.db.model.UserGroupUserKey;
import com.dlz.framework.ssme.db.model.UserRoleCriteria;
import com.dlz.framework.ssme.db.model.UserRoleKey;
import com.dlz.framework.ssme.db.service.RoleService;
import com.dlz.framework.ssme.db.service.UserGroupService;
import com.dlz.framework.ssme.db.service.UserGroupUserService;
import com.dlz.framework.ssme.db.service.UserRoleService;
import com.dlz.framework.ssme.db.service.UserService;
import com.dlz.framework.ssme.util.criterias.Criterias;
import com.dlz.framework.ssme.util.encry.Digests;
import com.dlz.framework.ssme.util.encry.Encodes;
import com.dlz.framework.ssme.util.encry.Securities;
import com.dlz.framework.util.JacksonUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.sf.json.JSONObject;

/**
 * 用户控制器类 方法上的注释为页面中Button的标题
 */
@Controller
@RequestMapping(ControllerConst.ADMIN+"/rbac/user")
public class UserController extends BaseController{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static MyLogger logger = MyLogger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserGroupService userGroupService;

	@Autowired
	private UserGroupUserService userGroupUserService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private ICommService commService;
	@RequestMapping()
	public String init() {
		return "sys/rbac/user";

	}

	@RequestMapping(value = "toUpdateJsp")
	public String toUpdateJsp() {
		return "login";
	}

	/*
	 * 显示-用户列表
	 */
	@ResponseBody
	@RequestMapping(value = "list")
	public Page list(HttpServletRequest request) {
		try {
			UserCriteria uc = Criterias.buildCriteria(UserCriteria.class, request);
			return userService.pageByExample(uc);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/*
	 * 检查-用户信息是否合法
	 */
	@ResponseBody
	@RequestMapping(value = "check")
	public boolean check(String loginId) {
		try {
			UserCriteria criteria = new UserCriteria();
			criteria.createCriteria().andLoginIdEqualTo(loginId);
			return userService.selectByExample(criteria).isEmpty();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	/*
	 * 添加用户
	 */
	@ResponseBody
	@RequestMapping(value = "save")
	public JSONResult save(String jsonParm, HttpServletRequest request) {
		JSONResult retM = createRsutlJson();
		User user = JacksonUtil.readValue(jsonParm, User.class);
		try {
			if(user.getUserId()==null){
				user.setUserId(commService.getSeq(User.class));
				userService.insert(user);
			}else{
				userService.updateByPrimaryKey(user);
			}
			return retM;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			retM.addErr("系统错误:"+e.getMessage());
		}
		return retM;
	}

	/**
	 * 取得未被绑定的用户
	 * @param request
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getUnBindUsers")
	public Page getUnBindUsers(HttpServletRequest request, Page page) throws Exception {
		ParaMap pm = new ParaMap("key.user.searchUnBindUsers", page);
		pm.addPara("userName", request.getParameter("userName"));
		pm.addPara("dId", request.getParameter("dId"));//部门ID
		pm.addPara("udId", request.getParameter("udId"));//部门ID
		pm.addPara("rId", request.getParameter("rId"));//角色ID
		pm.addPara("urId", request.getParameter("urId"));//角色ID
		pm.addPara("gId", request.getParameter("gId"));//用户组ID
		pm.addPara("ugId", request.getParameter("ugId"));//用户组ID
		pm.getPara().putAll(getParaMap(request));
		return commService.getPage(pm); 
	}

	/*
	 * 修改用户（之前）-查询用户信息
	 */
	@ResponseBody
	@RequestMapping(value = "{userId}")
	public User get(@PathVariable("userId") Long userId) {
		try {
			User u = userService.selectByPrimaryKey(userId);
			return u;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/*
	 * 修改用户（之前）-查询用户信息
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserInfo/{userId}")
	public JSONObject getUserInfo(@PathVariable("userId") Long userId) {
		try {
			User u = userService.selectByPrimaryKey(userId);
	    JSONObject userJsonObj = JacksonUtil.readValue(JacksonUtil.writeValueAsString(u), JSONObject.class);
	    JSONObject mergeJsonObj = new JSONObject();
	    mergeJsonObj.putAll(userJsonObj);
	    return mergeJsonObj;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/*
	 * 修改用户
	 */
	@ResponseBody
	@RequestMapping(value = "update")
	public boolean update(@RequestBody User user) {
		boolean result = true;
		try {
			userService.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = false;
		}
		return result;
	}

	/*
	 * 修改用户密码
	 * 
	 * @param user
	 * 
	 * @return
	 * 
	 * 2013-10-18
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePwd")
	public boolean updatePwd(User user) {
		try {
			entryptPassword(user);
			userService.updateByPrimaryKeySelective(user);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	/*
	 * 绑定用户组（之前）-查出所有的用户组并将该用户组已有的用户默认选中n
	 */
	@ResponseBody
	@RequestMapping(value = "getAllUserGroup/{userId}")
	public List<Map<String, Object>> getAllUserGroup(@PathVariable("userId") Long userId) {
		try {
			// 1、查询出所有用户组
			List<UserGroup> userGroupList = userGroupService.selectByExample(null);

			// 2、找出与用户组关联的信息
			UserGroupUserCriteria criteria = new UserGroupUserCriteria();
			criteria.createCriteria().andUserIdEqualTo(userId);
			List<UserGroupUserKey> keyList = userGroupUserService.selectByExample(criteria);

			// 3、将与用户关联的用户组信息保存到Set中
			Set<Long> userGroupUserSet = Sets.newHashSet();
			for (UserGroupUserKey key : keyList) {
				userGroupUserSet.add(key.getGrpId());
			}

			// 4、初始化treeList
			List<Map<String, Object>> treeList = Lists.newArrayList();

			// 5、将与用户关联的用户组默认选中并添加到treeList中
			for (UserGroup userGroup : userGroupList) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", userGroup.getGrpId());
				map.put("text", userGroup.getGrpNm());
				if (userGroupUserSet.contains(userGroup.getGrpId())) {
					map.put("checked", true);
				}
				treeList.add(map);
			}
			return treeList;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/*
	 * 绑定用户组
	 */
	@ResponseBody
	@RequestMapping(value = "insertUesrGroupUser/{userId}")
	public boolean insertUesrGroupUser(@PathVariable("userId") Long userId, Long[] boundInfos) {
		return userGroupUserService.insertUserGroupUserByUserGroup(userId, boundInfos);
	}

	/*
	 * 绑定角色（之前）-查出所有角色并将该用户组已有角色默认选中
	 */
	@ResponseBody
	@RequestMapping(value = "getAllRole/{uesrId}")
	public List<Map<String, Object>> getAllRole(@PathVariable("uesrId") Long uesrId,HttpServletRequest request) {
		try {
			// 1、查询出所有角色
			List<Role> roleList = roleService.selectByExample(null);

			// 2、找出与用户角色关联的信息
			UserRoleCriteria criteria = new UserRoleCriteria();
			criteria.createCriteria().andUserIdEqualTo(uesrId);
			List<UserRoleKey> keyList = userRoleService.selectByExample(criteria);

			// 3、将与用户关联的角色编号保存到Set集合中
			Set<Long> roleIdSet = Sets.newHashSet();
			for (UserRoleKey key : keyList) {
				roleIdSet.add(key.getRoleId());
			}

			// 4、初始化treeList
			List<Map<String, Object>> treeList = Lists.newArrayList();

			// 5、将与用户角色关联的角色默认选中并添加到treeList中
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
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 充值密码
	 * 
	 * @param userId
	 *          用户Id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/resetPwd/{userId}")
	public boolean resetPwd(@PathVariable("userId") Long userId) {
		try {
			User user = userService.selectByPrimaryKey(userId);
			user.setPwd(Constants.USER_DEFAULT_PASSWORD);
			entryptPassword(user);
			userService.updateByPrimaryKeySelective(user);
			return true;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return false;
		}
	}

	/*
	 * 绑定角色
	 */
	@ResponseBody
	@RequestMapping(value = "insertUesrRole/{userId}")
	public boolean insertUesrGroupRole(@PathVariable("userId") Long userId, Long[] boundInfos) {
		Set<Long> roleSet=new HashSet<Long>();
		for(Long l:boundInfos){
			roleSet.add(l);
		}
		return userRoleService.insertUesrRole(userId, roleSet);
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(Securities.SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPwd().getBytes(), salt, Securities.HASH_INTERATIONS);
		user.setPwd(Encodes.encodeHex(hashPassword));
	}

	/*
	 * 冻结、解冻用户
	 */
	@ResponseBody
	@RequestMapping(value = "updateStatus")
	public boolean updateStatus(@ModelAttribute User user) {
		try {
			return userService.updateByPrimaryKeySelective(user) > 0;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
	/*
	 * 解除登录硬件绑定
	 */
	@ResponseBody
	@RequestMapping(value = "/delLoginDevice/{userId}")
	public boolean delLoginDevice(@PathVariable("userId") Long userId) {
		try {
			String sql="delete from  T_P_LOGIN_DEVICE t where user_id=#{userId}";
			ParaMap pm = new ParaMap(sql);
			pm.addPara("userId", userId);
			commService.excuteSql(pm);
			return true;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return false;
		}
	}

}
