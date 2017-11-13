package com.dlz.apps.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlz.framework.db.enums.DateFormatEnum;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.modal.SearchParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.ssme.db.model.Dept;
import com.dlz.framework.ssme.db.model.DeptCriteria;
import com.dlz.framework.ssme.db.model.DeptUser;
import com.dlz.framework.ssme.db.model.DeptUserCriteria;
import com.dlz.framework.ssme.db.service.DeptService;
import com.dlz.framework.ssme.db.service.DeptUserService;
import com.dlz.framework.ssme.db.service.RbacService;
import com.dlz.framework.ssme.db.service.UserService;
import com.dlz.framework.ssme.shiro.ShiroUser;
import com.dlz.framework.util.JacksonUtil;

/**
 * MenueController
 * 菜单管理模块相关功能操作实现
 * 说明：
 * 
 * 2013-8-25
 */
@Controller
@RequestMapping("/rbac/dept")
public class DeptController {
	private static Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired
	UserService userService;
	@Autowired
	ICommService commService;
	@Autowired
	DeptService deptService;
	@Autowired
	RbacService rbacService;
	@Autowired
	DeptUserService deptUserService;
	
	/*
	 * 菜单初始页面-首页
	 */
	@RequestMapping()
	public String init(Model model, HttpServletRequest request) {
		try {
			return "/rbac/dept";
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	/*
	 * 菜单初始页面-首页
	 */
	@RequestMapping("/edit")
	public String edit(Model m,HttpServletRequest request) {
		try {
			return "/rbac/deptEdit";
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	/*
	 * 添加或更新（单条）
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdate")
	public String addOrUpdate(Dept dept) throws Exception {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (dept.getdId() != null) {
			Dept oldD=deptService.selectByPrimaryKey(dept.getdId());
			deptService.updateByPrimaryKeySelective(dept);
			if(!String.valueOf(oldD.getdManagerId()).equals(String.valueOf(dept.getdManagerId()))){
				DeptUserCriteria dc = new DeptUserCriteria();
				dc.createCriteria().andDuUIdEqualTo(dept.getdManagerId()).andDuDIdEqualTo(dept.getdId());
				deptUserService.deleteByExample(dc);
				DeptUser  du = new DeptUser();
				du.setDuUId(dept.getdManagerId());
				du.setDuDId(dept.getdId());
				du.setDuId(commService.getSeq(DeptUser.class));
				du.setDuAddUserId(user.getUserId());
				du.setDuAddTime(new Date());
				du.setDuDuty("1");
				deptUserService.insert(du);
			}
		} else {
			dept.setdCode(rbacService.getCode(dept.getdCode(), "d_code", "t_p_dept"));
			dept.setdId(commService.getSeq(Dept.class));
			dept.setdFid(dept.getdFid()==null?0l:dept.getdFid());
			dept.setdScFlg("0");
			deptService.insert(dept);
			if(dept.getdManagerId()!=null){
				DeptUser  du = new DeptUser();
				du.setDuUId(dept.getdManagerId());
				du.setDuDId(dept.getdId());
				du.setDuId(commService.getSeq(DeptUser.class));
				du.setDuAddUserId(user.getUserId());
				du.setDuAddTime(new Date());
				du.setDuDuty("1");//部门主管
				deptUserService.insert(du);
			}
		}
		return "OK";
	}
	
	@ResponseBody
	@RequestMapping(value="list")
	public List<Dept> getDepts() throws Exception{
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		DeptCriteria dc = new DeptCriteria();
		if(SecurityUtils.getSubject().hasRole("1")){
			dc.setOrderByClause("d_code asc");
		}else{
			SearchParaMap pm = new SearchParaMap("t_p_dept","d_code");
			pm.addCondition("d_manager_id", "=", user.getUserId());
			String dCode=(String)commService.getColum(pm);
			dc.createCriteria().andDCodeLike(dCode+"%");
			dc.setOrderByClause("d_code asc");
		}
		List<Dept> list = deptService.selectByExample(dc);
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value="getDeptsMember")
	public List<ResultMap> getDeptsMember(HttpServletRequest request) throws Exception{
		String dCode=request.getParameter("dCode");
		ParaMap pm = new ParaMap("key.dept.getDeptAndUsers",new Page(1,5000,"id"));
		pm.addPara("dCode", dCode);
		return commService.getMapList(pm);
	}
	
	@ResponseBody
	@RequestMapping(value="/add")
	public Map<String, Object>  add() throws Exception{
		Map<String, Object> m = new  HashMap<String, Object>();
		return m;
	}
	@ResponseBody
	@RequestMapping(value="/del/{id}")
	public String del(@PathVariable(value="id")Long id) throws Exception {
		ParaMap pm = new ParaMap("key.dept.searchUser");
		Dept dept = deptService.selectByPrimaryKey(id);
		pm.addPara("dCode", dept.getdCode());
		int cnt = commService.getCnt(pm);
		if(cnt>0){
			return "请删除部门成员后再删除部门！";
		}
		deptService.deleteByPrimaryKey(id);
		return "ok";
	}
	
	/**
	 * 取得部门已经绑定的用户
	 * @param request
	 * @param page
	 * @param dId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getBindUsers")
	public Page getBindUsers(HttpServletRequest request, Page page,Long dId) throws Exception {
		ParaMap pm = new ParaMap("key.dept.searchUser", page);
		pm.getConvert().addDateConvert(DateFormatEnum.DateTimeStr);
		pm.addPara("userName", request.getParameter("userName"));
		pm.addPara("dCode", request.getParameter("dCode"));
		pm.addPara("dId", request.getParameter("dId"));
		return commService.getPage(pm);
	}
	/**
	 * 添加用户跳转页面
	 * @param m
	 * @param dId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addUsers")
	public String addUsers(HttpServletRequest request,Model m,Long dId) throws Exception {
		m.addAttribute("dId", dId);
		m.addAttribute("addZg",request.getParameter("addZg"));
		return "/rbac/userListCheck";
	}
	/**
	 * 保存用户信息
	 * @param m
	 * @param dId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/saveUsers")
	public String saveUsers(String data) throws Exception {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		DeptUser[] items = JacksonUtil.readValue(data, DeptUser[].class);
		for (int i = 0; i < items.length; i++) {
			if (items[i].getDuId() != null) {
				deptUserService.updateByPrimaryKeySelective(items[i]);
			} else {
				items[i].setDuId(commService.getSeq(DeptUser.class));
				items[i].setDuAddUserId(user.getUserId());
				items[i].setDuAddTime(new Date());
				deptUserService.insert(items[i]);
			}
		}
		return "OK";
	}
	/**
	 * 添加用户
	 * @param request
	 * @param dIds
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/delUsers")
	public  String delUsers(HttpServletRequest request,String dIds) throws Exception {
		String dId[]=dIds.split(",");
		for(String id:dId){
			deptUserService.deleteByPrimaryKey(Long.parseLong(id));
		}
		return "OK";
	}
}
