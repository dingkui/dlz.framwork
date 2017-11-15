package com.dlz.framework.ssme.base.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dlz.apps.sys.cache.MenuCache;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.DbInfo;
import com.dlz.framework.db.enums.DateFormatEnum;
import com.dlz.framework.db.exception.DbException;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.ParaMap;
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
	protected MenuCache menuCahe;

	@Autowired
	protected ICommService commService;

	protected boolean hasRole(String role) {
		return SecurityUtils.getSubject().hasRole(role);
	}
	
	public void addSearchParaFromRequest(Map<String,String[]> paraMeterMap,ParaMap pm){
		for(Map.Entry<String,String[]> entry:paraMeterMap.entrySet()){
			String key=entry.getKey();
			if(!key.startsWith("search_")){
				continue;
			}else{
				key=key.substring(7);
				if(entry.getValue().length==1) {
					pm.addPara(key, entry.getValue()[0]);
				}else{
					pm.addPara(key, entry.getValue());
				}
			}
		}
	}
	public void addConverFromRequest(Map<String,String[]> paraMeterMap,ParaMap pm){
		for(Map.Entry<String,String[]> entry:paraMeterMap.entrySet()){
			String key=entry.getKey();
			if(entry.getValue().length!=1 || !key.startsWith("conver_")){
				continue;
			}else{
				String[] keys=key.split("_");
				String val=entry.getValue()[0];
				switch(keys[1]){
					case "dict":
						pm.getConvert().addDictConvert(keys[2], val);
						break;
					case "date":
						if("all".equals(keys[2])){
							pm.getConvert().addDateConvert(DateFormatEnum.valueOf(val));
						}else{
							pm.getConvert().addDateConvert(keys[2],DateFormatEnum.valueOf(val));
						}
						break;
				}
			}
		}
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
	
	/**
	 * 数据权限控制：需要使用paraMap方式查询数据，sql的结尾需要加上 ${_flow}
	 * flow:
	 * {
	 * role:"1,2,3",//有数据访问权限的角色
	 * sta："sta=1",//数据状态sql
	 * adminRole："1,2,3",//数据管理员角色
	 * dept："dept=:dept and pdept:=lastDeptId",//数据部门归属sql
	 * maRole："1,2,3",//部门主管角色，不判断用户角色
	 * user:"createUserId=:userId or sssUserId=:userId"//数据操作人 一般用户操作过数据就会有数据查看权限
	 * }
	 * @param request
	 * @param cloName_user_id
	 * @return
	 */
	protected String dealFlow(HttpServletRequest request,BaseParaMap pm) {
		ShiroUser loginUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		try {
			String mid = request.getParameter("menu_id");
			if (mid != null) {
				JSONMap flow = new JSONMap(menuCahe.get(Long.valueOf(mid)).getfFlow()); 
				if(!flow.isEmpty()){
					StringBuffer sql=new StringBuffer();
					
					//判断数据权限（未设置则不判断，设置了但角色不存在则无数据权限）
					if(!checkRole(flow.getStr("role",""), loginUser.getRoleList())){
						sql.append(" and 1=0 ");
					}else{
						String sta=flow.getStr("sta","");
						if(!"".equals(sta)){
							sql.append(" and (").append(sta).append(")");
						}
						
						//判断是否有数据管理员权限，有管理员权限则不做其他权限判断，未设置则表示无管理员权限配置
						boolean hasadminRole=checkRole(flow.getStr("adminRole",""), loginUser.getRoleList()) ;
						if(!hasadminRole){
							String deptId=String.valueOf(loginUser.getDept().getdId());
							String lastDeptId=String.valueOf(loginUser.getDept().getdFid());
							String deptOpt = flow.getStr("dept","").replaceAll(":deptId", deptId).replaceAll(":lastDeptId", lastDeptId);
							if(!"".equals(deptOpt)){
								sql.append(" and (").append(deptOpt).append(")");
							}
							
							//判断是否有部门经理权限，有部门经理员权限则不做其他权限判断
							boolean hasMaRole=checkRole(flow.getStr("maRole",""), loginUser.getRoleList()) ;
							if(!hasMaRole){
								String userId=String.valueOf(loginUser.getUserId());
								String userOpt = flow.getStr("user","").replaceAll(":userId", userId);
								if(!"".equals(userOpt)){
									sql.append(" and (").append(userOpt).append(")");
								}
							}
						}
					}
					String sqlKey=flow.getStr("sql");
					if(sqlKey!=null){
						pm.setSqlInput(sqlKey);
					}
					if(sql.length()>0){
						pm.addPara("_flow", sql);
						DbInfo.appendInfoToSql(pm.getSqlInput(), "${_flow}");
					}
					return flow.getStr("page");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 判断是否有角色权限
	 * @param sets
	 * @param userroles
	 * @param nosetPass 无设置时是否通过
	 * @return
	 */
	private boolean checkRole(String sets,List<Long> userroles){
		if("".equals(sets)){
			return false;
		}
		String[] strSet = sets.replaceAll(" ", "").split(",");
		for (String str : strSet) {
			if (userroles.contains(Long.valueOf(str))) {
				return true;
			}
		}
		return false;
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
