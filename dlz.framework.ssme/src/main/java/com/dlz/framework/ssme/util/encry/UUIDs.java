package com.dlz.framework.ssme.util.encry;

import java.util.UUID;

import com.dlz.framework.util.StringUtils;
import org.joda.time.DateTime;

public class UUIDs {

	public static String getRandomUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String getTimestamp() {
		return new DateTime().toString("yyyyMMddHHmmss");
	}

	public static Long getOrderId(Long seq) {
		String od = new DateTime().toString("yyyyMMddHHmmss")
				+ StringUtils.leftPad(String.valueOf(seq % 100000), 5, '0');
		return Long.parseLong(od);
	}
	
}
