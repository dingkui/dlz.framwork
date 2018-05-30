package com.dlz.framework.springframework;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import com.dlz.framework.holder.SpringHolder;

/**
 *  spring 启动开始执行
 * @author dingkui
 *
 */
@Component
public class TracingBeginProcessor implements BeanDefinitionRegistryPostProcessor{
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    	SpringHolder.init(beanFactory);
    }
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
    }
}