package com.dlz.plugin.socket.test.threads;

import java.util.HashSet;
import java.util.Set;

public class SynchronizedTest {
	
	private static Set<String> holderHandlerSet = new HashSet<String>();
	private static  void test1() throws InterruptedException{
		synchronized (SynchronizedTest.class) {
			System.out.println("test1 ...");
			for(String handler:holderHandlerSet){
				System.out.println(handler);
			}
		}
			Thread.sleep(2000);
			test1();
	}
	private static  void test2() throws InterruptedException{
		Thread.sleep(3000);
		synchronized (SynchronizedTest.class) {
			System.out.println("test2 ...");
			for(String handler:holderHandlerSet){
				holderHandlerSet.remove(handler);
				test2();
				return;
			}
		}
	}
	public static void main(String[] args) {
		holderHandlerSet.add("11");
		holderHandlerSet.add("222");
		holderHandlerSet.add("3333");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					test1();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					test2();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
}