package com.dlz.quartz;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;

import com.dlz.framework.springframework.AnnoMyComponent;

@AnnoMyComponent
@Scope("prototype")
public class Test2 {
	public Test2(){
		System.out.println("Test2 is init");
	}
	
	@PostConstruct
	public void init(){
		System.out.println("init doing");
	}
}
