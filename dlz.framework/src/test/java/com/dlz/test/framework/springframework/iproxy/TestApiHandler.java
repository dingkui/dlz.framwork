package com.dlz.test.framework.springframework.iproxy;

import java.lang.reflect.Method;

import com.dlz.framework.springframework.iproxy.ApiProxyHandler;
import org.springframework.stereotype.Component;

@Component
public class TestApiHandler extends ApiProxyHandler {
	@Override
	public Object done(Class<?> clazz,Method method, Object[] args) throws Exception {
		return method.getName()+getParaAsMap(method, args);
	}
}
