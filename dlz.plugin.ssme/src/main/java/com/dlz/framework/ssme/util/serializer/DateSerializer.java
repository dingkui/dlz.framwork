package com.dlz.framework.ssme.util.serializer;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.util.DateUtil;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateSerializer extends JsonSerializer<Date> {
	
    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
            JsonProcessingException {
        jgen.writeString(DateUtil.formatDate(value, "yyyy-MM-dd"));
    }
    
}