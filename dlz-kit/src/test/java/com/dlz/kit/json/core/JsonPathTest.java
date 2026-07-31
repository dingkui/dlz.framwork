package com.dlz.kit.json.core;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonPathTest {
    @Test
    void readsNestedMapsListsArraysAndNegativeIndices() {
        Map<String, Object> item1 = new LinkedHashMap<String, Object>();
        item1.put("name", "first");
        Map<String, Object> item2 = new LinkedHashMap<String, Object>();
        item2.put("name", "last");
        Map<String, Object> root = new LinkedHashMap<String, Object>();
        root.put("items", Arrays.asList(item1, item2));
        root.put("matrix", new int[][]{{1, 2}, {3, 4}});
        root.put("literal.key", "exact");

        assertEquals("first", JsonPath.get(root, "items[0].name"));
        assertEquals("last", JsonPath.get(root, "items[-1].name"));
        assertEquals(Integer.valueOf(4), JsonPath.get(root, "matrix[1][1]"));
        assertEquals("exact", JsonPath.get(root, "literal.key"));
        assertNull(JsonPath.get(root, "items[9].name"));
    }

    @Test
    void readsCompatibleJsonTextWithoutHandlingPojoReflection() {
        assertEquals(Integer.valueOf(2), JsonPath.get("{info:{values:[1,2]}}", "info.values[-1]"));
        assertNull(JsonPath.get(new Object(), "name"));
        assertNull(JsonPath.get("not-json", "name"));
    }

    @Test
    void handlesRootPathsCollectionsAndInvalidIndices() {
        Map<String, Object> root = new LinkedHashMap<String, Object>();
        root.put("items", new LinkedHashSet<String>(Arrays.asList("first", "last")));

        assertEquals(root, JsonPath.get(root, ""));
        assertEquals(root, JsonPath.get(root, null));
        assertEquals("last", JsonPath.get(root, ".items[-1]"));
        assertNull(JsonPath.get(root, "items[-3]"));
        assertNull(JsonPath.get(root, "items[abc]"));
        assertNull(JsonPath.get(root, "items[]"));
        assertNull(JsonPath.get(root, "items[0"));
    }

    @Test
    void delegatesPojoSegmentsToAnExplicitResolver() {
        Person person = new Person("DLZ");

        assertEquals("DLZ", JsonPath.get(person, "name",
                (value, path) -> value instanceof Person && "name".equals(path)
                        ? ((Person) value).name : null));
    }

    @Test
    void splitsPathsUsingLegacySegmentSemantics() {
        assertPart("a.b[1].c", "a", "b[1].c");
        assertPart("b[0][2].c", "b[0][2]", "c");
        assertPart("[2].c", "[2]", "c");
        assertPart("items[3].title", "items[3]", "title");
        assertPart("arr[5]", "arr[5]", null);
    }

    @Test
    void performsShallowRootShapeChecks() {
        assertTrue(JsonText.isObject(" {name:test} "));
        assertTrue(JsonText.isArray(" [not-valid-json] "));
        assertFalse(JsonText.isObject("{]"));
        assertFalse(JsonText.isArray("[1,2"));
        assertFalse(JsonText.isObject(null));
    }

    private static void assertPart(String path, String segment, String remaining) {
        JsonPathPart part = JsonPathParser.split(path);
        assertEquals(segment, part.getSegment());
        assertEquals(remaining, part.getRemaining());
    }

    private static final class Person {
        private final String name;

        private Person(String name) {
            this.name = name;
        }
    }
}
