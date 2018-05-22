package com.dlz.web.freemaker.tag;

import com.dlz.framework.bean.JSONMap;

import freemarker.template.TemplateModelException;

/**
 * 标签接口
 * 
 * @author dk
 */
public interface BaseTag{
	public Object deal(JSONMap map) throws TemplateModelException;
}
