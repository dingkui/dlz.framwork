package com.dlz.framework.ssme.util.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
public class IdcardUtil {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(IdcardUtil.class);
	private static Map<String, String> cityCodeMap = new HashMap<String, String>() {
		private static final long serialVersionUID = 197936596282292049L;
		{
			this.put("11", "北京");
			this.put("12", "天津");
			this.put("13", "河北");
			this.put("14", "山西");
			this.put("15", "内蒙古");
			this.put("21", "辽宁");
			this.put("22", "吉林");
			this.put("23", "黑龙江");
			this.put("31", "上海");
			this.put("32", "江苏");
			this.put("33", "浙江");
			this.put("34", "安徽");
			this.put("35", "福建");
			this.put("36", "江西");
			this.put("37", "山东");
			this.put("41", "河南");
			this.put("42", "湖北");
			this.put("43", "湖南");
			this.put("44", "广东");
			this.put("45", "广西");
			this.put("46", "海南");
			this.put("50", "重庆");
			this.put("51", "四川");
			this.put("52", "贵州");
			this.put("53", "云南");
			this.put("54", "西藏");
			this.put("61", "陕西");
			this.put("62", "甘肃");
			this.put("63", "青海");
			this.put("64", "宁夏");
			this.put("65", "新疆");
			this.put("71", "台湾");
			this.put("81", "香港");
			this.put("82", "澳门");
			this.put("91", "国外");
		}
	};

	// 每位加权因子
	private static int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

	// 第18位校检码
	private static String verifyCode[] = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };

	public static boolean isValidateIdcard(String idcard) {
		if(!isIdcard(idcard)){
			return false;
		}
		if (idcard.length() == 15) {
			return isValidate15Idcard(idcard);
		}
		return isValidate18Idcard(idcard);
	}
	public static Date getBirthday(String idcard){
		if(!isValidateIdcard(idcard)){
			return null;
		}
		if (idcard.length() == 15) {
			String birthday = idcard.substring(6, 12);
			try {
				return new SimpleDateFormat("yyMMdd").parse(birthday);
			} catch (ParseException e) {
				logger.error(e.getMessage(),e);
			}
		}
		String birthday = idcard.substring(6, 14);
		try {
			return new SimpleDateFormat("yyyyMMdd").parse(birthday);
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	public static String getProvince(String idcard){
		if(!isValidateIdcard(idcard)){
			return null;
		}
		return cityCodeMap.get(idcard.substring(0, 2));
	}

	private static boolean isValidate18Idcard(String idcard) {
		// 非18位为假
		if (!is18Idcard(idcard)) {
			return false;
		}
		String provinceid = idcard.substring(0, 2);
		// 判断是否为合法的省份
		if(!cityCodeMap.containsKey(provinceid)){
			return false;
		}
		try {
			Date birthdate = new SimpleDateFormat("yyyyMMdd").parse(idcard.substring(6, 14));
			// 该身份证生出日期在当前日期之后时为假
			if (birthdate == null || new Date().before(birthdate)) {
				return false;
			}
		} catch (ParseException e) {
			// 日期格式不对则为假
			return false;
		}
		// 将和值与11取模得到余数进行校验码判断
		String checkCode = getCheckCodeBySum(idcard.substring(0, 17));
		if (null == checkCode) {
			return false;
		}
		// 将身份证的第18位与算出来的校码进行匹配，不相等就为假
		if (!idcard.substring(17, 18).equalsIgnoreCase(checkCode)) {
			return false;
		}
		return true;
	}

	private static boolean isValidate15Idcard(String idcard) {
		// 非15位为假
		if (!is15Idcard(idcard)) {
			return false;
		}
		String provinceid = idcard.substring(0, 2);
		// 判断是否为合法的省份
		if(!cityCodeMap.containsKey(provinceid)){
			return false;
		}
		try {
			Date birthdate = new SimpleDateFormat("yyMMdd").parse(idcard.substring(6, 12));
			// 该身份证生出日期在当前日期之后时为假
			if (birthdate == null || new Date().before(birthdate)) {
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	private static boolean isIdcard(String idcard) {
		return idcard == null || "".equals(idcard) ? false : Pattern.matches("(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)", idcard);
	}
	private static boolean is15Idcard(String idcard) {
		return idcard == null || "".equals(idcard) ? false : Pattern.matches("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$", idcard);
	}
	private static boolean is18Idcard(String idcard) {
		return Pattern.matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$", idcard);
	}
	private static String getCheckCodeBySum(String no17) {
		int[] bit = converCharToInt(no17.toCharArray());
		int sum = 0;
		if (power.length != bit.length) {
			return null;
		}
		for (int i = 0; i < bit.length; i++) {
			for (int j = 0; j < power.length; j++) {
				if (i == j) {
					sum = sum + bit[i] * power[j];
				}
			}
		}
		return verifyCode[sum%11];
	}
	private static int[] converCharToInt(char[] c) throws NumberFormatException {
		int[] a = new int[c.length];
		int k = 0;
		for (char temp : c) {
			a[k++] = Integer.parseInt(String.valueOf(temp));
		}
		return a;
	}
}
