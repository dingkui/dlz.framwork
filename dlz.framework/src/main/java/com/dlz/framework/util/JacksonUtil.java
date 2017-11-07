package com.dlz.framework.util;

import java.io.OutputStream;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.exception.CodeException;
import com.dlz.framework.logger.MyLogger;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
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
		objectMapper.setSerializationInclusion(Include.USE_DEFAULTS);  

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
	
	public static <T> T coverObj(Object o, Class<T> valueType) {
		try {
			return readValue(getJson(o), valueType);
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
	
	@SuppressWarnings("unchecked")
	public static Object at(Object data,String key){
		if(data==null){
			return null;
		}
		Map<String,Object> para=null;
		if(data instanceof Map) {
			para=(Map<String,Object>)data;
		}else if(data instanceof String){
			para=readValue((String)data,Map.class);
		}else{
			para=readValue(getJson(data),Map.class);
		}
		
		if(para.containsKey(key)){
			return para.get(key);
		}
		int index=key.indexOf(".");
		if(index>-1){
			String pName=key.substring(0,index);
			if(para.containsKey(pName)){
				Object o=para.get(pName);
				if(o instanceof Map){
					return at(o, key.substring(index+1));
				}
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
