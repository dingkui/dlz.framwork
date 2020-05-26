package com.dlz.test.comm.util;

import com.dlz.comm.util.StringUtils;

import java.text.MessageFormat;
import java.util.regex.Pattern;

public class MessageFormatTest {
	private static Pattern msgPattern = Pattern.compile("\\{[\\w]*[^\\d]+[\\w]*\\}");
	static String formatMsg(Object message, Object... paras) {
		String msg = message.toString();
		if (msgPattern.matcher(msg).find()) {
			return msg;
		}
		return MessageFormat.format(msg, paras);
	}
	
	public static void main(String[] args) {
//		System.out.println(formatMsg("12zz{}3",1,2,3,4));
//		System.out.println(myformatMsg("a{}b{9，56}b{3，2}d{}{}{9}",1,2,3,4));
		String a=null;
		System.out.println(StringUtils.formatMsg("a{}b{1}c{}d{}{}",a));
		System.out.println(StringUtils.formatMsg("a{}b{1}c{}d{}{}",a,1,2,3,4,5,6));
		System.out.println(StringUtils.formatMsg("a{}b{1}c{}d{}{}",a));
		System.out.println(StringUtils.formatMsg("a{}b{1}c{}d{}{}",a));
		System.out.println(StringUtils.formatMsg("a{}}b{1}c{}d{}{}",a));
		System.out.println(StringUtils.formatMsg("abcd"));
	}
}
