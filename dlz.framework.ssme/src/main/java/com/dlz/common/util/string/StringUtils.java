package com.dlz.common.util.string;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	public static String NVL(String cs) {
		return NVL(cs, "");
	}
	public static String NVL(String cs,String defaultStr) {
		return cs == null ? defaultStr: cs;
	}
	public static String ObjNVLString(Object cs,String defaultV) {
		return ObjNVL(cs, defaultV, String.class);
	}
	public static Long ObjNVLLong(Object cs,Long defaultV) {
		return ObjNVL(cs, defaultV, Long.class);
	}
	public static <T> T ObjNVL(Object cs,T defaultV,Class<T> requiredType) {
		if(cs ==null){
			return defaultV;
		}
		return coverObj(cs, requiredType);
	}
	
	public static String nonNull(Object s) {
		 if (s==null)
      return "";
  return s.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T coverObj(Object cs,Class<T> requiredType) {
		if(cs==null){
			return null;
		}
		if(requiredType.isInstance(cs)){
			return (T)cs;
		}
		String o =String.valueOf(cs);
		if(requiredType==String.class){
			return (T)o;
		}
		if(isNumber(o)){
			if(requiredType==Long.class){
				return (T)new Long(Double.valueOf(o).longValue());
			}
			if(requiredType==Double.class){
				return (T)Double.valueOf(o);
			}
			if(requiredType==BigDecimal.class){
				return (T)new BigDecimal(Double.valueOf(o));
			}
			if(requiredType==Integer.class){
				return (T)new Integer(Double.valueOf(o).intValue());
			}
		}
		return null;
	}
	

	
	public static String getStrByBytes(byte[] value) throws UnsupportedEncodingException{
		return new String(value,"UTF-8");
	}
	/**
	 * <p>
	 * 检验是否为空
	 * </p>
	 * 
	 * <pre>
	 * Collection，Map，Array,CharSequence
	 * </pre>
	 */
	@SuppressWarnings({ "rawtypes" })
	public static boolean isEmpty(Object cs) {
		if (cs == null) {
			return true;
		}
		if (cs instanceof Collection) {
			return ((Collection) cs).isEmpty();
		} else if (cs instanceof Map) {
			return ((Map) cs).isEmpty();
		} else if (cs.getClass().isArray()) {
			return ((Object[]) cs).length == 0;
		} else if (cs instanceof CharSequence) {
			return ((CharSequence) cs).length() == 0;
		} else if (cs instanceof Date) {
			return false;
		} else if (cs instanceof Number) {
			return false;
		} else {
			return false;
			//throw new IllegalArgumentException("检验空参数有误：" + cs.getClass());
		}
	}
	
	public static boolean isNumber(String o){
		if (isEmpty(o)) {
			return false;
		}
		return isEmpty(((String) o).replaceAll("[\\d.+-]", ""));
	}
	public static boolean isLongOrInt(String o){
		if (!isNumber(o)) {
			return false;
		}
		return isEmpty(((String) o).replaceAll("[\\d+-]", ""));
	}

	public static boolean isNotEmpty(Object cs) {
		return !isEmpty(cs);
	}
	public static String joinObject(Object cs,Object b) {
		return String.valueOf(cs)+String.valueOf(b);
	}

	/**
	 * <p>
	 * 首字母大写
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.capitalize(null)  = null
	 * StringUtils.capitalize("")    = ""
	 * StringUtils.capitalize("cat") = "Cat"
	 * StringUtils.capitalize("cAt") = "CAt"
	 * </pre>
	 */
	public static String capitalize(final String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		char firstChar = str.charAt(0);
		if (Character.isTitleCase(firstChar)) {
			// already capitalized
			return str;
		}
		return new StringBuilder(strLen).append(Character.toTitleCase(firstChar)).append(str.substring(1)).toString();
	}

	/**
	 * <p>
	 * 在左边添加指定字符达到指定的长度
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.leftPad(null, *, *)     = null
	 * StringUtils.leftPad("", 3, 'z')     = "zzz"
	 * StringUtils.leftPad("bat", 3, 'z')  = "bat"
	 * StringUtils.leftPad("bat", 5, 'z')  = "zzbat"
	 * StringUtils.leftPad("bat", 1, 'z')  = "bat"
	 * StringUtils.leftPad("bat", -1, 'z') = "bat"
	 * </pre>
	 */
	public static String leftPad(final String str, final int size, final char padChar) {
		if (str == null) {
			return null;
		}
		final int pads = size - str.length();
		if (pads <= 0) {
			return str; // returns original String when possible
		}
		if (pads > 8192) {
			return leftPad(str, size, padChar);
		}
		return repeat(padChar, pads).concat(str);
	}

	/**
	 * <p>
	 * 构造指定个数的字符的字符串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.repeat('e', 0)  = ""
	 * StringUtils.repeat('e', 3)  = "eee"
	 * StringUtils.repeat('e', -2) = ""
	 * </pre>
	 */
	public static String repeat(final char ch, final int repeat) {
		final char[] buf = new char[repeat];
		for (int i = repeat - 1; i >= 0; i--) {
			buf[i] = ch;
		}
		return new String(buf);
	}
	
	/**
	 * 字段转换成数据库键名 aaBbCc→AA_BB_CC<br>
	 * 如果参数含有_则不做转换
	 * @param str
	 * @author dk 2015-04-10
	 * @return
	 */
	public static String converStr2ClumnStr(String str) {
		if(str==null){
			return null;
		}
		if(str.indexOf("_")>-1){
			return str;
		}
		if(str.equals(str.toUpperCase())){
			return str;
		}
  	return str.replaceAll(" (?i)desc", " desc")
  			.replaceAll(" (?i)asc", " asc")
//  			.replaceAll("^(?i)select ", "select ")
//  			.replaceAll(" (?i)from ", " from ")
//  			.replaceAll("^(?i)update ", " update ")
//  			.replaceAll(" (?i)set ", " set ")
//  			.replaceAll(" (?i)where ", " where ")
//  			.replaceAll(" (?i)and ", " and ")
//  			.replaceAll(" (?i)exists ", " exists ")
//  			.replaceAll(" (?i)join ", " join ")
  			.replaceAll("([A-Z])", "_$1").toUpperCase();
	}
	/**
	 * 转换数据库键名   aa_bb_cc→aaBbCc
	 * @param clumn
	 * @author dk 2015-04-09
	 * @return
	 */	
	public static String converClumnStr2Str(String clumn){
		if(clumn==null){
			return "";
		}
		clumn=clumn.toLowerCase();
		Matcher mat = Pattern.compile("_([a-z])").matcher(clumn);
  	while(mat.find()){
  		clumn = clumn.replace("_"+mat.group(1), mat.group(1).toUpperCase());
  	}
		return clumn.replaceAll("_", "");
	}
//	public static void main(String[] args) {
////		System.out.println(converClumnStr2Str("aa_bb_cc"));
////		System.out.println(converClumnStr2Str("A_CC_VV_c"));
////		System.out.println(converStr2ClumnStr("fDCDESCcc desc,XXdb asC"));
////		System.out.println(converStr2ClumnStr("aCcVvCC"));
//		System.out.println(isLongOrInt("-111"));
//		System.out.println(isLongOrInt("1111"));
//		System.out.println(isLongOrInt("+111.11"));
//		System.out.println(Long.valueOf("-111.11"));
//	}

	/**
	 * <p>
	 * 试用指定字符构造字符串
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.join(null, *)         = null
	 * StringUtils.join([], *)           = ""
	 * StringUtils.join([null], *)       = ""
	 * StringUtils.join([1, 2, 3], ";")  = "1;2;3"
	 * StringUtils.join([1, 2, 3], null) = "123"
	 * </pre>
	 */
	public static <T> String join(final T[] array, String separator) {
		separator = NVL(separator);
		if (array == null) {
			return null;
		}
		final int noOfItems = array.length;
		if (noOfItems <= 0) {
			return "";
		}
		final StringBuilder buf = new StringBuilder(noOfItems * 16);
		for (int i = 0; i < noOfItems; i++) {
			if (i > 0) {
				buf.append(separator);
			}
			buf.append(array[i]);
		}
		return buf.toString();
	}

	public static <T> List<T> arrayToList(final T[] array) {
		return Arrays.asList(array);
	}

	public static <T> Object[] listToArray(final Collection<T> list) {
		return list.toArray();
	}

	public static <T> String join(final Collection<T> array, String separator) {
		return join(listToArray(array), separator);
	}
	public static <T> String join(final Collection<T> array, char separator) {
		return join(listToArray(array), String.valueOf(separator));
	}

	public static String[] split(String value, String regex) {
		return value == null ? null : value.split(regex);
	}

}
