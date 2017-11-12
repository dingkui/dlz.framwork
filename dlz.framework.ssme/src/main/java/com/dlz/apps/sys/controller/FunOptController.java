package com.dlz.apps.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.ssme.db.model.FunOpt;
import com.dlz.framework.ssme.db.model.FunOptCriteria;
import com.dlz.framework.ssme.db.service.FunOptService;
import com.dlz.framework.ssme.db.service.RbacService;

/**
 * FunOptController 说明：资源管理模块中相关功能实现  2013-8-23
 */

@Controller
@RequestMapping(value = "/rbac/funopt")
public class FunOptController {
	private static Logger logger = LoggerFactory.getLogger(FunOptController.class);

	@Autowired
	private FunOptService funOptService;
	@Autowired
	private ICommService commService;

	@Autowired
	RbacService rbacService;

	/*
	 * 左边树形菜单通过此方法跳转至页面
	 * 
	 * @return  2013-8-23
	 */
	@RequestMapping()
	public String init() {
		return "/sys/rbac/funOpt";
	}

	/*
	 * 资源管理：页面加载时列表信息查询
	 * 
	 * @param request
	 * 
	 * @return  2013-8-23
	 */
	@ResponseBody
	@RequestMapping(value = "listAll")
	public List<FunOpt> listAll(HttpServletRequest request, HttpServletResponse response){
	  try {
	  	FunOptCriteria foc = new FunOptCriteria();
			foc.setOrderByClause("f_code");
			return funOptService.selectByExample(foc);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}

	/*
	 * 资源管理：新增时验证录入的功能操作名称是否已存在
	 * 
	 * @param funOptNm
	 * 
	 * @return  2013-8-23
	 */
	@ResponseBody
	@RequestMapping(value = "check")
	public boolean check(String funOptNm) {
		try {
			FunOptCriteria criteria = new FunOptCriteria();
			criteria.createCriteria().andFunOptNmEqualTo(funOptNm);
			List<FunOpt> foList = funOptService.selectByExample(criteria);
			return foList.isEmpty();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}

	/*
	 * 资源管理：新增时验证URL是否已存在
	 * 
	 * @param url
	 * 
	 * @return  2013-8-23
	 */
	@ResponseBody
	@RequestMapping(value = "checkUrl")
	public boolean checkUrl(String url) {
		try {
			FunOptCriteria criteria = new FunOptCriteria();
			criteria.createCriteria().andUrlEqualTo(url);
			List<FunOpt> foList = funOptService.selectByExample(criteria);
			return  foList.isEmpty();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	} 

	/*
	 * 添加或更新（单条）
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdate")
	public String addOrUpdate(HttpServletRequest request,FunOpt funOpt) throws Exception {
		if (funOpt.getFunOptId() != null) {
			funOptService.updateByPrimaryKeySelective(funOpt);
		} else {
			funOpt.setfCode(rbacService.getCode(funOpt.getfCode(), "f_code", "T_P_FUN_OPT"));
			funOpt.setFunOptId(commService.getSeq(FunOpt.class));
			funOpt.setParentFunOptId(funOpt.getParentFunOptId()==null?0l:funOpt.getParentFunOptId());
			funOptService.insert(funOpt);
		}
		return "OK";
	}

	/*
	 * 资源管理：获取更新待对象信息
	 * 
	 * @param funOptId
	 * 
	 * @return  2013-8-24
	 */
	@ResponseBody
	@RequestMapping(value = "getFunopt/{funOptId}")
	public FunOpt getFunopt(@PathVariable("funOptId") Long funOptId) {
		try {
			return funOptService.selectByPrimaryKey(funOptId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	/*
	 * 资源管理：删除之前验证所选记录是否存在
	 * 
	 * @param funOptId
	 * 
	 * @return  2013-8-24
	 */
	@ResponseBody
	@RequestMapping(value = "delFreCheck/{funOptId}")
	public boolean delFreCheck(@PathVariable("funOptId") Long funOptId) {
		try {
			String sql="begin delete from T_P_FUN_OPT where FUN_OPT_ID = #{id}; ";
			sql+="delete from t_p_role_fun_opt where FUN_OPT_ID = #{id}; end;";
			ParaMap pm = new ParaMap(sql);
			pm.addPara("id", funOptId);
			commService.excuteSql(pm);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}

	/*
	 * 资源管理：删除之前验证所选记录是否有子节点
	 * 
	 * @param funOptId
	 * 
	 * @return  2013-8-24
	 */
	@ResponseBody
	@RequestMapping(value = "checkChild/{funOptId}")
	public boolean checkChild(@PathVariable("funOptId") Long funOptId) {
		try {
			FunOptCriteria foc = new FunOptCriteria();
			foc.createCriteria().andParentFunOptIdEqualTo(funOptId);
			List<FunOpt> list = funOptService.selectByExample(foc);
			return list.isEmpty();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}

	/*
	 * 资源管理：删除
	 * 
	 * @param funOptId
	 * 
	 * @return  2013-8-24
	 */
	@ResponseBody
	@RequestMapping(value = "delete/{funOptId}")
	public boolean delete(@PathVariable("funOptId") Long funOptId) {
		try {
			funOptService.deleteByPrimaryKey(funOptId);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}

	/*
	 * 资源管理：查询对应模块功能操作
	 * 
	 * @return  2013-8-26
	 */
	@ResponseBody
	@RequestMapping("selFunopt")
	public List<Map<String, Object>> selFunopt() {
		try {
			List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
			FunOptCriteria foc = new FunOptCriteria();
			foc.createCriteria().andParentFunOptIdIsNotNull();
			List<FunOpt> list = funOptService.selectByExample(foc);
			for (FunOpt fo : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", fo.getFunOptId());
				map.put("text", fo.getFunOptNm());
				treeList.add(map);
			}
			return treeList;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
}
