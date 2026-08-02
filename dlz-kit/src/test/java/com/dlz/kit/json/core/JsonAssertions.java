package com.dlz.kit.json.core;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class JsonAssertions {
    private JsonAssertions() {
    }

    static void assertJsonEquivalent(Object expected, Object actual) {
        if (expected instanceof Number && actual instanceof Number) {
            BigDecimal expectedNumber = new BigDecimal(expected.toString());
            BigDecimal actualNumber = new BigDecimal(actual.toString());
            assertEquals(0, expectedNumber.compareTo(actualNumber),
                    () -> "JSON numbers differ: expected " + expected + " but was " + actual);
            return;
        }
        if (expected instanceof Map && actual instanceof Map) {
            Map<?, ?> expectedMap = (Map<?, ?>) expected;
            Map<?, ?> actualMap = (Map<?, ?>) actual;
            assertEquals(expectedMap.keySet(), actualMap.keySet());
            for (Object key : expectedMap.keySet()) {
                assertJsonEquivalent(expectedMap.get(key), actualMap.get(key));
            }
            return;
        }
        if (expected instanceof List && actual instanceof List) {
            List<?> expectedList = (List<?>) expected;
            List<?> actualList = (List<?>) actual;
            assertEquals(expectedList.size(), actualList.size());
            for (int index = 0; index < expectedList.size(); index++) {
                assertJsonEquivalent(expectedList.get(index), actualList.get(index));
            }
            return;
        }
        if (expected == null || actual == null) {
            assertEquals(expected, actual);
            return;
        }
        assertTrue(expected.getClass().isInstance(actual) || actual.getClass().isInstance(expected),
                () -> "JSON value types differ: " + expected.getClass() + " and " + actual.getClass());
        assertEquals(expected, actual);
    }
}
