package com.dlz.framework.ssme.base.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dlz.framework.db.exception.DbException;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.exception.BaseException;
import com.dlz.framework.exception.JspException;
import com.dlz.framework.ssme.base.logic.PageDealCommonLogic;
import com.dlz.framework.ssme.db.model.MenuDataOpt;
import com.dlz.framework.ssme.db.service.MenuDataOptService;
import com.dlz.framework.ssme.shiro.ShiroUser;
import com.dlz.framework.util.StringUtils;

public class BaseController extends PageDealCommonLogic {
	private static Logger logger = LoggerFactory.getLogger(BaseController.class);
	public static String SESSION_MEMEBER = "member";
	public static String SESSION_MEMBER_HEADINFO = "memberHeadInfo";
	public static String SESSION_USER_LIMIT = "userLimit";
	@Autowired
	protected MenuDataOptService menuDataOptService;

	@Autowired
	protected ICommService commService;

	protected boolean hasRole(String role) {
		return SecurityUtils.getSubject().hasRole(role);
	}

	/**
	 * 
	 * @param request
	 * @param cloName_user_id
	 * @return
	 */
	protected ShiroUser getUserWithMenuDataOpt(HttpServletRequest request) {
		ShiroUser loginUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		try {
			String mid = request.getParameter("menu_id");
			if (mid != null) {
				MenuDataOpt m = (MenuDataOpt)request.getSession().getAttribute("MenuDataOpt"+mid);
				if (m == null) {
					m = menuDataOptService.selectByPrimaryKey(Long.valueOf(mid));
					request.getSession().setAttribute("MenuDataOpt" + mid, m);
				}
				if (m != null) {
					logger.debug("menu_id=" + mid);
					String roleOpt = m.getExt1().replaceAll(":userId", String.valueOf(loginUser.getUserId()));
					String[] roleids = m.getRoleIds().replaceAll(" ", "").split(",");
					Set<String> strSet = new HashSet<String>(Arrays.asList(roleids));
					for (Long role : loginUser.getRoleList()) {
						if (strSet.contains(String.valueOf(role))) {
							roleOpt = null;
							break;
						}
					}
					if(StringUtils.isNotEmpty(m.getCurrentStatus())){
						request.setAttribute("search_condition_status", m.getCurrentStatus());
					}
					if(StringUtils.isNotEmpty(roleOpt)){
						request.setAttribute("search_condition_userrole", "(" + roleOpt + ")");
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return loginUser;
	}

	
	/** 基于@ExceptionHandler异常处理 */
	@ExceptionHandler
	public String exp(HttpServletRequest request, Exception ex) {
		// 根据不同错误转向不同页面
		if (ex instanceof DbException) {
			request.setAttribute("ex", ex);
			logger.error(ex.getMessage(), ex);
			return "error/error";
		}
		if (ex instanceof TypeMismatchException) {
			ex = JspException.buildJspException(ex.getMessage(), ex);
			request.setAttribute("ex", ex);
			logger.error(ex.getMessage(), ex);
			return "error/error";
		} else {
			ex = new BaseException( ex.getMessage(),6001, ex);
			request.setAttribute("ex", ex);
			logger.error(ex.getMessage(), ex);
			return "error/error";
		}
	}

}
