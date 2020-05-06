package com.dlz.app.uim.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;  
/** 
 * 注解用户扩展角色信息ID
 * Author: dk 2018-05-28
 * Since: 
 */  
@Retention(RetentionPolicy.RUNTIME)   
@Target(ElementType.TYPE)  
public @interface AnnoRoleInfoId  
{  
	int value() default 0; 
} 