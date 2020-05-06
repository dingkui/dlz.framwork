package com.dlz.common.util.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import com.dlz.comm.json.JSONMap;
import com.dlz.framework.util.system.Reflections;

public class ReflectionsTest {
	@Test
	public void test1() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		C1 obj = new C1();
//		Method method = Reflections.getMethod(obj, "a",new Class []{JSONMap.class});
//		method.invoke(obj, new Object[]{null});
		Method method2 = Reflections.getMethod(obj, "b",new Class []{JSONMap.class});
		method2.invoke(obj, new JSONMap("{\"a\":1}"));
		
		Method method3 = Reflections.getAccessibleMethod(obj, "b",new Class []{JSONMap.class});
		method3.invoke(obj, new JSONMap("{\"a\":1}"));
	}

}
