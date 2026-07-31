package com.dlz.kit.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MemoryCache测试")
class MemoryCacheTest {

    private MemoryCache cache;

    @BeforeEach
    void setUp() {
        // MemoryCache uses a static CACHE map shared across all instances; clear it
        try {
            Field cacheField = MemoryCache.class.getDeclaredField("CACHE");
            cacheField.setAccessible(true);
            ((Map<?, ?>) cacheField.get(null)).clear();
        } catch (Exception ignored) {}
        cache = new MemoryCache();
    }

    @Test
    @DisplayName("put和get基本操作")
    void testPutAndGet() {
        cache.put("test", "key1", "value1", -1);
        Serializable result = cache.get("test", "key1", null);
        assertEquals("value1", result);
    }

    @Test
    @DisplayName("get不存在的key返回null")
    void testGetNonExistent() {
        assertNull(cache.get("test", "nonexistent", null));
    }

    @Test
    @DisplayName("remove删除缓存")
    void testRemove() {
        cache.put("test", "key1", "value1", -1);
        cache.remove("test", "key1");
        assertNull(cache.get("test", "key1", null));
    }

    @Test
    @DisplayName("removeAll清空缓存")
    void testRemoveAll() {
        cache.put("test", "key1", "value1", -1);
        cache.put("test", "key2", "value2", -1);
        cache.removeAll("test");
        assertNull(cache.get("test", "key1", null));
        assertNull(cache.get("test", "key2", null));
    }

    @Test
    @DisplayName("keys 通配符 * 返回全部")
    void testKeysWildcard() {
        cache.put("test", "key1", "v1", -1);
        cache.put("test", "key2", "v2", -1);
        Set<String> keys = cache.keys("test", "*");
        assertEquals(2, keys.size());
        assertTrue(keys.contains("key1"));
        assertTrue(keys.contains("key2"));
    }

    @Test
    @DisplayName("keys 通配符 .* 返回全部")
    void testKeysDotWildcard() {
        cache.put("test", "a1", "v1", -1);
        cache.put("test", "b2", "v2", -1);
        Set<String> keys = cache.keys("test", ".*");
        assertEquals(2, keys.size());
    }

    @Test
    @DisplayName("keys 前缀匹配")
    void testKeysPrefixMatch() {
        cache.put("test", "user:1", "v1", -1);
        cache.put("test", "user:2", "v2", -1);
        cache.put("test", "order:1", "v3", -1);
        Set<String> keys = cache.keys("test", "user:*");
        assertEquals(2, keys.size());
        assertTrue(keys.contains("user:1"));
        assertTrue(keys.contains("user:2"));
    }

    @Test
    @DisplayName("all 通配符返回全部")
    void testAllWildcard() {
        cache.put("test", "k1", "v1", -1);
        cache.put("test", "k2", "v2", -1);
        Map<String, Serializable> all = cache.all("test", "*");
        assertEquals(2, all.size());
        assertEquals("v1", all.get("k1"));
    }

    @Test
    @DisplayName("all 前缀过滤")
    void testAllPrefixFilter() {
        cache.put("test", "user:1", "v1", -1);
        cache.put("test", "order:1", "v2", -1);
        Map<String, Serializable> all = cache.all("test", "user:*");
        assertEquals(1, all.size());
        assertTrue(all.containsKey("user:1"));
    }

    @Test
    @DisplayName("带过期时间的put")
    void testPutWithExpiration() {
        cache.put("test", "expiring", "value", 3600);
        assertEquals("value", cache.get("test", "expiring", null));
    }

    @Test
    @DisplayName("过期缓存不可读取和枚举")
    void testExpiredValueIsInvisible() throws InterruptedException {
        cache.put("test", "expiring", "value", 1);
        Thread.sleep(1100);

        assertNull(cache.get("test", "expiring", null));
        assertFalse(cache.keys("test").contains("expiring"));
        assertFalse(cache.all("test").containsKey("expiring"));
    }

    @Test
    @DisplayName("支持非字符串可序列化键")
    void testSerializableKey() {
        cache.put("test", 1, "value", -1);

        assertEquals("value", cache.get("test", 1, null));
        cache.remove("test", 1);
        assertNull(cache.get("test", 1, null));
    }

    @Test
    @DisplayName("不同缓存名称隔离")
    void testCacheNameIsolation() {
        cache.put("cache1", "key", "v1", -1);
        cache.put("cache2", "key", "v2", -1);
        assertEquals("v1", cache.get("cache1", "key", null));
        assertEquals("v2", cache.get("cache2", "key", null));
    }

    @Test
    @DisplayName("MemoryCache(cacheName) 构造器注册到CacheHolder")
    void testConstructorWithCacheName() {
        // Clean up before test
        try {
            java.lang.reflect.Field cacheSetField = CacheHolder.class.getDeclaredField("CacheSet");
            cacheSetField.setAccessible(true);
            Map<String, ICache> cacheSet = (Map<String, ICache>) cacheSetField.get(null);
            cacheSet.remove("memTest");
        } catch (Exception ignored) {}

        MemoryCache namedCache = new MemoryCache("memTest");
        ICache retrieved = CacheHolder.get("memTest");
        assertSame(namedCache, retrieved);
    }
}
