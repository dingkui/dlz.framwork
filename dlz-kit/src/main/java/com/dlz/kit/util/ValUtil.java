package com.dlz.kit.util;

import com.dlz.kit.exception.SystemException;
import com.dlz.kit.json.JSONList;
import com.dlz.kit.json.JSONMap;
import com.dlz.kit.util.system.FieldReflections;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对象转换工具类
 *
 * @author dk 2017-11-03
 */
@Slf4j
public class ValUtil {
    private ValUtil() {
    }

    /**
     * 匹配路径中的数组索引，如 c[1] 中的 [1]
     */
    private static final Pattern INDEX_PATTERN = Pattern.compile("\\[(?<idx>\\d+)\\]");
    public final static Integer ZERO_INT = 0;
    public final static Long ZERO_LONG = 0l;
    public final static Float ZERO_FLOAT = 0f;
    public final static Double ZERO_DOUBLE = 0.0;
    public final static String STR_BLANK = "";
    public final static String STR_NULL = "null";

    private static Number toNumber(Object input, Number defaultV) {
        if (input == null || "".equals(input)) {
            return defaultV;
        }
        if (input instanceof Number) {
            return (Number) input;
        }
        // 快速失败：无效数字格式立即抛出异常
        return new BigDecimal(input.toString().trim());
    }

    public static BigDecimal toBigDecimal(Object input) {
        return toBigDecimal(input, null);
    }

    public static BigDecimal toBigDecimal(Object input, BigDecimal defaultV) {
        if (input == null || "".equals(input)) {
            return defaultV;
        }
        if (input instanceof BigDecimal) {
            return (BigDecimal) input;
        }
        // Float 和 Double 需要通过 toString 避免精度问题
        if (input instanceof Float || input instanceof Double) {
            return new BigDecimal(input.toString());
        }
        // Integer 和 Long 可以直接转换
        if (input instanceof Integer || input instanceof Long) {
            return new BigDecimal(((Number) input).longValue());
        }
        return new BigDecimal(input.toString());
    }

    public static BigDecimal toBigDecimalZero(Object input) {
        return toBigDecimal(input, BigDecimal.ZERO);
    }


    public static Double toDouble(Object input) {
        return toDouble(input, null);
    }

    public static Double toDouble(Object input, Double defaultV) {
        Number o = toNumber(input, null);
        if (o == null) {
            return defaultV;
        }
        return o.doubleValue();
    }

    public static Double toDoubleZero(Object input) {
        return toDouble(input, ZERO_DOUBLE);
    }

    public static Float toFloat(Object input) {
        return toFloat(input, null);
    }

    public static Float toFloat(Object input, Float defaultV) {
        Number o = toNumber(input, null);
        if (o == null) {
            return defaultV;
        }
        return o.floatValue();
    }

    public static Float toFloatZero(Object input) {
        return toFloat(input, ZERO_FLOAT);
    }

    public static Integer toInt(Object input) {
        return toInt(input, null);
    }

    public static Integer toInt(Object input, Integer defaultV) {
        Number o = toNumber(input, null);
        if (o == null) {
            return defaultV;
        }
        return o.intValue();
    }

    public static Integer toIntZero(Object input) {
        return toInt(input, ZERO_INT);
    }

    public static Long toLong(Object input) {
        return toLong(input, null);
    }

    public static Long toLong(Object input, Long defaultV) {
        Number o = toNumber(input, null);
        if (o == null) {
            return defaultV;
        }
        return o.longValue();
    }

    public static Long toLongZero(Object input) {
        return toLong(input, ZERO_LONG);
    }


    public static Boolean toBoolean(Object input) {
        return toBoolean(input, false);
    }

    public static Boolean toBoolean(Object input, Boolean defaultV) {
        if (input == null) {
            return defaultV;
        }
        if (input instanceof Boolean) {
            return (Boolean) input;
        }
        if (input instanceof Number) {
            return ((Number) input).intValue() != 0;
        }
        String r = input.toString().trim().toLowerCase();
        // 明确的 false 值
        if ("false".equals(r) || "0".equals(r) || "".equals(r)) {
            return false;
        }
        // 其他都是 true
        return true;
    }

    public static String toStrBlank(Object input) {
        return toStr(input, STR_BLANK);
    }

