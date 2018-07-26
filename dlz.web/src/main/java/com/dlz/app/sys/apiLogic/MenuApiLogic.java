package com.dlz.app.sys.apiLogic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlz.app.sys.service.IMenuService;
import com.dlz.app.uim.annotation.AnnoAuth;
import com.dlz.app.uim.bean.AuthUser;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.exception.CodeException;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.StringUtils;
import com.dlz.framework.util.config.ConfUtil;
import com.dlz.web.logic.AuthedCommLogic;
/**
 * 部门管理
 * @author lxm 
 *
 * 2018年6月7日
 */
@Service
@AnnoAuth("admin")
public class MenuApiLogic extends AuthedCommLogic{
	private MyLogger logger = MyLogger.getLogger(getClass());
	@Autowired
	IMenuService menuService;
	
	/**
	 * 获取用户菜单树
	 * @param data
	 * @return
	 */
	@AnnoAuth("*")
	public JSONResult done(JSONMap data){
		JSONResult r = JSONResult.createResult();
		List<ResultMap> resultMapList=menuService.searchMapList(new JSONMap());
		return r.addData(getMenus(resultMapList,"0",true,getMember()));
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
	public JSONResult save(JSONMap data){
		JSONResult r = JSONResult.createResult();
		try {
			menuService.addOrUpdate(data);
			r.addMsg("保存成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			r.addErr("保存失败");
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
