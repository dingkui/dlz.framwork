package com.dlz.framework.springframework.scaner;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.springframework.AnnoMyComponent;
import com.dlz.framework.util.StringUtils;

/**
 * 自定义扫描 带有AnnoMyCompment注解的类 注入到spring并立即执行
 * 
 * @author dk
 */
@Component
public class MyComponentScaner implements IScaner{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	@Override
	public String getResoucePath() {
		return "classpath*:com/dlz/**/*.class";
	}
	public MyComponentScaner() {
		addScanerProcessors(new IScanerProcessor() {
			BeanDefinitionRegistry registry = SpringHolder.getBeanDefinitionRegistry();
			@Override
			public boolean isCandidate(AnnotatedBeanDefinition beanDefinition) {
				return beanDefinition.getMetadata().hasAnnotation(AnnoMyComponent.class.getName());
			}

			@Override
			public void proces(AnnotatedBeanDefinition definition) {
				Class<?> forName=getBeanClass(definition, false);
				if(forName==null){
					return;
				}
				AnnoMyComponent annotation = forName.getAnnotation(AnnoMyComponent.class);
				String beanName =annotation.value();
				if("".equals(beanName)){
					 beanName = StringUtils.getBeanId(definition.getBeanClassName());
				}
				Scope scope = forName.getAnnotation(Scope.class);
				if(scope!=null){
					definition.setScope(scope.value());
				}
				Lazy lazy = forName.getAnnotation(Lazy.class);
				if(lazy!=null){
					definition.setLazyInit(lazy.value());
				}
				DependsOn dependsOn = forName.getAnnotation(DependsOn.class);
				if(dependsOn!=null){
					definition.setDependsOn(dependsOn.value());
				}
				registry.registerBeanDefinition(beanName, definition);
			}
		});
	}
}