package com.dlz.framework.util.bean;

import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.util.bean.anno.DictAnno;
import com.dlz.framework.util.system.Reflections;

import java.lang.reflect.Field;
import java.util.List;


public class DictUtil {

	/**
	 * 字典转换 转换数据 xxx 属性 需要有xxx_text属性
	 * @param data 要转换的数据 (要转换的数据中需要有DictAnno注解)
	 * @return 转换后的数据, 原属性不动,字典属性名为在原属性名后加"xxx_text"
	 */
	public static <T> List<T> autoFillDict(List<T> data) {
		if (data == null || data.isEmpty()) {
			return data;
		}
		IDictService dict= SpringHolder.getBean(IDictService.class);
		Class<T> tClass = (Class<T>)  data.get(0).getClass();
		Field[] objFields = tClass.getDeclaredFields();
		for (Field f:objFields) {
			DictAnno dictAnno = f.getAnnotation(DictAnno.class);
			if (dictAnno == null) {
				continue;
			}
			String target=dictAnno.target();
			if("".equals(target)){
				target= f.getName() +"_text";
			}
			String finalTarget = target;
			data.forEach(o->{
				Object v= Reflections.getFieldValue(o,f);
				Reflections.setFieldValue(o, finalTarget, dict.getVal(dictAnno.value(),dictAnno.multiple(),v));
			});
		}
		return data;
	}


	/**
	 * 字典转换 转换数据 xxx 属性 需要有xxx_text属性
	 * @param data 要转换的数据 (要转换的数据中需要有DictAnno注解)
	 * @return 转换后的数据, 原属性不动,字典属性名为在原属性名后加"xxx_text"
	 */
	public static <T> T autoFillDict(T data) {
		if (data == null) {
			return data;
		}
		IDictService dict= SpringHolder.getBean(IDictService.class);
		Class<T> tClass = (Class<T>)  data.getClass();
		Field[] objFields = tClass.getDeclaredFields();
		for (Field f:objFields) {
			DictAnno dictAnno = f.getAnnotation(DictAnno.class);
			if (dictAnno == null) {
				continue;
			}
			String target=dictAnno.target();
			if("".equals(target)){
				target= f.getName() +"_text";
			}
			String finalTarget = target;
			Object v= Reflections.getFieldValue(data,f);
			Reflections.setFieldValue(data, finalTarget, dict.getVal(dictAnno.value(),dictAnno.multiple(),v));
		}
		return data;
	}
}
