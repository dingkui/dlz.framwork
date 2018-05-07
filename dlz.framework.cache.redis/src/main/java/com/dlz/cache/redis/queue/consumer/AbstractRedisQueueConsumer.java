package com.dlz.cache.redis.queue.consumer;

import java.util.List;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.cache.redis.queue.annotation.RedisQueueConsumer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author 杨斌冰-工具组-技术中心
 *         <p>
 *         2018/3/1 15:31
 */
public abstract class AbstractRedisQueueConsumer<T> {

	private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JedisPool jedisPool;
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
		RedisQueueConsumer annotation = this.getClass().getAnnotation(RedisQueueConsumer.class);
		if(annotation!=null){
			init(annotation.value());
		}
	}

	public abstract void doConsume(T message);
	public void init(String queueName) {
		logger.info("The Consumer in Queue [{}] Started.", this.getClass().getName());
		Executors.newSingleThreadExecutor().submit(() -> {
			try {
				try (Jedis jedis = jedisPool.getResource()) {
					while (true) {
						List<String> message = jedis.blpop(0, queueName);
						if (message != null && message.size() > 1) {
							this.doConsume(new ObjectMapper().readValue(message.get(1), new TypeReference<T>(){}));
						}
					}
				}
			} catch (Exception e) {
				logger.error("The Consumer in Queue [" + queueName + "] Error.", e);
			}
		});
	}
}
