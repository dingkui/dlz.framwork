package com.dlz.test.service;

import com.dlz.framework.db.service.ICommPlusService;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.holder.SpringHolder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;


/**
 * 单元测试基类,资源初始化<br>
 * @author dk
 */

public class CommPlusServiceTest {
	public static ICommService cs;
	public static ICommPlusService cs2;
	
	@BeforeClass
	public static void before() throws Exception {
		SpringHolder.init();
		cs=SpringHolder.getBean("commServiceImpl");
		cs2=SpringHolder.getBean(ICommPlusService.class);
	}
	@Test
	public void test11(){
		Map<String, Object> map = cs.getMap("select * from t_b_dict",false);
		List<Dict> map2 = cs2.list(new Dict());
		System.out.println(map);
		System.out.println(map2);
	}
}
