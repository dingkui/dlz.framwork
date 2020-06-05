package com.dlz.test.base;

import com.dlz.framework.holder.SpringHolder;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * 单元测试基类,资源初始化<br>
 * @author dk
 */
public class BaseTest {
	@BeforeClass
	public static void before()  {
		SpringHolder.init();
	}

	@Test
	public void test(){

	}

}
