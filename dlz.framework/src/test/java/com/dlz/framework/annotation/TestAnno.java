package com.dlz.framework.annotation;

import org.junit.Test;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TestAnno {
	@Test
	public void t1(){
		System.out.println(this.getClass().getAnnotation(Service.class));
		System.out.println(this.getClass().getAnnotation(Component.class));
	}
}
