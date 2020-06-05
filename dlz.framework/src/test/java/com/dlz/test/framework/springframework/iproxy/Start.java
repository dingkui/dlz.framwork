package com.dlz.test.framework.springframework.iproxy;

import org.junit.Before;
import org.junit.Test;

import com.dlz.framework.holder.SpringHolder;

public class Start {
	@Before
	public void setUp() throws Exception {
		SpringHolder.init();
		testService=(ITestApi)SpringHolder.getBean("iTestApi");
		test2Service=(ITest2Api)SpringHolder.getBean("iTest2Api");
		test3Service=(ITestApi)SpringHolder.getBean("iTestApi");
	}
	
	ITest2Api test2Service;
	ITestApi testService;
	ITestApi test3Service;

    @Test
    public void test() {
        System.out.println(test2Service.sayHello("1","2"));
        System.out.println(testService.sayHello("-1","-2"));
        System.out.println(test3Service.sayHello("-1","-2"));
        System.out.println(test2Service.sayHello("-1"));
        System.out.println(test2Service.sayHello());
    }
}