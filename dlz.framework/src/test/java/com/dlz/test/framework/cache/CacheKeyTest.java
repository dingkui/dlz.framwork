package com.dlz.test.framework.cache;

import com.dlz.comm.json.JSONMap;
import com.dlz.framework.cache.aspect.ICacheKeyIf;

import java.lang.reflect.Method;

/**
 * AOP 缓存操作
 * 系统方法添加注解 @CacheAnno则支持缓存
 * @author dk 2020-06-05
 */
public class CacheKeyTest implements ICacheKeyIf {
    public String getKey(Method method, JSONMap paraMap){
        return "666";
    }
}
