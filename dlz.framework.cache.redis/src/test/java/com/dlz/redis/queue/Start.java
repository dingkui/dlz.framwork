package com.dlz.redis.queue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.framework.holder.SpringHolder;

public class Start {
	@Before
	public void setUp() throws Exception {
		SpringHolder.init();
		testQueueDeal=SpringHolder.getBean(ITestQueueDeal.class);
	}
	
	@Autowired
	private ITestQueueDeal testQueueDeal;

	@Test
	public void queue1Task() {
		// 定义好消息队列的接口可直接调用，不需要管实现，实现由代理完成
		testQueueDeal.sendMessage1("Msg:[" + (int) (Math.random() * Integer.MAX_VALUE) + "]");
	}

	@Test
	public void queue2Task() {
		testQueueDeal.sendMessage2(Arrays.asList("Hello ", "World."));
	}
	
	@Test
	public void queue3Task() {
		// 定义好消息队列的接口可直接调用，不需要管实现，实现由代理完成
		testQueueDeal.sendMessage3("Msg3:[" + (int) (Math.random() * Integer.MAX_VALUE) + "]");
	}
	
    @Test
    public void test() {
        System.out.println(1);
    }
}