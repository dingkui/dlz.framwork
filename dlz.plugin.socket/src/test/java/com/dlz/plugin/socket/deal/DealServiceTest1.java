package com.dlz.plugin.socket.deal;

import com.dlz.plugin.socket.interfaces.IDealService;

/**
 * 服务器端业务处理测试1
 * @author dk
 */
public class DealServiceTest1 implements IDealService {
	//取得返回信息
	public String getResStr(String input){
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return "[input:["+input+"] return:myreta啊啊啊啊]";
	}
}
