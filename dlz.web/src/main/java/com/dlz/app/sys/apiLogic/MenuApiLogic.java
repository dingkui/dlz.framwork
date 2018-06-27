package com.dlz.app.sys.apiLogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlz.app.sys.service.IMenuService;
import com.dlz.app.uim.annotation.AnnoAuth;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.StringUtils;
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
		//一级菜单
		List<ResultMap> resultMapList = menuService.searchMapList(new JSONMap("{parent_id:0}"));
		resultMapList.parallelStream().forEach((res)->{
			//二级菜单
			List<ResultMap> resultMapList1 = menuService.searchMapList(new JSONMap("{parent_id:"+res.getStr("id")+"}"));
			if(resultMapList1.size()>0){
				res.add("children", resultMapList1);
				resultMapList1.parallelStream().forEach((child)->{
					//三级菜单
					List<ResultMap> resultMapList2 = menuService.searchMapList(new JSONMap("{parent_id:"+child.getStr("id")+"}"));
					if(resultMapList2.size()>0){
						child.add("children", resultMapList2);
					}
				});
			}
		});
		return r.addData(resultMapList);
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
