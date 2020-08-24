package com.dlz.framework.util.bean.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字典转换注解
 *
 * 加上注解在使用beanUtil比较属性差异时, 会转换为字典中的数据
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DictAnno {
	/**
	 * 字典type
	 * @return /
	 */
    String value() default "";

	/**
	 * 设置的字段名
	 * @return /
	 */
	String target() default "";

	/**
	 * 判断是否是逗号分隔的多重字典转换属性
	 * @return /
	 */
	boolean multiple() default false;
}
