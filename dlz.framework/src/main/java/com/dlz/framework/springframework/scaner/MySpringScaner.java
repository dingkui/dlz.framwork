package com.dlz.framework.springframework.scaner;

import java.io.IOException;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.springframework.AnnoMyCompment;
import com.dlz.framework.springframework.iproxy.ApiProxyFactory;
import com.dlz.framework.springframework.iproxy.anno.AnnoApi;
import com.dlz.framework.util.StringUtils;

/**
 * 自定义扫描注册bean
 * @author dingkui
 *
 */
/**
 * 自定义扫描 只扫描接口和抽象类，并且注解了AnnoInterfaceProxy的类 命名为： I*Api.java
 * 
 * @author dk
 */
public class MySpringScaner {
	private static MyLogger logger = MyLogger.getLogger(MySpringScaner.class);

	private static boolean isInit = false;

	private static String API_CLASSPATH = "classpath*:com/dlz/**/I*Api.class";

	private MySpringScaner() {
	}

	public static void init() {
		if (isInit) {
			logger.warn("ApiClassScaner已经初始化过，跳过该操作");
			return;
		}
		isInit = true;
		logger.debug("自定义扫描处理中。。。");
		BeanDefinitionRegistry registry = SpringHolder.getBeanDefinitionRegistry();
		IScaner MyCompmentScaner = new IScaner() {
			@Override
			public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition, Class<?> beanClass) {
				return beanDefinition.getMetadata().hasAnnotation(AnnoMyCompment.class.getName());
			}

			@Override
			public void doWithResult(AnnotatedBeanDefinition definition, Class<?> beanClass) {
				AnnoMyCompment annotation = beanClass.getAnnotation(AnnoMyCompment.class);
				Class<?> forName=beanClass;
				String beanName =annotation.value();
				if("".equals(beanName)){
					 beanName = StringUtils.getBeanId(definition.getBeanClassName());
				}
				definition.setScope(annotation.scope());
				registry.registerBeanDefinition(beanName, definition);
				if(!annotation.layz()){
					SpringHolder.getBeans(forName);
				}
			}
		};
		new MySpringScaner().doComponents(API_CLASSPATH, new IScaner() {
			@Override
			public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition, Class<?> beanClass) {
				return beanDefinition.getMetadata().isAbstract() && beanDefinition.getMetadata().hasAnnotation(AnnoApi.class.getName());
			}

			@Override
			public void doWithResult(AnnotatedBeanDefinition definition, Class<?> beanClass) {
				AnnoApi annotation = beanClass.getAnnotation(AnnoApi.class);
				String beanName = annotation.value();
				if ("".equals(beanName)) {
					beanName = StringUtils.getBeanId(definition.getBeanClassName());
				}
				definition.getPropertyValues().add("interfaceClass", definition.getBeanClassName());
				definition.setBeanClassName(ApiProxyFactory.class.getName());
				registry.registerBeanDefinition(StringUtils.getBeanId(definition.getBeanClassName()), definition);
			}
		}).doComponents("classpath*:com/dlz/**/*.class", MyCompmentScaner).doComponents("classpath*:com/enation/app/**/*Work.class", MyCompmentScaner);
	}

	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

	private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(new PathMatchingResourcePatternResolver());

	public MySpringScaner doComponents(String classPath, IScaner... scaners) {
		try {
			Resource[] resources = resourcePatternResolver.getResources(classPath);
			for (Resource resource : resources) {
				if (resource.isReadable()) {
					String beanClassName="";
					try {
						MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
						ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
						sbd.setResource(resource);
						sbd.setSource(resource);
						beanClassName = sbd.getBeanClassName();
						Class<?> forName = Class.forName(beanClassName);
						for (IScaner scaner : scaners) {
							if (scaner.isCandidateComponent(sbd, forName)) {
								scaner.doWithResult(sbd, forName);
								continue;
							}
						}
					} catch (ClassNotFoundException ex) {
						logger.error("ClassNotFoundException:"+beanClassName+" "+ex.getMessage());
					} catch (ExceptionInInitializerError ex) {
						logger.error("ExceptionInInitializerError:"+beanClassName+" "+ex.getMessage());
					} catch (Throwable ex) {
						logger.error(ex.getMessage(), ex);
//						throw new BeanDefinitionStoreException("Failed to read candidate component class: " + resource, ex);
					}
				}
			}
		} catch (IOException ex) {
			throw new BeanDefinitionStoreException("I/O failure during classpath scanning", ex);
		}
		return this;
	}

	private interface IScaner {
		public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition, Class<?> beanClass);

		public void doWithResult(AnnotatedBeanDefinition definition, Class<?> beanClass);
	}
}