package com.dlz.kit.cache;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CacheUtil测试")
class CacheUtilTest {

    @BeforeEach
    void setUp() {
        clearCacheHolder();
        resetCacheUtilField();
    }

    @AfterEach
    void tearDown() {
        clearCacheHolder();
        resetCacheUtilField();
    }

    private void clearCacheHolder() {
        try {
            Field cacheSetField = CacheHolder.class.getDeclaredField("CacheSet");
            cacheSetField.setAccessible(true);
            Map<String, ICache> cacheSet = (Map<String, ICache>) cacheSetField.get(null);
            cacheSet.clear();
        } catch (Exception ignored) {}
    }

    private void resetCacheUtilField() {
        try {
            Field cacheField = CacheUtil.class.getDeclaredField("cache");
            cacheField.setAccessible(true);
            cacheField.set(null, null);
        } catch (Exception ignored) {}
    }

    @Test
    @DisplayName("getMemoCache 返回非null缓存")
    void testGetMemoCache() {
        ICache memoCache = CacheUtil.getMemoCache();
        assertNotNull(memoCache);
    }

    @Test
    @DisplayName("getCache(name) 默认创建MemoryCache")
    void testGetCacheDefaultMemoryCache() {
        ICache cache = CacheUtil.getCache("test");
        assertNotNull(cache);
        assertTrue(cache instanceof MemoryCache);
    }

    @Test
    @DisplayName("put和get基本操作")
    void testPutAndGet() {
        CacheUtil.put("testCache", "key1", "value1", -1);
        Serializable result = CacheUtil.get("testCache", "key1");
        assertEquals("value1", result);
    }

    @Test
    @DisplayName("get(cacheName, key, valueLoader) 懒加载")
    void testGetWithValueLoader() {
        String result = CacheUtil.get("testCache", "lazyKey", () -> "loaded");
        assertEquals("loaded", result);
    }

    @Test
    @DisplayName("evict 清除单个缓存")
    void testEvict() {
        CacheUtil.put("testCache", "key1", "value1", -1);
        CacheUtil.evict("testCache", "key1");
        assertNull(CacheUtil.get("testCache", "key1"));
    }

    @Test
    @DisplayName("clear 清空缓存")
    void testClear() {
        CacheUtil.put("testCache", "key1", "value1", -1);
        CacheUtil.put("testCache", "key2", "value2", -1);
        CacheUtil.clear("testCache");
        assertNull(CacheUtil.get("testCache", "key1"));
        assertNull(CacheUtil.get("testCache", "key2"));
    }

    @Test
    @DisplayName("init 设置自定义缓存实现")
    void testInit() {
        MemoryCache customCache = new MemoryCache();
        CacheUtil.init(customCache);
        ICache cache = CacheUtil.getCache("newName");
        assertNotNull(cache);
    }

    @Test
    @DisplayName("init(null) 不覆盖")
    void testInitNull() {
        CacheUtil.init(null);
        ICache cache = CacheUtil.getCache("default");
        assertNotNull(cache);
    }
}
