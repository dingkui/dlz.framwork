package com.dlz.framework.task;

import org.slf4j.Logger;

public  class MyTimerTaskTest extends MyTimerTask<Long> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	@SuppressWarnings("unused")
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(MyTimerTaskTest.class);

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