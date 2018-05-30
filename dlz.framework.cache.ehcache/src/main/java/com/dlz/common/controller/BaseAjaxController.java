package com.dlz.common.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.dlz.common.bean.AuthUser;
import com.dlz.common.logic.interfaces.IWapLogic;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.exception.LogicException;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.holder.ThreadHolder;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.system.Reflections;

public abstract class BaseAjaxController{
	protected MyLogger logger = MyLogger.getLogger(this.getClass());
	/**
	 * 根据传递的参数自动登录
	 * @param member
	 * @param para
	 * @param uimap
	 * @param m
	 * @return
	 */
	protected AuthUser autoLogin(AuthUser member,JSONMap para, JSONMap uimap, JSONResult m){
		return null;
	};
	//取得用户登录信息
	protected <T extends AuthUser> T getAuthInfo(){
		return ThreadHolder.getAuthInfo();
	};
	protected abstract JSONMap getClientUserInfo();//取得客户端用户信息
	//取得渠道信息
	protected abstract String getChannel();
	//执行兼容api
	protected Method doCompatibilityApiLogic(IWapLogic pds,JSONMap para,String methodStr,JSONResult m) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return null;
	};

	public JSONResult doAjax(String data, String ui,String aType) {
		AuthUser member = getAuthInfo();
		JSONMap para = new JSONMap(data);
		JSONResult m = JSONResult.createResult();
		if(member==null){
			member=autoLogin(member,para, new JSONMap(ui),m);
		}
		if("nobind".equals(m.getMsg())){
			return m;
		}
		m.putAll(doAjax(para, ui, aType, member));
		return m;
	}
	public JSONResult doAjaxs(String data, String ui) {
		AuthUser member = ThreadHolder.getAuthInfo();
		JSONMap para = new JSONMap(data);
		JSONResult m = JSONResult.createResult();
		if(member==null){
			member=autoLogin(member,para, new JSONMap(ui),m);
		}
		if("nobind".equals(m.getMsg())){
			return m;
		}
		m.putAll(doAjaxs(data, ui, member));
		return m;
	}
	
	private JSONResult doAjaxs(String data, String ui,AuthUser member) {
		JSONResult m = JSONResult.createResult();
		JSONMap datas = JSONMap.createJsonMap(data);
		Object[] urls=(Object[])datas.getArray("urls");
		Object[] datases=datas.getArray("datases");
		if(urls==null||datases==null||urls.length!=datases.length){
			logger.error("参数有误:urls和dataes长度必须相等["+data+"]");
			return m.addErr("参数有误:urls和dataes长度必须相等");
		}
		Object[] returns=new Object[urls.length];
		for(int i=0;i<urls.length;i++){
			JSONResult r=doAjax(JSONMap.createJsonMap(datases[i]), ui, (String)urls[i], member);
			if(r.isError()){
				return r;
			}else{
				returns[i]=r.get("data");
			}
		}
		m.put("datas", returns);
		return m;
	}
	
	private JSONResult doAjax(JSONMap para, String ui,String aType,AuthUser member) {
		JSONResult m = JSONResult.createResult();
		String[] deals = aType.split("_");
		String methodStr = "done";
		if (deals.length > 1) {
			methodStr = deals[1];
		}
		IWapLogic pds = (IWapLogic)SpringHolder.getBean(deals[0]+"ApiLogic");
		try {
			if(pds==null){
				logger.error("地址有误【" + deals[0] + "】bean未定义");
				m.addErr(-2, "陌生的地方：【" + aType + "】");
				return m;
			}
			if(member==null && pds.needAuth()&&!"member_login".equals(aType)){
				m.addErr(0, "未登录");
				return m;
			}
			Method method = Reflections.getMethod(pds, methodStr,JSONMap.class);
			if(method!=null){
				m.addData(method.invoke(pds, para));
			}
			
			//兼容老的api方法 doXx(HttpServletRequest,Page,Map)
			if(method==null){
				method = doCompatibilityApiLogic(pds, para, methodStr, m);
			}
			
			if(method==null){
				throw new NoSuchMethodException();
			}
					
			if(para.getInt("needUserInfo")!=null){
				JSONMap loginInfo = getClientUserInfo();
				if(loginInfo!=null){
					m.put("loginInfo", loginInfo);
				}
			}
		} catch (NoSuchBeanDefinitionException e) {
			logger.error("地址有误【" + aType + "】bean未定义");
			m.addErr(-2, "您发现了一个神秘的地方→【" + aType + "】");
		} catch (NoSuchMethodException e) {
			logger.error("地址有误【" + aType + "】方法定义有误");
			m.addErr(-2, "陌生的地方：【" + aType + "】");
		} catch (IllegalArgumentException e) {
			logger.error(pds.getClass().getSimpleName() + "." + methodStr + "的参数有误！");
			m.addErr(-3, "陌生的地方：【" + aType + "】");
		} catch (IllegalAccessException e) {
			logger.error(pds.getClass().getSimpleName() + "." + methodStr + "的参数作用域有误！");
			m.addErr(-4, "陌生的地方：【" + aType + "】");
		} catch (InvocationTargetException e) {
			Throwable  te=e.getTargetException();
			if(te instanceof LogicException) {
				logger.warn(te.getMessage());
				m.addErr(-90, te.getMessage());
			}else{
				logger.error("业务异常【" + pds.getClass().getSimpleName() + "." + methodStr + "】");
				logger.error("datas:" + para.toString());
				logger.error(te.getMessage(), e);
				m.addErr(-91, "系统开小差了，休息一会再回来！");
			}
		} catch (Exception e) {
			logger.error("业务异常【" + pds.getClass().getSimpleName() + "." + methodStr + "】");
			logger.error("datas:" + para.toString());
			logger.error(e.getMessage(), e);
			m.addErr(-99, "系统开小差了，休息一会再回来！");
		} finally {
			if (member != null) {
				logger.info(getInfo(getChannel()+" "+aType, member.getId(), member.getL_id(), para, m, ui));
			} else {
				logger.info(getInfo(getChannel()+" "+aType, para, m, ui));
			}
		}
		return m;
	}
	
	private String getInfo(String title,Object... info){
		try {
			if(info==null){
				return title;
			}
			if(info.length==1){
				return title+":"+JacksonUtil.getJson(info[0]);
			}
			return title+":"+JacksonUtil.getJson(info);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return title+":"+info;
	}
}
