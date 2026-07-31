package com.dlz.kit.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DateFormat测试")
class DateFormatTest {

    @Test
    @DisplayName("of(pattern) 创建实例")
    void testOfPattern() {
        DateFormat df = DateFormat.of("yyyy-MM-dd");
        assertNotNull(df);
        assertEquals("yyyy-MM-dd", df.pattern);
    }

    @Test
    @DisplayName("of(pattern, timezone) 创建实例")
    void testOfPatternTimezone() {
        DateFormat df = DateFormat.of("yyyy-MM-dd", TimeZone.getTimeZone("UTC"));
        assertNotNull(df);
        assertEquals("yyyy-MM-dd", df.pattern);
    }

    @Test
    @DisplayName("format(Date) 格式化日期")
    void testFormatDate() {
        DateFormat df = DateFormat.of("yyyy-MM-dd");
        Date date = new Date();
        String result = df.format(date);
        assertNotNull(result);
        assertTrue(result.matches("\\d{4}-\\d{2}-\\d{2}"));
    }

    @Test
    @DisplayName("format(TemporalAccessor) 格式化LocalDateTime")
    void testFormatTemporalAccessor() {
        DateFormat df = DateFormat.of("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.of(2024, 1, 15, 10, 30, 45);
        String result = df.format(now);
        assertEquals("2024-01-15 10:30:45", result);
    }

    @Test
    @DisplayName("format(null) 返回空字符串")
    void testFormatNullTemporal() {
        DateFormat df = DateFormat.of("yyyy-MM-dd");
        assertEquals("", df.format((java.time.temporal.TemporalAccessor) null));
    }

    @Test
    @DisplayName("parse 解析日期字符串")
    void testParse() {
        DateFormat df = DateFormat.of("yyyy-MM-dd");
        Date date = df.parse("2024-01-15");
        assertNotNull(date);
    }

    @Test
    @DisplayName("parse 无效字符串返回null")
    void testParseInvalid() {
        DateFormat df = DateFormat.of("yyyy-MM-dd");
        assertNull(df.parse("not-a-date"));
    }

    @Test
    @DisplayName("parse2LocalDate 解析LocalDateTime")
    void testParse2LocalDateTime() {
        DateFormat df = DateFormat.of("yyyy-MM-dd HH:mm:ss");
        LocalDateTime result = df.parse2LocalDateTime("2024-01-15 10:30:45");
        assertNotNull(result);
        assertEquals(2024, result.getYear());
        assertEquals(1, result.getMonthValue());
        assertEquals(15, result.getDayOfMonth());
    }

    @Test
    @DisplayName("parse2LocalDate 无效字符串返回null")
    void testParse2LocalDateTimeInvalid() {
        DateFormat df = DateFormat.of("yyyy-MM-dd HH:mm:ss");
        assertNull(df.parse2LocalDateTime("not-a-datetime"));
    }

    @Test
    @DisplayName("formatNow 返回当前日期")
    void testFormatNow() {
        DateFormat df = DateFormat.of("yyyy-MM-dd");
        String result = df.formatNow();
        assertNotNull(result);
        assertTrue(result.matches("\\d{4}-\\d{2}-\\d{2}"));
    }

    @Test
    @DisplayName("toString 返回pattern")
    void testToString() {
        DateFormat df = DateFormat.of("yyyy-MM-dd HH:mm");
        assertEquals("yyyy-MM-dd HH:mm", df.toString());
    }

    @Test
    @DisplayName("并发format安全")
    void testConcurrentFormat() throws InterruptedException {
        DateFormat df = DateFormat.of("yyyy-MM-dd");
        Date date = new Date();
        Thread[] threads = new Thread[10];
        boolean[] errors = {false};
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    try {
                        String result = df.format(date);
                        if (result == null || !result.matches("\\d{4}-\\d{2}-\\d{2}")) {
                            errors[0] = true;
                        }
                    } catch (Exception e) {
                        errors[0] = true;
                    }
                }
            });
        }
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        assertFalse(errors[0]);
    }
}
