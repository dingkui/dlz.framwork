package com.dlz.framework.cloud.handler;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dlz.framework.cloud.annotation.AnnoCloud;
import com.dlz.framework.springframework.iproxy.ApiProxyHandler;
import com.dlz.framework.util.HttpUtil;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.ValUtil;

@Component
public class HttpClientApiHandler extends ApiProxyHandler {
	
    @Value("${gateway}")
    private String gateway;
     
	@Override
	public Object done(Class<?> clazz,Method method, Object[] args) throws Exception {
		AnnoCloud annotation = clazz.getAnnotation(AnnoCloud.class);
		String servive=null;
		if(annotation!=null){
			servive=annotation.value();
		}
		if("".equals(servive)){
			servive=clazz.getSimpleName().replaceAll("i(.*)Api", "$1");
		}
		String ret= HttpUtil.sendHttpPost(gateway+"/"+servive+"/"+method.getName(), getParaMap(method, args));
		return ValUtil.getObj(ret, method.getReturnType());
	}
	
	protected Map<String,String> getParaMap(Method method,Object[] args){
		Map<String,String> m=new HashMap<>();
		Parameter[] parameters = method.getParameters();
		for(int i=0;i<parameters.length;i++){
			if(args[i] instanceof CharSequence || args[i] instanceof Number){
				m.put(parameters[i].getName(), args[i].toString());
			}else{
				m.put(parameters[i].getName(), JacksonUtil.getJson(args[i]));
			}
		}
		return m;
	}
	public static void main(String[] args) {
		String[] a=new String[]{"12","23"};
		String json = JacksonUtil.getJson(a);
		System.out.println(json);
		System.out.println(a.getClass());
		System.out.println(ValUtil.getObj(json, a.getClass()));
	}
}
