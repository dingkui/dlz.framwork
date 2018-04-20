package com.dlz.common.util.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MoneyUtil {
	private static DecimalFormat  decimalFormat = new DecimalFormat("###,##0.00");
	private static BigDecimal base = new BigDecimal("100");
	public static String getMoneyStr(Long num){
		if(num==null){
			num=new Long(0);
		}
		BigDecimal big = new BigDecimal(num.longValue());
		return decimalFormat.format(big.divide(base, 2, RoundingMode.DOWN));	
	}
	public static String getMoneyStr(double num,String format){
		DecimalFormat decimalFormat = new DecimalFormat(format);
		BigDecimal big = new BigDecimal(String.valueOf(num));
		return decimalFormat.format(big.divide(base, 2, RoundingMode.DOWN));	
	}
	public static String getMoneyStr(long num,String format){
		DecimalFormat decimalFormat = new DecimalFormat(format);
		BigDecimal big = new BigDecimal(String.valueOf(num));
		return decimalFormat.format(big.divide(base, 2, RoundingMode.DOWN));	
	}
	public static String getMoneyStr(long num,String divide,String format){
		DecimalFormat decimalFormat = new DecimalFormat(format);
		BigDecimal big = new BigDecimal(String.valueOf(num));
		BigDecimal base = new BigDecimal(divide);
		return decimalFormat.format(big.divide(base, 2, RoundingMode.DOWN));	
	}
	/**
	 * 格式成万
	 * @param value
	 * @return
	 */
	public static String fmtTenThousand(Long value) {
		BigDecimal bigDecimal = new BigDecimal((double) value / 1000000).setScale(2, BigDecimal.ROUND_HALF_UP);
		return bigDecimal.toString() + "万";
	}
	public static String toMoney(Long value) {
		return new BigDecimal((double) value / 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
	
	public static long getMeneyByStr(String value){
		Double doubleBidAmount = new BigDecimal(value.replace(",", "")).doubleValue() * 100.0;
		return doubleBidAmount.longValue();
	}

	public static String toMoney(String value) {
		return toMoney(Long.parseLong(value));
	}

	public static String getPercent(Long value1, Long value2) {
		return new BigDecimal((double) value1 * 100 / (double) value2).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
	
	private static final String UNIT = "万千佰拾亿千佰拾万千佰拾元角分";  
    private static final String DIGIT = "零壹贰叁肆伍陆柒捌玖";  
    private static final double MAX_VALUE = 9999999999999.99D;  
    public static String change(double v) {  
     if (v < 0 || v > MAX_VALUE){  
         return "参数非法!";  
     }  
     long l = Math.round(v * 100);  
     if (l == 0){  
         return "零元整";  
     }  
     String strValue = l + "";  
     // i用来控制数  
     int i = 0;  
     // j用来控制单位  
     int j = UNIT.length() - strValue.length();  
     String rs = "";  
     boolean isZero = false;  
     for (; i < strValue.length(); i++, j++) {  
      char ch = strValue.charAt(i);  
      if (ch == '0') {  
       isZero = true;  
       if (UNIT.charAt(j) == '亿' || UNIT.charAt(j) == '万' || UNIT.charAt(j) == '元') {  
        rs = rs + UNIT.charAt(j);  
        isZero = false;  
       }  
      } else {  
       if (isZero) {  
        rs = rs + "零";  
        isZero = false;  
       }  
       rs = rs + DIGIT.charAt(ch - '0') + UNIT.charAt(j);  
      }  
     }  
     if (!rs.endsWith("分")) {  
      rs = rs + "整";  
     }  
     rs = rs.replaceAll("亿万", "亿");  
     return rs;  
    }  
      
}
