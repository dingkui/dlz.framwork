package com.dlz.app.uim.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;  
/** 
 * 注解用户扩展信息名称
 * Author: dk 2018-05-28
 * Since: 
 */  
@Retention(RetentionPolicy.RUNTIME)   
@Target(ElementType.TYPE)  
@Inherited
public @interface AnnoExtInfoName  
{  
	String value() default ""; 
} 