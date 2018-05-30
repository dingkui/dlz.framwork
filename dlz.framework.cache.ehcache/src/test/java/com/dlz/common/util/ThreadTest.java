package com.dlz.common.util;


import org.junit.Test;

public class ThreadTest {

	/**
	 * 检索多条数据
	 * @throws Exception
	 */
	@Test
	public void getList() throws Exception{
		System.out.println(111);
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(222);
			}
		}).start();
		System.out.println(333);
	}
}
