package com.dlz.common.util.string;

import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.dlz.common.util.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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
	private static Logger logger = LoggerFactory.getLogger(JacksonUtil.class);
	private static ObjectMapper objectMapper = new ObjectMapper();

	private static HttpHeaders headers = new HttpHeaders();
	private static MediaType mediaType = new MediaType("text", "html", Charset.forName("utf-8"));

	static {
		headers.setContentType(mediaType);
	}

	static {
		// https://github.com/FasterXML/jackson-databind
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		/**
		 * 配置默认的日期转换格式 ，参考http://wiki.fasterxml.com/JacksonFAQDateHandling
		 */
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	public static String getJson(Object o) throws JsonProcessingException {
		return objectMapper.writeValueAsString(o);
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

	public static <T> ResponseEntity<T> toResponseEntity(T obj) {
		return new ResponseEntity<T>(obj, headers, HttpStatus.OK);
	}
}
