package com.dlz.app.uim.apiLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlz.app.uim.annotation.AnnoAuth;
import com.dlz.app.uim.service.IUimRoleService;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.util.DateUtil;
import com.dlz.framework.util.StringUtils;
import com.dlz.web.logic.AuthedCommLogic;
/**
 * 角色管理
 * @author lxm 
 *
 * 2018年6月7日
 */
@Service
@AnnoAuth("sys_admin")
public class RoleApiLogic extends AuthedCommLogic{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	@Autowired
	IUimRoleService roleService;
	
	/**
	 * 获取角色分页列表
	 * @param data
	 * @return
	 */
	public JSONResult getAllRoles(JSONMap data){
		JSONResult r = JSONResult.createResult();
		int pageIndex = data.getInt("pageIndex");
		int pageSize = data.getInt("pageSize");
		return r.addData(roleService.mapPageByPara(new Page<>(pageIndex, pageSize),new JSONMap()));
	}
	/**
	 * 获取角色列表
	 * @param data
	 * @return
	 */
	public JSONResult getAllRoleList(JSONMap data){
		JSONResult r = JSONResult.createResult();
		return r.addData(roleService.searchMapList(new JSONMap()));
	}
	
	public JSONResult save(JSONMap data){
		JSONResult r = JSONResult.createResult();
	/*	JSONMap role =roleService.getRoleByCode(data.getStr("code"));
		data.put("disabled", "true".equals(data.getStr("disabled"))?1:0);*/
		if(data.getLong("role_id")==null){
//			if(role!=null){
//				return r.addErr("角色编号已存在");
//			}
			data.put("createTime", DateUtil.getCurDateStr("YYYY-MM-dd HH:mm:ss"));
		}else{
			data.put("updateTime", DateUtil.getCurDateStr("YYYY-MM-dd HH:mm:ss"));
		}
		roleService.addOrUpdate(data);
		return r.addMsg("保存成功");
	}
	
	public JSONResult del(JSONMap data){
		JSONResult r = JSONResult.createResult();
		String id = data.getStr("id");
		String ids = data.getStr("ids");
		if(!StringUtils.isEmpty(id)){
			roleService.delByKey(id);
			r.addMsg("删除成功");
		}else if(!StringUtils.isEmpty(ids)){
			roleService.delByKeys(ids);
			r.addMsg("批量删除成功");
		}else{
			r.addErr("删除失败，ID不能为空");
		}
		return r;
	}
	public JSONResult disabled(JSONMap data){
		JSONResult r = JSONResult.createResult();
		Long id = data.getLong("id");
		String disabled = data.getStr("disabled");
		if(!StringUtils.isEmpty(id)&&!StringUtils.isEmpty(disabled)){
			int i = roleService.setDisabled(id, disabled);
			if(i==1){
				r.addMsg("操作成功");
			} else {
				r.addErr("操作失败");
			} 
		}else{
			r.addErr("操作失败，参数不能为空");
		}
		return r;
	}
}
