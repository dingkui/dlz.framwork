package com.dlz.framework;

import org.junit.Test;

import com.dlz.framework.util.JacksonUtil;

public class JacksonUtilTest {
	@Test
	public void coverString(){
		System.out.println(JacksonUtil.cover2String("123"));
		System.out.println(JacksonUtil.cover2String(new String[]{"1","2","3"}));
		System.out.println(JacksonUtil.cover2String(new Number[]{1,2,3}));
	}
	@Test
	public void Str(){
		System.out.println("sasd${aa}".indexOf("${")>-1);
	}
}
