package com.dlz.framework.ssme.util.serializer;

import java.io.IOException;
import java.math.BigDecimal;

import com.dlz.framework.logger.MyLogger;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PercentSerializer extends JsonSerializer<Long> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static MyLogger logger = MyLogger.getLogger(PercentSerializer.class);

	public static String toPercent(Long value) {
		String result=new BigDecimal((double) value / 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		if(result.endsWith("00")){
			result=result.substring(0,result.indexOf("."));
		}else if(result.endsWith("0")){
			result=result.substring(0,result.length()-1);
		}
		return result;
	}

	public static String toPercent(String value) {
		return toPercent(Long.parseLong(value));
	}

	@Override
	public void serialize(Long value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		try {
			jgen.writeString(toPercent(value));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

}