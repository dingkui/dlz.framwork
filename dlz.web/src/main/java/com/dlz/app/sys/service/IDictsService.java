package com.dlz.app.sys.service;

import com.dlz.app.sys.bean.Dicts;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.service.IBaseService;
public interface IDictsService extends IBaseService<Dicts, Long> {
	/*
	 * 添加或更新(多个)
	 * 
	 * @return
	 */
	default boolean addOrUpdates(JSONMap[] items) {
		for (int i = 0; i < items.length; i++) {
			addOrUpdate(items[i]);
		}
		return true;
	}
	
	/*
	 * 批量删除
	 */
	default boolean dels(Long[] ids){
		for (int i = 0; i < ids.length; i++) {
			delByKey(ids[i]);
		}
		return true;
	}
//	/*
//	 * 删除单条
//	 * 
//	 * @return
//	 */
//	default int delByKey(Long id) {
//		dcc.createCriteria().andPidEqualTo(id);
//		int i =dictsService.countByExample(dcc);
//		if(i>0){
//			return "存在子节点，请删除子节点后再删除";
//		}
//		
//		Dicts d = dictsService.selectByPrimaryKey(id);
//		dictsService.deleteByPrimaryKey(id);
//		ParaMap pm = new ParaMap("key.dicts.upisleaf");
//		pm.addPara("id", d.getPid());
//		pm.addPara("pid", d.getPid());
//		pm.addPara("isLeaf", 1l);
//		commService.excuteSql(pm);
//		dictsCache.remove(id);
//		return "OK";
//	}
}
