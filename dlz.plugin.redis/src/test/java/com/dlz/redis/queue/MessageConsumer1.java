package com.dlz.redis.queue;

import org.springframework.stereotype.Component;

import com.dlz.plugin.redis.annotation.AnnoRedisQueueConsumer;
import com.dlz.plugin.redis.queue.consumer.ARedisQueueConsumer;

@Component
@AnnoRedisQueueConsumer("queue1")
public class MessageConsumer1 extends ARedisQueueConsumer<String> {
	@Override
	public void doConsume(String message) {
		System.out.println(message);
	}
}