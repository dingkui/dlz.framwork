package com.dlz.framework.redisqueue.consumer;

import com.dlz.comm.exception.SystemException;
import com.dlz.comm.util.ExceptionUtils;
import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.ValUtil;
import com.dlz.comm.util.encry.TraceUtil;
import com.dlz.framework.cache.RedisKeyMaker;
import com.dlz.framework.redisqueue.annotation.AnnoRedisQueueConsumer;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
     *
     * @param message
     */
    public abstract void doConsume(T message);

    @Autowired
    RedisKeyMaker keyMaker;

    @Autowired
    private JedisPool jedisPool;

    private Class<T> finalClassType = null;
    private String redisQueueName = null;

    @PostConstruct
    private void init() {
        AnnoRedisQueueConsumer annotation = this.getClass().getAnnotation(AnnoRedisQueueConsumer.class);
        if (annotation == null) {
            throw new SystemException(this.getClass().getName() + "必须注解 AnnoRedisQueueConsumer");
        }
        String queueName = annotation.value();
        SystemException.notEmpty(queueName, () -> "消费者未配置队列名字:" + this.getClass());
        redisQueueName = keyMaker.getKey(queueName);

        Type superClass = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        Class<T> classType = (Class<T>) type;
        ;
        if (type instanceof ParameterizedType) {
            classType = (Class<T>) ((ParameterizedType) type).getRawType();
        }
        finalClassType = classType;
        for (int i = 0; i < annotation.num(); i++) {
            doConsumer(i + 1, 0);
        }
    }

    private void doConsumer(int threadIndex, long millis) {
        Executors.newSingleThreadExecutor().submit(() -> {
            TraceUtil.setTraceId();
            long waitmillis = millis > 600000 ? 600000 : millis;
            if(waitmillis>0){
                try {
                    Thread.sleep(waitmillis);
                    log.info("this is retry ....");
                } catch (InterruptedException e1) {
                    log.error("thread sleep error!", e1);
                }
            }
            try {
                try (Jedis jedis = jedisPool.getResource()) {
                    log.info("队列[{}]消费者启动成功:{}[{}]", redisQueueName, this.getClass().getName(), threadIndex);
                    waitmillis = 0;
                    while (true) {
                        List<String> message = jedis.blpop(0, redisQueueName);
                        if (message != null && message.size() > 1) {
                            try{
                                log.info("{}接收消息:{}", this.getClass().getSimpleName(), message.get(1));
                                this.doConsume(ValUtil.getObj(message.get(1), finalClassType));
                            }catch (Exception e){
                                log.error("doConsume error:mgs={}",e.getMessage());
                                log.error(ExceptionUtils.getStackTrace(e));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                waitmillis += 10000;
                log.error("The Consumer in Queue [" + redisQueueName + "] Error.");
                log.error(ExceptionUtils.getStackTrace(e));
                log.info("it will retry after {} millis!", waitmillis);
                doConsumer(threadIndex, waitmillis);
//				throw e;
            }
        });
    }
}