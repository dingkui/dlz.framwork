package com.dlz.test.base;

import java.lang.reflect.ParameterizedType;

public class CommServiceImpl {
	public interface Wrapper<T> {
	}
	public interface Test extends Wrapper<String> {
	}
	public static class Test2 implements Wrapper<String> {
	}
	public static Class<?> findSuperClassParameterType(Class<?> subClass, int parameterIndex) {
		ParameterizedType pt = (ParameterizedType) (subClass.getGenericSuperclass());
		return (Class<?>) pt.getActualTypeArguments()[parameterIndex];
	}
	public static void main(String[] args) {
		Test2 test1 = new Test2();
		System.out.println(findSuperClassParameterType(test1.getClass(),0));
//		System.out.println(findSuperClassParameterType(Test.class,0));
	}

}