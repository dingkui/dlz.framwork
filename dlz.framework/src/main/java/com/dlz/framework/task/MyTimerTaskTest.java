package com.dlz.framework.task;

import com.dlz.framework.logger.MyLogger;

public  class MyTimerTaskTest extends MyTimerTask<Long> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	@SuppressWarnings("unused")
	private static MyLogger logger = MyLogger.getLogger(MyTimerTaskTest.class);

	@Override
	public void done(Long t) {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print(" "+t);
	}
}