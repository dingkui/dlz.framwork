package com.dlz.common.util;

import org.junit.Test;

import com.dlz.framework.bean.JSONList;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.JacksonUtil;

public class JSONMapTest {
	private static MyLogger logger =  MyLogger.getLogger(JSONMapTest.class);
	
	@Test
	public void test(){
		JSONMap paras = new JSONMap();
		paras.put("puid", "1111111111111111123123123213413333333333333333333333333333333333333333333333333333333333333333333333234124312341431324.21352345324534253");
		logger.info(paras.getBigDecimal("puid"));
		paras.put("puid1", "123");
		logger.info(paras.getBigDecimal("puid1"));
		paras.put("puid2", 123l);
		logger.info(paras.getBigDecimal("puid2"));
		paras.put("puid3", "123.1");
		logger.info(paras.getBigDecimal("puid3"));
		paras.put("puid4", 123.1);
		logger.info(paras.getBigDecimal("puid4"));
		logger.info(paras.getFloat("puid4"));
		logger.info(paras.getLong("puid4"));
		logger.info(paras.getInt("puid4"));
		logger.info(paras.getStr("puid4"));
	}
	
	/**
	 * 类型转换
	 */
	@Test
	public void test2(){
		JSONMap paras = new JSONMap();
		paras.put("puid", "1111111111111111123123123213413333333333333333333333333333333333333333333333333333333333333333333333234124312341431324.21352345324534253");
		logger.info(paras.getBigDecimal("puid"));
		paras.put("puid1", "123");
		logger.info(paras.getBigDecimal("puid1"));
		paras.put("puid2", 123l);
		logger.info(paras.getBigDecimal("puid2"));
		paras.put("puid3", "123.1");
		logger.info(paras.getBigDecimal("puid3"));
		paras.put("puid4", 123.1);
		logger.info(paras.getBigDecimal("puid4"));
		
		JSONMap paras2 = new JSONMap(null);
		logger.info(paras2.getFloat("puid4"));
		logger.info(paras2.getLong("puid4"));
		logger.info(paras2.getInt("puid4"));
		logger.info(paras2.getStr("puid4"));
	}
	
	/**
	 * 多级查询
	 */
	@Test
	public void test3(){
		JSONMap paras = new JSONMap();
		JSONMap paras2 = new JSONMap();
		JSONMap paras3 = new JSONMap();
		paras.put("a", paras2);
		paras2.put("b", paras3);
		paras3.put("c", 1);
		logger.info(paras.getStr("a.b.c"));
		logger.info(paras2.getFloat("b.c"));
	}
	/**
	 * JSON构造
	 */
	@Test
	public void test4(){
		JSONMap paras = new JSONMap();
		JSONMap paras2 = new JSONMap();
		JSONMap paras3 = new JSONMap();
		paras.put("a", paras2);
		paras2.put("b", paras3);
		paras3.put("c", 1);
		
		String json1=paras.toString();
		logger.info(json1);
		JSONMap paras4 = new JSONMap(json1);
		logger.info(paras4.getObj("a.b").get("c"));
		logger.info(paras4.getStr("a.b.c"));
	}
	
	
	/**
	 * 类型转换
	 */
	@Test
	public void test5(){
		JSONMap paras = new JSONMap("{\"a\":[1,2,3],\"b\":{\"b\":1,\"a\":2}}");
		logger.info(paras.getList("a"));
		logger.info(paras.getArray("a"));
		logger.info(paras.getList("b"));
		
		JSONMap c2=JacksonUtil.coverObj(paras, JSONMap.class);
		logger.info(c2.getList("a"));
		logger.info(c2.getArray("a"));
		logger.info(c2.getList("b"));
	}
	
	/**
	 * 类型转换
	 */
	@Test
	public void test6(){
		JSONList a=new JSONList("[\"{\\\"b\\\":1,\\\"a\\\":2}\",{\"b\":1,\"a\":2},{\"b1\":1,\"a1\":2}]");
		
		logger.info(a.getMap(0).getStr("b"));
//		logger.info(c2.getArray("a"));
//		logger.info(c2.getList("b"));
	}
	
}

