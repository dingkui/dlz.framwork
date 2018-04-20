package com.dlz.common.util.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class BytesDeserialize extends JsonDeserializer<byte[]> {  
  
    @Override  
    public byte[] deserialize(JsonParser jp, DeserializationContext ctxt)  throws IOException, JsonProcessingException {  
        return jp.getText().getBytes();  
    }
}