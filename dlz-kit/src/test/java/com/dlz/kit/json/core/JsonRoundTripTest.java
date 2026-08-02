package com.dlz.kit.json.core;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.dlz.kit.json.core.JsonAssertions.assertJsonEquivalent;

class JsonRoundTripTest {
    @Test
    void roundTripsDeterministicRandomDocuments() {
        Random random = new Random(20260731L);

        for (int index = 0; index < 500; index++) {
            Object expected = randomValue(random, 0);
            String json = Json.stringify(expected);
            Object actual = Json.parse(json);
            assertJsonEquivalent(expected, actual);
        }
    }

    @Test
    void parserAndWriterAreSafeForIndependentConcurrentCalls() throws Exception {
        final String document = "{\"id\":1,\"name\":\"并发😀\",\"values\":[1,2.50,true,null]}";
        ExecutorService executor = Executors.newFixedThreadPool(8);
        try {
            List<Callable<String>> tasks = new ArrayList<Callable<String>>();
            for (int task = 0; task < 200; task++) {
                tasks.add(() -> Json.stringify(Json.parse(document)));
            }
            for (Future<String> future : executor.invokeAll(tasks)) {
                assertEquals(document, future.get());
            }
        } finally {
            executor.shutdownNow();
        }
    }

    @Test
    void permitsSharedReferencesButRejectsActualCycles() {
        List<Object> shared = Arrays.<Object>asList(1, 2);
        List<Object> root = Arrays.<Object>asList(shared, shared);
        assertEquals("[[1,2],[1,2]]", Json.stringify(root));

        Map<String, Object> indirectCycle = new LinkedHashMap<String, Object>();
        List<Object> child = new ArrayList<Object>();
        indirectCycle.put("child", child);
        child.add(indirectCycle);
        assertThrows(JsonException.class, () -> Json.stringify(indirectCycle));
    }

    @Test
    void optionsAreImmutableAndValidateLimits() {
        JsonOptions.Builder builder = JsonOptions.builder().enable(JsonFeature.ALLOW_COMMENTS);
        JsonOptions first = builder.build();
        builder.disable(JsonFeature.ALLOW_COMMENTS).enable(JsonFeature.WRITE_PRETTY);

        assertEquals(Integer.valueOf(1), Json.parse("/*ok*/1", first));
        assertThrows(JsonParseException.class, () -> Json.parse("/*no*/1", builder.build()));
        assertThrows(IllegalArgumentException.class, () -> JsonOptions.builder().enable(null));
        assertThrows(IllegalArgumentException.class, () -> JsonOptions.builder().maxDepth(0));
        assertThrows(IllegalArgumentException.class, () -> JsonOptions.builder().maxStringLength(-1));
        assertThrows(IllegalArgumentException.class, () -> JsonOptions.builder().maxContainerSize(0));
    }

    private static Object randomValue(Random random, int depth) {
        int choice = depth >= 4 ? random.nextInt(7) : random.nextInt(9);
        switch (choice) {
            case 0: return null;
            case 1: return random.nextBoolean();
            case 2: return random.nextInt();
            case 3: return random.nextLong();
            case 4: return new BigInteger(80, random).subtract(BigInteger.ONE.shiftLeft(79));
            case 5: return BigDecimal.valueOf(random.nextLong(), random.nextInt(8));
            case 6: return randomString(random);
            case 7:
                List<Object> list = new ArrayList<Object>();
                for (int i = 0, size = random.nextInt(5); i < size; i++) {
                    list.add(randomValue(random, depth + 1));
                }
                return list;
            default:
                Map<String, Object> map = new LinkedHashMap<String, Object>();
                for (int i = 0, size = random.nextInt(5); i < size; i++) {
                    map.put("key" + i + randomString(random), randomValue(random, depth + 1));
                }
                return map;
        }
    }

    private static String randomString(Random random) {
        String[] parts = {"", "ascii", "中文", "😀", "quote\"", "slash\\", "line\n", "\u0001"};
        return parts[random.nextInt(parts.length)];
    }
}
