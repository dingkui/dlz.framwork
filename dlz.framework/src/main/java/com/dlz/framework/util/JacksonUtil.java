package com.dlz.framework.util;

import java.io.OutputStream;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.dlz.framework.bean.JSONList;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.exception.CodeException;
import com.dlz.framework.logger.MyLogger;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

/**
 * 2013 2013-9-13 下午4:54:15
 */
@SuppressWarnings({ "rawtypes" })
public class JacksonUtil {
	private static MyLogger logger = MyLogger.getLogger(JacksonUtil.class);
	private static ObjectMapper objectMapper = new ObjectMapper();

	static {
		// https://github.com/FasterXML/jackson-databind
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setSerializationInclusion(Include.NON_NULL);  

		/**
		 * 配置默认的日期转换格式 ，参考http://wiki.fasterxml.com/JacksonFAQDateHandling
		 */
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	public static String getJson(Object o){
		try {
			return objectMapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new CodeException("JSON转换异常"+e.getMessage(),e);
		}
	}
	public static void main(String[] args) {
//		JSONList l=new JSONList();
//		l.add(1);
//		l.add("xx");
//		System.out.println(getJson("aa"));
//		System.out.println(getJson(l));
//		System.out.println(getJson(null));
		System.out.println(Map.class.isAssignableFrom(JSONMap.class));
		System.out.println(ResultMap.class.isAssignableFrom(JSONMap.class));
	}
	
	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public static <T> T readValue(String content, Class<T> valueType) {
		try {
			return objectMapper.readValue(content, valueType);
		} catch (Exception e) {
			logger.error("JacksonUtil.readValue error,content:"+content);
			logger.error("JacksonUtil.readValue error,valueType:"+valueType);
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	public static <T> T readValue(String content, T valueType) {
		try {
			return objectMapper.readValue(content, new TypeReference<T>() {  
            });
		} catch (Exception e) {
			logger.error("JacksonUtil.readValue error,content:"+content);
			logger.error("JacksonUtil.readValue error,valueType:"+valueType);
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	

	public static JsonNode getJsonNode(String jsonStr, String key) {
		// token
		try {
			String[] split = StringUtils.split(key, ".");
			JsonNode jsonNode = objectMapper.readTree(jsonStr);
			JsonNode rootNode = jsonNode.get(split[0]);
			if (split.length <= 1) {
				return rootNode;
			}

			JsonNode tempNode = rootNode;
			for (int i = 1; i < split.length; i++) {
				tempNode = tempNode.get(split[i]);
				if (tempNode == null) {
					break;
				}
			}
			return tempNode;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static JsonNode string2json(String jsonStr) {
		try {
			return objectMapper.readTree(jsonStr);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T coverObj(Object o, Class<T> valueType) {
		try {
			if(o==null){
				return null;
			}
			if(valueType.isAssignableFrom(o.getClass())){
				return (T)o;
			}
			if(valueType.isAssignableFrom(JSONList.class)){
				return (T)new JSONList(o);
			}
			String str=null;
			if(o instanceof CharSequence){
				str=o.toString().trim();
			}else{
				str=JacksonUtil.getJson(o);
			}
//			if(valueType.isAssignableFrom(RestPara.class)){
//				return (T)new RestPara(str);
//			}
			return readValue(str, valueType);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	public static <T> T at(Object data,String key, Class<T> valueType){
		Object o=at(data, key);
		if(o==null){
			return null;
		}
		return coverObj(o, valueType);
	}
	
	/**
	 * 从对象中使用路径取出需要的值
	 * @param data 可以是json字符串，数组，集合或者对象
	 * @param key 对象路径，支持属性和index
	 * 		.符号表示属性操作
	 * 		[i]表示index,i设置为负数表示反方向读取，比如 -1表示倒数第一个
	 *  使用例子：{"info":{"a":[[{"b":1},{"c":2}],[{"d":3},{"e":4},{"f":5}]]}}
	 *  	要取出  c所在对象的属性：info.a[0][1].b
	 *      取出f所在对象 :info.a[1][2]
	 *      取出f所在对象 :info.a[1][-1]
	 * @return
	 */
	public static Object at(Object data,String key){
		if(data==null||"".equals(key)){
			return data;
		}
		if(data instanceof Object[]||data instanceof Collection){
			if(key.startsWith("[")){
				return getObjFromList(ValUtil.getList(data), key);
			}
			return null;
		}
			
		if(key.startsWith(".")){
			key=key.substring(1);
		}
		return getObjFromMap(ValUtil.getObj(data, Map.class), key);
	}
	private static Object getObjFromList(List list,String key){
		int size=list.size();
		int end = key.indexOf(']');
		int index=Integer.parseInt(key.substring(1, end));
		if(index<0){
			index+=size;
		}
		if(index<0||index>size){
			return null;
		}
		return at(list.get(index),key.substring(end+1));
	}
	
	private static Object getObjFromMap(Map para,String key){
		String pName=key;
		if(para.containsKey(pName)){
			return para.get(pName);
		}
		
		
		int index=key.indexOf('.');
		if(index>-1){
			pName=key.substring(0,index);
			if(para.containsKey(pName)){
				return at(para.get(pName), key.substring(index));
			}
		}
		
		index=pName.indexOf('[');
		if(index>-1){
			pName=key.substring(0,index);
			if(para.containsKey(pName)){
				return at(para.get(pName), key.substring(index));
			}
		}
		
		return null;
	}
	
	public static JSONResult nodeAsMap(JsonNode jsonNode) {
		JSONResult nodeMap=new JSONResult();
		Iterator<Entry<String, JsonNode>> fields=jsonNode.fields();
		while(fields.hasNext()){
			Entry<String, JsonNode> field=fields.next();
			JsonNode fi=field.getValue();
			if(fi.isObject()){
				nodeMap.put(field.getKey(),nodeAsMap(field.getValue()));
			}else{
				nodeMap.put(field.getKey(),fi.isNumber()?fi.asLong():fi.asText());
			}
		}
		return nodeMap;
	}

	public static String optionString(JsonNode jsonNode, String key, String defaultVale) {
		JsonNode node = jsonNode.get(key);
		if (node == null) {
			return defaultVale;
		}
		return node.asText();
	}

	public static String optionString(JsonNode jsonNode, String key) {

		return optionString(jsonNode, key, "");
	}

	public static int optionInt(JsonNode jsonNode, String key, int defaultVale) {
		JsonNode node = jsonNode.get(key);
		if (node == null) {
			return defaultVale;
		}
		return node.asInt();
	}

	public static int optionInt(JsonNode jsonNode, String key) {

		return optionInt(jsonNode, key, 0);
	}

	public static void xml2json(Class clazz, Reader reader, OutputStream os) {
		xml2json(clazz, reader, os, null);
	}

	public static void xml2json(Class clazz, Reader reader, OutputStream os, BeanProcesser beanProcesser) {
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshal = context.createUnmarshaller();

			Object bean = unmarshal.unmarshal(reader);

			ObjectMapper objectMapper = new ObjectMapper();
			JaxbAnnotationModule module = new JaxbAnnotationModule();
			objectMapper.registerModule(module);

			objectMapper.writeValue(os, bean);

			if (beanProcesser != null) {
				beanProcesser.processBean(bean);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static String writeValueAsString(Object bean) {
		// ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(bean);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}

	public interface BeanProcesser {
		public Object processBean(Object bean);
	}
}
