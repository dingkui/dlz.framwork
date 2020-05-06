package com.dlz.app.sys.service;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.dlz.comm.json.JSONMap;
import com.dlz.framework.db.service.IBaseService;

@Controller
public interface IDictService extends IBaseService{
	/**
	 * 取得字典明细
	 * @param dictCode
	 * @return
	 */
	public List<JSONMap> getDetails(String dictCode);
	
	/**
	 * 设置字典明细，先删除再保存
	 * @param dictCode
	 * @return
	 */
	public List<JSONMap> setDetails(String dictCode,List<JSONMap> details);
}
