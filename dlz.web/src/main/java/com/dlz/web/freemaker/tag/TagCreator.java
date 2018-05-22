package com.dlz.web.freemaker.tag;

import java.util.List;

import com.dlz.framework.holder.SpringHolder;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 标签创建者
 * 
 * @author kingapex
 *2013-8-1上午9:48:05
 */
public class TagCreator implements TemplateMethodModel {
	

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
		return SpringHolder.getBean(beanid);
	}
}
