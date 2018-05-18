package com.dlz.quartz;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy(true)
public class Test3 {
	public Test3(){
		System.out.println("Test3 With para");
	}
}