    public static String toStrWithEmpty(Object input, String defaultValue) {
        if (null == input || input.equals(STR_NULL) || input.equals(STR_BLANK)) {
            return defaultValue;
        }
        return toStr(input, defaultValue);
    }

    public static String toStr(Object input) {
        return toStr(input, null);
    }

    public static String toStr(Object input, String defaultV) {
        if (input == null) {
            return defaultV;
        }
        if (input instanceof CharSequence || input instanceof Number) {
            return input.toString();
        }
        if (input instanceof Date) {
            return DateUtil.DATETIME.format((Date) input);
        } else if (input instanceof LocalDateTime) {
            return DateUtil.DATETIME.format((LocalDateTime) input);
        } else if (input instanceof LocalDate) {
            return DateUtil.DATE.format((LocalDate) input);
        } else if (input instanceof TemporalAccessor) {
            return input.toString();
        }
        return JsonUtil.getJson(input);
    }

    public static String toStr(byte[] input, Charset charset) {
        return new String(input, charset);
    }

    public static String toStr(byte[] input) {
        if (input == null) {
            return null;
        }
        return new String(input, StandardCharsets.UTF_8);
    }

    public static JSONList toList(Object input) {
        return toList(input, (List) null);
    }

    public static JSONList toList(Object input, Collection defaultV) {
        if (input == null) {
            return new JSONList(defaultV);
        }
        if (input instanceof JSONList) {
            return (JSONList) input;
        }
        return new JSONList(input);
    }

    public static <T> List<T> toList(Object input, Class<T> clazz) {
        T[] array = toArray(input, clazz);
        if (array == null) {
            return null;
        }
        return Arrays.asList(array);
    }

    public static JSONList toListEmpty(Object input) {
        return toList(input, new ArrayList());
    }


    public static Object[] toArray(Object input) {
        return toArray(input, (Object[]) null);
    }

    public static Object[] toArray(Object input, Object[] defaultV) {
        if (input == null) {
            return defaultV;
        }
        if (input instanceof Object[]) {
            return (Object[]) input;
        } else if (input instanceof Collection) {
            return ((Collection) input).toArray();
        }
        return new JSONList(input).toArray();
    }

    public static <T> T[] toArray(Collection input, Class<T> clazz) {
        if (input == null) {
            return null;
        }
        T[] re = (T[]) Array.newInstance(clazz, input.size());
        int i = 0;
        for (Object item : input) {
            re[i++] = toObj(item, clazz);
        }
        return re;
    }

    public static <T> T[] toArray(Object[] input, Class<T> clazz) {
        if (input == null) {
            return null;
        }
        T[] re = (T[]) Array.newInstance(clazz, input.length);
        for (int i = 0; i < input.length; i++) {
            re[i] = toObj(input[i], clazz);
        }
        return re;
    }

    public static <T> T[] toArray(Object input, Class<T> clazz) {
        if (input == null) {
            return null;
        }
        if (input instanceof Collection) {
            return toArray((Collection) input, clazz);
        }
        if (input instanceof Object[]) {
            return toArray((Object[]) input, clazz);
        }
        if (input instanceof CharSequence) {
            String string = input.toString();
            if (JsonUtil.isJsonArray(string)) {
                return toArray(JsonUtil.readListValue(string, clazz), clazz);
            }
            return toArray(string.split(","), clazz);
        }
        String string = toStr(input);
        if (JsonUtil.isJsonArray(string)) {
            return toArray(JsonUtil.readListValue(string, clazz), clazz);
        }
        throw new SystemException("参数不能转换成数组:" + string);
    }

    public static LocalDateTime toLocalDateTime(Object input, String format, LocalDateTime defaultV) {
        if (input == null) {
            return defaultV;
        }
        if (input instanceof LocalDateTime) {
            return (LocalDateTime) input;
        }
        if (input instanceof Date) {
            return DateUtil.getLocalDateTime((Date) input);
        }
        if (input instanceof Number) {
            return DateUtil.getLocalDateTime(new Date(((Number) input).longValue()));
        }
        return DateUtil.getLocalDateTime(toStr(input), format);
    }

    public static LocalDateTime toLocalDateTime(Object input) {
        return toLocalDateTime(input, null, null);
    }

    public static LocalDateTime toLocalDateTime(Object input, String format) {
        return toLocalDateTime(input, format, null);
    }

    public static Date toDate(Object input) {
        return toDate(input, (Date) null);
    }

