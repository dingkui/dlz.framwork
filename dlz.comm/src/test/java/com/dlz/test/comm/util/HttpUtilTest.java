package com.dlz.test.comm.util;

import com.dlz.comm.json.JSONMap;
import com.dlz.comm.util.ValUtil;
import com.dlz.comm.util.web.HttpEnum;
import com.dlz.comm.util.web.HttpRequestParam;
import org.apache.http.client.config.RequestConfig;
import org.junit.Test;

public class HttpUtilTest {
	private static final String authorization = "Authorization";
	@Test
	public void timoutTest(){
		System.out.println(getOldId("c34bc463-f6c0-4a53-8d9e-2e1d5fdeed501"));
		System.out.println(getOldId("c34bc463-f6c0-4a53-8d9e-2e1d5fdeed50"));
	}

	private static String getOldId(String token){
		HttpRequestParam<JSONMap> formReq = HttpRequestParam.createFormReq("http://39.108.125.90:8080/scene-portal/rest/api/user/info", null, JSONMap.class);
		formReq.setRequestConfig(RequestConfig.custom().setConnectTimeout(100).build());
		formReq.addHeader(authorization,"bearer "+token);
		try {
			return HttpEnum.GET.send(formReq).getStr("data");
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
