package com.dlz.commbiz.sys.controller;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlz.common.base.controller.BaseController;
import com.dlz.common.logic.interfaces.IWapLogic;
import com.dlz.common.shiro.ShiroUser;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.util.JacksonUtil;
@Controller
@RequestMapping(value = "")
public class PageController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(PageController.class);
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
	
	@RequestMapping(value = "/doAjax/{aType}")
	@ResponseBody
	public Object doAjax(HttpServletRequest request,String data, Page page, @PathVariable(value = "aType")String aType) {
		Subject currentUser = SecurityUtils.getSubject();
		Map<String, Object> m = createRsutlJson();
		String[] deals=aType.split("_");
		String methodStr="done";
		if(deals.length>1){
			methodStr=deals[1];
		}
		
		IWapLogic pds = (IWapLogic)SpringHolder.getBean(deals[0]+"ApiLogic");
		try {
			if(pds==null){
				addErr(m, "地址有误【"+deals[0]+"】");
				m.put("flag", -1);
				return m;
			}
			if(pds.needAuth() && !currentUser.isAuthenticated()){
				addErr(m, "未登录");
				m.put("flag", 0);
				return m;
			}
			Map<String,Object> datas = null;
			if(data!=null){
				datas=JacksonUtil.readValue(data, HashMap.class);
			}else{
				datas=new HashMap<String,Object>();
			}
			
			Method method = pds.getClass().getMethod(methodStr,HttpServletRequest.class,Page.class,Map.class);
			Object r = method.invoke(pds, request,page,datas);
			m.put("data", r);
		} catch (NoSuchMethodException e) {
			addErr(m, "地址有误【"+aType+"】");
			m.put("flag", -2);
		} catch (IllegalArgumentException e) {
			logger.error(pds.getClass().getSimpleName()+"."+methodStr+"的参数有误！");
			addErr(m, "地址有误【"+aType+"】");
			m.put("flag", -3);
		} catch (Exception e) {
			logger.error("业务异常【"+pds.getClass().getSimpleName()+"."+methodStr+"】");
			logger.error("datas:"+data);
			logger.error(e.getMessage(),e);
			addErr(m, "业务异常【"+aType+"】["+e.getMessage()+"]");
			m.put("flag", -99);
		}finally{
			if(currentUser.isAuthenticated()){
				ShiroUser user = (ShiroUser) currentUser.getPrincipal();
			}else{
			}
		}
		return m;
	}
}
