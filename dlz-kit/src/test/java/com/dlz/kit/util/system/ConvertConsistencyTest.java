package com.dlz.kit.util.system;

import com.dlz.kit.util.system.annotation.SetValue;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;

class ConvertConsistencyTest {
    public static class Receiver { public String name = "Alice"; }
    public static class Source { public Receiver receiver = new Receiver(); }
    public static class Target { @SetValue("receiver") public String name; public String missing = "default"; }
    public static class Indexed { @SetValue("receivers[0]") public String name; }
    public static class IndexedSource { public Receiver[] receivers = {new Receiver()}; }

    @Test void expandsObjectAndPrimitiveArrays() {
        assertEquals(Arrays.asList("a", "b"), ConvertUtil.convert(new String[]{"a", "b"}, ArrayList.class));
        assertEquals(Arrays.asList(1, 2), ConvertUtil.convert(new int[]{1, 2}, ArrayList.class));
        assertEquals(Arrays.asList(true, false), ConvertUtil.convert(new boolean[]{true, false}, ArrayList.class));
        assertEquals(Collections.emptyList(), ConvertUtil.convert(new int[0], ArrayList.class));
        assertEquals(Arrays.asList("a", null), ConvertUtil.convert(new String[]{"a", null}, ArrayList.class));
    }
    @Test void mapAndBeanUseSameAnnotationPath() {
        Map<String,Object> map = new HashMap<>();
        map.put("receiver", Collections.singletonMap("name", "Alice"));
        Target fromMap = ConvertUtil.convert(map, Target.class);
        Target fromBean = ConvertUtil.convert(new Source(), Target.class);
        assertEquals("Alice", fromMap.name);
        assertEquals(fromMap.name, fromBean.name);
        assertEquals("default", fromBean.missing);
        Map result = ConvertUtil.convert(fromBean, HashMap.class);
        assertEquals("Alice", ((Map) result.get("receiver")).get("name"));
        assertEquals("Alice", ConvertUtil.convert(result, Target.class).name);
        Source absent = new Source(); absent.receiver = null;
        assertNull(ConvertUtil.convert(absent, Target.class).name);
    }
    @Test void annotationPathSupportsArrayNodes() {
        assertEquals("Alice", ConvertUtil.convert(new IndexedSource(), Indexed.class).name);
        Map<String,Object> map = new HashMap<>();
        map.put("receivers", Arrays.asList(Collections.singletonMap("name", "Alice")));
        assertEquals("Alice", ConvertUtil.convert(map, Indexed.class).name);
    }
    @Test void callbacksRunOnceForSameTypeAndArrayConversions() {
        AtomicInteger calls = new AtomicInteger();
        Target existing = new Target();
        assertSame(existing, ConvertUtil.convert(existing, Target.class, v -> {v.name = "changed"; calls.incrementAndGet();}));
        assertEquals("changed", existing.name);
        Integer[] array = ConvertUtil.convert(Arrays.asList(1, 2), Integer[].class,
                v -> {v[0] = 9; calls.incrementAndGet();});
        assertArrayEquals(new Integer[]{9, 2}, array);
        assertEquals(2, calls.get());
        assertNull(ConvertUtil.convert(null, Target.class, v -> calls.incrementAndGet()));
        assertThrows(NumberFormatException.class, () -> ConvertUtil.convert("invalid", Integer.class, v -> calls.incrementAndGet()));
        assertEquals(2, calls.get());
        IllegalStateException failure = new IllegalStateException("callback");
        assertSame(failure, assertThrows(IllegalStateException.class,
                () -> ConvertUtil.convert(existing, Target.class, v -> {throw failure;})));
    }
}
