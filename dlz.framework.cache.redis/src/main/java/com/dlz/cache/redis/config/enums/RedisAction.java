package com.dlz.cache.redis.config.enums;

/**
 * 消息发送策略
 * @author 杨斌冰-工具组-技术中心
 *         <p>
 *         2018/3/1 14:05
 */
public enum RedisAction {
    STR("同步发送(不管是否成功消费)"),
    LIST("异步发送(不管是否成功消费)"),
	MAP("异步发送(不管是否成功消费)");

    private String desc;
    RedisAction(String _desc){
        this.desc = _desc;
    }
}
