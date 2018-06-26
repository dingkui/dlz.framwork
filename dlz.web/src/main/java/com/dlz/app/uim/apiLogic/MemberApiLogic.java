package com.dlz.app.uim.apiLogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlz.app.uim.annotation.AnnoAuth;
import com.dlz.app.uim.service.IUimMemberService;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.StringUtils;
import com.dlz.web.logic.AuthedCommLogic;
/**
 * 用户管理
 * @author lxm 
 *
 * 2018年6月7日
 */
@Service
@AnnoAuth("admin")
public class MemberApiLogic extends AuthedCommLogic{
	private MyLogger logger = MyLogger.getLogger(getClass());
	@Autowired
	IUimMemberService memberService;
	
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
