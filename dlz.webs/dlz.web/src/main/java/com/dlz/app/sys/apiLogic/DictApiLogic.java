package com.dlz.app.sys.apiLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.dlz.app.sys.service.IDictService;
import com.dlz.app.uim.annotation.AnnoAuth;
import com.dlz.comm.json.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.db.cache.DictCache;
import com.dlz.framework.db.modal.Page;
import org.slf4j.Logger;
import com.dlz.comm.util.StringUtils;
import com.dlz.web.logic.AuthedCommLogic;
/**
 * 字典管理
 * @author lxm 
 *
 * 2018年6月7日
 */
@Service
@AnnoAuth("admin")
@Lazy
public class DictApiLogic extends AuthedCommLogic{

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
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
		String id = data.getStr("id");
		String ids = data.getStr("ids");
		Integer flag=1;
		if(!StringUtils.isEmpty(id)){
			flag = dictService.delByKey(id);
		}else if(!StringUtils.isEmpty(ids)){
			flag = dictService.delByKeys(ids);
		}else{
			r.addErr("删除失败，ID不能为空");
		}
		if(flag==-2){
			r.addErr("您要删除的字典存在子节点，请先删除子节点再进行操作");
		}
		return r;
	}
	
	/**
	 * 获取字典详情列表
	 * @param data
	 * @return
	 */
	@AnnoAuth("N")
	public JSONResult getDictDetail(JSONMap data){
		JSONResult r = JSONResult.createResult();
		String dictCode = data.getStr("dictCode");
		if(StringUtils.isEmpty(dictCode)){
			return r.addErr("字典编号不能为空");
		}
		return r.addData(dictCache.getDictList(dictCode));
	}
	/**
	 * 根据字典值获取字典Text
	 * @param data
	 * @return
	 */
	@AnnoAuth("N")
	public JSONResult getDictText(JSONMap data){
		JSONResult r = JSONResult.createResult();
		String dictCode = data.getStr("dictCode");
		String value = data.getStr("val");
		if(StringUtils.isEmpty(dictCode)||StringUtils.isEmpty(value)){
			return r.addErr("字典参数不能为空");
		}
		return r.addData(dictCache.getVal(dictCode, value));
	}
}
