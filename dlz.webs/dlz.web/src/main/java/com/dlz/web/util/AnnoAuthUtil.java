package com.dlz.web.util;

import java.lang.reflect.Method;

import com.dlz.app.uim.annotation.AnnoAuth;
import com.dlz.app.uim.bean.AuthUser;
import com.dlz.web.bean.JSONResult;
import org.slf4j.Logger;
import com.dlz.comm.util.config.ConfUtil;
import com.dlz.web.holder.ThreadHolder;

public class AnnoAuthUtil {

	protected static final Logger logger = org.slf4j.LoggerFactory.getLogger(AnnoAuthUtil.class);
	/**
	 * @param needRole
	 * @return 0 不需要登录权限 -1 未登录 -2 设置权限未通过 1 需要登录且已登录 2 权限通过
	 */
	public static int doneAnno(AnnoAuth needRole, AuthUser member) {
		if (needRole == null) {
			return 0;
		}
		String value = needRole.value();
		//AnnoAuth设置成N时不做权限判断
		if ("N".equals(value)) {
			return 0;
		}
		if (member == null) {
			return -1;
		}
		if ("*".equals(value)) {
			return 1;
		}
		String[] roles = value.split(",");
		for (String r : roles) {
			if (member.hasRole(ConfUtil.getConfig("auth.role." + r))) {
				return 2;
			}
		}
		if (logger.isWarnEnabled()) {
			logger.warn("访问权限限制：url={},needAuth={},member={},memberrole={}", ThreadHolder.getHttpRequest().getRequestURI(), value, member.getId(),
					member.getRoles());
		}
		return -2;
	}

	/**
	 * @param needRole
	 * @return 0 未设置权限 -1 未登录 -2 设置权限未通过 1 设置权限通过 2 设置权限通过
	 */
	public static boolean dealAnnoResult(AnnoAuth needRole, AuthUser member, JSONResult m) {
		int doneAnno = doneAnno(needRole, member);
		if (doneAnno >= 0) {
			return true;
		}
		if (doneAnno == -1) {
			m.addErr(0,"未登录");
		} else {
			m.addErr(-2, "无操作权限[" + needRole.value() + "]");
		}
		return false;
	}

	public static boolean doneAnno(Class<?> clas, Method method, AuthUser member, JSONResult m) {
		AnnoAuth annotation = method.getAnnotation(AnnoAuth.class);
		if(annotation==null){
			annotation=clas.getAnnotation(AnnoAuth.class);
		}
		return dealAnnoResult(annotation, member, m);
	}
	
	public static int doneAnno(Class<?> clas, Method method, AuthUser member) {
		AnnoAuth annotation = method.getAnnotation(AnnoAuth.class);
		if(annotation==null){
			annotation=clas.getAnnotation(AnnoAuth.class);
		}
		return doneAnno(annotation, member);
	}
}
