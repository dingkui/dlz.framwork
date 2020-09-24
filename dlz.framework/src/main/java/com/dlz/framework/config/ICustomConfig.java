package com.dlz.framework.config;


/**
 * 客户端配置取得接口
 *
 */
public interface ICustomConfig  {
     String get(String key);
     void set(String key,String value);
}
