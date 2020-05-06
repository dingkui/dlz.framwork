package com.dlz.plugin.socket.deal;

import java.util.Date;

import com.dlz.plugin.socket.interfaces.IDealService;

/**
 * 服务器端业务处理测试1
 * @author dk
 */
public class DealServiceTest1 implements IDealService {

	static int i=0;
	static long allt = new Date().getTime();
	public void deal(String postStr) {
		if(i++%1000==0){
			System.out.println(i+":"+(new Date().getTime()-allt));
		}
	}
	
	//取得返回信息
	public String getResStr(String input){
		if(i++%10000==0){
			System.out.println("服务器收到："+i+" 时间:"+(new Date().getTime()-allt));
			allt = new Date().getTime();
		}
		return "[input:["+input+"] return:myreta啊啊啊啊]";
	}
}
