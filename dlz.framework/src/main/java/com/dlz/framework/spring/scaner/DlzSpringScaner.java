package com.dlz.framework.spring.scaner;

import com.dlz.comm.util.ExceptionUtils;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.spring.scaner.IScaner.IScanerProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 自定义扫描处理
 * 查找AScaner的实现，执行其中定义的扫描处理器
 * @author dingkui
 *
 */
@Slf4j
public class DlzSpringScaner {
	ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
	MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(new PathMatchingResourcePatternResolver());
	public void runScaner() {
		log.debug("自定义扫描处理中。。。");
		Map<String, IScaner> beans = SpringHolder.getBeans(IScaner.class);
		if (!beans.isEmpty()) {
			for (Entry<String, IScaner> entry : beans.entrySet()) {
				doComponents(entry.getValue());
				SpringHolder.unregisterBean(entry.getKey());
			}
			ConfigurableListableBeanFactory beanFactory = SpringHolder.getBeanFactory();
//			// Stop using the temporary ClassLoader for type matching.
//			beanFactory.setTempClassLoader(null);
//
//			// Allow for caching all bean definition metadata, not expecting further changes.
//			beanFactory.freezeConfiguration();
			beanFactory.preInstantiateSingletons();
		}
	}

	public void doComponents(IScaner scaner) {
		if (scaner.getClass().toString().indexOf('$')>0) {
			log.warn("自定义扫描处理器未装载处理器" + scaner.getClass() );
			return;
		}
		if (scaner.getScanerProcessors().isEmpty()) {
			log.warn("自定义扫描处理器未装载处理器" + scaner.getClass() + " ResoucePath:" + scaner.getResoucePath());
			return;
		}
		try {
			Resource[] resources = resourcePatternResolver.getResources(scaner.getResoucePath());
			for (Resource resource : resources) {
				if (resource.isReadable()) {
					String beanClassName = "";
					try {
						MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
						ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
						beanClassName = sbd.getBeanClassName();
						sbd.setResource(resource);
						sbd.setSource(resource);
						for (IScanerProcessor scanerProcessor : scaner.getScanerProcessors()) {
							if (scanerProcessor.isCandidate(sbd)) {
								scanerProcessor.proces(sbd);
								continue;
							}
						}
					} catch (Throwable ex) {
						log.error(ExceptionUtils.getStackTrace(ex.getMessage() + " " + beanClassName,ex));
					}
				}
			}
		} catch (IOException ex) {
			throw new BeanDefinitionStoreException("I/O failure during classpath scanning", ex);
		}
	}
}