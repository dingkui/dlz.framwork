package com.dlz.web.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;  
/** 
 * 方法过滤器注解
 * Author: dk 2017-06-28
 */  
@Retention(RetentionPolicy.RUNTIME)   
@Target(ElementType.METHOD)   
public @interface AnnoInterceptor  
{  
	String value() default ""; 
} 