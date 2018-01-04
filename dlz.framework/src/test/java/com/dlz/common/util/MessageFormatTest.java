package com.dlz.common.util;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dlz.framework.util.JacksonUtil;

public class MessageFormatTest {
	private static Pattern msgPattern = Pattern.compile("\\{[\\w]*[^\\d]+[\\w]*\\}");
	static String formatMsg(Object message, Object... paras) {
		String msg = message.toString();
		if (msgPattern.matcher(msg).find()) {
			return msg;
		}
		return MessageFormat.format(msg, paras);
	}
	
	private static Pattern myMsgPattern = Pattern.compile("\\{([^\\}]*)\\}");
	static String myformatMsg(Object message, Object... paras) {
		String msg = getStr(message, "");
		if (paras==null) {
			paras=new Object[]{null};
		}
		Matcher mat = myMsgPattern.matcher(msg);
		StringBuffer sb = new StringBuffer();
		int end=0;
		int i=0;
		while(mat.find()){
			String indexStr=mat.group(1).replaceAll("[^\\d]*", "");
			int index=0;
			if(!"".equals(indexStr)){
				index=Integer.parseInt(indexStr);
			}else{
				index=i;
			}
			sb.append(msg,end, mat.start());
			if(paras.length>index){
				sb.append(getStr(paras[index], null));
			}else{
				sb.append(mat.group(0));
			}
			end=mat.end();
			i++;
		}
		
		return sb.append(msg,end,msg.length()).toString();
	}
	
	private static String getStr(Object obj,String defualt){
		if(obj==null){
			return defualt;
		}
		if (obj instanceof CharSequence) {
			return obj.toString();
		}
		return JacksonUtil.getJson(obj);
	}
	
	
	
	

	public static void main(String[] args) {
//		System.out.println(formatMsg("12zz{}3",1,2,3,4));
//		System.out.println(myformatMsg("a{}b{9，56}b{3，2}d{}{}{9}",1,2,3,4));
		String a=null;
		System.out.println(myformatMsg("a{}b{1}c{}d{}{}",a));
		System.out.println(myformatMsg("a{}b{1}c{}d{}{}",a,1,2,3,4,5,6));
		System.out.println(myformatMsg("a{}b{1}c{}d{}{}",a));
		System.out.println(myformatMsg("a{}b{1}c{}d{}{}",a));
		System.out.println(myformatMsg("abcd"));
	}
}
