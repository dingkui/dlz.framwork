package com.dlz.kit.cache;

import com.dlz.kit.util.VAL;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;

class MemoryCacheNullTest {
    @Test void rejectsNullWithoutCreatingOrReplacingAnEntry() {
        MemoryCache cache = new MemoryCache();
        String name = UUID.randomUUID().toString();
        try {
            assertThrows(IllegalArgumentException.class, () -> cache.put(name, "missing", null, 60));
            assertTrue(cache.keys(name, "*").isEmpty());
            assertTrue(cache.all(name).isEmpty());
            cache.put(name, "existing", "old", 0);
            assertThrows(IllegalArgumentException.class, () -> cache.put(name, "existing", null, 60));
            assertEquals("old", cache.get(name, "existing", String.class));
        } finally {cache.removeAll(name);}
    }
    @Test void loadersReturningNullDoNotWriteAndCanRetry() {
        MemoryCache cache = new MemoryCache();
        String name = UUID.randomUUID().toString();
        AtomicInteger calls = new AtomicInteger();
        try {
            for (int i=0;i<2;i++) {
                assertNull(cache.getAndSet(name, "one", () -> {calls.incrementAndGet(); return VAL.of(null, 60);}));
                assertNull(cache.getAndSetList(name, "list", () -> {calls.incrementAndGet(); return VAL.of(null, 60);}, String.class));
                assertNull(cache.getAndSet(name, "no-result", () -> {calls.incrementAndGet(); return null;}));
            }
            assertEquals(6, calls.get());
            assertTrue(cache.all(name).isEmpty());
            assertEquals("loaded", cache.getAndSet(name, "one", () -> VAL.of("loaded", 60)));
            assertEquals("loaded", cache.getAndSet(name, "one", () -> {fail("cache hit must not load"); return null;}));
        } finally {cache.removeAll(name);}
    }
}
