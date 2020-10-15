package com.dlz.framework.cache.aspect;

import com.dlz.framework.cache.ICache;
import com.dlz.framework.cache.service.impl.CacheEhcahe;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识不是数据库操作元素
 *
 * @author dk 2018-05-28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheEvictAnno {
    /**
     * 表名
     *
     * @return
     */
    String value() default "";

	/**
	 * 键值的key 方法的参数名 如 id,key,bean等，支持 bean.id形式
	 *
	 * @return
	 */
	String key();

//	/**
//	 * 缓存实现
//	 *
//	 * @return
//	 */
//	Class<? extends ICache> cacheClass() default CacheEhcahe.class;
//
//    /**
//     * 缓存时间 毫秒
//     *
//     * @return
//     */
//    long cacheTime() default 3600000L;

}
