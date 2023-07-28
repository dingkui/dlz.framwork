package com.dlz.test.framework.cache;

import com.dlz.comm.json.JSONMap;
import com.dlz.comm.util.encry.TraceUtil;
import com.dlz.framework.cache.CacheHolder;
import com.dlz.framework.cache.CacheUtil;
import com.dlz.framework.cache.ICache;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.redis.service.impl.CacheRedisJsonHash;
import com.dlz.framework.redis.service.impl.CacheRedisJsonKey;
import com.dlz.framework.redis.service.impl.CacheRedisSerialHash;
import com.dlz.framework.redis.service.impl.CacheRedisSerialKey;
import com.dlz.test.framework.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestCache extends BaseTest {
	@Autowired
	ICache bean;
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
	@Test
	public void t14(){
		JSONMap a = new JSONMap("a", "1222222222222222222");
		bean.put("xx","xx", a.toString(),-1);
		String b=bean.get("xx","xx",String.class);
		System.out.println(b);
		CacheUtil.init(CacheRedisSerialKey.class);
		CacheUtil.put("xx","xx2", a.toString(),-1);
		Serializable serializable = CacheUtil.get("xx", "xx2");
		System.out.println(CacheUtil.get("xx","xx3"));
		System.out.println(CacheUtil.get("xx","xx3",()->new BeanTest("1","4")));
		System.out.println(CacheUtil.get("xx","xx3",JSONMap.class));
		System.out.println(serializable);
		System.out.println(SpringHolder.getBean(ICache.class));
	}

	@Test
	public void t15(){
		TraceUtil.setTraceId();

		CacheUtil.init(CacheRedisJsonKey.class);
		System.out.println(CacheUtil.get("jsonkey","xx1",()->new BeanTest("jsonkey","xx1")));
		System.out.println(CacheUtil.get("jsonkey","xx2",()->new BeanTest("jsonkey","xx2")));

		CacheUtil.init(CacheRedisJsonHash.class);
		System.out.println(CacheUtil.get("jsonhash","xx1",()->new BeanTest("jsonhash","xx1")));
		System.out.println(CacheUtil.get("jsonhash","xx2",()->new BeanTest("jsonhash","xx2")));

		CacheUtil.init(CacheRedisSerialKey.class);
		System.out.println(CacheUtil.get("serialkey","xx1",()->new BeanTest("serialkey","xx1")));
		System.out.println(CacheUtil.get("serialkey","xx2",()->new BeanTest("serialkey","xx2")));

		CacheUtil.init(CacheRedisSerialHash.class);
		System.out.println(CacheUtil.get("serialhash","xx1",()->new BeanTest("serialhash","xx1")));
		System.out.println(CacheUtil.get("serialhash","xx2",()->new BeanTest("serialhash","xx2")));

	}


}
