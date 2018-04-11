package com.dlz.framework.util.config;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.dlz.framework.bean.JSONList;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.util.ValUtil;
@SuppressWarnings("unchecked")
public class XmlConfigUtil {
	final static String XML_ID = "id";
	final static String XML_TYPE_LIST = "list";
	final static String XML_TYPE_MAP = "map";
	final static String XML_TYPE_ITEM = "item";
	
	private static Map<String,JSONMap> configCache = new HashMap<String,JSONMap>();
	private static Object readXmlAsObject(Element element) {
		String type = element.getName().toLowerCase();
		if (XML_TYPE_MAP.equals(type)) {
			return readXmlAsMap(element);
		}else if(XML_TYPE_LIST.equals(type)) {
			return readXmlAsList(element);
		}else if(XML_TYPE_ITEM.equals(type)) {
			return element.getText();
		}
		throw new RuntimeException("节点不支持："+type);
	}

	private static JSONMap readXmlAsMap(Element element) {
		JSONMap map = new JSONMap();
		for (Element el : (List<Element>) element.elements()) {
			String id = el.attributeValue(XML_ID);
			if (id == null) {
				throw new RuntimeException(el.getName() + "未配置" + XML_ID);
			}
			map.put(id, readXmlAsObject(el));
		}
		return map;
	}

	private static List<Object> readXmlAsList(Element element) {
		JSONList list = new JSONList();
		for (Element el : (List<Element>) element.elements()) {
			list.add(readXmlAsObject(el));
		}
		return list;
	}
	
	private static JSONMap getConfig(String configName) {
		JSONMap config = configCache.get(configName);
		if (config != null) {
			return config;
		}
		URL u = XmlConfigUtil.class.getClassLoader().getResource(configName + ".xml");
		if (u != null) {
			try {
				config = (JSONMap)readXmlAsObject(XMLUtil.readXmlAsDoc(u.getFile()).getRootElement());
				configCache.put(configName, config);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		if (config == null) {
			throw new RuntimeException("配置文件未找到：" + configName + ".xml");
		}
		return config;
	}
	
	public static void reload() {
		configCache.clear();
	}
	
	public static JSONList getList(String configName,String key) {
		Object configInfo = getConfig(configName).get(key);
		if(configInfo instanceof JSONList){
			return (JSONList)configInfo;
		}else{
			throw new RuntimeException(key+"设置的类型不对，应该为："+XML_TYPE_LIST);
		}
	}
	public static JSONMap getMap(String configName,String key) {
		Object configInfo = getConfig(configName).get(key);
		if(configInfo instanceof JSONMap){
			return (JSONMap)configInfo;
		}else{
			throw new RuntimeException(key+"设置的类型不对，应该为："+XML_TYPE_MAP);
		}
	}
	public static String getStr(String configName,String key) {
		Object configInfo = getConfig(configName).get(key);
		if(configInfo instanceof String){
			return (String)configInfo;
		}else{
			throw new RuntimeException(key+"设置的类型不对，应该为："+XML_TYPE_ITEM);
		}
	}
	public static Integer getInteger(String configName,String key){
		return ValUtil.getInt(getStr(configName, key));
	}
	public static Long getLong(String configName,String key){
		return ValUtil.getLong(getStr(configName, key));
	}
	public static Float getFloat(String configName,String key){
		return ValUtil.getFloat(getStr(configName, key));
	}
	public static <T> T getObj(String configName,String key,Class<T> classs){
		return ValUtil.getObj(getConfig(configName).get(key),classs);
	}
}
