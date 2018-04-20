package com.dlz.common.util.config;

import java.net.URL;
import java.text.MessageFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLMessageUtil {
	private static final Logger logger = LoggerFactory.getLogger(XMLMessageUtil.class);
	
	private static String mesaageConfigName="message";
	
	static {
		load();
	}
	public static void load() {
		load(mesaageConfigName);
	}
	/**
	 * 加载xml配置
	 * @param configName
	 */
	private static void load(String configName) {
		  URL u = XMLMessageUtil.class.getClassLoader().getResource(configName+".xml");
		  if(u!=null){
		  	XmlConfigUtil.loadXml(u.getFile(), mesaageConfigName);
		  }
	}
	
	/**
	 * 取得配置文件中Map中的对应的key信息
	 * @param mapKey
	 * @param key
	 * @param lang
	 * @param arguments
	 * @return
	 */
	public static String getMapStr(String mapKey,String key,String lang,Object[] arguments){
		lang=lang==null?"":"_"+lang;
		Map<String, String> m = XmlConfigUtil.getMap(mesaageConfigName+lang, mapKey);
		if(m==null){
			logger.error("message未配置："+mesaageConfigName+lang+".xml id:"+mapKey);
			return mapKey+"__"+key;
		}
		String message=m.get(key);
		if(message==null){
			logger.error("message未配置："+mesaageConfigName+lang+".xml mapKey:"+mapKey+" id:"+key);
			return mapKey+"__"+key;
		}
		return MessageFormat.format(message, arguments);
	}
	private static String getStr(String key,String lang,Object[] arguments){
		lang=lang==null?"":"_"+lang;
		String message = XmlConfigUtil.getText(mesaageConfigName+lang, key);
		if(message==null){
			logger.error("message未配置："+mesaageConfigName+lang+".xml id:"+key);
			return key;
		}
		return MessageFormat.format(message, arguments);
	}
	
	public static String getSms(String key,String lang,Object[] arguments){
		return getMapStr("sms", key, lang, arguments);
	}
	
	public static String getSiteMsg(String key,String lang,Object[] arguments){
		return getMapStr("siteMsg", key, lang, arguments);
	}
	
	public static String getSms(String key,Object[] arguments){
		return getSms(key, null, arguments);
	}
	
	public static String getMessage(String key,String lang,Object[] arguments){
		return getStr(key, lang, arguments);
	}
	public static String getMessage(String key,Object[] arguments){
		return getStr(key, null, arguments);
	}
	public static String getMessage(String key,String lang){
		return getStr(key, lang, null);
	}
	public static String getMessage(String key){
		return getStr(key, null, null);
	}
}
