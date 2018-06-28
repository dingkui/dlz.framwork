package com.dlz.app.sys.apiLogic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlz.app.sys.service.IDictService;
import com.dlz.app.uim.annotation.AnnoAuth;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.db.cache.DictCache;
import com.dlz.framework.db.cache.bean.Dict;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.exception.CodeException;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.StringUtils;
import com.dlz.framework.util.config.ConfUtil;
import com.dlz.web.logic.AuthedCommLogic;
/**
 * 字典管理
 * @author lxm 
 *
 * 2018年6月7日
 */
@Service
@AnnoAuth("ROLE_ADMIN")
public class DictApiLogic extends AuthedCommLogic{
	private MyLogger logger = MyLogger.getLogger(getClass());
	@Autowired
	IDictService dictService;
	@Autowired
	DictCache dictCache;
	
	/**
	 * 获取字典列表
	 * @param data
	 * @return
	 */
	public JSONResult getDictList(JSONMap data){
		JSONResult r = JSONResult.createResult();
		int pageIndex = data.getInt("pageIndex");
		int pageSize = data.getInt("pageSize");
		data.remove("pageIndex");
		data.remove("pageSize");
		Page page =dictService.mapPageByPara(new Page<>(pageIndex, pageSize), data);
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
			dictService.addOrUpdate(data);
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
		String ids = data.getStr("ids");
		if(!StringUtils.isEmpty(ids)){
			dictService.delByKeys(ids);
			r.addMsg("批量删除成功");
		}else{
			r.addErr("删除失败，ID不能为空");
		}
		return r;
	}
	
	/**
	 * 获取字典详情列表
	 * @param data
	 * @return
	 */
	public JSONResult getDictDetail(JSONMap data){
		JSONResult r = JSONResult.createResult();
		String dictCode = data.getStr("dictCode");
		if(StringUtils.isEmpty(dictCode)){
			return r.addErr("字典编号不能为空");
		}
		return r.addData(dictCache.getDictList(dictCode));
	}
}
