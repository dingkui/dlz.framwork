package com.dlz.framework.redisqueue.enums;

/**
 * 消息发送策略
 */
public enum SendStrategyEn {
    SYNC("同步发送(不管是否成功消费)"),
    ASYNC("异步发送(不管是否成功消费)");
    SendStrategyEn(String _desc){
    }
}
