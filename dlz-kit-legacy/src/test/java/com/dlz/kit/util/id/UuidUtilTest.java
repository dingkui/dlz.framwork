package com.dlz.kit.util.id;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UuidUtil测试")
class UuidUtilTest {

    @Test
    @DisplayName("randomUUID 格式正确")
    void testRandomUUID() {
        String uuid = UuidUtil.randomUUID();
        assertNotNull(uuid);
        assertTrue(uuid.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"));
    }

    @Test
    @DisplayName("uuid 不包含横线")
    void testUuid() {
        String uuid = UuidUtil.uuid();
        assertNotNull(uuid);
        assertEquals(32, uuid.length());
        assertFalse(uuid.contains("-"));
    }

    @Test
    @DisplayName("shortUuid 长度为8")
    void testShortUuid() {
        String shortId = UuidUtil.shortUuid();
        assertNotNull(shortId);
        assertEquals(8, shortId.length());
    }

    @Test
    @DisplayName("shortUuid 仅包含合法字符")
    void testShortUuidChars() {
        String shortId = UuidUtil.shortUuid();
        assertTrue(shortId.matches("[a-zA-Z0-9]+"));
    }

    @Test
    @DisplayName("生成多个UUID唯一")
    void testUniqueness() {
        Set<String> uuids = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            uuids.add(UuidUtil.uuid());
        }
        assertEquals(1000, uuids.size());
    }

    @Test
    @DisplayName("生成多个shortUuid有合理唯一性")
    void testShortUuidUniqueness() {
        Set<String> uuids = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            uuids.add(UuidUtil.shortUuid());
        }
        assertEquals(100, uuids.size());
    }

    @Test
    @DisplayName("chars数组长度为62")
    void testCharsArrayLength() {
        assertEquals(62, UuidUtil.chars.length);
    }
}
