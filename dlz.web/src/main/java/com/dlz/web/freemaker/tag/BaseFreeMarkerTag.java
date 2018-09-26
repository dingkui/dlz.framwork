package com.dlz.web.freemaker.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.util.ValUtil;
import com.dlz.web.holder.ThreadHolder;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 标签接口
 * 
 * @author dk
 */
public abstract class BaseFreeMarkerTag implements TemplateMethodModelEx {
	protected final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	private int pageSize =10;
	
	@Override
	public Object exec(List jsonParam) throws TemplateModelException {
		//判断参数是否为空
		if(jsonParam!=null && !jsonParam.isEmpty()){
			String param = jsonParam.get(0).toString();
			if(param!=null){
				if(!param.startsWith("{")){
					 param="{"+param+"}";
				}
				//将json转换成MAP
				JSONMap jsonObject  =new JSONMap(param);
				Integer pageSizeNum =(Integer)jsonObject.get("pageSize");
				if(pageSizeNum!=null){
					this.pageSize= pageSizeNum;
				}
				return this.exec(jsonObject);
			}else{
				return this.exec(new JSONMap());
			}
		}else{
			return this.exec(new JSONMap());
		}
	}
	
	/***
	 * 获取类实体
	 * @param beanid 
	 */
	protected <T> T getBean(String beanid){
		return SpringHolder.getBean(beanid);
	}
	
	protected abstract Object exec(JSONMap params) throws TemplateModelException;

	/***
	 * 获取每页显示数量
	 * @return
	 */
	protected int getPageSize() {
		return pageSize;
	}

	/**
	 * 获取当前的页数
	 * @return
	 */
	protected int getPage() {
		int page =1;
		HttpServletRequest request  = ThreadHolder.getHttpRequest();
		page  = ValUtil.getInt(request.getParameter("page"),1);
		page = page < 1 ? 1 : page;
		return page;
	}
	
	/**
	 * 获取request请求
	 * @return
	 */
	protected HttpServletRequest getRequest(){
		return  ThreadHolder.getHttpRequest();
	}
	
	/**
	 * 重定向至404页面
	 */
	protected void redirectTo404Html(){
		HttpServletResponse response=ThreadHolder.getHttpResponse();
		HttpServletRequest request=ThreadHolder.getHttpRequest();
		try {
			response.sendRedirect(request.getContextPath()+"/404.html");
			return;
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
	}
}