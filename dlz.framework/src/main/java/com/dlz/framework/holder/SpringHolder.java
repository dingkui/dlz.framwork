package com.dlz.framework.holder;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 以静态变量保存Spring ApplicationContext,可在任意代码中取出ApplicaitonContext.
 * 
 * @author dk
 */
@Component
@Lazy(false)
public class SpringHolder implements ApplicationContextAware {
	private static BeanFactory  beanFactory;
	public static void init(){
		if(beanFactory==null){
			new ClassPathXmlApplicationContext("classpath*:spring_cfg/*.xml");
		}
	}
	public static void init(BeanFactory  applicationContext){
		if(beanFactory==null){
			beanFactory = applicationContext;
		}
	}
	/**
	 * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		if(beanFactory==null){
			beanFactory =applicationContext;
		}
	}
	
	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		if(beanFactory.containsBean(name))
		return (T) beanFactory.getBean(name);
		return null;
	}


	/**
	 * 是否包含某个bean
	 * @param beanName
	 * @return
	 */
	public static boolean containsBean(String beanName){
		return beanFactory.containsBean(beanName);
	}
	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		return (T) beanFactory.getBean(clazz);
	}

	private static void checkApplicationContext() {
		if (beanFactory == null)
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextUtil");
	}
}
