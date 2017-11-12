package com.dlz.framework.ssme.util.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unchecked")
class XmlConfigUtil {
	private static final Logger logger = LoggerFactory.getLogger(XmlConfigUtil.class);
	private static Map<String,Map<String,Object>> configCache = new HashMap<String,Map<String,Object>>();
	
	public static Map<String,Object> loadXml(String path,String configName) {
		try {
			Map<String,Object> map= XMLUtil.loadXml(path); 
			configCache.put(configName, map);
			return map;
		} catch (Exception e) {
			logger.error(e.getMessage()+" path:"+path, e);
		}
		return null;
	}
	
	public static List<String> getList(String configName,String key) {
		try {
			Object o = configCache.get(configName).get(key);
			if(o==null){
				throw new Exception("信息未配置:key="+key);
			}
			if(o instanceof List){
				return (List<String>)o;
			}else{
				throw new Exception("设置的类型不对，应该为："+XMLUtil.XML_TYPE_LIST);
			}
		} catch (Exception e) {
			logger.error(e.getMessage()+" configName:"+configName, e);
		}
		return null;
	}
	public static Map<String,String> getMap(String configName,String key) {
		try {
			Object o = configCache.get(configName).get(key);
			if(o==null){
				throw new Exception("信息未配置:key="+key);
			}
			if(o instanceof Map){
				return (Map<String,String>)o;
			}else{
				throw new Exception("设置的类型不对，应该为："+XMLUtil.XML_TYPE_MAP);
			}
		} catch (Exception e) {
			logger.error(e.getMessage()+" configName:"+configName, e);
		}
		return null;
	}
	public static String getText(String configName,String key) {
		try {
			Object o = configCache.get(configName).get(key);
			if(o==null){
				throw new Exception("信息未配置:key="+key);
			}
			if(o instanceof String){
				return (String)o;
			}else{
				throw new Exception("设置的类型不对，应该为："+XMLUtil.XML_TYPE_TEXT);
			}
		} catch (Exception e) {
			logger.error(e.getMessage()+" configName:"+configName, e);
		}
		return null;
	}
	
	public static Object getObejct(String configName,String key) {
		try {
			Object o = configCache.get(configName).get(key);
			if(o==null){
				throw new Exception("信息未配置:key="+key);
			}
			return o;
		} catch (Exception e) {
			logger.error(e.getMessage()+" configName:"+configName, e);
		}
		return null;
	}
}
