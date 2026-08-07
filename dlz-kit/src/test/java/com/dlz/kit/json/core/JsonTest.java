package com.dlz.kit.json.core;

import com.dlz.kit.json.JSONList;
import com.dlz.kit.json.JSONMap;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonTest {
    @Test
    void parsesNestedJsonIntoJdkContainers() {
        Map<String, Object> root = Json.parseObject(
                "{\"id\":1,\"name\":\"DLZ\",\"active\":true,\"items\":[null,{\"amount\":12.50}]}"
        );

        assertEquals(Integer.valueOf(1), root.get("id"));
        assertEquals("DLZ", root.get("name"));
        assertEquals(Boolean.TRUE, root.get("active"));
        assertInstanceOf(ArrayList.class, root.get("items"));
        List<?> items = (List<?>) root.get("items");
        assertEquals(new BigDecimal("12.50"), ((Map<?, ?>) items.get(1)).get("amount"));
    }

    @Test
    void preservesIntegerRangeAndDecimalPrecision() {
        List<Object> values = Json.parseArray(
                "[1,2147483648,9223372036854775808,0.100,1.2e3]"
        );

        assertInstanceOf(Integer.class, values.get(0));
        assertInstanceOf(Long.class, values.get(1));
        assertEquals(new BigInteger("9223372036854775808"), values.get(2));
        assertEquals(new BigDecimal("0.100"), values.get(3));
        assertEquals(new BigDecimal("1.2e3"), values.get(4));
    }

    @Test
    void supportsExplicitLenientSyntax() {
        JSONMap root = Json.parseObject(
                "{/*comment*/name:'dlz',items:['one','two',],}",
                JsonOptions.JSON_OPTIONS_LENIENT
        );

        assertEquals("dlz", root.get("name"));
        assertEquals("one", root.getStr("items[0]"));
    }

    @Test
    void strictModeRejectsLenientSyntaxAndTrailingInput() {
        final JSONMap jsonMap = Json.parseObject("{name:1}");
        final JSONList objects = Json.parseArray("[1,]");
        assertEquals(1, jsonMap.size());
        assertEquals(1, objects.size());
        assertThrows(JsonParseException.class, () -> Json.parse("{} trailing"));
        assertThrows(JsonParseException.class, () -> Json.parse("01"));
    }

    @Test
    void reportsSourceLocation() {
        JsonParseException error = assertThrows(
                JsonParseException.class,
                () -> Json.parse("{\n  \"items\": [1,]\n}")
        );

        assertTrue(error.getLine() >= 2);
        assertTrue(error.getColumn() > 0);
        assertTrue(error.getMessage().contains("line"));
    }

    @Test
    void enforcesDepthAndContainerLimits() {
        JsonOptions shallow = JsonOptions.builder().maxDepth(2).build();
        JsonOptions oneItem = JsonOptions.builder().maxContainerSize(1).build();
        JsonOptions shortStrings = JsonOptions.builder().maxStringLength(3).build();

        assertThrows(JsonParseException.class, () -> Json.parse("[[[1]]]", shallow));
        assertThrows(JsonParseException.class, () -> Json.parse("[1,2]", oneItem));
        assertThrows(JsonParseException.class, () -> Json.parse("\"long\"", shortStrings));
        assertThrows(JsonException.class, () -> Json.stringify(Arrays.asList(1, 2), oneItem));
        assertThrows(JsonException.class, () -> Json.stringify("long", shortStrings));
    }

    @Test
    void writesEscapesNullsAndPrettyOutput() {
        Map<String, Object> value = new LinkedHashMap<String, Object>();
        value.put("message", "line1\n\"line2\"");
        value.put("nothing", null);

        assertEquals(
                "{\"message\":\"line1\\n\\\"line2\\\"\",\"nothing\":null}",
                Json.stringify(value)
        );

        JsonOptions prettyWithoutNulls = JsonOptions.builder()
                .enable(JsonFeature.WRITE_PRETTY)
                .build();
        String pretty = Json.stringify(value, prettyWithoutNulls);
        assertTrue(pretty.contains("\n"));
        assertFalse(pretty.contains("nothing"));
    }

    @Test
    void rejectsCircularReferencesAndNonFiniteNumbers() {
        List<Object> cycle = new ArrayList<Object>();
        cycle.add(cycle);

        assertThrows(JsonException.class, () -> Json.stringify(cycle));
        assertThrows(JsonException.class, () -> Json.stringify(Double.NaN));
        assertThrows(JsonException.class, () -> Json.stringify(Float.POSITIVE_INFINITY));
    }

    @Test
    void canRejectDuplicateKeys() {
        JsonOptions options = JsonOptions.builder()
                .enable(JsonFeature.FAIL_ON_DUPLICATE_KEYS)
                .build();

        assertThrows(JsonParseException.class, () -> Json.parseObject("{\"id\":1,\"id\":2}", options));
    }
}
