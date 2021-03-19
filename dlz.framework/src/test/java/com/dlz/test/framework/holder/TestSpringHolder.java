package com.dlz.test.framework.holder;

import com.dlz.framework.holder.SpringHolder;
import com.dlz.test.framework.BaseTest;
import org.junit.Test;

public class TestSpringHolder extends BaseTest {
	@Test
	public void t1(){
		BeanClass2 beanClass2 = SpringHolder.registerBean(BeanClass2.class, false);
		BeanClass2 beanClass21 = SpringHolder.registerBean(BeanClass2.class, false);
		BeanClass2 beanClass23 = SpringHolder.registerBean(BeanClass2.class, false);
		System.out.println(beanClass2+"" +beanClass2.getBeanClass1());
		System.out.println(beanClass21+"" +beanClass21.getBeanClass1());
		System.out.println(beanClass23+"" +beanClass23.getBeanClass1());
	}
}
