package com.dlz.apps.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlz.app.sys.bean.AuthUser;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.logger.MyLogger;
import com.dlz.web.inf.IApiAjax;
import com.dlz.web.util.AjaxApiUtil;
@Controller
@RequestMapping("")
public class AjaxController implements IApiAjax{
	private static MyLogger logger = MyLogger.getLogger(AjaxController.class);
	
	@RequestMapping(value = "/api_web/m")
	@ResponseBody
	@Override
	public JSONResult doAjaxs(String data, String ui) {
		return IApiAjax.super.doAjaxs(data,ui);
	}
	
	@RequestMapping(value = "/api_web/{aType}")
	@ResponseBody
	public JSONResult apidoAjax(HttpServletRequest request,String data,String ui, Page<?> page, @PathVariable(value = "aType")String aType) {
		return AjaxApiUtil.doAjax(data, ui, aType,page, this);
	}
	@RequestMapping(value = "/doAjax/{aType}")
	@ResponseBody
	public JSONResult doAjax(HttpServletRequest request,String data,String ui, Page<?> page, @PathVariable(value = "aType")String aType) {
		return apidoAjax(request, data, ui, page, aType);
	}
	
	@RequestMapping(value = "/api_web/data/{aType}")
	@ResponseBody
	public Object doAjaxOfData(HttpServletRequest request,String data,String ui, Page<?> page, @PathVariable(value = "aType")String aType) {
		JSONResult resultMap = AjaxApiUtil.doAjax(data, ui, aType,page, this);
		return resultMap.get("data");
	}
	@RequestMapping(value = "/doAjaxOfWeb/{aType}")
	@ResponseBody
	public Object doAjaxOfWeb(HttpServletRequest request,String data,String ui, Page<?> page, @PathVariable(value = "aType")String aType) {
		return doAjaxOfData(request, data, ui, page, aType);
	}

	@Override
	public MyLogger getLogger() {
		return logger;
	}

	@Override
	public AuthUser autoLogin(String ui, JSONResult m) {
		return null;
	}

	@Override
	public String getChannel() {
		return "ssme";
	}
}
