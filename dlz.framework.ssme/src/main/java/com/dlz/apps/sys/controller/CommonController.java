package com.dlz.apps.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlz.apps.sys.cache.MenuCache;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.ssme.base.controller.BaseController;

/**
 * FlowController 说明：通用操作
 */
@Controller
@RequestMapping(value = "")
public class CommonController extends BaseController{

	@Autowired
	private MenuCache menuCahe;

	/*
	 * 根据菜单配置跳转到指定页面
	 */
	@RequestMapping("/flows")
	public String init(Long mid,Model model) {
		if (mid == null) {
			return null;
		}
		JSONMap flow = new JSONMap(menuCahe.get(mid).getfFlow()); 
		if(flow.isEmpty()){
			return null;
		}
		model.asMap().putAll(flow);
		return flow.getStr("page");
	}
	
	/**
	 * 根据菜单配置查询列表
	 * @param request
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping("/flows/list")
	public Page<ResultMap> list(HttpServletRequest request,Page page) throws Exception {
		ParaMap pm=new ParaMap("",page);
		addSearchParaFromRequest(request.getParameterMap(), pm);
		dealFlow(request, pm);
		addConverFromRequest(request.getParameterMap(), pm);
		return commService.getPage(pm);
	}
}
