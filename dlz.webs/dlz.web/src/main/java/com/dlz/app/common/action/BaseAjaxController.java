package com.dlz.app.common.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dlz.app.uim.bean.AuthUser;
import com.dlz.app.uim.holder.UserHolder;
import com.dlz.comm.json.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.web.inf.IApiAjax;
import com.dlz.web.util.ApiUtil;

public abstract class BaseAjaxController implements IApiAjax{
	public <T extends AuthUser> T getAuthInfo(){
		return UserHolder.getAuthInfo();
	}
	/**
	 * 多个ajax一次请求
	 * @param data：{
	 * 	  urls:数组  多个ajax的aType 必须
	 * 	  datases：数组 对应urls的请求参数
	 * }
	 * @param ui
	 * @return JSONResult{
	 * 		datas:数组 多个ajax请求的返回值
	 * }
	 */
	@RequestMapping(value = "m")
	public JSONResult doAjaxs(String data, String ui) {
		return ApiUtil.doApiLogics(data, ui, this);
	}
	/**
	 * ajax执行时需要传入参数data(json格式)
	 * @param ui
	 * @param aType
	 * @return logic中的返回值封装入JSONResult
	 */
	@RequestMapping(value = "{aType}",method={RequestMethod.GET,RequestMethod.POST})
	public JSONResult doAjax(String data,String ui, @PathVariable(value = "aType")String aType) {
		return ApiUtil.doApiLogic(data, ui, aType,this);
	}
	
	/**
	 * ajax执行时需要传入参数data(json格式)
	 * @param ui
	 * @param aType
	 * @return 返回值是logic中的返回值
	 */
	@RequestMapping(value = "d/{aType}")
	public Object doAjaxOfData(String data,String ui, @PathVariable(value = "aType")String aType) {
		JSONResult resultMap = doAjax(data, ui, aType);
		return 1==resultMap.getFlag()?resultMap.get("data"):resultMap;
	}
	
	/**
	 * ajax执行时将所有参赛放到dada中
	 * @param request
	 * @param ui
	 * @param aType
	 * @return logic中的返回值封装入JSONResult
	 */
	@RequestMapping(value = "p/{aType}")
	public JSONResult doAjaxWithAllPara(HttpServletRequest request,String ui, @PathVariable(value = "aType")String aType) {
		JSONMap data =  new JSONMap();
		addParaFromRequest(request.getParameterMap(), data);
		return ApiUtil.doApiLogic(data, ui, aType,this);
	}
	/**
	 * ajax执行时将所有参赛放到dada中
	 * @param request
	 * @param ui
	 * @param aType
	 * @return 返回值是logic中的返回值
	 */	
	@RequestMapping(value = "pd/{aType}")
	public Object doAjaxWithAllParaOfData(HttpServletRequest request,String ui, @PathVariable(value = "aType")String aType) {
		JSONResult resultMap = doAjaxWithAllPara(request, ui, aType);
		return 1==resultMap.getFlag()?resultMap.get("data"):resultMap;
	}
	/**
	 * ajax执行时将所有参赛放到dada中
	 * @param request
	 * @param ui
	 * @param aType
	 * @return logic中的返回值封装入JSONResult
	 */
	@RequestMapping(value = "b/{aType}")
	public JSONResult doAjaxWithAllPara(HttpServletRequest request,String ui, @PathVariable(value = "aType")String aType,@RequestBody(required=false) JSONMap model) {
		return ApiUtil.doApiLogic(model, ui, aType,this);
	}
	/**
	 * ajax执行时将所有参赛放到dada中
	 * @param request
	 * @param ui
	 * @param aType
	 * @return 返回值是logic中的返回值
	 */	
	@RequestMapping(value = "bd/{aType}")
	public Object doAjaxWithAllParaOfData(HttpServletRequest request,String ui, @PathVariable(value = "aType")String aType,@RequestBody(required=false) JSONMap model) {
		JSONResult resultMap = doAjaxWithAllPara(request, ui, aType,model);
		return 1==resultMap.getFlag()?resultMap.get("data"):resultMap;
	}
	
	
	public void addParaFromRequest(Map<String,String[]> paraMeterMap,JSONMap map){
		for(Map.Entry<String,String[]> entry:paraMeterMap.entrySet()){
			String key=entry.getKey();
			if(entry.getValue().length==1) {
				map.add(key, entry.getValue()[0]);
			}else{
				map.add(key, entry.getValue());
			}
		}
	}
}
