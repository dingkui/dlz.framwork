package com.dlz.redis.queue;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dlz.plugin.redis.annotation.AnnoRedisQueueConsumer;
import com.dlz.plugin.redis.queue.consumer.ARedisQueueConsumer;

//继承AbstractRedisQueueConsumer并通过RedisQueueConsumer注解标明队列名称即可
@Component
@AnnoRedisQueueConsumer("queue2")
public class MessageConsumer2 extends ARedisQueueConsumer<List<String>> {
	@Override
	public void doConsume(List<String> msgList) {
		System.out.println(msgList);
	}
}