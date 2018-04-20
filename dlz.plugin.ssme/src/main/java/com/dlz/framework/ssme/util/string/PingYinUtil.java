package com.dlz.framework.ssme.util.string;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


/**
 * 拼音工具类
 * <dependency>
	    <groupId>com.belerweb</groupId>
	    <artifactId>pinyin4j</artifactId>
	    <version>2.5.0</version>
	</dependency>
 * 
 */
public class PingYinUtil { 
	
	/**
	 * 将字符串中的中文转化为拼音,其他字符不变
	 * 
	 * @param inputString
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */
	public static String getPingYin(String inputString) throws BadHanyuPinyinOutputFormatCombination {
		if(inputString==null){
			return null;
		}
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);

		char[] input = inputString.trim().toCharArray();
		String output = ""; 
			for (int i = 0; i < input.length; i++) {
				if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
					output += temp[0];
				} else
					output += java.lang.Character.toString(input[i]);
			}
	 
		return output;
	}

	/**
	 * 获取汉字串拼音首字母，英文字符不变
	 * 
	 * @param chinese
	 *          汉字串
	 * @return 汉语拼音首字母
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */
	public static String getFirstSpell(String chinese) throws BadHanyuPinyinOutputFormatCombination {
		if(chinese==null){
			return null;
		}
		StringBuffer pybf = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
			 
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
//					System.out.println(chinese+" "+" "+i+" "+arr[i]+" "+Integer.valueOf(arr[i]));
					if (temp != null && temp.length>0 && temp[0].length()>0) {
						pybf.append(temp[0].charAt(0));
					}
				 
			} else {
				pybf.append(arr[i]);
			}
		}
		return pybf.toString().replaceAll("\\W", "").trim();
	}
	

	/*
	 * 获取汉字串拼音，英文字符不变
	 * 
	 * @param chinese
	 *          汉字串
	 * @return 汉语拼音
	 */
	public static String getFullSpell(String chinese) throws BadHanyuPinyinOutputFormatCombination {
		if(chinese==null){
			return null;
		}
		StringBuffer pybf = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) { 
					pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);
			 
			} else {
				pybf.append(arr[i]);
			}
		}
		return pybf.toString();
	} 
	
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
		System.out.println(PingYinUtil.getFirstSpell("张成三ｃc"));
		System.out.println(PingYinUtil.getPingYin("张成三ｃc"));
		System.out.println(PingYinUtil.getFirstSpell("张蒋松蒋蒋国"));
		System.out.println(PingYinUtil.getPingYin("‬蒋松国"));
	}
}
