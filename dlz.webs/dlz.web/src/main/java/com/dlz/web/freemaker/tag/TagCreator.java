package com.dlz.web.freemaker.tag;

import java.util.List;

import com.dlz.comm.json.JSONMap;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.comm.util.JacksonUtil;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 标签创建者
 * 支持创建后直接执行exec方法
 * 
 * @author dk
 */
public class TagCreator implements TemplateMethodModelEx {
	/**
	 * 传入spring beanid 返回标签的实例
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List args) throws TemplateModelException {
		String beanid =args.get(0).toString();
		if(beanid==null||"".equals(beanid)){
			throw new TemplateModelException("标签beanid参数不能为空");
		}
		try{
			BaseFreeMarkerTag tag= SpringHolder.getBean(beanid);
			if(tag==null){
				throw new TemplateModelException("标签["+beanid+"]不存在");
			}
			if(args.size()==1){
				return tag;
			}
			JSONMap para=JacksonUtil.readValue(args.get(1).toString(), JSONMap.class);
			return tag.exec(para);
		}catch(Exception e){
			throw new TemplateModelException("tag执行出错"+e.getMessage());
		}
	}
}
