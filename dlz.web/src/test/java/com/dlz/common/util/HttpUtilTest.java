package com.dlz.common.util;

import org.junit.Test;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.util.encry.ParseUtil.ParserEnum;
import com.dlz.web.util.HttpUtil;

public class HttpUtilTest {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	@Test
	public void sendHttpGet() throws Exception {
//		HttpGetUtil.get("http://blog.csdn.net/hll174/article/details/51276594");
//		HttpGetUtil.get("https://www.baidu.com");
//		HttpPostUtil.post("http://blog.csdn.net/hll174/article/details/51276594");
		JSONMap m=new JSONMap();
		m.put("data", new JSONMap().add("a", 123));
//		m.put("n", "333");
		m.put("timestamp", "ccc");
//		HttpPostUtil.post("http://shop.jkj51.com/finder/finder?action=finder.login");
		
		HttpUtil.HttpUtilEnum.POST.send("http://127.0.0.1:2222/api/test_n",m);
		
	}
	
	
	
	@Test
	public void creatDiz() throws Exception {
		System.out.println(ParserEnum.D623.encode(1001));
	}
}

