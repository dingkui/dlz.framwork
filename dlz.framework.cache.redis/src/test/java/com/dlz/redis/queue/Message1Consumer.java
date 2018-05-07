package com.dlz.redis.queue;

import org.springframework.stereotype.Component;

import com.dlz.cache.redis.queue.annotation.RedisQueueConsumer;
import com.dlz.cache.redis.queue.consumer.AbstractRedisQueueConsumer;

@Component
@RedisQueueConsumer("queue1")
public class Message1Consumer extends AbstractRedisQueueConsumer<String> {
	@Override
	public void doConsume(String message) {
		System.out.println(message);
	}
}