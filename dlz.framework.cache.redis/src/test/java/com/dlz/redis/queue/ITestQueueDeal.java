package com.dlz.redis.queue;

import java.util.List;

import com.dlz.cache.redis.queue.annotation.RedisQueueProvider;
import com.dlz.framework.springframework.iproxy.anno.AnnoInterfaceDeal;

//通过RedisQueue接口定义消息队列生产者interface
//通过RedisQueueProvider接口定义生产者队列名称
@AnnoInterfaceDeal("redisQueue")
public interface ITestQueueDeal {
	@RedisQueueProvider("queue1")
	void sendMessage1(String msg);

	@RedisQueueProvider("queue2")
	void sendMessage2(List<String> msgList);
	
	@RedisQueueProvider("queue3")
	void sendMessage3(String test);
}