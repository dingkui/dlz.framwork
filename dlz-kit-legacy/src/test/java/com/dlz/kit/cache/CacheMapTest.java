package com.dlz.kit.cache;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CacheMap测试")
class CacheMapTest {

    @Test
    @DisplayName("getAndSet 缓存未命中时加载")
    void testGetAndSetCacheMiss() {
        CacheMap<String, String> cache = new CacheMap<>();
        String result = cache.getAndSet("key1", () -> "loaded");
        assertEquals("loaded", result);
        assertEquals("loaded", cache.get("key1"));
    }

    @Test
    @DisplayName("getAndSet 缓存命中时直接返回")
    void testGetAndSetCacheHit() {
        CacheMap<String, String> cache = new CacheMap<>();
        cache.put("key1", "existing");
        String result = cache.getAndSet("key1", () -> "new_value");
        assertEquals("existing", result);
    }

    @Test
    @DisplayName("getAndSet valueLoader为null")
    void testGetAndSetNullLoader() {
        CacheMap<String, String> cache = new CacheMap<>();
        String result = cache.getAndSet("key1", null);
        assertNull(result);
    }

    @Test
    @DisplayName("getAndSet valueLoader返回null不缓存")
    void testGetAndSetLoaderReturnsNull() {
        CacheMap<String, String> cache = new CacheMap<>();
        String result = cache.getAndSet("key1", () -> null);
        assertNull(result);
        assertNull(cache.get("key1"));
    }

    @Test
    @DisplayName("getAndSet valueLoader抛异常返回null")
    void testGetAndSetLoaderThrows() {
        CacheMap<String, String> cache = new CacheMap<>();
        String result = cache.getAndSet("key1", () -> {
            throw new RuntimeException("load error");
        });
        assertNull(result);
    }

    @Test
    @DisplayName("CacheMap 基本ConcurrentHashMap操作")
    void testBasicMapOperations() {
        CacheMap<String, Integer> cache = new CacheMap<>();
        cache.put("a", 1);
        cache.put("b", 2);
        assertEquals(2, cache.size());
        assertEquals(1, cache.get("a"));
        cache.remove("a");
        assertEquals(1, cache.size());
    }
}
