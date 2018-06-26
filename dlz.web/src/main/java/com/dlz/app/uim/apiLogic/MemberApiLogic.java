package com.dlz.app.uim.apiLogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlz.app.uim.annotation.AnnoAuth;
import com.dlz.app.uim.bean.AuthUser;
import com.dlz.app.uim.holder.UserHolder;
import com.dlz.app.uim.service.IUimMemberService;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.StringUtils;
import com.dlz.framework.util.encry.Md5Util;
import com.dlz.web.logic.AuthedCommLogic;
/**
 * 用户管理
 * @author lxm 
 *
 * 2018年6月7日
 */
@Service
@AnnoAuth("ROLE_ADMIN")
public class MemberApiLogic extends AuthedCommLogic{
	private MyLogger logger = MyLogger.getLogger(getClass());
	@Autowired
	IUimMemberService memberService;
	
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
		ResultMap member = memberService.searchMap(new JSONMap("{'login_id':'"+userName+"'}"));
		if(member!=null){
			if(Md5Util.md5(member.getStr("userId")+password).equals(member.getStr("pwd"))){
				AuthUser authUser =new AuthUser();
				authUser.setId(member.getInt("userId"));
				authUser.setL_id(member.getStr("loginId"));
				authUser.setMobile(member.getStr("mobile"));
				authUser.setName(member.getStr("userName"));
				List<Integer> roles=memberService.getMemberRoles(member.getInt("userId"));
				authUser.getRoles().addAll(roles);
				UserHolder.setAuthInfo(authUser);
				r.addData(authUser);
			}
		}else{
			r.addErr("用户名或密码错误");
		}
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
		return r.addData(memberService.mapPageByPara(new Page<>(pageIndex, pageSize),new JSONMap(data)));
	}
	/**
	 * 获取用户角色列表
	 * @param data
	 * @return
	 */
	public JSONResult getMemberRoles(JSONMap data){
		JSONResult r = JSONResult.createResult();
		int id = data.getInt("id");
		List<Integer> roles=memberService.getMemberRoles(id);
		return r.addData(roles);
	}
	
	/**
	 * 添加/修改用户
	 * @param data
	 * @return
	 */
	public JSONResult save(JSONMap data){
		JSONResult r = JSONResult.createResult();
		try {
			memberService.addOrUpdate(data);
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
			memberService.deleteUserRole(Long.valueOf(id));
			memberService.deleteUserDept(Long.valueOf(id));
			r.addMsg("删除成功");
		}else if(!StringUtils.isEmpty(ids)){
			memberService.delByKeys(ids);
			String[] delId=ids.split(",");
			for(int i=1;i<delId.length;i++){
				memberService.deleteUserRole(Long.valueOf(delId[i]));
				memberService.deleteUserDept(Long.valueOf(delId[i]));
			}
			r.addMsg("批量删除成功");
		}else{
			r.addErr("删除失败，ID不能为空");
		}
		return r;
	}
	
}