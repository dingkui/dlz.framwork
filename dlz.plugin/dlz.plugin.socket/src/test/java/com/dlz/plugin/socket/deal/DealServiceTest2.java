package com.dlz.plugin.socket.deal;

import com.dlz.plugin.socket.interfaces.IDealService;

/**
 * 服务器测试程序
 * @author dk
 */
public class DealServiceTest2 implements IDealService {

	//取得返回信息
	public String getResStr(String input){
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "r:"+input;
	}
}