    public static Date toDate(String input, String format) {
        return DateUtil.getDate(input, format);
    }

    public static Date toDate(Object input, Date defaultV) {
        if (input == null) {
            return defaultV;
        }
        if (input instanceof Date) {
            return (Date) input;
        }
        if (input instanceof Number) {
            return new Date(((Number) input).longValue());
        }
        if (input instanceof LocalDateTime) {
            return DateUtil.getDate((LocalDateTime) input);
        }
        if (input instanceof LocalDate) {
            return DateUtil.getDate((LocalDate) input);
        }
        return DateUtil.getDate(toStr(input));
    }

    public static String toDateStr(Object input) {
        Date date = toDate(input);
        if (date == null) {
            return "";
        }
        return DateUtil.DATETIME.format(date);
    }

    public static String toDateStr(Object input, String format) {
        Date date = toDate(input);
        if (date == null) {
            return "";
        }
        return DateUtil.format(date, format);
    }

    public static final Map<Class<?>, Function<Object, ?>> CONVERTS_NATIVE = new HashMap<>();

    static {
        CONVERTS_NATIVE.put(String.class, ValUtil::toStr);
        CONVERTS_NATIVE.put(Integer.class, ValUtil::toInt);
        CONVERTS_NATIVE.put(int.class, (obj) -> ValUtil.toInt(obj, 0));
        CONVERTS_NATIVE.put(Long.class, ValUtil::toLong);
        CONVERTS_NATIVE.put(long.class, (obj) -> ValUtil.toLong(obj, 0l));
        CONVERTS_NATIVE.put(Date.class, ValUtil::toDate);
        CONVERTS_NATIVE.put(BigDecimal.class, ValUtil::toBigDecimal);
        CONVERTS_NATIVE.put(Float.class, ValUtil::toFloat);
        CONVERTS_NATIVE.put(float.class, (obj) -> ValUtil.toFloat(obj, 0f));
        CONVERTS_NATIVE.put(Double.class, ValUtil::toDouble);
        CONVERTS_NATIVE.put(double.class, (obj) -> ValUtil.toDouble(obj, 0d));
        CONVERTS_NATIVE.put(Boolean.class, ValUtil::toBoolean);
        CONVERTS_NATIVE.put(boolean.class, (obj) -> ValUtil.toBoolean(obj, Boolean.FALSE));
        CONVERTS_NATIVE.put(LocalDateTime.class, ValUtil::toLocalDateTime);
        CONVERTS_NATIVE.put(null, input -> input);
    }

    /**
     * 检查指定类型是否为已注册的原生类型
     */
    public static boolean isNativeType(Class<?> clazz) {
        return clazz != null && CONVERTS_NATIVE.containsKey(clazz);
    }

    public static <T> T toNativeObj(Object input, Class<T> clazz) {
        if (input == null) {
            return null;
        }
        if (clazz.isAssignableFrom(input.getClass())) {
            return (T) input;
        }
        final Function function = CONVERTS_NATIVE.get(clazz);
        if (function != null) {
            return (T) function.apply(input);
        }
        return null;
    }

    public static <T> T toObj(Object input, Class<T> classs) {
        if (input == null || classs == null) {
            return (T) input;
        }
        T re = toNativeObj(input, classs);
        return re != null ? re : JsonUtil.coverObj(input, classs);
    }

    public static <T> T toObj(Object input, Type type) {
        if (input == null || type == null) {
            return (T) input;
        }
        if (type instanceof Class) {
            return toObj(input, (Class<? extends T>) type);
        } else if (type instanceof ParameterizedType) {
            return JsonUtil.coverObj(input, type);
        }
        throw new SystemException(type + "未识别泛型参数");
    }

    public static boolean isEmpty(Object cs) {
        return StringUtils.isEmpty(cs);
    }


