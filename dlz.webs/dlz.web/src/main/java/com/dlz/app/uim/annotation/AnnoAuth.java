package com.dlz.app.uim.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;  
/** 
 * 方法执行需要角色注解
 * Author: dk 2017-06-16
 * Since: 
 */  
@Retention(RetentionPolicy.RUNTIME)   
@Target({ElementType.METHOD,ElementType.TYPE})  
@Inherited
public @interface AnnoAuth  
{  
	String value() default "*"; 
} 