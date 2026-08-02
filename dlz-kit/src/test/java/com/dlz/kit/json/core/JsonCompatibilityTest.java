package com.dlz.kit.json.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.dlz.kit.json.core.JsonAssertions.assertJsonEquivalent;

class JsonCompatibilityTest {
    private static final ObjectMapper JACKSON = new ObjectMapper();

    static Stream<String> validJsonDocuments() {
        return Stream.of(
                "null", "true", "false", "0", "-0", "2147483647", "-2147483648",
                "9223372036854775807", "-9223372036854775808", "1.25", "-0.01",
                "1e3", "1E-3", "1e+3", "\"\"", "\"text\"", "\"\\\"\\\\\\/\\b\\f\\n\\r\\t\"",
                "\"\\u4e2d\\u6587\"", "\"\\ud83d\\ude00\"", "[]", "{}", "[null,true,false]",
                "{\"a\":1,\"b\":[2,3],\"c\":{\"d\":\"x\"}}",
                " \t\r\n{\"space\":true}\r\n"
        );
    }

    static Stream<String> invalidJsonDocuments() {
        return Stream.of(
                "", " ", "+1", ".1", "1.", "01", "-01", "--1", "1e", "1e+",
                "NaN", "Infinity", "undefined", "'text'", "{a:1}", "[1,]", "{\"a\":1,}",
                "//comment\n1", "/*comment*/1", "[", "{", "[1", "{\"a\"}", "{\"a\":}",
                "{\"a\":1", "[1 2]", "true false", "nullx", "\"unterminated",
                "\"bad\\xescape\"", "\"bad\\'escape\"", "\"bad\\u12xz\"", "\"line\nfeed\"",
                "\u00a0null", "\u2003null", "null\u00a0"
        );
    }

    @ParameterizedTest(name = "valid JSON: {0}")
    @MethodSource("validJsonDocuments")
    void acceptsStandardJsonDocuments(String json) throws Exception {
        Object parsed = Json.parse(json);
        Object expected = JACKSON.readValue(json, Object.class);
        String encoded = Json.stringify(parsed);

        assertJsonEquivalent(expected, parsed);
        assertJsonEquivalent(parsed, Json.parse(encoded));
        JACKSON.readTree(encoded);
    }

    @ParameterizedTest(name = "invalid JSON: {0}")
    @MethodSource("invalidJsonDocuments")
    void rejectsInvalidJsonDocuments(String json) {
        assertThrows(JsonParseException.class, () -> Json.parse(json));
    }

    @Test
    void decodesEveryStandardEscapeAndUnicodeSurrogatePair() {
        String decoded = (String) Json.parse("\"\\\"\\\\\\/\\b\\f\\n\\r\\t\\u4e2d\\ud83d\\ude00\"");

        assertEquals("\"\\/\b\f\n\r\t中😀", decoded);
        assertEquals(decoded, Json.parse(Json.stringify(decoded)));
    }

    @Test
    void escapesControlAndNonAsciiCharactersWhenRequested() {
        JsonOptions options = JsonOptions.builder()
                .enable(JsonFeature.WRITE_NULLS)
                .enable(JsonFeature.ESCAPE_NON_ASCII)
                .build();

        String json = Json.stringify("中😀\u0001", options);

        assertEquals("\"\\u4e2d\\ud83d\\ude00\\u0001\"", json);
        assertEquals("中😀\u0001", Json.parse(json));
    }

    @Test
    void appliesDuplicateKeyPolicyExplicitly() {
        Map<String, Object> defaultResult = Json.parseObject("{\"id\":1,\"id\":2}");
        JsonOptions rejectDuplicates = JsonOptions.builder()
                .enable(JsonFeature.FAIL_ON_DUPLICATE_KEYS)
                .build();

        assertEquals(Integer.valueOf(2), defaultResult.get("id"));
        assertThrows(JsonParseException.class,
                () -> Json.parseObject("{\"id\":1,\"id\":2}", rejectDuplicates));
    }

    @Test
    void acceptsLenientFeaturesOnlyWhenEnabled() {
        JsonOptions commentsOnly = JsonOptions.builder().enable(JsonFeature.ALLOW_COMMENTS).build();
        JsonOptions singleQuotesOnly = JsonOptions.builder().enable(JsonFeature.ALLOW_SINGLE_QUOTES).build();
        JsonOptions keysOnly = JsonOptions.builder().enable(JsonFeature.ALLOW_UNQUOTED_KEYS).build();
        JsonOptions valuesOnly = JsonOptions.builder().enable(JsonFeature.ALLOW_UNQUOTED_STRING_VALUES).build();
        JsonOptions trailingOnly = JsonOptions.builder().enable(JsonFeature.ALLOW_TRAILING_COMMA).build();

        assertEquals(Integer.valueOf(1), Json.parse("/*a*/1//b", commentsOnly));
        assertEquals("x", Json.parse("'x'", singleQuotesOnly));
        assertEquals("it's", Json.parse("'it\\'s'", singleQuotesOnly));
        assertEquals(Integer.valueOf(1), Json.parseObject("{a:1}", keysOnly).get("a"));
        assertEquals("word", Json.parse("word", valuesOnly));
        assertEquals(Arrays.asList(1), Json.parseArray("[1,]", trailingOnly));
    }

    @Test
    void validatesRootShapesAndNullArguments() {
        assertEquals(Collections.emptyMap(), Json.parseObject("{}"));
        assertEquals(Collections.emptyList(), Json.parseArray("[]"));
        assertThrows(JsonException.class, () -> Json.parseObject("[]"));
        assertThrows(JsonException.class, () -> Json.parseArray("{}"));
        assertThrows(IllegalArgumentException.class, () -> Json.parse(null));
        assertThrows(IllegalArgumentException.class, () -> Json.parse("null", null));
    }

    @Test
    void writesSupportedJdkShapesAndRejectsUnsupportedValues() {
        Map<Object, Object> value = new LinkedHashMap<Object, Object>();
        value.put(7, Arrays.asList('x', Sample.VALUE, new int[]{1, 2}));

        assertEquals("{\"7\":[\"x\",\"VALUE\",[1,2]]}", Json.stringify(value));
        assertThrows(JsonException.class, () -> Json.stringify(new Object()));

        Map<Object, Object> nullKey = new LinkedHashMap<Object, Object>();
        nullKey.put(null, true);
        assertThrows(JsonException.class, () -> Json.stringify(nullKey));
    }

    @Test
    void omitsOrWritesNullObjectPropertiesWithoutChangingArrayNulls() {
        Map<String, Object> value = new LinkedHashMap<String, Object>();
        value.put("missing", null);
        value.put("items", Arrays.asList(null, 1));
        JsonOptions omitObjectNulls = JsonOptions.builder().build();

        assertEquals("{\"items\":[null,1]}", Json.stringify(value, omitObjectNulls));
        assertTrue(Json.stringify(value).contains("\"missing\":null"));
        assertFalse(Json.stringify(value, omitObjectNulls).contains("missing"));
    }

    @Test
    void preservesFractionScaleAndNumericValueWhileProducingStandardJson() {
        String json = Json.stringify(Arrays.asList(new BigDecimal("1.2300"), new BigDecimal("1E+3")));

        assertEquals("[1.2300,1000]", json);
        List<Object> result = Json.parseArray(json);
        assertEquals(new BigDecimal("1.2300"), result.get(0));
        assertJsonEquivalent(new BigDecimal("1000"), result.get(1));
    }

    private enum Sample {
        VALUE
    }
}
