package com.dlz.app.sys.apiLogic;

import com.dlz.app.sys.service.IMenuService;
import com.dlz.app.uim.annotation.AnnoAuth;
import com.dlz.app.uim.bean.AuthUser;
import com.dlz.comm.json.JSONMap;
import com.dlz.comm.util.StringUtils;
import com.dlz.comm.util.config.ConfUtil;
import com.dlz.web.bean.JSONResult;
import com.dlz.framework.db.modal.InsertParaMap;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.modal.UpdateParaMap;
import com.dlz.comm.exception.CodeException;
import com.dlz.web.logic.AuthedCommLogic;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * 部门管理
 * @author lxm 
 *
 * 2018年6月7日
 */
@Service
@AnnoAuth("admin")
@Lazy
public class MenuApiLogic extends AuthedCommLogic{

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	@Autowired
	IMenuService menuService;

	/**
	 * 获取用户菜单树
	 * 
	 * @param data
	 * @return
	 */
	@AnnoAuth("*")
	public JSONResult done(JSONMap data) {
		JSONResult r = JSONResult.createResult();
		List<ResultMap> resultMapList = menuService.searchMapList(new JSONMap("type", "1"));
		return r.addData(getMenus(resultMapList, "0", true, getMember()));
	}
	
	/**
	 * 获取菜单树
	 * @param data
	 * @return
	 */
	public JSONResult getAllList(JSONMap data){
		JSONResult r = JSONResult.createResult();
		List<ResultMap> resultMapList=menuService.searchMapList(new JSONMap());
		return r.addData(getMenus(resultMapList,"0",false,getMember()));
	}
	
	
	private List<ResultMap> getMenusByParent(List<ResultMap> resultMapListAll,String parentId,boolean checkAtuh,AuthUser user){
		return resultMapListAll.size()>0?resultMapListAll.stream().filter(res->{
			if(!res.getStr("parentId").equals(parentId)){
				return false;
			}
			if(checkAtuh){
				final Set<Integer> menuAuths = getMenuAuths(user, res);
				for(Integer auth:menuAuths){
					if(user.hasRole(auth)){
						res.put("access", menuAuths);
						return true;
					}
				}
				return false;
			}else{
				return true;
			}
		}).collect(Collectors.toList()):new ArrayList<>();
	}	
	
	private List<ResultMap> getMenus(List<ResultMap> resultMapListAll,String parentId,boolean checkAtuh,AuthUser user){
		List<ResultMap> resultMapList = getMenusByParent(resultMapListAll, parentId,checkAtuh,user);
		if(resultMapList.size()>0){
			resultMapList.stream().forEach((res)->{
				List<ResultMap> sub=getMenus(resultMapListAll,res.getStr("id"),checkAtuh,user);
				if(sub.size()>0){
					res.add("children", sub);
				}
			});
		}
		return resultMapList;
	}
	
	@SuppressWarnings("unchecked")
	private Set<Integer> getMenuAuths(AuthUser user,ResultMap res){
		final Object access = res.get("access");
		if (access instanceof Set) {
			return (Set<Integer>)access;
		}
		final String str = (String)access;
		Set<Integer> roles=new HashSet<Integer>();
		if(str!=null){
			String[] acces=str.split(",");
			for(String acce:acces){
				if(StringUtils.isNumber(acce)){
					roles.add(Integer.parseInt(acce));
					continue;
				}
				String str2 = ConfUtil.getStr("auth.role."+acce);
				if(str2==null){
					throw new CodeException("无效的权限："+acce);
				}
				String[] role=str2.split(",");
				for(String r:role){
					roles.add(Integer.parseInt(r));
				}
			}
		}
		return roles;
	}
	
	/**
	 * 菜单新增/修改
	 * @param data
	 * @return
	 */
	public JSONResult save(JSONMap data) {
		JSONResult r = JSONResult.createResult();
		try {
			Long key=data.getLong("id");
			if(key==null){
				InsertParaMap pm=new InsertParaMap("t_p_menu");
				data.add("id", commService.getSeq("t_p_menu_id_seq"));
				pm.addValues(data);
				commService.excuteSql(pm);
			}else{
				UpdateParaMap pm=new UpdateParaMap("t_p_menu");
				pm.addSetValues(data);
				pm.addEqCondition("id", key);
				commService.excuteSql(pm);
			}
			//add by zhuwb 2018/10/19 角色菜单关系绑定start
			// 保存角色-菜单；角色id(,分隔)
			String access = data.getStr("access");
			// 获取主键ID
			Long menuId = data.getLong("id");
			logger.debug("菜单Id:{}", menuId);
			commService.excuteSql("delete from t_p_role_menu where menu_id = ?", menuId);
			if (access.indexOf("all") > -1) {
				// 所有角色都有该菜单权限、
				ParaMap pm = new ParaMap("INSERT INTO t_p_role_menu (role_id, menu_id, menu_type) select role_id,#{menuId} as menu_id,#{menuType} as menu_type from t_p_role");
				pm.addPara("menuId", menuId);
				pm.addPara("menuType", data.getStr("type"));
				commService.excuteSql(pm);
			} else {
				// 保存角色（已选择角色）-菜单；
				ParaMap pm = new ParaMap("INSERT INTO t_p_role_menu (role_id, menu_id, menu_type) select role_id,#{menuId} as menu_id,#{menuType} as menu_type from t_p_role where role_id in (${roleIds})");
				pm.addPara("menuId", menuId);
				pm.addPara("menuType", data.getStr("type"));
				pm.addPara("roleIds", access);
				commService.excuteSql(pm);
			}
			r.addMsg("保存成功");
			//add by zhuwb 2018/10/19 角色菜单关系绑定end
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			r.addErr("保存失败");
		}
		return r;
	}
	
	/** 
	* @Title: nameExists 
	* @Description: 检查菜单名是否已经存在、 
	* @throws 
	*/
	public JSONResult nameExists(JSONMap data){
		JSONResult r = JSONResult.createResult();
		try {
			List<ResultMap> list = menuService.searchMapList(data);
			r.addData((list.size()>0));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			r.addErr("查询失败");
		}
		return r;
	}
	
	/**
	 * 菜单删除
	 * @param data
	 * @return
	 */
	public JSONResult del(JSONMap data){
		JSONResult r = JSONResult.createResult();
		String ids = data.getStr("ids");
		if(!StringUtils.isEmpty(ids)){
			menuService.delByKeys(ids);
			r.addMsg("批量删除成功");
		}else{
			r.addErr("删除失败，ID不能为空");
		}
		return r;
	}
}
