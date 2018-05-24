package com.dlz.web.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.dlz.app.sys.bean.AuthUser;
import com.dlz.framework.annotation.AnnoAuth;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.exception.LogicException;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.system.Reflections;
import com.dlz.web.holder.ThreadHolder;
import com.dlz.web.inf.IApiAjax;

public class AjaxApiUtil {
	protected static final MyLogger logger = MyLogger.getLogger(AjaxApiUtil.class);
	
	public static JSONResult doAjax(JSONMap data, String ui, String aType,IApiAjax ajaxApi) {
		AuthUser member = ajaxApi.getAuthInfo();
		JSONResult m = JSONResult.createResult();
		if (member == null) {
			member = ajaxApi.autoLogin(ui, m);
		}
		if ("nobind".equals(m.getMsg())) {
			return m;
		}
		m.putAll(doAjax(data,null, ui, aType, member,ajaxApi.getChannel(),ajaxApi.getLogger()));
		if(data.getInt("needUserInfo")!=null){
			JSONMap loginInfo = ajaxApi.getClientUserInfo();
			if(loginInfo!=null){
				m.put("loginInfo", loginInfo);
			}
		}
		return m;
	}
	
	public static JSONResult doAjax(String data, String ui, String aType,IApiAjax ajaxApi) {
		AuthUser member = ajaxApi.getAuthInfo();
		JSONResult m = JSONResult.createResult();
		if (member == null) {
			member = ajaxApi.autoLogin(ui, m);
		}
		if ("nobind".equals(m.getMsg())) {
			return m;
		}
		JSONMap para = new JSONMap(data);
		m.putAll(doAjax(para,null, ui, aType, member,ajaxApi.getChannel(),ajaxApi.getLogger()));
		if(para.getInt("needUserInfo")!=null){
			JSONMap loginInfo = ajaxApi.getClientUserInfo();
			if(loginInfo!=null){
				m.put("loginInfo", loginInfo);
			}
		}
		return m;
	}
	
	public static JSONResult doAjax(String data, String ui, String aType,Page<?> page,IApiAjax ajaxApi) {
		AuthUser member = ajaxApi.getAuthInfo();
		JSONResult m = JSONResult.createResult();
		if (member == null) {
			member = ajaxApi.autoLogin(ui, m);
		}
		if ("nobind".equals(m.getMsg())) {
			return m;
		}
		JSONMap para = new JSONMap(data);
		m.putAll(doAjax(para,page, ui, aType, member,ajaxApi.getChannel(),ajaxApi.getLogger()));
		if(para.getInt("needUserInfo")!=null){
			JSONMap loginInfo = ajaxApi.getClientUserInfo();
			if(loginInfo!=null){
				m.put("loginInfo", loginInfo);
			}
		}
		return m;
	}

	public static JSONResult doAjaxs(String data, String ui,IApiAjax ajaxApi) {
		AuthUser member = ThreadHolder.getAuthInfo();
		JSONResult m = JSONResult.createResult();
		if (member == null) {
			member = ajaxApi.autoLogin(ui, m);
		}
		if ("nobind".equals(m.getMsg())) {
			return m;
		}
		JSONMap para = new JSONMap(data);
		m.putAll(AjaxApiUtil.doAjaxs(data, ui, member,ajaxApi.getChannel(),ajaxApi.getLogger()));
		if(para.getInt("needUserInfo")!=null){
			JSONMap loginInfo = ajaxApi.getClientUserInfo();
			if(loginInfo!=null){
				m.put("loginInfo", loginInfo);
			}
		}
		return m;
	}

