package com.dlz.test.framework.redis.queue;

import com.dlz.framework.redisqueue.annotation.AnnoRedisQueueConsumer;
import com.dlz.framework.redisqueue.consumer.ARedisQueueConsumer;
import org.springframework.stereotype.Component;

import java.util.List;

//继承AbstractRedisQueueConsumer并通过RedisQueueConsumer注解标明队列名称即可
@Component
@AnnoRedisQueueConsumer("queue2")
public class MessageConsumer2 extends ARedisQueueConsumer<List<String>> {
	@Override
	public void doConsume(List<String> msgList) {
		System.out.println(msgList);
	}
}