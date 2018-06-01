package com.dlz.plugin.redis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dlz.plugin.redis.enums.SendStrategyEn;

/**
 * redis队列生产者注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AnnoRedisQueueProvider {

    /**
     * 队列名称
     * @return
     */
    String value() default "";

    /**
     * 发送策略，默认同步
     * @return
     */
    SendStrategyEn strategy() default SendStrategyEn.SYNC;
}