	public static JSONResult doAjax(JSONMap datas, String ui, String aType, AuthUser member,String doType) {
		return doAjax(datas,null, ui, aType, member,doType, logger);
	}
	private static JSONResult doAjax(JSONMap datas,Page<?> page, String ui, String aType, AuthUser member, String doType, MyLogger logger) {
		JSONResult m = JSONResult.createResult();
		String[] deals = aType.split("_");
		String methodStr = "done";
		if (deals.length > 1) {
			methodStr = deals[1];
		}
		Object apiLogic = null;
		try {
			apiLogic = SpringHolder.getBean(deals[0] + "ApiLogic");
			if (apiLogic == null) {
				logger.error("地址有误【" + deals[0] + "】bean未定义");
				m.addErr(-3, "陌生的地方：【" + aType + "】");
				return m;
			}
			int methodType = 0;
			Method method = Reflections.getMethod(apiLogic, methodStr, JSONMap.class);
			if (method != null) {
				methodType = 1;
			}
			// 兼容老的api方法 doXx(HttpServletRequest,Page,Map)
			if (method == null) {
				method = Reflections.getMethod(apiLogic, methodStr,HttpServletRequest.class, Page.class, JSONMap.class);
				if (method != null) {
					methodType = 2;
				}
			}

			if (method == null) {
				throw new NoSuchMethodException();
			}

			// 优先使用方法中定义的权限
			AnnoAuth annotation = method.getAnnotation(AnnoAuth.class);
			if (annotation == null) {
				// 方法中未定义权限则使用类上注解的权限
				annotation = apiLogic.getClass().getAnnotation(AnnoAuth.class);
			}
			// 判断权限
			if (!AnnoAuthUtil.dealAnnoResult(annotation, member, m)) {
				return m;
			}

			if (methodType == 1) {
				m.addData(method.invoke(apiLogic, datas));
			} else if (methodType == 2) {
				m.addData(method.invoke(apiLogic,ThreadHolder.getHttpRequest(), page, datas));
//				m.addData(method.invoke(pds, ParaUtil.getPage(ThreadHolder.getHttpRequest(), datas), datas));
			}
		} catch (NoSuchBeanDefinitionException e) {
			logger.error("地址有误【" + aType + "】bean未定义");
			m.addErr(-4, "陌生的地方:【" + aType + "】");
		} catch (NoSuchMethodException e) {
			logger.error("地址有误【" + aType + "】方法定义有误");
			m.addErr(-5, "陌生的地方：【" + aType + "】");
		} catch (IllegalArgumentException e) {
			logger.error(apiLogic.getClass().getSimpleName() + "." + methodStr + "的参数有误！");
			m.addErr(-6, "陌生的地方：【" + aType + "】");
		} catch (IllegalAccessException e) {
			logger.error(apiLogic.getClass().getSimpleName() + "." + methodStr + "的参数作用域有误！");
			m.addErr(-7, "陌生的地方：【" + aType + "】");
		} catch (InvocationTargetException e) {
			Throwable te = e.getTargetException();
			if (te instanceof LogicException) {
				logger.warn(te.getMessage());
				m.addErr(-90, ((LogicException) te).getErrorInfo());
			} else {
				logger.error("业务异常【" + apiLogic.getClass().getSimpleName() + "." + methodStr + "】");
				logger.error("datas:" + datas.toString());
				logger.error(te.getMessage(), e);
				m.addErr(-91, "系统开小差了，休息一会再回来！");
			}
		} catch (Exception e) {
			logger.error("业务异常【" + apiLogic.getClass().getSimpleName() + "." + methodStr + "】");
			logger.error("datas:" + datas.toString());
			logger.error(e.getMessage(), e);
			m.addErr(-99, "系统开小差了，休息一会再回来！");
		} finally {
			if (member != null) {
				logger.info(getInfo(logger, doType + " " + aType, member.getId(), member.getL_id(), datas, m, ui));
			} else {
				logger.info(getInfo(logger, doType + " " + aType, datas, m, ui));
			}
		}
		return m;
	}

	private static JSONResult doAjaxs(String data, String ui, AuthUser member, String doType, MyLogger logger) {
		JSONResult m = JSONResult.createResult();
		JSONMap datas = JSONMap.createJsonMap(data);
		Object[] urls = (Object[]) datas.getArray("urls");
		Object[] datases = datas.getArray("datases");
		if (urls == null || datases == null || urls.length != datases.length) {
			logger.error("参数有误:urls和dataes长度必须相等[" + data + "]");
			return m.addErr("参数有误:urls和dataes长度必须相等");
		}
		Object[] returns = new Object[urls.length];
		for (int i = 0; i < urls.length; i++) {
			JSONResult r = doAjax(JSONMap.createJsonMap(datases[i]),null, ui, (String) urls[i], member, doType, logger);
			if (r.isError()) {
				return r;
			} else {
				returns[i] = r.get("data");
			}
		}
		m.put("datas", returns);
		return m;
	}

	private static String getInfo(MyLogger logger, String title, Object... info) {
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
