package com.dlz.jf.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.dlz.common.bean.AuthUser;
import com.dlz.common.logic.interfaces.IAppLogic;
import com.dlz.framework.annotation.AnnoAuth;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.exception.LogicException;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.holder.ThreadHolder;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.JacksonUtil;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
public abstract class BaseAjaxController extends Controller{
	protected MyLogger logger = MyLogger.getLogger(this.getClass());
	protected abstract AuthUser autoLogin(AuthUser member,JSONMap para, JSONMap uimap, JSONResult m);
	private boolean doneAnno(Method method,AuthUser member,JSONMap para,JSONResult m){
		AnnoAuth needRole=method.getAnnotation(AnnoAuth.class);
		if(needRole!=null){
			if(member==null){
				m.addErr("未登录");
				return false;
			} else {
				if(!"*".equals(needRole.value())){
					String[] roles=needRole.value().split(",");
					boolean hasRole=false;
					for(String r:roles){
						if(member.hasRole(PropKit.get("auth.role."+r))){
							hasRole=true;
							break;
						}
					}
					if(!hasRole){
						m.addErr(-2,"无操作权限["+needRole.value()+"]");
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public Map<String, Object> doAjax(String data, String ui,String aType) {
		AuthUser member = ThreadHolder.getAuthInfo();
		JSONMap para = new JSONMap(data);
		JSONResult m = JSONResult.createResult();
		if(member==null){
			member=autoLogin(member,para, new JSONMap(ui),m);
		}
		return doAjax(para, ui, aType, member);
	}
	
	public JSONResult doAjaxs(String data, String ui) {
		AuthUser member = ThreadHolder.getAuthInfo();
		JSONMap para = new JSONMap(data);
		JSONResult m = JSONResult.createResult();
		if(member==null){
			member=autoLogin(member,para, new JSONMap(ui),m);
		}
		return doAjaxs(data, ui, member);
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
				returns[i]=r;
				m.addErr(r.getFlag(), r.getMsg());
			}else{
				returns[i]=r.get("data");
			}
		}
		m.put("datas", returns);
		return m;
	}
	
	private JSONResult doAjax(JSONMap datas, String ui,String aType,AuthUser member) {
		JSONResult m = JSONResult.createResult();
		String[] deals = aType.split("_");
		String methodStr = "done";
		if (deals.length > 1) {
			methodStr = deals[1];
		}
		IAppLogic pds=null;
		try {
			pds = SpringHolder.getBean(deals[0]+"ApiLogic");
			if(pds==null){
				logger.error("地址有误【" + deals[0] + "】bean未定义");
				m.addErr(-2, "陌生的地方：【" + aType + "】");
				return m;
			}
			if(member==null && pds.needAuth()){
				m.addErr(0, "未登录");
				return m;
			}
			Method method = pds.getClass().getMethod(methodStr, JSONMap.class);
			if(!doneAnno(method,member, datas,m)){
				return m;
			}
			m.addData(method.invoke(pds, datas));
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
				logger.info(getInfo(aType, member.getId(), member.getL_id(), datas, m, ui));
			} else {
				logger.info(getInfo(aType, datas, m, ui));
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
