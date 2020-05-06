package com.dlz.web.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;  
/** 
 * 方法执行校验注解 
 * Author: dk 2017-06-16
 * Since: 
 */  
@Retention(RetentionPolicy.RUNTIME)   
@Target(ElementType.METHOD)   
public @interface AnnoValidate  
{  
	String value() default "*"; 
} 