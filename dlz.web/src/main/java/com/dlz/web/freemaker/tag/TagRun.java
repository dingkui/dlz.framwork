package com.dlz.web.freemaker.tag;

import java.util.HashMap;
import java.util.List;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.util.JacksonUtil;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 标签执行
 * 
 * @author dk
 */
public class TagRun implements TemplateMethodModel {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	

	/**
	 * 传入spring beanid 返回标签的实例
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List args) throws TemplateModelException {
		String beanid =(String)args.get(0);
		if(beanid==null||"".equals(beanid)){
			throw new TemplateModelException("标签beanid参数不能为空");
		}
		try{
			BaseTag tag= SpringHolder.getBean(beanid);
			if(tag==null){
				throw new TemplateModelException("标签["+beanid+"]不存在");
			}
			String paras=null;
			JSONMap para=null;
			if(args.size()>1){
				paras= (String)args.get(1);
			}
			if(paras!=null){
				para= JacksonUtil.readValue(paras, JSONMap.class);
			}
			Object o= tag.deal(para);
			if(o==null){
				o=new HashMap();
			}
			return o;
		}catch(Exception e){
			throw new TemplateModelException("tag执行出错"+e.getMessage());
		}
	}
}
