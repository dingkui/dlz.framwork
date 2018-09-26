package com.dlz.apps.sys.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlz.apps.ControllerConst;
import com.dlz.apps.sys.cache.MenuCache;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.modal.ResultMap;
import org.slf4j.Logger;
import com.dlz.framework.ssme.base.controller.BaseController;
@Controller
@RequestMapping(value = ControllerConst.ADMIN)
public class PageController extends BaseController{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(PageController.class);
	@Autowired
	private MenuCache menuCahe;

	/*
	 * 根据菜单配置跳转到指定页面
	 */
	@RequestMapping("/flows")
	public String init(HttpServletRequest request,Long _mid,ModelMap model) {
		if (_mid == null) {
			return null;
		}
		Enumeration ms = request.getParameterNames();
		while(ms.hasMoreElements()){
			String a = (String)ms.nextElement();
			model.put(a, request.getParameter(a));
		}
		JSONMap flow = new JSONMap(menuCahe.get(_mid).getfFlow()); 
		if(flow.isEmpty()){
			return null;
		}
		model.putAll(flow);
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
	/**
	 * 根据参数查询列表
	 * @param request
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping("/sql/list")
	public Page<ResultMap> listFromSql(HttpServletRequest request,Page page,String sqlKey) throws Exception {
		if(sqlKey==null||!sqlKey.startsWith("key")){
			return page;
		}
		ParaMap pm=new ParaMap(sqlKey,page);
		addSearchParaFromRequest(request.getParameterMap(), pm);
		dealFlow(request, pm);
		addConverFromRequest(request.getParameterMap(), pm);
		return commService.getPage(pm);
	}
	/**
	 * 跳转至页面支持传递参数 2016-07-23
	 * @return
	 */
	@RequestMapping("/gp_{p1}/{p2}/{p3}")
	public String goPage(@PathVariable(value = "p1")String p1,@PathVariable(value = "p2")String p2,@PathVariable(value = "p3")String p3,HttpServletRequest request,ModelMap m) {
		Enumeration ms = request.getParameterNames();
		while(ms.hasMoreElements()){
			String a = (String)ms.nextElement();
			m.put(a, request.getParameter(a));
		}
		StringBuilder sb = new StringBuilder("/"+p1);
		if(p2!=null){
			sb.append("/"+p2);
		}
		if(p3!=null){
			sb.append("/"+p3);
		}
		return sb.toString();
	}
	/**
	 * 跳转至页面支持传递参数 2016-07-23
	 * @return
	 */
	@RequestMapping("/gp_{p1}/{p2}")
	public String goPage(@PathVariable(value = "p1")String p1,@PathVariable(value = "p2")String p2,HttpServletRequest request,ModelMap m) {
		return goPage(p1, p2, null, request, m);
	}
	/**
	 * 跳转至页面支持传递参数 2016-07-23
	 * @return
	 */
	@RequestMapping("/gp_{p1}")
	public String goPage(@PathVariable(value = "p1")String p1,HttpServletRequest request,ModelMap m) {
		return goPage(p1, null, null, request, m);
	}
}
