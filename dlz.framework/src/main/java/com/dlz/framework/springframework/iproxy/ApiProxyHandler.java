package com.dlz.framework.springframework.iproxy;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.dlz.framework.bean.JSONMap;

/**
 * 接口代理实现类
 * @author dk
 */
public abstract class ApiProxyHandler {
	void doNothing1(){new java.util.ArrayList<>().forEach(a->{});}
	protected JSONMap getParaAsMap(Method method,Object[] args){
		JSONMap m=new JSONMap();
		Parameter[] parameters = method.getParameters();
		for(int i=0;i<parameters.length;i++){
			m.add(parameters[i].getName(), args[i]);
		}
		return m;
	}
	public abstract Object done(Class<?> clas,Method method,Object[] args) throws Exception;
}
