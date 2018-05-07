package com.dlz.common.util.api;

import java.lang.reflect.Method;

import com.dlz.common.bean.AuthUser;
import com.dlz.common.util.config.ConfUtil;
import com.dlz.framework.annotation.AnnoAuth;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.holder.ThreadHolder;
import com.dlz.framework.logger.MyLogger;

public class AnnoAuthUtil {
	protected static final MyLogger logger = MyLogger.getLogger(AnnoAuthUtil.class);
	/**
	 * @param needRole
	 * @return 0 未设置权限 -1 未登录 -2 设置权限未通过 1 设置权限通过 2 设置权限通过
	 */
	public static int doneAnno(AnnoAuth needRole, AuthUser member) {
		if (needRole == null) {
			return 0;
		}
		if (member == null) {
			return -1;
		}
		String value = needRole.value();
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
	private static boolean dealAnnoResult(AnnoAuth needRole, AuthUser member, JSONResult m) {
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
}
