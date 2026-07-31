package com.dlz.kit.json.core;

import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonTypesTest {
    @Test
    void createsListTypeWithoutMapperDependency() {
        ParameterizedType type = (ParameterizedType) JsonTypes.listOf(String.class);

        assertEquals(List.class, type.getRawType());
        assertArrayEquals(new Type[]{String.class}, type.getActualTypeArguments());
        assertEquals("java.util.List<java.lang.String>", type.getTypeName());
    }

    @Test
    void supportsNestedParameterizedTypes() {
        Type type = JsonTypes.parameterized(Map.class, String.class, JsonTypes.listOf(Long.class));

        assertEquals("java.util.Map<java.lang.String, java.util.List<java.lang.Long>>", type.getTypeName());
    }
}
