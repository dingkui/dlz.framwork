package com.dlz.redis.queue;

import java.util.List;

import com.dlz.framework.springframework.iproxy.anno.AnnoApi;
import com.dlz.plugin.redis.annotation.AnnoRedisQueueProvider;
import com.dlz.plugin.redis.enums.SendStrategyEn;

//通过RedisQueue接口定义消息队列生产者interface
//通过RedisQueueProvider接口定义生产者队列名称
@AnnoApi(handler="redisQueueProvider")
public interface ITestQueueProviderApi {
	@AnnoRedisQueueProvider(value="queue1",strategy=SendStrategyEn.ASYNC)
	void sendMessage1(String msg);

	@AnnoRedisQueueProvider(value="queue2",strategy=SendStrategyEn.ASYNC)
	void sendMessage2(List<String> msgList);
	
	@AnnoRedisQueueProvider(value="queue3",strategy=SendStrategyEn.ASYNC)
	void sendMessage3(String test);
}