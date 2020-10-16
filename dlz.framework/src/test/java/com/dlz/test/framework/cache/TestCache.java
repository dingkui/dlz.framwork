package com.dlz.test.framework.cache;

import com.dlz.framework.cache.CacheHolder;
import com.dlz.framework.cache.ICache;
import com.dlz.framework.holder.SpringHolder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestCache {
	ICache bean;
	@Before
	public void init(){
		SpringHolder.init();
		bean = SpringHolder.getBean(ICache.class);
	}
	@Test
	public void t1(){
		List<String> s=new ArrayList<>();
		s.add("xx");
		bean.put("aaa","xxx1111222","xxx",360000);
		bean.put("aaa","xxx22333","xddxx3333",360000);
		bean.put("aaa","xxx33444","xxddxxxxx",360000);
	}
	@Test
	public void t12(){
		ArrayList<String> s=new ArrayList<>();
		s.add("xx");
		ICache iCache = CacheHolder.get("t1", bean);
		iCache.put("t1","x1","1",-1);
		iCache.put("t1","x2",s,3600);
	}

	@Test
	public void t13(){
		ICache iCache = CacheHolder.get("t1", bean);
		ArrayList<String> data = iCache.get("t1", "x2", null);
		System.out.println(data);
	}
}
