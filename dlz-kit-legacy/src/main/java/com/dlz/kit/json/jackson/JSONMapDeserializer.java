package com.dlz.kit.json.jackson;

import com.dlz.kit.json.JSONMap;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class JSONMapDeserializer extends JsonDeserializer<JSONMap> {
    @Override
    public JSONMap deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String node = parser.getCodec().readValue(parser, String.class);
        if (node == null || node.isEmpty()) {
            return null;
        }
        return new JSONMap(node);
    }
}
