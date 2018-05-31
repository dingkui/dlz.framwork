package com.dlz.framework.springframework.iproxy;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.springframework.iproxy.anno.AnnoApi;
import com.dlz.framework.springframework.scaner.IScaner;
import com.dlz.framework.util.StringUtils;

/**
 * 自定义扫描 只扫描接口和抽象类，并且注解了AnnoApi的类 命名为： I*Api.java
 * 
 * @author dk
 */
public class ApiScaner implements IScaner{
	@Override
	public String getResoucePath() {
		return "classpath*:com/dlz/**/I*Api.class";
	}
	public ApiScaner() {
		addScanerProcessors(new IScanerProcessor() {
			BeanDefinitionRegistry registry = SpringHolder.getBeanDefinitionRegistry();
			@Override
			public boolean isCandidate(AnnotatedBeanDefinition beanDefinition) {
				Class<?> forName=getBeanClass(beanDefinition, false);
				AnnoApi annotation = forName.getAnnotation(AnnoApi.class);
				return beanDefinition.getMetadata().isAbstract() && annotation!=null;
			}

			@Override
			public void proces(AnnotatedBeanDefinition definition) {
				Class<?> forName=getBeanClass(definition, false);
				if(forName==null){
					return;
				}
				AnnoApi annotation = forName.getAnnotation(AnnoApi.class);
				String beanName =annotation.value();
				if("".equals(beanName)){
					 beanName = StringUtils.getBeanId(definition.getBeanClassName());
				}
				definition.getPropertyValues().add("interfaceClass", definition.getBeanClassName());
				definition.setBeanClassName(ApiProxyFactory.class.getName());
				registry.registerBeanDefinition(beanName, definition);
			}
		});
	}
}