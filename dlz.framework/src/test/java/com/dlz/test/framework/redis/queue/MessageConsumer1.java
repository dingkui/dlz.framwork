//package com.dlz.test.framework.redis.queue;
//
//import com.dlz.framework.redis.queue.annotation.AnnoRedisQueueConsumer;
//import com.dlz.framework.redis.queue.consumer.ARedisQueueConsumer;
//import org.springframework.stereotype.Component;
//
//@Component
//@AnnoRedisQueueConsumer("queue1")
//public class MessageConsumer1 extends ARedisQueueConsumer<String> {
//	@Override
//	public void doConsume(String message) {
//		System.out.println(message);
//	}
//}