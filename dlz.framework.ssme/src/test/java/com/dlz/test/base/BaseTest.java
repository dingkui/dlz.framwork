package com.dlz.test.base;

import org.junit.BeforeClass;

import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.holder.SpringHolder;
 

/**
 * 单元测试基类,资源初始化<br>
 * @author dk
 */
public class BaseTest {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	public static ICommService cs;
	
	@BeforeClass
	public static void before() throws Exception {
		SpringHolder.init();
		cs=SpringHolder.getBean(ICommService.class);
	}
}
