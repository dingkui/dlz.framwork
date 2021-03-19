package com.dlz.test.framework.holder;

import com.dlz.comm.exception.SystemException;
import com.dlz.comm.util.ExceptionUtils;
import com.dlz.comm.util.encry.TraceUtil;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.test.framework.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
@Slf4j
public class TestSpringHolder extends BaseTest {
	@Test
	public void t1(){
		TraceUtil.setTraceId("t1");
		BeanClass2 beanClass2 = SpringHolder.registerBean(BeanClass2.class, false);
		BeanClass2 beanClass21 = SpringHolder.registerBean(BeanClass2.class, false);
		BeanClass2 beanClass23 = SpringHolder.registerBean(BeanClass2.class, false);
		log.debug(beanClass2+" " +beanClass2.getBeanClass1());
		log.debug(beanClass21+" " +beanClass21.getBeanClass1());
		log.debug(beanClass23+" " +beanClass23.getBeanClass1());
		log.trace("111");
		log.info("111");
		log.warn("111");
		log.error("111");
	}
}
