package com.dlz.test.framework.redis.queue;

import com.dlz.framework.holder.SpringHolder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class Start {

	@Before
	public void setUp() throws Exception {
		SpringHolder.init();
		testQueueDeal=SpringHolder.getBean(ITestQueueProviderApi.class);
	}
	
	@Autowired
	private ITestQueueProviderApi testQueueDeal;

	@Test
	public void queue1Task() {
		// 定义好消息队列的接口可直接调用，不需要管实现，实现由代理完成
		testQueueDeal.sendMessage1("Msg:[" + (int) (Math.random() * Integer.MAX_VALUE) + "]");
		testQueueDeal.sendMessage2(Arrays.asList("Hello ", "World."));
		// 定义好消息队列的接口可直接调用，不需要管实现，实现由代理完成
		testQueueDeal.sendMessage3("Msg3:[" + (int) (Math.random() * Integer.MAX_VALUE) + "]");
		// 定义好消息队列的接口可直接调用，不需要管实现，实现由代理完成
		testQueueDeal.sendMessage1("Msg:[" + (int) (Math.random() * Integer.MAX_VALUE) + "]");
		testQueueDeal.sendMessage2(Arrays.asList("Hello ", "World."));
		// 定义好消息队列的接口可直接调用，不需要管实现，实现由代理完成
		testQueueDeal.sendMessage3("Msg3:[" + (int) (Math.random() * Integer.MAX_VALUE) + "]");
		// 定义好消息队列的接口可直接调用，不需要管实现，实现由代理完成
		testQueueDeal.sendMessage1("Msg:[" + (int) (Math.random() * Integer.MAX_VALUE) + "]");
		testQueueDeal.sendMessage2(Arrays.asList("Hello ", "World."));
		// 定义好消息队列的接口可直接调用，不需要管实现，实现由代理完成
		testQueueDeal.sendMessage3("Msg3:[" + (int) (Math.random() * Integer.MAX_VALUE) + "]");
		// 定义好消息队列的接口可直接调用，不需要管实现，实现由代理完成
		testQueueDeal.sendMessage1("Msg:[" + (int) (Math.random() * Integer.MAX_VALUE) + "]");
		testQueueDeal.sendMessage2(Arrays.asList("Hello ", "World."));
		// 定义好消息队列的接口可直接调用，不需要管实现，实现由代理完成
		testQueueDeal.sendMessage3("Msg3:[" + (int) (Math.random() * Integer.MAX_VALUE) + "]");
		// 定义好消息队列的接口可直接调用，不需要管实现，实现由代理完成
		testQueueDeal.sendMessage1("Msg:[" + (int) (Math.random() * Integer.MAX_VALUE) + "]");
		testQueueDeal.sendMessage2(Arrays.asList("Hello ", "World."));
		// 定义好消息队列的接口可直接调用，不需要管实现，实现由代理完成
		testQueueDeal.sendMessage3("Msg3:[" + (int) (Math.random() * Integer.MAX_VALUE) + "]");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Test
    public void test() {
        System.out.println(1);
    }
}