package com.dlz.app.sys.apiLogic;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlz.app.sys.service.IBaseSetService;
import com.dlz.app.uim.annotation.AnnoAuth;
import com.dlz.app.uim.bean.AuthUser;
import com.dlz.app.uim.holder.UserHolder;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.db.modal.InsertParaMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.modal.UpdateParaMap;
import com.dlz.framework.util.StringUtils;
import com.dlz.web.logic.AuthedCommLogic;

/**
 * 参数管理
 * @author Administrator
 *
 */
@Service
@AnnoAuth("admin")
public class BaseSetApiLogic extends AuthedCommLogic{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
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
		
		ParaMap paraMap = new ParaMap("key.baseset.list", new Page<>(pageIndex, pageSize, "create_time","desc"));
		paraMap.getConvert().addDictConvert("status", "EffectStatus");
		paraMap.addPara("baseCodeLike", data.getStr("baseCode"));
		return result.addData(commService.getPage(paraMap));
	}
	
	/**
	 * 新增或修改
	 * @param data
	 * @return
	 */
	public JSONResult save(JSONMap data){
		AuthUser authInfo = UserHolder.getAuthInfo();
		JSONResult result = JSONResult.createResult();
		Integer modalType = data.getInt("modalType");
		data.remove("modalType");
		
		data.add("update_time", new Date());
		data.add("update_id", authInfo.getId());
		data.add("update_name", authInfo.getName());
		
		String baseCode = data.getStr("baseCode");
		if(modalType == 0){
			JSONMap jsonMap = new JSONMap();
			jsonMap.add("baseCode", data.getStr("baseCode"));
			if(baseSetService.searchMap("key.baseset.list", jsonMap) != null){
				return result.addErr("编码已存在,请重新录入");
			}
			InsertParaMap iParaMap = new InsertParaMap("t_b_base_set");
			iParaMap.addValues(data);
			iParaMap.addValue("status", 1);
			iParaMap.addValue("create_time", new Date());
			iParaMap.addValue("create_id", authInfo.getId());
			iParaMap.addValue("create_name", authInfo.getName());
			commService.excuteSql(iParaMap);
		}else{
			UpdateParaMap uParaMap = new UpdateParaMap("t_b_base_set");
			uParaMap.addSetValue("base_value", data.getStr("baseValue"));
			uParaMap.addSetValue("remarks", data.getStr("remarks"));
			uParaMap.addEqCondition("base_code", data.getStr("baseCode"));
			commService.excuteSql(uParaMap);
		}
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
		if(StringUtils.isEmpty(ids)){
			return result.addErr("参数为空");
		}
		String[] idArr = ids.split(",");
		for (String id : idArr) {
			baseSetService.delByKey(id);
		}
		return result.addData("操作成功");
	}
}
