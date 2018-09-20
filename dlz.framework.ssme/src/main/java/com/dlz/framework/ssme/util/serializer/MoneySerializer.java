package com.dlz.framework.ssme.util.serializer;

import java.io.IOException;

import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.ssme.util.bean.MoneyUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MoneySerializer extends JsonSerializer<Long> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	
	private static MyLogger logger = MyLogger.getLogger(MoneySerializer.class);

	@Override
	public void serialize(Long value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		try {
			jgen.writeString(MoneyUtil.toMoney(value));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
    
}