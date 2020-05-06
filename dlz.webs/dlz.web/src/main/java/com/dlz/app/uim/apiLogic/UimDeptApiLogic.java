package com.dlz.app.uim.apiLogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.dlz.app.uim.annotation.AnnoAuth;
import com.dlz.app.uim.service.IUimDeptService;
import com.dlz.comm.json.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.db.modal.ResultMap;
import org.slf4j.Logger;
import com.dlz.comm.util.StringUtils;
import com.dlz.web.logic.NoAuthCommLogic;
/**
 * 部门管理
 * @author lxm 
 *
 * 2018年6月7日
 */
@Service
@AnnoAuth("sys_admin")
@Lazy
public class UimDeptApiLogic extends NoAuthCommLogic{

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	@Autowired
	IUimDeptService deptService;
	
	/**
	 * 获取部门树（三级）
	 * @param data
	 * @return
	 */
	public JSONResult getDeptList(JSONMap data){
		JSONResult r = JSONResult.createResult();
		String ddCorpid = data.getStr("ddCorpid");
		if(StringUtils.isEmpty(ddCorpid)){
			return r.addData(getDepts("0"));
		}else{
			return r.addData(getDepts("0",ddCorpid));
		}
	}
	
	private List<ResultMap> getDepts(String parentId,String ddCorpid){
		List<ResultMap> resultMapList = deptService.searchMapList(new JSONMap("d_fid",parentId,"ddCorpid",ddCorpid));
		if(resultMapList.size()>0){
			resultMapList.parallelStream().forEach((res)->{
				List<ResultMap> sub=getDepts(res.getStr("dId"));
				if(sub.size()>0){
					res.add("children", sub);
				}
			});
		}
		return resultMapList;
	}
	
	private List<ResultMap> getDepts(String parentId){
		List<ResultMap> resultMapList = deptService.searchMapList(new JSONMap("d_fid",parentId));
		if(resultMapList.size()>0){
			resultMapList.parallelStream().forEach((res)->{
				List<ResultMap> sub=getDepts(res.getStr("dId"));
				if(sub.size()>0){
					res.add("children", sub);
				}
			});
		}
		return resultMapList;
	}
	
	/**
	 * 获取部门列表
	 * @param data
	 * @return
	 */
	@AnnoAuth("N")
	public JSONResult getAllDeptList(JSONMap data){
		JSONResult r = JSONResult.createResult();
		List<ResultMap> resultMapList = deptService.searchMapList(new JSONMap());
		return r.addData(resultMapList);
	}
	
	/**
	 * 部门新增/修改
	 * @param data
	 * @return
	 */
	public JSONResult save(JSONMap data){
		JSONResult r = JSONResult.createResult();
		try {
			deptService.addOrUpdate(data);
			r.addMsg("保存成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			r.addErr("保存失败");
		}
		return r;
	}
	
	public JSONResult del(JSONMap data){
		JSONResult r = JSONResult.createResult();
		String ids = data.getStr("ids");
		if(!StringUtils.isEmpty(ids)){
			deptService.delByKeys(ids);
			r.addMsg("批量删除成功");
		}else{
			r.addErr("删除失败，ID不能为空");
		}
		return r;
	}
}
