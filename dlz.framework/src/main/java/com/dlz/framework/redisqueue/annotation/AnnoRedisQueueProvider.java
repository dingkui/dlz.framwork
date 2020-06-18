package com.dlz.framework.redisqueue.annotation;

import com.dlz.framework.redisqueue.enums.SendStrategyEn;

import java.lang.annotation.*;

/**
 * redis队列生产者注解
 * eg:
 * <pre>
 * @AnnoApi(handler="redisQueueProvider")
 * public interface ITestQueueProviderApi {
 *    @AnnoRedisQueueProvider(value="queue1",strategy=SendStrategyEn.ASYNC)
 *    void sendMessage1(String msg);
 *
 *    @AnnoRedisQueueProvider(value="queue2",strategy=SendStrategyEn.ASYNC)
 *    void sendMessage2(List<String> msgList);
 * }
 * </pre>
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
