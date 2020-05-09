package com.dlz.web.cloud.handler;

import com.dlz.comm.util.ValUtil;
import com.dlz.web.bean.RestPara;
import com.dlz.web.annotation.AnnoRestPara;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
 
public class MyHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{  
  
    @Override  
    public boolean supportsParameter(MethodParameter parameter) {  
    	return parameter.hasParameterAnnotation(AnnoRestPara.class)||parameter.getParameterType().isAssignableFrom(RestPara.class);
    }  
  
    @Override
    public Object resolveArgument(MethodParameter parameter,  
            ModelAndViewContainer mavContainer, NativeWebRequest webRequest,  
            WebDataBinderFactory binderFactory) throws Exception {  
        if (binderFactory==null) {  
            return null;  
        }  
        AnnoRestPara methodAnnotation = parameter.getMethodAnnotation(AnnoRestPara.class);
		String paraName=null;
		if(methodAnnotation!=null){
			paraName=methodAnnotation.value();
		}
        if("".equals(paraName)){
        	paraName=parameter.getParameterName();
        }
        String para=webRequest.getParameter(parameter.getParameterName());
        if(para==null){
        	return null;
        }
        return ValUtil.getObj(para, parameter.getParameterType());
    }  
}  