package com.dlz.apps.sys.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlz.apps.freemaker.service.impl.FreemarkerEmailTemplateService;
import com.dlz.apps.freemaker.service.impl.FreemarkerMsmTemplateService;
import com.dlz.apps.freemaker.service.impl.FreemarkerResumeTemplateService;
import com.dlz.apps.sys.util.DictUtil;
import com.dlz.framework.cache.AbstractCache;
import com.dlz.framework.db.DbInfo;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.ssme.base.controller.BaseController;
import com.dlz.framework.ssme.db.service.FunOptService;
import com.dlz.framework.ssme.shiro.ShiroUser;
import com.dlz.framework.ssme.util.config.ConfigUtil;
import com.dlz.framework.ssme.util.config.XMLMessageUtil;
import com.dlz.framework.util.system.Reflections;
import com.google.common.collect.Maps;

/**
 * RoleController 说明：角色管理模块相关功能 2013-8-25
 */
@Controller
@RequestMapping(value = "/main")
public class MainController extends BaseController{

	@Autowired
	private FunOptService funOptService;

	/*
	 * 左边树形菜单通过此方法跳转至页面
	 * 
	 * @return 2013-8-25
	 */
	@RequestMapping()
	public String init(Model model) {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("pidCondition", "and parent_fun_opt_id =0");
		paramMap.put("fCode", "");
		paramMap.put("roles", user.getRoles());
		model.addAttribute("menuList", funOptService.getOptsByRoles(paramMap));
		return "index";
	}
	/*
	 * 左边树形菜单通过此方法跳转至页面
	 * 
	 * @return 2013-8-25
	 */
	@RequestMapping("index1")
	public String index1(Model model) {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("pidCondition", "and parent_fun_opt_id =0");
		paramMap.put("fCode", "");
		paramMap.put("roles", user.getRoles());
		List<Map<String, Object>> rootMenuList = funOptService.getOptsByRoles(paramMap);
		model.addAttribute("menuList", rootMenuList);
		return "index1";
	}

	@ResponseBody
	@RequestMapping("/reload/{act}")
	public String reload(HttpServletRequest request,@PathVariable("act") String act) throws Exception {
		switch (act) {
			case "prop":
				ConfigUtil.loadProperty();
				
				ParaMap paraMap=new ParaMap("key.ptn.updateAllUserRelation");
				commService.excuteSql(paraMap);
				break;
			case "db":
				DbInfo.reload();
				break;
			case "xml":
				XMLMessageUtil.load();
				break;
			case "freemarker":
				SpringHolder.getBean(FreemarkerResumeTemplateService.class).init();
				SpringHolder.getBean(FreemarkerEmailTemplateService.class).init();
				SpringHolder.getBean(FreemarkerMsmTemplateService.class).init();
				break;
			case "cache":
				AbstractCache.clearAll();
				break;
			case "makJson":
				DictUtil.makeJson(request.getSession().getServletContext().getRealPath("/static/json/"));
				break;
			case "makProdJson":
//				PtnGoodsService ptnGoodsService = SpringHolder.getBean(PtnGoodsService.class);
//				ptnGoodsService.makeJson(request.getSession().getServletContext().getRealPath("/static/json/"), "221");
				break;
			case "All":
				ConfigUtil.loadProperty();
				DbInfo.reload();
				XMLMessageUtil.load();
				SpringHolder.getBean(FreemarkerResumeTemplateService.class).init();
				SpringHolder.getBean(FreemarkerEmailTemplateService.class).init();
				SpringHolder.getBean(FreemarkerMsmTemplateService.class).init();
				AbstractCache.clearAll();
				DictUtil.makeJson(request.getSession().getServletContext().getRealPath("/static/json/"));
				break;
		}
		return "刷新成功";
	}
	
	@ResponseBody
	@RequestMapping("/work/{workName}/{workmethod}")
	public String work(@PathVariable(value = "workName") String workName,@PathVariable(value = "workmethod") String workmethod) throws InterruptedException, BeansException, ClassNotFoundException {
		if (workName == null) {
			return "workName is null";
		}
		Object workBean = null;
		try {
			workBean = SpringHolder.getBean(Class.forName("com.dlz.quarzt." + workName));
		} catch (Exception e) {
			return "bean is not exist";
		}
		try {
			Reflections.invokeMethodByName(workBean, workmethod, null);
		} catch (Exception e) {
			return "执行失败：" + e.getMessage();
		}
		return "执行成功";
	}

	@RequestMapping("/clear")
	public String clear() {
		return "sys/rbac/clearCathe";
	}
	@RequestMapping("/doing")
	public String doing() {
		return "doing";
	}
	
}
