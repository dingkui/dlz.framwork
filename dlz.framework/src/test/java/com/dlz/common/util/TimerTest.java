package com.dlz.common.util;


import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

public class TimerTest {

	Timer timer=new Timer();
	/**
	 * 检索多条数据
	 * @throws Exception
	 */
	@Test
	public void getList() throws Exception{
		 TimerTask tasks=new TimerTask() {
			 int i=0;
				@Override
				public void run() {
							System.out.println(" 完成："+i+++"%");
							if(i==10){
								this.cancel();
							}
					
				}
			};
			timer.schedule(tasks, 5000, 1000);
			Thread.sleep(20000);
	}
}
