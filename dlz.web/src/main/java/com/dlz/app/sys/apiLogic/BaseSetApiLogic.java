package com.dlz.app.sys.apiLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlz.app.sys.service.IBaseSetService;
import com.dlz.app.uim.annotation.AnnoAuth;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.util.StringUtils;
import com.dlz.web.logic.AuthedCommLogic;

/**
 * 参数管理
 * @author Administrator
 *
 */
@Service
@AnnoAuth("ROLE_ADMIN")
public class BaseSetApiLogic extends AuthedCommLogic{
	@Autowired
	IBaseSetService baseSetService;
	
	/**
	 * 获取参数列表
	 * @param data
	 * @return
	 */
	public JSONResult list(JSONMap data){
		JSONResult result = JSONResult.createResult();
		int pageIndex = data.getInt("pageIndex");
		int pageSize = data.getInt("pageSize");
		data.remove("pageIndex");
		data.remove("pageSize");
		return result.addData(baseSetService.mapPageByPara(new Page<>(pageIndex, pageSize,"create_time","desc"),new JSONMap(data)));
	}
	
	/**
	 * 新增或修改
	 * @param data
	 * @return
	 */
	public JSONResult save(JSONMap data){
		JSONResult result = JSONResult.createResult();
		baseSetService.addOrUpdate(data);
		return result.addData("操作成功");
	}
	
	/**
	 * 删除
	 * @param data
	 * @return
	 */
	public JSONResult del(JSONMap data){
		JSONResult result = JSONResult.createResult();
		String ids = data.getStr("ids");
		if(!StringUtils.isEmpty(ids)){
			return result.addErr("参数为空");
		}
		baseSetService.delByKeys(ids);
		return result.addData("操作成功");
	}
}
