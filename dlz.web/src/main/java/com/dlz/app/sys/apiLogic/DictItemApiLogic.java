package com.dlz.app.sys.apiLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlz.app.sys.service.IDictItemService;
import com.dlz.app.uim.annotation.AnnoAuth;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.db.cache.DictCache;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.StringUtils;
import com.dlz.web.logic.AuthedCommLogic;
/**
 * 字典明细管理
 * @author lxm 
 *
 * 2018年6月7日
 */
@Service
@AnnoAuth("admin")
public class DictItemApiLogic extends AuthedCommLogic{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private MyLogger logger = MyLogger.getLogger(getClass());
	@Autowired
	IDictItemService dictItemService;
	@Autowired
	DictCache dictCache;
	
	/**
	 * 获取字典列表
	 * @param data
	 * @return
	 */
	public JSONResult getItemList(JSONMap data){
		JSONResult r = JSONResult.createResult();
		int pageIndex = data.getInt("pageIndex");
		int pageSize = data.getInt("pageSize");
		data.remove("pageIndex");
		data.remove("pageSize");
		Page page =dictItemService.mapPageByPara(new Page<>(pageIndex, pageSize), data);
		return r.addData(page);
	}
	
	/**
	 * 字典新增/修改
	 * @param data
	 * @return
	 */
	public JSONResult save(JSONMap data){
		JSONResult r = JSONResult.createResult();
		try {
			dictItemService.addOrUpdate(data);
			dictCache.reload(data.getStr("dictId"));
			r.addMsg("保存成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			r.addErr("保存失败");
		}
		return r;
	}
	
	/**
	 * 字典删除
	 * @param data
	 * @return
	 */
	public JSONResult del(JSONMap data){
		JSONResult r = JSONResult.createResult();
		String id = data.getStr("id");
		String ids = data.getStr("ids");
		String dictId = data.getStr("dictId");
		if(!StringUtils.isEmpty(id)){
			dictItemService.delByKey(id);
			r.addMsg("删除成功");
		}else if(!StringUtils.isEmpty(ids)){
			dictItemService.delByKeys(ids);
			r.addMsg("批量删除成功");
		}else{
			r.addErr("删除失败，ID不能为空");
		}
		return r;
	}
	
}
