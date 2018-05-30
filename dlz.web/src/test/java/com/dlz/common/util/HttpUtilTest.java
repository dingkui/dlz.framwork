package com.dlz.common.util;

import org.junit.Test;

import com.dlz.framework.util.ParseUtil.ParserEnum;
import com.dlz.web.util.HttpUtil;

public class HttpUtilTest {
	@Test
	public void sendHttpGet() throws Exception {
		HttpUtil.sendHttpGet("http://blog.csdn.net/hll174/article/details/51276594");
	}
	
	
	@Test
	public void creatDiz() throws Exception {
		System.out.println(ParserEnum.D623.encode(1001));
	}
}

