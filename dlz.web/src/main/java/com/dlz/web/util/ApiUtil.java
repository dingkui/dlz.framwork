package com.dlz.web.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.dlz.app.uim.bean.AuthUser;
import com.dlz.app.uim.holder.UserHolder;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.exception.LogicException;
import com.dlz.framework.holder.SpringHolder;
import org.slf4j.Logger;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.ValUtil;
import com.dlz.framework.util.system.Reflections;
import com.dlz.web.holder.ThreadHolder;
import com.dlz.web.inf.IApiAjax;

public class ApiUtil {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	protected static final Logger logger = org.slf4j.LoggerFactory.getLogger(ApiUtil.class);
	
	public static JSONResult doApiLogic(JSONMap data, String ui, String aType,Page<?> page,IApiAjax ajaxApi) {
		JSONResult m = JSONResult.createResult();
		AuthUser member = ajaxApi.autoLogin(ui, m);
		if ("nobind".equals(m.getMsg())) {
			return m;
		}
		m.putAll(doApiLogic(data,page, ui, aType, member,ajaxApi.getChannel()));
		if(data.getInt("needUserInfo")!=null){
			JSONMap loginInfo = ajaxApi.getClientUserInfo();
			if(loginInfo!=null){
				m.put("loginInfo", loginInfo);
			}
		}
		return m;
	}
	public static JSONResult doApiLogic(JSONMap data, String ui, String aType,IApiAjax ajaxApi) {
		return doApiLogic(data, ui, aType,null, ajaxApi);
	}
	public static JSONResult doApiLogic(String data, String ui, String aType,IApiAjax ajaxApi) {
		return doApiLogic(new JSONMap(data), ui, aType,null, ajaxApi);
	}
	public static JSONResult doApiLogic(String data, String ui, String aType,Page<?> page,IApiAjax ajaxApi) {
		return doApiLogic(new JSONMap(data), ui, aType,page, ajaxApi);
	}
	public static JSONResult doApiLogic(JSONMap datas, String aType, String doType) {
		return doApiLogic(datas,null, null, aType, UserHolder.getAuthInfo(),doType);
	}

	public static JSONResult doApiLogics(String data, String ui,IApiAjax ajaxApi) {
		AuthUser member = UserHolder.getAuthInfo();
		JSONResult m = JSONResult.createResult();
		if (member == null) {
			member = ajaxApi.autoLogin(ui, m);
		}
		if ("nobind".equals(m.getMsg())) {
			return m;
		}

		JSONMap datas = new JSONMap(data);
		Object[] urls = (Object[]) datas.getArray("urls");
		Object[] datases = datas.getArray("datases");
		if (urls == null || datases == null || urls.length != datases.length) {
			logger.error("参数有误:urls和dataes长度必须相等[" + data + "]");
			return m.addErr("参数有误:urls和dataes长度必须相等");
		}
		Object[] returns = new Object[urls.length];
		for (int i = 0; i < urls.length; i++) {
			JSONResult r = doApiLogic(JSONMap.createJsonMap(datases[i]),null, ui, (String) urls[i], member, ajaxApi.getChannel());
			if (r.isError()) {
				return r;
			} else {
				returns[i] = r.get("data");
			}
		}
		m.put("datas", returns);
		
		if(datas.getInt("needUserInfo")!=null){
			JSONMap loginInfo = ajaxApi.getClientUserInfo();
			if(loginInfo!=null){
				m.put("loginInfo", loginInfo);
			}
		}
		return m;
	}

	private static JSONResult doApiLogic(JSONMap datas,Page<?> page, String ui, String aType, AuthUser member, String doType) {
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

			// 判断权限
			if (!AnnoAuthUtil.doneAnno(apiLogic.getClass(),method, member, m)) {
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
			if(logger.isInfoEnabled()){
				if (member != null) {
						logger.info(getInfo(doType + " " + aType, member.getId(), member.getL_id(), datas, m, ui));
				} else {
					logger.info(getInfo(doType + " " + aType, datas, m, ui));
				}
			}
		}
		return m;
	}
	
	public static Object doService(Object service, String methodName, String doType) {
		Object result=null;
		Object[] paras=null;
		String err=null;
		AuthUser member=UserHolder.getAuthInfo();
		try {
			Method method = Reflections.getMethodByName(service, methodName);
			if (method == null) {
				err=WebUtil.renderErr(HttpStatus.SC_NOT_FOUND,service,methodName, "方法不存在");
				return null;
			}
			// 判断访问权限
			int doneAnnoResult = AnnoAuthUtil.doneAnno(service.getClass(),method, member);
			if (doneAnnoResult<0) {
				err=WebUtil.renderErr(HttpStatus.SC_FORBIDDEN,service,methodName, "没有访问权限："+doneAnnoResult);
				return null;
			}
			Parameter[] parameters = method.getParameters();
			HttpServletRequest request=WebUtil.getRequest(); 
			paras = new Object[parameters.length];
			for (int i = 0; i < parameters.length; i++) {
				paras[i] = ValUtil.getObj(request.getParameter(parameters[i].getName()), parameters[i].getType());
			}
			result=method.invoke(service,  paras);
		} catch (IllegalArgumentException e) {
			logger.error(err,e);
			err=WebUtil.renderErr(HttpStatus.SC_METHOD_NOT_ALLOWED,service,methodName, "参数有误:"+e.getMessage());
		} catch (IllegalAccessException e) {
			logger.error(err,e);
			err=WebUtil.renderErr(HttpStatus.SC_NOT_ACCEPTABLE,service,methodName, "参数作用域有误！");
		} catch (InvocationTargetException e) {
			Throwable te = e.getTargetException();
			if (te instanceof LogicException) {
				LogicException logicException = (LogicException) te;
				String errorCode = logicException.getErrorCode();
				logger.warn(err,e);
				err=WebUtil.renderErr(Integer.parseInt(errorCode.substring(1)),service,methodName, logicException.getErrorInfo());
			} else {
				logger.error(err,e);
				err=WebUtil.renderErr(HttpStatus.SC_INTERNAL_SERVER_ERROR,service,methodName, te.getMessage());
			}
		} catch (Exception e) {
			logger.error(err,e);
			err=WebUtil.renderErr(HttpStatus.SC_INTERNAL_SERVER_ERROR,service,methodName, e.getMessage());
		} finally {
			if (member != null) {
				if(err!=null){
					logger.info(getInfo(doType, member.getId(), member.getL_id(), paras, result));
				}else{
					logger.info(getInfo(doType, member.getId(), member.getL_id(), paras, err));
				}
			} else {
				if(err!=null){
					logger.info(getInfo(doType, paras, result));
				}else{
					logger.info(getInfo(doType, paras, err));
				}
			}
		}
		return null;
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
