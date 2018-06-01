package com.dlz.plugin.redis.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * redis队列消费者注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface AnnoRedisQueueConsumer {

    /**
     * 队列名称 
     * @return
     */
    String value() default "";
}
