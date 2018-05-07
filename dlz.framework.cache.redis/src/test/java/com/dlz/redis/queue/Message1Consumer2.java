package com.dlz.redis.queue;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dlz.cache.redis.queue.annotation.RedisQueueConsumer;
import com.dlz.cache.redis.queue.consumer.AbstractRedisQueueConsumer;

//继承AbstractRedisQueueConsumer并通过RedisQueueConsumer注解标明队列名称即可
@Component
@RedisQueueConsumer("queue2")
public class Message1Consumer2 extends AbstractRedisQueueConsumer<List<String>> {
	@Override
	public void doConsume(List<String> msgList) {
		System.out.println(msgList);
	}
}