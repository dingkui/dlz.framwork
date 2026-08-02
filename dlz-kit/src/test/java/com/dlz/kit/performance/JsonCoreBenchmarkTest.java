package com.dlz.kit.performance;

import com.dlz.kit.json.core.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.util.Map;

/** Lightweight local comparison. Use JMH for publishable benchmark numbers. */
class JsonCoreBenchmarkTest {
    private static final ObjectMapper JACKSON = new ObjectMapper();
    private static final String DOCUMENT = "{\"code\":0,\"message\":\"success\",\"data\":{"
            + "\"order\":{\"id\":\"WX20260731001\",\"amount\":9900,\"paid\":true},"
            + "\"items\":[{\"sku\":\"A-1\",\"qty\":2},{\"sku\":\"B-2\",\"qty\":1}],"
            + "\"note\":\"JSON benchmark 中文😀\"}}";
    private static volatile Object blackhole;

    @Test
    void compareWithJacksonWhenExplicitlyEnabled() throws Exception {
        Assumptions.assumeTrue(Boolean.getBoolean("dlz.json.benchmark"),
                "enable with -Ddlz.json.benchmark=true");
        int warmup = Integer.getInteger("dlz.json.benchmark.warmup", 10_000);
        int iterations = Integer.getInteger("dlz.json.benchmark.iterations", 100_000);
        Map<?, ?> dlzValue = (Map<?, ?>) Json.parse(DOCUMENT);
        Map<?, ?> jacksonValue = JACKSON.readValue(DOCUMENT, Map.class);

        runParseDlz(warmup);
        runParseJackson(warmup);
        runWriteDlz(dlzValue, warmup);
        runWriteJackson(jacksonValue, warmup);

        long dlzParse = runParseDlz(iterations);
        long jacksonParse = runParseJackson(iterations);
        long dlzWrite = runWriteDlz(dlzValue, iterations);
        long jacksonWrite = runWriteJackson(jacksonValue, iterations);

        System.out.printf("DLZ JSON benchmark: %,d iterations, document=%d chars%n",
                iterations, DOCUMENT.length());
        print("parse", dlzParse, jacksonParse, iterations);
        print("write", dlzWrite, jacksonWrite, iterations);
    }

    private static long runParseDlz(int iterations) {
        long started = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            blackhole = Json.parse(DOCUMENT);
        }
        return System.nanoTime() - started;
    }

    private static long runParseJackson(int iterations) throws JsonProcessingException {
        long started = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            blackhole = JACKSON.readValue(DOCUMENT, Map.class);
        }
        return System.nanoTime() - started;
    }

    private static long runWriteDlz(Object value, int iterations) {
        long started = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            blackhole = Json.stringify(value);
        }
        return System.nanoTime() - started;
    }

    private static long runWriteJackson(Object value, int iterations) throws JsonProcessingException {
        long started = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            blackhole = JACKSON.writeValueAsString(value);
        }
        return System.nanoTime() - started;
    }

    private static void print(String operation, long dlzNanos, long jacksonNanos, int iterations) {
        double dlzOps = iterations * 1_000_000_000d / dlzNanos;
        double jacksonOps = iterations * 1_000_000_000d / jacksonNanos;
        System.out.printf("%-5s DLZ=%10.0f ops/s, Jackson=%10.0f ops/s, DLZ/Jackson=%.2fx%n",
                operation, dlzOps, jacksonOps, dlzOps / jacksonOps);
    }
}
