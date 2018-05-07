package com.dlz.framework.springframework.iproxy;

import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

import com.dlz.framework.springframework.iproxy.AInterfaceProxyHandler;

@Component
public class TestHandler extends AInterfaceProxyHandler {
	@Override
	public Object done(Class<?> clazz,Method method, Object[] args) throws Exception {
		return method.getName()+getParaAsMap(method, args);
	}
}
