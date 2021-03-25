package com.dlz.test.comm.util;

import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.ValUtil;
import com.fasterxml.jackson.databind.JavaType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class JacksonUtilTest {
	static Logger logger=LoggerFactory.getLogger(JacksonUtilTest.class);
	@Test
	public void coverString(){
		logger.debug("123123");
	}

	@Test
	public void JacsonArrayTest() throws ClassNotFoundException {
		Map<String,Float>[] c=new HashMap[0];
		System.out.println(c.getClass());
		JavaType javaType2 = JacksonUtil.constructType(Map.class,String.class,Float.class);
		JavaType javaType = JacksonUtil.constructTypeByTypes(Class.forName("[L"),javaType2);
		String a="[{\"a\":1}]";
		Map<String,Float>[] b = JacksonUtil.readValue(a,javaType);
		logger.debug("123123");
	}
}
