package com.dlz.app.uim.apiLogic;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.dlz.app.sys.service.IMenuService;
import com.dlz.app.uim.annotation.AnnoAuth;
import com.dlz.app.uim.bean.AuthUser;
import com.dlz.app.uim.bean.AuthUserWithInfo;
import com.dlz.app.uim.enums.PwdTypeEnum;
import com.dlz.app.uim.holder.UserHolder;
import com.dlz.app.uim.service.IUimDeptService;
import com.dlz.app.uim.service.IUimInfoService;
import com.dlz.app.uim.service.IUimMemberService;
import com.dlz.app.uim.service.IUimRoleService;
import com.dlz.comm.json.JSONMap;
import com.dlz.web.bean.JSONResult;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.comm.util.StringUtils;
import com.dlz.comm.util.config.ConfUtil;
import com.dlz.comm.util.encry.Md5Util;
import com.dlz.web.holder.ThreadHolder;
import com.dlz.web.logic.AuthedCommLogic;
/**
 * 用户管理
 * @author lxm 
 *
 * 2018年6月7日
 */
@Service
@AnnoAuth("sys_admin")
@Lazy
public class UimMemberApiLogic extends AuthedCommLogic{

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	@Autowired
	private IUimMemberService memberService;
	@Autowired
	private IUimInfoService infoService;
	@Autowired
	private IUimRoleService roleService;
	@Autowired
	private IUimDeptService deptService;
	@Autowired
	private IMenuService menuService;
	/**
	 * 用户登录
	 * @param data
	 * @return
	 */
	@AnnoAuth("N")
	public JSONResult login(JSONMap data){
		JSONResult r = JSONResult.createResult();
		String userName = data.getStr("userName");
		String password = data.getStr("password");
		if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)){
			return r.addErr("请输入用户名和密码");
		}
		if(ConfUtil.getBoolean("conf.sitefront.needVcode",true)){
			if(!data.getStr("vcode","1").equals(ThreadHolder.getSessionAttr("valid_code"))){
				return r.addErr("验证码有误");
			}
		}
		ResultMap member = memberService.searchMap(new JSONMap("login_id",userName,"user_status",1));
		if(member!=null){
			if(Md5Util.md5(member.getStr("userId")+password).equals(member.getStr("pwd"))){
				AuthUserWithInfo authUser =new AuthUserWithInfo();
				authUser.setId(member.getLong("userId"));
				authUser.setLoginId(member.getStr("loginId"));
//				authUser.setMobile(member.getStr("mobile"));
				authUser.setUserName(member.getStr("userName"));
				UserHolder.setAuthInfo(authUser);
				return r;
//				r.addData(authUser);
			}else{
				r.addErr("密码错误");
			}
		}else{
			r.addErr("用户不存在");
		}
		ThreadHolder.sessionInvalidate();
		return r;
	}
	
	public JSONResult btnPermis(JSONMap data) {
		JSONResult r = JSONResult.createResult();
		AuthUser authInfo = UserHolder.getAuthInfo();
		Set<Long> roles = authInfo.getRoles();
		String rolesIds = StringUtils.join(roles.toArray(), ",");
		// 根据角色ID，查询按钮(//1#菜单，2#按钮)
		List<String> btnPremis = menuService.getPremisByRoleIds("2", rolesIds);
		r.addData(btnPremis);
		return r;
	}
	
	/**
	 * 获取用户分页列表
	 * @param data
	 * @return
	 */
	public JSONResult getAllMember(JSONMap data){
		JSONResult r = JSONResult.createResult();
		int pageIndex = data.getInt("pageIndex");
		int pageSize = data.getInt("pageSize");
		data.remove("pageIndex");
		data.remove("pageSize");
		if(!StringUtils.isEmpty(data.getStr("dId"))){
			data.put("dId", data.getInt("dId"));
		}
		return r.addData(memberService.mapPageByPara(new Page<>(pageIndex, pageSize),new JSONMap(data)));
	}
	/**
	 * 获取用户列表
	 * @param data
	 * @return
	 */
	@AnnoAuth("N")
	public JSONResult getMemberList(JSONMap data){
		JSONResult r = JSONResult.createResult();
		return r.addData(memberService.searchMapList(data));
	}
	/**
	 * 获取用户角色列表
	 * @param data
	 * @return
	 */
	public JSONResult getMemberRoles(JSONMap data){
		JSONResult r = JSONResult.createResult();
		List<Long> roles=roleService.getUserRoleIds(data.getLong("id"));
		return r.addData(roles);
	}
	
	/**
	 * 添加/修改用户
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JSONResult save(JSONMap data){
		JSONResult r = JSONResult.createResult();
		Long key=data.getLong("userId");
		List<Integer> roleList = data.getListObj("roles",Integer.class);//用户角色
		if(key==null){
			List<ResultMap> memberList = memberService.searchMapList(new JSONMap("login_id", data.getStr("loginId")));
			if(memberList.size()>0){
				return r.addErr("登录名已存在");
			}
		}
		try {
			data=memberService.addOrUpdate(data);
			key=data.getLong("userId");
			//保存用户基本信息
			infoService.saveExtInfo(key, "Base", data);
			//保存用户角色
			roleService.addUserRoles(key, StringUtils.join(roleList, ","), true);
			r.addMsg("保存成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			r.addErr("保存失败");
		}
		return r;
	}
	/**
	 * 修改用户基本信息
	 * @param data
	 * @return
	 */
	public JSONResult saveBase(JSONMap data){
		JSONResult r = JSONResult.createResult();
		try {
			infoService.saveExtInfo(data.getLong("id"), "Base", data);
			r.addMsg("保存成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			r.addErr("保存失败");
		}
		return r;
	}
	/**
	 * 启用/禁用用户
	 * @param data
	 * @return
	 */
	public JSONResult enable(JSONMap data){
		JSONResult r = JSONResult.createResult();
		try {
			memberService.changeStatus(data);
			r.addMsg("操作成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			r.addErr("操作失败");
		}
		return r;
	}
	/**
	 * 更新密码
	 * @param data
	 * @return
	 */
	@AnnoAuth("admin")
	public JSONResult editPasswd(JSONMap data){
		JSONResult r = JSONResult.createResult();
		try {
			if(!data.getStr("newPass","@@@").equals(data.getStr("rePass","@"))){
				return r.addErr("两次输入密码不一致");
			}
			if(infoService.checkPwd(data.getLong("id"), PwdTypeEnum.valueOf(data.getStr("pwdType","login")), data.getStr("oldPass"))){
				infoService.savePwd(data.getLong("id"), PwdTypeEnum.valueOf(data.getStr("pwdType","login")), data.getStr("newPass","@@@"));
			}else{
				return r.addErr("原密码不正确");
			}
			r.addMsg("密码修改成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			r.addErr("密码修改失败");
		}
		return r;
	}
	/**
	 * 更新自己的密码
	 * @param data
	 * @return
	 */
	@AnnoAuth("*")
	public JSONResult editMypwd(JSONMap data){
		JSONResult r = JSONResult.createResult();
		try {
			if(!data.getStr("newPass","@@@").equals(data.getStr("rePass","@"))){
				return r.addErr("两次输入密码不一致");
			}
			if(infoService.checkPwd(getMember().getId(), PwdTypeEnum.valueOf(data.getStr("pwdType","login")), data.getStr("oldPass"))){
				infoService.savePwd(getMember().getId(), PwdTypeEnum.valueOf(data.getStr("pwdType","login")), data.getStr("newPass","@@@"));
			}else{
				return r.addErr("原密码不正确");
			}
			r.addMsg("密码修改成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			r.addErr("密码修改失败");
		}
		return r;
	}
	/**
	 * 删除/批量删除用户
	 * @param data
	 * @return
	 */
	public JSONResult del(JSONMap data){
		JSONResult r = JSONResult.createResult();
		String id = data.getStr("id");
		String ids = data.getStr("ids");
		if(!StringUtils.isEmpty(id)){
			memberService.delByKey(id);
			roleService.deleteUserRoles(Long.valueOf(id));
			deptService.deleteUserDepts(Long.valueOf(id));
			r.addMsg("删除成功");
		}else if(!StringUtils.isEmpty(ids)){
			memberService.delByKeys(ids);
			String[] delId=ids.split(",");
			for(int i=1;i<delId.length;i++){
				roleService.deleteUserRoles(Long.valueOf(delId[i]));
				deptService.deleteUserDepts(Long.valueOf(delId[i]));
			}
			r.addMsg("批量删除成功");
		}else{
			r.addErr("删除失败，ID不能为空");
		}
		return r;
	}
	
}
