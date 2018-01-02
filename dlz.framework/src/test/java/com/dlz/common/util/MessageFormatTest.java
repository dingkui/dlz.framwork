package com.dlz.common.util;

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
		System.out.println(formatMsg("12zz{}3",1,2,3,4));
	}
}
