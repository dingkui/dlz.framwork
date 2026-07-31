package com.dlz.kit.json.jackson;

import com.dlz.kit.json.JSONList;
import com.dlz.kit.json.JSONMap;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.Deserializers;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/** Jackson-specific API. Add {@code dlz-json-jackson} to use this class. */
public final class JacksonUtil {
    private static final ObjectMapper MAPPER = createMapper();

    private JacksonUtil() {
    }

    public static ObjectMapper getInstance() {
        return MAPPER;
    }

    public static String getJson(Object value) {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Jackson serialization failed", e);
        }
    }

    public static byte[] toJsonAsBytes(Object value) {
        return getJson(value).getBytes(StandardCharsets.UTF_8);
    }

    public static JSONMap readValue(String content) {
        return readValue(content, JSONMap.class);
    }

    public static <T> T readValue(String content, Class<T> targetType) {
        try {
            return MAPPER.readValue(content, targetType);
        } catch (Exception e) {
            throw new IllegalArgumentException("Jackson deserialization failed", e);
        }
    }

    public static <T> T readValue(String content, JavaType targetType) {
        try {
            return MAPPER.readValue(content, targetType);
        } catch (Exception e) {
            throw new IllegalArgumentException("Jackson deserialization failed", e);
        }
    }

    public static <T> T readValue(String content, TypeReference<T> targetType) {
        try {
            return MAPPER.readValue(content, targetType);
        } catch (Exception e) {
            throw new IllegalArgumentException("Jackson deserialization failed", e);
        }
    }

    public static <T> List<T> readListValue(String content, Class<T> elementType) {
        return readValue(content, mkJavaType(List.class, elementType));
    }

    public static JSONList readList(String content) {
        return readValue(content, JSONList.class);
    }

    public static JsonNode valueToTree(Object value) {
        return MAPPER.valueToTree(value);
    }

    public static JsonNode readTree(String content) {
        try {
            return MAPPER.readTree(content);
        } catch (Exception e) {
            throw new IllegalArgumentException("Jackson tree parsing failed", e);
        }
    }

    public static <T> T treeToValue(TreeNode value, Class<T> targetType) {
        try {
            return MAPPER.treeToValue(value, targetType);
        } catch (Exception e) {
            throw new IllegalArgumentException("Jackson tree conversion failed", e);
        }
    }

    public static <T> T convertValue(Object value, Class<T> targetType) {
        return MAPPER.convertValue(value, targetType);
    }

    public static <T> T convertValue(Object value, JavaType targetType) {
        return MAPPER.convertValue(value, targetType);
    }

    public static <T> T convertValue(Object value, TypeReference<T> targetType) {
        return MAPPER.convertValue(value, targetType);
    }

    public static <T> T coverObj(Object value, Class<T> targetType) {
        return coverObj(value, mkJavaType(targetType));
    }

    public static <T> T coverObj(Object value, JavaType targetType) {
        if (value == null) {
            return null;
        }
        if (targetType.getRawClass().isInstance(value) && targetType.getBindings().isEmpty()) {
            return (T) value;
        }
        if (value instanceof CharSequence) {
            return readValue(value.toString(), targetType);
        }
        return MAPPER.convertValue(value, targetType);
    }

    public static JavaType mkJavaType(Class<?> rawType, Class<?>... parameterTypes) {
        if (parameterTypes == null || parameterTypes.length == 0) {
            return MAPPER.getTypeFactory().constructType(rawType);
        }
        return MAPPER.getTypeFactory().constructParametricType(rawType, parameterTypes);
    }

    public static JavaType mkJavaType(Type type) {
        return MAPPER.getTypeFactory().constructType(type);
    }

    private static ObjectMapper createMapper() {
        Deserializers deserializers = new Deserializers.Base() {
            @Override
            public JsonDeserializer<?> findBeanDeserializer(JavaType type, DeserializationConfig config,
                                                            BeanDescription beanDescription) {
                return type.getRawClass() == Object.class ? new JacksonObjectDeserializer() : null;
            }
        };
        DeserializerFactoryConfig factoryConfig = new DeserializerFactoryConfig()
                .withAdditionalDeserializers(deserializers);
        DefaultDeserializationContext context = new DefaultDeserializationContext.Impl(
                new BeanDeserializerFactory(factoryConfig));
        ObjectMapper mapper = new ObjectMapper(null, null, context);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.setLocale(Locale.CHINA);
        mapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        mapper.registerModule(DlzJavaTimeModule.INSTANCE);
        return mapper;
    }
}
