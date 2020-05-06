package com.dlz.framework.springframework.scaner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;

import org.slf4j.Logger;

/**
 * 扫描定义
 * 
 * @author dingkui
 *
 */
public interface IScaner {
	List<IScanerProcessor> scaners = new ArrayList<>();

	public String getResoucePath();

	default void addScanerProcessors(IScanerProcessor scanerProcessor) {
		scaners.add(scanerProcessor);
	}

	default Collection<IScanerProcessor> getScanerProcessors() {
		return scaners;
	}

	/**
	 * 扫描处理器
	 */
	public interface IScanerProcessor {
		static Logger logger = org.slf4j.LoggerFactory.getLogger(IScanerProcessor.class);

		default Class<?> getBeanClass(AnnotatedBeanDefinition beanDefinition, boolean init) {
			try {
				if (init) {
					return Class.forName(beanDefinition.getBeanClassName());
				}
				return Class.forName(beanDefinition.getBeanClassName(), false, this.getClass().getClassLoader());
			} catch (ClassNotFoundException e) {
				logger.error(beanDefinition.getBeanClassName() + " " + e.getMessage(), e);
			}
			return null;
		}

		/**
		 * 是否符合处理器条件
		 * 
		 * @param beanDefinition
		 * @param beanClass
		 * @return
		 */
		public boolean isCandidate(AnnotatedBeanDefinition beanDefinition);

		/**
		 * 处理相关的定义类
		 * 
		 * @param definition
		 * @param beanClass
		 */
		public void proces(AnnotatedBeanDefinition definition);
	}
}