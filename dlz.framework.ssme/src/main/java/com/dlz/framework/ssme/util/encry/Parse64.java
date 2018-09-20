package com.dlz.framework.ssme.util.encry;

/**
 * 64进制转换
 * @author dingkui
 *
 */
public class Parse64 {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static char[] rDigits = { 
		'K', 'F', 'Q', 'Z', 'O', 'T', 'L', 'M', 'D', 'E',
		'A', 'X', 'Y', '5', 'R', 'S', '6', '7', 'I', 'J',
		'0', '1', '2', 'P', 'G', '4', 'W', 'H', '8', '9', 
		'U', 'V', '3', 'B', 'C', 'N' };
	
//	private static char[] rDigits10 = {'7','5', '1', '4', '2', '0', '9', '8', '6', '3' };

	/**
	 * long转64进制
	 * @param value
	 * @return
	 */
	public static String Parse64Encode(long value) {
		int digitIndex = 0;
		int radix = rDigits.length;// 64进制
		long longPositive = Math.abs(value);
		char[] outDigits = new char[20];
		for (digitIndex = 0; digitIndex <20; digitIndex++) {
			if (longPositive == 0) {
				break;
			}
			outDigits[outDigits.length - digitIndex - 1] = rDigits[Long.valueOf(longPositive % radix).intValue()];
			longPositive /= radix;
		}
		return new String(outDigits, outDigits.length - digitIndex, digitIndex);
	}

	/**
	 * 64进制转long
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static long Parse64Decode(String value) throws Exception {
		int fromBase = rDigits.length;
		if (value == null || value.trim().equals("")) {
			return 0L;
		}
		value=value.trim().replaceAll("[^A-Za-z0-9]", "");
		char[] values = value.toUpperCase().toCharArray();

		String sDigits = new String(rDigits, 0, fromBase);
		long result = 0;
		for (int i = 0; i < value.length(); i++) {
			if (!sDigits.contains(String.valueOf(values[i]))) {
				throw new Exception(String.format("The argument \"{0}\" is not in {1} system", values[i], fromBase));
			} else {
				try {
					int index = 0;
					for (int xx = 0; xx < rDigits.length; xx++) {
						if (rDigits[xx] == values[value.length() - i - 1]) {
							index = xx;
							break;
						}
					}
					result += Math.pow(fromBase, i) * index;// 2
				} catch (Exception e) {
					throw new Exception("运算溢出",e);
				}
			}
		}
		return result;
	}
	
	private static int jm=7;
	private static long to_leng_4=46656l;
	private static long to_leng_5=1679616l;
	/**
	 * 64进制转long
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static long Parse64Decode(String value,long c) throws Exception {
		long r=Parse64Decode(value)-c;
		return r%jm==0?r/jm:-1l;
	}
	/**
	 * long转64进制
	 * @param value
	 * @return
	 */
	public static String Parse64Encode(long value,long c) {
		return Parse64Encode(value*jm+c);
	}
	/**
	 * long转36进制（结果最少4位）
	 * @param value
	 * @return
	 */
	public static String Parse36Encode4(long value) {
		return Parse64Encode(value*jm+to_leng_4);
	}
	/**
	 * 36进制转long（结果最少4位）
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static long Parse36Decode4(String value) throws Exception {
		long r=Parse64Decode(value)-to_leng_4;
		return r%jm==0?r/jm:-1l;
	}
	/**
	 * long转36进制（结果最少4位）
	 * @param value
	 * @return
	 */
	public static String Parse36Encode5(long value) {
		return Parse64Encode(value*jm+to_leng_5);
	}
	/**
	 * 36进制转long（结果最少4位）
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static long Parse36Decode5(String value) throws Exception {
		long r=Parse64Decode(value)-to_leng_5;
		return r%jm==0?r/jm:-1l;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(Parse36Encode4(123));
		System.out.println(Parse36Decode4("174"));
	}
}