package com.dlz.framework.util;

import java.math.BigDecimal;

/**
 * 数字操作工具类
 * @author dk 2017-11-03
 */
public class NumberUtil{
	private static BigDecimal B100=new BigDecimal(100);
	private static BigDecimal B0=new BigDecimal(0);
	public static long getLongMoney(Object money){
		return ValUtil.getBigDecimal(money,B0).multiply(B100).longValue();
	}
	public static Double money2Double(long money){
		return new BigDecimal(money).divide(B100).doubleValue();
	}
	public static Double add(Double money,Double add){
		return (new BigDecimal(money.doubleValue())).add(new BigDecimal(add.doubleValue())).doubleValue();
	}
	public static Double subtract(Double money,Double subtract){
		return (new BigDecimal(money.doubleValue())).subtract(new BigDecimal(subtract.doubleValue())).doubleValue();
	}

}