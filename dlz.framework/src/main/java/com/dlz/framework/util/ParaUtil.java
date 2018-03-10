package com.dlz.framework.util;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.logger.MyLogger;

/**
 * 参数处理工具
 * @author dingkui
 *
 */
@SuppressWarnings("rawtypes")
public class ParaUtil {
	protected static MyLogger logger = MyLogger.getLogger(ParaUtil.class);
	
	private static boolean isEmputy(String str) {
		return str == null || "".equals(str);
	}
	
	public static JSONMap getParaMatersFromRequest(ServletRequest request){
		JSONMap datas=new JSONMap();
		Map<String,String[]> paraMeterMap=request.getParameterMap();
		for(Map.Entry<String,String[]> entry:paraMeterMap.entrySet()){
			if(entry.getValue().length==1) {
				datas.put(entry.getKey(), entry.getValue()[0]);
			}else{
				datas.put(entry.getKey(), entry.getValue());
			}
		}
		return datas;
	}

	public static Page getPage(HttpServletRequest request) {
		Page pi = new Page();
		if (!isEmputy(request.getParameter("pageSize"))) {
			pi.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		}
		if (!isEmputy(request.getParameter("pageIndex"))) {
			pi.setPageIndex(Integer.parseInt(request.getParameter("pageIndex")));
		}
		if (!isEmputy(request.getParameter("sortField"))) {
			pi.setSortField(request.getParameter("sortField"));
		}
		if (!isEmputy(request.getParameter("sortOrder"))) {
			pi.setSortOrder(request.getParameter("sortOrder"));
		}
		return pi;
	}

	public static Page getPage(JSONMap pg) {
		Page page = new Page();
		if(pg!=null){
			if(pg.containsKey("pageSize")){
				page.setPageSize(pg.getInt("pageSize",20));
			}
			if(pg.containsKey("pageNow")){
				page.setPageNow(pg.getInt("pageNow",1));
			}
			if(pg.containsKey("sortField")){
				page.setSortField(pg.getStr("sortField"));
			}
			if(pg.containsKey("sortOrder")){
				page.setSortOrder(pg.getStr("sortOrder"));
			}
		}
		return page;
	}
}