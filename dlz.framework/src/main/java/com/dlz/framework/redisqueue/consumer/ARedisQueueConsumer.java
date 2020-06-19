package com.dlz.framework.redisqueue.consumer;

import com.dlz.comm.exception.SystemException;
import com.dlz.comm.util.JacksonUtil;
import com.dlz.framework.cache.RedisKeyMaker;
import com.dlz.framework.redisqueue.annotation.AnnoRedisQueueConsumer;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * redis队列消费者基类
 * 消费者需要注解 AnnoRedisQueueConsumer 消费者队列的名称
 */
@Slf4j
public abstract class ARedisQueueConsumer<T> {

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
	RedisKeyMaker keyMaker;
	
	@Autowired
    private JedisPool jedisPool;
//	public void setJedisPool(JedisPool jedisPool) {
//		if(hasInit){
//			throw new SystemException(this.getClass().getName()+"不能重复初始化");
//		}
//		hasInit=true;
//		this.jedisPool = jedisPool;
//		init();
//	}

	@PostConstruct
	private void init() {
		AnnoRedisQueueConsumer annotation = this.getClass().getAnnotation(AnnoRedisQueueConsumer.class);
		if(annotation==null){
			throw new SystemException(this.getClass().getName()+"必须注解 AnnoRedisQueueConsumer");
		}
		String queueName=annotation.value();
		SystemException.notEmpty(queueName,()->"消费者未配置队列名字:"+this.getClass());
		String redisQueueName = keyMaker.getKey(queueName);

		TypeReference<T> paratype=new TypeReference<T>(){};
		for (int i = 0; i < annotation.num(); i++) {
			int finalI = i;
			Executors.newSingleThreadExecutor().submit(() -> {
				try {
					log.info("队列[{}]消费者启动成功:{}[{}]", redisQueueName, this.getClass().getName(), finalI + 1);
					try (Jedis jedis = jedisPool.getResource()) {
						while (true) {
							try {
								List<String> message = jedis.blpop(0, redisQueueName);
								if (message != null && message.size() > 1) {
									log.info("{}接收消息:{}", this.getClass().getSimpleName(), message.get(1));
									this.doConsume(JacksonUtil.readValue(message.get(1), paratype));
								}
							}catch (Exception e) {
								log.error("The Consumer in Queue [" + redisQueueName + "] Error.", e);
							}
						}
					}
				} catch (Exception e) {
					log.error("The Consumer in Queue [" + redisQueueName + "] Error.", e);
				}
			});
		}
//		ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
//				new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
//		executorService.submit(() -> {
//			try {
//				try (Jedis jedis = jedisPool.getResource()) {
//					while (true) {
//						List<String> message = jedis.blpop(0, queueName);
//						if (message != null && message.size() > 1) {
//							this.doConsume(JacksonUtil.readValue(message.get(1), paratype));
//						}
//					}
//				}
//			} catch (Exception e) {
//				logger.error("The Consumer in Queue [" + queueName + "] Error.", e);
//			}
//		});
	}
}
