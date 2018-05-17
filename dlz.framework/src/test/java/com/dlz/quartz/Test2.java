package com.dlz.quartz;

import javax.annotation.PostConstruct;

import com.dlz.framework.springframework.AnnoMyCompment;

@AnnoMyCompment
public class Test2 {
	public Test2(){
		System.out.println("Test2 is init");
	}
	
	@PostConstruct
	public void init(){
		System.out.println("init doing");
	}
	
}
