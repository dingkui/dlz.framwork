package com.dlz.framework.holder;

import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.dlz.framework.util.StringUtils;

/**
 * 以静态变量保存Spring ApplicationContext,可在任意代码中取出ApplicaitonContext.
 * 
 * @author dk
 */
@Component
@Lazy(false)
public class SpringHolder implements ApplicationContextAware {
	private static ApplicationContext  beanFactory;
	public static void init(){
		if(beanFactory==null){
			new ClassPathXmlApplicationContext("classpath*:spring_cfg/*.xml");
		}
	}
	public static void init(String xml){
		if(beanFactory==null){
			beanFactory=new ClassPathXmlApplicationContext("classpath*:spring_cfg/"+xml+".xml");
		}
	}
	public static void init(ApplicationContext applicationContext){
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
	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> Map<String, T> getBeans(Class<T> clazz) {
		checkApplicationContext();
		return beanFactory.getBeansOfType(clazz);
	}

	private static void checkApplicationContext() {
		if (beanFactory == null)
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextUtil");
	}
	
	/**
     * 注册bean
     * @param beanId 所注册bean的id
     * @param className bean的className，
     *                     三种获取方式：1、直接书写，如：com.mvc.entity.User
     *                                   2、User.class.getName
     *                                   3.user.getClass().getName()
     */
    public static void registerBean(String beanId,String className) {
    	if(beanFactory.containsBean(beanId)){
    		return;
    	}
        // get the BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder =
        BeanDefinitionBuilder.genericBeanDefinition(className);
        // get the BeanDefinition
        BeanDefinition beanDefinition=beanDefinitionBuilder.getBeanDefinition();
        // register the bean
        ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition(beanId,beanDefinition);
    }
    /**
     * 注册bean
     * @param beanId 所注册bean的id
     * @param className bean的className，
     *                     三种获取方式：1、直接书写，如：com.mvc.entity.User
     *                                   2、User.class.getName
     *                                   3.user.getClass().getName()
     */
    public static void registerBean(String className) {
    	registerBean(StringUtils.getBeanId(className), className);
    }
    /**
     * 注册bean
     * @param beanId 所注册bean的id
     * @param className bean的className，
     *                     三种获取方式：1、直接书写，如：com.mvc.entity.User
     *                                   2、User.class.getName
     *                                   3.user.getClass().getName()
     */
    public static void registerBean(Class<?> clazz) {
    	registerBean(clazz.getName());
    }
    /**
     * 移除bean
     * @param beanId bean的id
     */
    public static void unregisterBean(String beanId){
    	((DefaultListableBeanFactory) beanFactory).removeBeanDefinition(beanId);
    }
}