    /**
     * 获取子字段的值
     *
     * @param value 源对象（通常是 Map / List / Bean）
     * @param path  路径表达式，支持点分隔与数组索引，如 a.b.c[1]
     * @return 子字段值，路径中任一节点为空则返回 null
     */
    public static <T> T at(Object value, String path) {
        Object re = value;
        // 按下标 "[" 切分，再以 "." 拆出普通字段段，从而支持 a.b.c[1] 这种混合路径
        String[] segments = path.split("\\.");
        for (String seg : segments) {
            if (re == null) {
                return null;
            }
            // 解析该段中可能出现的 字段名 + 数组索引，如 a[1]、[1]、a.b 等
            Matcher m = INDEX_PATTERN.matcher(seg);
            int lastEnd = 0;
            String field = null;
            while (m.find()) {
                // 索引前的字段名（可能是空，如纯 [1] 开头）
                String pre = seg.substring(lastEnd, m.start());
                if (field != null || !pre.isEmpty()) {
                    re = readField(re, field == null ? pre : field);
                    if (re == null) {
                        return null;
                    }
                }
                field = null;
                re = readIndex(re, m.group("idx"));
                if (re == null) {
                    return null;
                }
                lastEnd = m.end();
            }
            // 处理最后一个索引之后的剩余字段名（如 a[1].b 中的 a[1] 之后没有，但 a.b[1] 中 b 在后面段）
            if (lastEnd < seg.length()) {
                String tail = seg.substring(lastEnd);
                re = readField(re, tail);
                if (re == null) {
                    return null;
                }
            }
        }
        return (T)re;
    }

    /**
     * 从 Map / Bean 中读取字段值
     */
    private static Object readField(Object re, String field) {
        if (re instanceof Map) {
            return ((Map<?, ?>) re).get(field);
        }
        if (re instanceof CharSequence) {
            return new JSONMap(re.toString()).get(field);
        }
        return FieldReflections.getValue(re, FieldReflections.getField(re.getClass(), field, true));
    }

    /**
     * 从 List / 数组 中按下标读取值
     */
    private static Object readIndex(Object re, String idx) {
        int i = Integer.parseInt(idx);
        if (re instanceof List) {
            return ((List<?>) re).get(i);
        }
        if (re.getClass().isArray()) {
            return Array.get(re, i);
        }
        if (re instanceof CharSequence) {
            return new JSONList(re.toString()).get(i);
        }
        throw new SystemException("不支持的类型:" + re.getClass().getName());
    }


    /**
     * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.支持多级属性
     *
     * @param obj       支持pojo对象,map,数组和list
     * @param fieldName 支持多级属性
     * @param value     属性值
     * @param ignore    忽略空值或错误的属性
     */
    public static Object set(final Object obj, final String fieldName, final Object value, final boolean ignore) {
        if (obj == null) {
            if (ignore) {
                return obj;
            }
            throw new SystemException("can't setValue [" + fieldName + "] from [" + obj + "]");
        }
        Object object = obj;
        final int i = fieldName.indexOf(".");
        if (i > -1) {
            //多级处理
            String name = fieldName.substring(0, i);
            String subFieldName = fieldName.substring(i + 1);
            Object subObject = set(at(obj, name), subFieldName, value, ignore);
            return set(obj, name, subObject, true);
        }

        //一级处理
        if (obj instanceof CharSequence) {
            if (StringUtils.isNumber(fieldName)) {
                final int index = Integer.parseInt(fieldName);
                JSONList list = new JSONList(object.toString());
                if (list.size() <= index) {
                    if (ignore) {
                        return obj;
                    }
                    throw new SystemException("can't setValue [" + fieldName + "] from [" + obj + "]");
                }
                list.set(index, value);
                return list.toString();
            } else {
                JSONMap map = new JSONMap(object.toString());
                map.put(fieldName, value);
                return map.toString();
            }
        } else if (object instanceof Map) {
            ((Map) object).put(fieldName, value);
        } else if (object instanceof List || object.getClass().isArray()) {
            if (!StringUtils.isLongOrInt(fieldName)) {
                if (ignore) {
                    return obj;
                }
                throw new SystemException("can't setValue [" + fieldName + "] from [" + obj + "]");
            }
            final int i1 = Integer.parseInt(fieldName);
            if (object instanceof List) {
                final List list = (List) object;
                if (list.size() <= i1) {
                    if (ignore) {
                        return obj;
                    }
                    throw new SystemException("can't setValue [" + fieldName + "] from [" + obj + "]");
                }
                list.set(i1, value);
            } else if (object.getClass().isArray()) {
                final Object[] array = (Object[]) object;
                if (array.length <= i1) {
                    if (ignore) {
                        return obj;
                    }
                    throw new SystemException("can't setValue [" + fieldName + "] from [" + obj + "]");
                }
                array[i1] = value;
            }
        } else {
            FieldReflections.setValue(object, fieldName, value, ignore);
        }
        return object;
    }

}
