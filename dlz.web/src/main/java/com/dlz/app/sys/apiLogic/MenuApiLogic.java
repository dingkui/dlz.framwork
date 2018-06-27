package com.dlz.app.sys.apiLogic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlz.app.sys.service.IMenuService;
import com.dlz.app.uim.annotation.AnnoAuth;
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
@AnnoAuth("ROLE_ADMIN")
public class MenuApiLogic extends AuthedCommLogic{
	private MyLogger logger = MyLogger.getLogger(getClass());
	@Autowired
	IMenuService menuService;
	
	/**
	 * 获取菜单树（三级）
	 * @param data
	 * @return
	 */
	@AnnoAuth("N")
	public JSONResult getAllList(JSONMap data){
		JSONResult r = JSONResult.createResult();
		return r.addData(getMenus("0"));
	}
	
	
	private List<ResultMap> getMenus(String parentId){
		List<ResultMap> resultMapList = menuService.searchMapList(new JSONMap("parent_id",parentId));
		if(resultMapList.size()>0){
			resultMapList.parallelStream().forEach((res)->{
				setMenuAuths(res);
				List<ResultMap> sub=getMenus(res.getStr("id"));
				if(sub.size()>0){
					res.add("children", sub);
				}
			});
		}
		return resultMapList;
	}
	
	private void setMenuAuths(ResultMap res){
		final String str = res.getStr("access");
		if(str!=null){
			String[] acces=str.split(",");
			Set<Integer> roles=new HashSet<Integer>();
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
			res.put("access", roles);
		}
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
