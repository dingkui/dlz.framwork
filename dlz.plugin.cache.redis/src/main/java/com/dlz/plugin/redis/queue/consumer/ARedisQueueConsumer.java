package com.dlz.plugin.redis.queue.consumer;

import java.util.List;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.codec.CodecException;

import com.dlz.framework.util.JacksonUtil;
import com.dlz.plugin.redis.annotation.AnnoRedisQueueConsumer;
import com.fasterxml.jackson.core.type.TypeReference;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis队列消费者基类
 * 消费者需要注解 AnnoRedisQueueConsumer 消费者队列的名称
 */
public abstract class ARedisQueueConsumer<T> {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 消费者处理逻辑
	 * @param message
	 */
	public abstract void doConsume(T message);
	
	/**
	 * 是否已经初始化
	 */
	private boolean hasInit=false;
	
	@Autowired
    private JedisPool jedisPool;
	public void setJedisPool(JedisPool jedisPool) {
		if(hasInit){
			throw new CodecException(this.getClass().getName()+"不能重复初始化");
		}
		hasInit=true;
		this.jedisPool = jedisPool;
		init();
	}


	private void init() {
		AnnoRedisQueueConsumer annotation = this.getClass().getAnnotation(AnnoRedisQueueConsumer.class);
		if(annotation==null){
			throw new CodecException(this.getClass().getName()+"必须注解 AnnoRedisQueueConsumer");
		}
		String queueName=annotation.value();
		logger.info("The Consumer in Queue [{}] Started.", this.getClass().getName());
		TypeReference<T> paratype=new TypeReference<T>(){};
		Executors.newSingleThreadExecutor().submit(() -> {
			try {
				try (Jedis jedis = jedisPool.getResource()) {
					while (true) {
						List<String> message = jedis.blpop(0, queueName);
						if (message != null && message.size() > 1) {
							this.doConsume(JacksonUtil.readValue(message.get(1), paratype));
						}
					}
				}
			} catch (Exception e) {
				logger.error("The Consumer in Queue [" + queueName + "] Error.", e);
			}
		});
	}
}
