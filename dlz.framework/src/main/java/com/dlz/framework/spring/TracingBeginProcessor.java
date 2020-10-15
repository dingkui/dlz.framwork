//package com.dlz.framework.spring;
//
//import com.dlz.framework.holder.SpringHolder;
//import com.dlz.framework.spring.iproxy.ApiScaner;
//import com.dlz.framework.spring.scaner.DlzSpringScaner;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.stereotype.Component;
//
///**
// *  spring 启动开始执行
// * @author dingkui
// *
// */
//public class TracingBeginProcessor implements BeanDefinitionRegistryPostProcessor{
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//    	SpringHolder.init(beanFactory);
//    	new DlzSpringScaner().doComponents(new ApiScaner());
//    }
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
//    }
//}