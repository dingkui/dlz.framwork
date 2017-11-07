package com.dlz.commbiz.sys.controller;

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

import com.dlz.commbiz.sys.meb.service.MebAccDetailService;
import com.dlz.commbiz.sys.rbac.model.Role;
import com.dlz.commbiz.sys.rbac.service.FunOptService;
import com.dlz.commbiz.sys.rbac.service.RoleService;
import com.dlz.commbiz.sys.rbac.service.UserRoleService;
import com.dlz.commbiz.sys.rbac.service.UserService;
import com.dlz.common.shiro.ShiroUser;
import com.dlz.framework.db.service.ICommService;
import com.google.common.collect.Maps;

/**
 * MenueController
 * 菜单管理模块相关功能操作实现
 * 说明：
 * 
 * 2013-8-25
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	private static Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserRoleService userRoleService;
	@Autowired
	private FunOptService funOptService;
	
	@Autowired
	ICommService commService;
	@Autowired
	MebAccDetailService mebAccDetailService;
	
	/*
	 * 菜单初始页面-首页
	 */
	@RequestMapping()
	public String init(Model model, HttpServletRequest request) {
		try {
			ShiroUser loginUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			Role role = new Role();
			if(!loginUser.getRoleList().isEmpty()){
				role = roleService.selectByPrimaryKey(loginUser.getRoleList().get(0));
			}
			model.addAttribute("user", loginUser);
			model.addAttribute("role", role);
			model.addAttribute("userGroup", loginUser.getUserGroup());
			return "/welcome";
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="/outlookmenu/{id}")
	public List<Map<String, Object>> outlookmenu(@PathVariable("id") Long id) throws Exception{
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String fCode=funOptService.selectByPrimaryKey(id).getfCode();
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("pidCondition", "and fun_opt_id !="+id);
		paramMap.put("fCode", fCode);
		paramMap.put("roles", user.getRoleList());
		return funOptService.getOptsByRoles(paramMap);
	}
	
	@RequestMapping(value="/{id}")
	public String rbac(Model model,@PathVariable(value="id")Integer menueId) {
		model.addAttribute("id", menueId);
		return "/menue_index";
	}
}
