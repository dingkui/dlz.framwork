package com.dlz.framework.ssme.util.serializer;

import java.io.IOException;

import com.dlz.framework.util.StringUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class IDCardSerializer  extends JsonSerializer<String> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
  
	public static String formatCardNum(String value) {
		if(!StringUtils.isEmpty(value)){
			if(value.length() == 18){
				String str = value.substring(0, 8);
				String end = value.substring(15, value.length());
				String fmtStr = str + "******" + end;
				return fmtStr;
			}else if(value.length() == 15){
				String str = value.substring(0, 5);
				String end = value.substring(12, value.length());
				String fmtStr = str + "****" + end;
				return fmtStr;
			}			
		}
		return null;
	}
	
    @Override
    public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
            JsonProcessingException {
        jgen.writeString(formatCardNum(value));
    } 
}
