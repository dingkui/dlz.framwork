package com.dlz.framework.ssme.util.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class BytesSerializer extends JsonSerializer<byte[]> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
    @Override
    public void serialize(byte[] value, JsonGenerator jgen, SerializerProvider provider) throws IOException,  JsonProcessingException {
        jgen.writeString(new String(value,"UTF-8"));
    }
}