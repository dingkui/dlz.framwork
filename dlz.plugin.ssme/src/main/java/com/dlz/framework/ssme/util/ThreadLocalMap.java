package com.dlz.framework.ssme.util;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"rawtypes","unchecked"})
public class ThreadLocalMap{
	private static ThreadLocal<Map> serialNum = new ThreadLocal<Map>(){
		protected Map initialValue() {
			return new HashMap();
		}
	};
	public static void put(String key,Object value){
		(serialNum.get()).put(key, value);
	}
	public static <T> T get(String key,Class T) {
		return (T)(serialNum.get().get(key));
	}
	public static Object get(String key) {
		return serialNum.get().get(key);
	}
	public static String getString(String key) {
		return (String)serialNum.get().get(key);
	}
}