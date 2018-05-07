package com.dlz.common.util.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.dlz.common.bean.AuthUser;
import com.dlz.common.logic.interfaces.IAppLogic;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.exception.LogicException;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.JacksonUtil;

public class AjaxApiUtil {
	protected static final MyLogger logger = MyLogger.getLogger(AjaxApiUtil.class);

	public static JSONResult doAjax(JSONMap datas, String ui, String aType, AuthUser member,String doType) {
		return doAjax(datas, ui, aType, member,doType, logger);
	}
	public static JSONResult doAjax(JSONMap datas, String ui, String aType, AuthUser member,String doType,MyLogger logger) {
		JSONResult m = JSONResult.createResult();
		String[] deals = aType.split("_");
		String methodStr = "done";
		if (deals.length > 1) {
			methodStr = deals[1];
		}
		IAppLogic pds = null;
		try {
			pds = SpringHolder.getBean(deals[0] + "ApiLogic");
			if (pds == null) {
				logger.error("地址有误【" + deals[0] + "】bean未定义");
				m.addErr(-3, "陌生的地方：【" + aType + "】");
				return m;
			}
			if (member == null && pds.needAuth()) {
				m.addErr(0, "未登录");
				return m;
			}
			Method method = pds.getClass().getMethod(methodStr, JSONMap.class);
			if (!AnnoAuthUtil.doneAnno(pds.getClass(),method, member, m)) {
				return m;
			}
			m=(JSONResult)method.invoke(pds, datas);
		} catch (NoSuchBeanDefinitionException e) {
			logger.error("地址有误【" + aType + "】bean未定义");
			m.addErr(-4, "陌生的地方:【" + aType + "】");
		} catch (NoSuchMethodException e) {
			logger.error("地址有误【" + aType + "】方法定义有误");
			m.addErr(-5, "陌生的地方：【" + aType + "】");
		} catch (IllegalArgumentException e) {
			logger.error(pds.getClass().getSimpleName() + "." + methodStr + "的参数有误！");
			m.addErr(-6, "陌生的地方：【" + aType + "】");
		} catch (IllegalAccessException e) {
			logger.error(pds.getClass().getSimpleName() + "." + methodStr + "的参数作用域有误！");
			m.addErr(-7, "陌生的地方：【" + aType + "】");
		} catch (InvocationTargetException e) {
			Throwable te = e.getTargetException();
			if (te instanceof LogicException) {
				logger.warn(te.getMessage());
				m.addErr(-90, te.getMessage());
			} else {
				logger.error("业务异常【" + pds.getClass().getSimpleName() + "." + methodStr + "】");
				logger.error("datas:" + datas.toString());
				logger.error(te.getMessage(), e);
				m.addErr(-91, "系统开小差了，休息一会再回来！");
			}
		} catch (Exception e) {
			logger.error("业务异常【" + pds.getClass().getSimpleName() + "." + methodStr + "】");
			logger.error("datas:" + datas.toString());
			logger.error(e.getMessage(), e);
			m.addErr(-99, "系统开小差了，休息一会再回来！");
		} finally {
			if (member != null) {
				logger.info(getInfo(doType+" "+aType, member.getId(), member.getL_id(), datas, m, ui));
			} else {
				logger.info(getInfo(doType+" "+aType, datas, m, ui));
			}
		}
		return m;
	}

	private static String getInfo(String title, Object... info) {
		try {
			if (info == null) {
				return title;
			}
			if (info.length == 1) {
				return title + ":" + JacksonUtil.getJson(info[0]);
			}
			return title + ":" + JacksonUtil.getJson(info);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return title + ":" + info;
	}

}
