package com.dlz.test.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dlz.framework.db.service.ICommPlusService;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.util.system.Reflections;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


/**
 * 单元测试基类,资源初始化<br>
 * @author dk
 */
@Slf4j
public class CommPlusServiceTest {
	public static ICommService cs;
	@Autowired
	public static ICommPlusService commPlusService;
	
	@BeforeClass
	public static void before() throws Exception {
		SpringHolder.init();
		cs=SpringHolder.getBean("commServiceImpl");
		commPlusService=SpringHolder.getBean(ICommPlusService.class);
	}
	@Test
	public void test01(){
		Map<String, Object> map = cs.getMap("select * from t_b_dict",false);
		List<Dict> map2 = commPlusService.list(new Dict());
		System.out.println(map);
		System.out.println(map2);
	}
	@Test
	public void test1(){
		List<Dict> list = commPlusService.list(new Dict());
		log.debug("re： {}",list);
	}

	@Test
	public void test2(){
		LambdaQueryWrapper<Dict> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Dict::getDictStatus,1);
		List<Dict> list = commPlusService.list(queryWrapper);
		log.debug("re： {}",list);
	}
}
