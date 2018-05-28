package com.dlz.framework.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识不是数据库操作元素
 * @author dk 2018-05-28
 */
@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.TYPE) 
public @interface AnnoTable {
	/**
	 * 表名
	 * @return
	 */
	String value();
	/**
	 * 主键名，目前不支持复合主键
	 * @return
	 */
	String pk() default "";
	/**
	 * 查询的sqlkey
	 * @return
	 */
	String sqlKey() default "";
}
