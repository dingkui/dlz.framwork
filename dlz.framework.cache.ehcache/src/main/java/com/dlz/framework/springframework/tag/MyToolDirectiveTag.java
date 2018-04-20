package com.dlz.framework.springframework.tag;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.util.JacksonUtil;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.SimpleHash;
import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelListSequence;
@SuppressWarnings({"unchecked","rawtypes"})
public class MyToolDirectiveTag implements TemplateDirectiveModel {

	
	/**
	 * <@mytoll name="aaa" method="toJson"/>
	 */
	@Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException {
        Writer out = env.getOut();
        String name=coverObject(getObject((TemplateModel) params.get("name")),String.class);
        String method=coverObject(getObject((TemplateModel) params.get("method")),String.class);
        method=method==null?"toJson":method;
       
        if("toJson".equalsIgnoreCase(method)){
        	toJson(out, getObject(env.getVariable(name)));
        }
        if("toString".equalsIgnoreCase(method)){
        	toString(out, getObject(env.getVariable(name)));
        }
        if("deal".equalsIgnoreCase(method)){
	    	String tagName=coverObject(getObject((TemplateModel) params.get("tagName")),String.class);
	        String tagPara=coverObject(getObject((TemplateModel) params.get("tagPara")),String.class);
			try{
				BaseTag tag= SpringHolder.getBean(tagName);
				if(tag==null){
					throw new TemplateModelException("标签["+tagName+"]不存在");
				}
				JSONMap para=null;
				if(tagPara!=null){
					para= JacksonUtil.readValue(tagPara, JSONMap.class);
				}
				Object o= tag.deal(para);
				if(o==null){
					o=new HashMap();
				}
				env.__setitem__(name, o);
			}catch(Exception e){
				throw new TemplateModelException("tag执行出错"+e.getMessage());
			}
        }else{
        	if (body != null) {
        		body.render(env.getOut());
        	}
        }
        
    }
    
    private void toJson(Writer out,Object o) throws TemplateException, IOException{
    	out.write(JacksonUtil.getJson(o));
    }
    
    private void toString(Writer out,Object o) throws TemplateException, IOException{
    	String s=String.valueOf(o);
    	out.write(s.replaceAll("[\\[\\]\\{\\}\"]", "").replaceAll("=", ":"));
    }
      
	private <T> T coverObject(Object object,Class<T> c) throws TemplateModelException{
    	return (T)object;
    }
    private Object getObject(TemplateModel paramValue) throws TemplateModelException{
    	if(paramValue==null){
    		return null;
    	}
    	if(paramValue instanceof BeanModel){
    		return ((BeanModel)paramValue).getWrappedObject();
    	}else if(paramValue instanceof SimpleSequence){
    		return ((SimpleSequence)paramValue).toList();
    	}else if(paramValue instanceof TemplateModelListSequence){
    		return ((TemplateModelListSequence)paramValue).getWrappedObject();
    	}else if(paramValue instanceof SimpleHash){
    		return ((SimpleHash)paramValue).toMap();
    	}else if(paramValue instanceof SimpleScalar){
    		return ((SimpleScalar)paramValue).getAsString();
    	}else if(paramValue instanceof SimpleNumber){
    		return ((SimpleNumber)paramValue).getAsNumber();
    	}
    	throw new TemplateModelException("类型不识别："+paramValue.getClass());
    }
}