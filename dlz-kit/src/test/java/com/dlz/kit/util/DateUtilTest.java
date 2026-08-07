package com.dlz.kit.util;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


/**
 * DateUtil 工具类单元测试
 * 
 * 测试日期格式化、解析、转换等核心功能
 * 
 * @author test
 */
public class DateUtilTest {
    
    private static final Logger log = LoggerFactory.getLogger(DateUtilTest.class);
    
    // 测试数据
    private static final String TEST_DATE_STR = "2023-12-25";
    private static final String TEST_DATETIME_STR = "2023-12-25 14:30:45";
    private static final String TEST_UTC_STR = "2023-12-25T14:30:45.123Z";
    private static final String TEST_TIME_STR1 = "14:30";
    private static final String TEST_TIME_STR2 = "14:30:45";
    
    @Test
    public void testNow() {
        Date now = DateUtil.now();
        assertNotNull(now, "当前时间不应为null");
        log.info("当前时间: {}", now);
    }
    
    @Test
    public void testFormat_Date_String() {
        Date date = DateUtil.getDate(TEST_DATE_STR);
        String formatted = DateUtil.format(date, DateUtil.PATTERN_DATE);
        assertEquals(TEST_DATE_STR, formatted, "日期格式化应该正确");
        
        // 测试null输入
        String nullResult = DateUtil.format(null, DateUtil.PATTERN_DATE);
        assertEquals("", nullResult, "null日期应返回空字符串");
    }
    
    @Test
    public void testFormatNow() {
        String nowStr = DateUtil.formatNow(DateUtil.PATTERN_DATE);
        assertNotNull(nowStr, "当前时间格式化不应为null");
        assertTrue(nowStr.matches("\\d{4}-\\d{2}-\\d{2}"), "格式化结果应该是有效的日期格式");
        log.info("当前日期: {}", nowStr);
    }
    
    @Test
    public void testGetDateStr() {
        Date date = DateUtil.getDate(TEST_DATE_STR);
        String dateStr = DateUtil.getDateStr(date);
        assertEquals(TEST_DATE_STR, dateStr, "获取日期字符串应该正确");
        
        // 测试无参数版本
        String currentDateStr = DateUtil.getDateStr();
        assertNotNull(currentDateStr, "无参数版本不应返回null");
        assertTrue(currentDateStr.matches("\\d{4}-\\d{2}-\\d{2}"), "应该是有效的日期格式");
    }
    
    @Test
    public void testGetDateTimeStr() {
        Date date = DateUtil.getDate(TEST_DATETIME_STR);
        String dateTimeStr = DateUtil.getDateTimeStr(date);
        assertEquals(TEST_DATETIME_STR, dateTimeStr, "获取日期时间字符串应该正确");
        
        // 测试无参数版本
        String currentDateTimeStr = DateUtil.getDateTimeStr();
        assertNotNull(currentDateTimeStr, "无参数版本不应返回null");
        assertTrue(currentDateTimeStr.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"), "应该是有效的日期时间格式");
    }
    
    @Test
    public void testParseUTCDate() {
        Date date = DateUtil.parseUTCDate(TEST_UTC_STR);
        assertNotNull(date, "UTC日期解析不应为null");
        
        // 验证解析结果
        String formatted = DateUtil.format(date, DateUtil.PATTERN_UTC);
        log.info("UTC解析结果: {} -> {}", TEST_UTC_STR, formatted);
        
        // 测试无效格式
        Date invalidDate = DateUtil.parseUTCDate("invalid-date");
        assertNull(invalidDate, "无效UTC日期应返回null");
    }
    
    @Test
    public void testGetDate_String() {
        // 测试标准日期格式
        Date date1 = DateUtil.getDate(TEST_DATE_STR);
        assertNotNull(date1, "标准日期格式解析不应为null");
        
        // 测试日期时间格式
        Date date2 = DateUtil.getDate(TEST_DATETIME_STR);
        assertNotNull(date2, "日期时间格式解析不应为null");
        
        // 测试时间格式1 (HH:mm)
        Date date3 = DateUtil.getDate(TEST_TIME_STR1);
        assertNotNull(date3, "时间格式(HH:mm)解析不应为null");
        
        // 测试时间格式2 (HH:mm:ss)
        Date date4 = DateUtil.getDate(TEST_TIME_STR2);
        assertNotNull(date4, "时间格式(HH:mm:ss)解析不应为null");
        
        // 测试UTC格式
        Date date5 = DateUtil.getDate(TEST_UTC_STR);
        assertNotNull(date5, "UTC格式解析不应为null");
        
        // 测试null输入
        Date nullDate = DateUtil.getDate((String)null);
        assertNull(nullDate, "null输入应返回null");
        
        // 测试无效格式
        Date invalidDate = DateUtil.getDate("invalid-format");
        assertNull(invalidDate, "无效格式应返回null");
    }
    
    @Test
    public void testGetDate_String_String() {
        // 测试指定格式解析
        Date date = DateUtil.getDate(TEST_DATE_STR, DateUtil.PATTERN_DATE);
        assertNotNull(date, "指定格式解析不应为null");
        
        // 测试null格式（自动识别）
        Date autoDate = DateUtil.getDate(TEST_DATE_STR, null);
        assertNotNull(autoDate, "null格式应自动识别");
        
        // 测试null输入
        Date nullInput = DateUtil.getDate(null, DateUtil.PATTERN_DATE);
        assertNull(nullInput, "null输入应返回null");
        
        // 测试无效格式
        Date invalidFormatDate = DateUtil.getDate(TEST_DATE_STR, "invalid-pattern");
        assertNull(invalidFormatDate, "无效格式应返回null");
    }
    
    @Test
    public void testGetDate_LocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 25, 14, 30, 45);
        Date date = DateUtil.getDate(localDateTime);
        assertNotNull(date, "LocalDateTime转换为Date不应为null");
        
        // 验证转换结果
        String formatted = DateUtil.format(date, DateUtil.PATTERN_DATETIME);
        assertEquals("2023-12-25 14:30:45", formatted, "转换结果应该匹配");
    }
    
    @Test
    public void testGetDate_LocalDate() {
        LocalDate localDate = LocalDate.of(2023, 12, 25);
        Date date = DateUtil.getDate(localDate);
        assertNotNull(date, "LocalDate转换为Date不应为null");
        
        // 验证转换结果
        String formatted = DateUtil.format(date, DateUtil.PATTERN_DATE);
        assertEquals("2023-12-25", formatted, "转换结果应该匹配");
        
        // 测试null输入
        Date nullDate = DateUtil.getDate((LocalDate) null);
        assertNull(nullDate, "null LocalDate应返回null");
    }
    
    @Test
    public void testGetLocalDateTime_String_String() {
        // 测试指定格式解析
        LocalDateTime dateTime1 = DateUtil.getLocalDateTime(TEST_DATETIME_STR, DateUtil.PATTERN_DATETIME);
        assertNotNull(dateTime1, "指定格式解析不应为null");
        
        // 测试null格式（自动识别）
        LocalDateTime dateTime2 = DateUtil.getLocalDateTime(TEST_DATETIME_STR, null);
        assertNotNull(dateTime2, "null格式应自动识别");
        
        // 测试null输入
        LocalDateTime nullInput = DateUtil.getLocalDateTime(null, DateUtil.PATTERN_DATETIME);
        assertNull(nullInput, "null输入应返回null");
    }
    
    @Test
    public void testGetLocalDateTime_Date() {
        Date date = DateUtil.getDate(TEST_DATETIME_STR);
        LocalDateTime localDateTime = DateUtil.getLocalDateTime(date);
        assertNotNull(localDateTime, "Date转换为LocalDateTime不应为null");
        
        // 测试null输入
        LocalDateTime nullResult = DateUtil.getLocalDateTime((Date) null);
        assertNull(nullResult, "null Date应返回null");
    }
    
    @Test
    public void testGetLocalDateTime_String() {
        // 测试各种格式的自动识别
        LocalDateTime dateTime1 = DateUtil.getLocalDateTime(TEST_DATE_STR);
        assertNull(dateTime1, "日期格式解析不应为null");
        
        LocalDateTime dateTime2 = DateUtil.getLocalDateTime(TEST_DATETIME_STR);
        assertNotNull(dateTime2, "日期时间格式解析不应为null");
        
        LocalDateTime dateTime3 = DateUtil.getLocalDateTime(TEST_TIME_STR1);
        assertNotNull(dateTime3, "时间格式解析不应为null");
        
        LocalDateTime dateTime4 = DateUtil.getLocalDateTime(TEST_UTC_STR);
        assertNotNull(dateTime4, "UTC格式解析不应为null");
        
        // 测试null输入
        LocalDateTime nullResult = DateUtil.getLocalDateTime((String) null);
        assertNull(nullResult, "null输入应返回null");
        
        // 测试无效格式
        LocalDateTime invalidResult = DateUtil.getLocalDateTime("invalid-format");
        assertNull(invalidResult, "无效格式应返回null");
    }
    
    @Test
    public void testParseUTCLocalDateTime() {
        LocalDateTime dateTime = DateUtil.parseUTCLocalDateTime(TEST_UTC_STR);
        assertNotNull(dateTime, "UTC字符串解析为LocalDateTime不应为null");
        
        // 验证年份
        assertEquals(2023, dateTime.getYear(), "年份应该正确");
        assertEquals(12, dateTime.getMonthValue(), "月份应该正确");
        assertEquals(25, dateTime.getDayOfMonth(), "日期应该正确");
        
        // 测试无效格式
        LocalDateTime invalidResult = DateUtil.parseUTCLocalDateTime("invalid-utc-format");
        assertNull(invalidResult, "无效UTC格式应返回null");
        
        // 测试null输入
        LocalDateTime nullResult = DateUtil.parseUTCLocalDateTime(null);
        assertNull(nullResult, "null输入应返回null");
    }
    
    @Test
    public void testFormatter() {
        // 测试预定义格式
        DateFormat yearFormatter = DateUtil.formatter(DateUtil.PATTERN_YEAR);
        assertNotNull(yearFormatter, "年份格式化器不应为null");
        
        DateFormat monthFormatter = DateUtil.formatter(DateUtil.PATTERN_MONTH);
        assertNotNull(monthFormatter, "月份格式化器不应为null");
        
        DateFormat dateFormatter = DateUtil.formatter(DateUtil.PATTERN_DATE);
        assertNotNull(dateFormatter, "日期格式化器不应为null");
        
        // 测试自定义格式
        DateFormat customFormatter = DateUtil.formatter("yyyy/MM/dd");
        assertNotNull(customFormatter, "自定义格式化器不应为null");
    }
    
    @Test
    public void testDateFormatConsistency() {
        Date date = DateUtil.getDate(TEST_DATETIME_STR);
        LocalDateTime localDateTime = DateUtil.getLocalDateTime(TEST_DATETIME_STR);
        
        // 验证Date和LocalDateTime转换的一致性
        Date convertedDate = DateUtil.getDate(localDateTime);
        LocalDateTime convertedLocalDateTime = DateUtil.getLocalDateTime(date);
        
        assertNotNull(convertedDate, "转换后的Date不应为null");
        assertNotNull(convertedLocalDateTime, "转换后的LocalDateTime不应为null");
        
        String dateStr1 = DateUtil.format(date, DateUtil.PATTERN_DATETIME);
        String dateStr2 = DateUtil.format(convertedDate, DateUtil.PATTERN_DATETIME);
        assertEquals(dateStr1, dateStr2, "Date转换前后应该一致");
    }
    
    @Test
    public void testSpecialFormats() {
        // 测试各种预定义格式
        Date date = DateUtil.getDate(TEST_DATETIME_STR);
        
        String yearFormat = DateUtil.format(date, DateUtil.PATTERN_YEAR);
        assertEquals("2023", yearFormat, "年份格式应该正确");
        
        String monthFormat = DateUtil.format(date, DateUtil.PATTERN_MONTH);
        assertEquals("2023-12", monthFormat, "月份格式应该正确");
        
        String dateFormat = DateUtil.format(date, DateUtil.PATTERN_DATE);
        assertEquals(TEST_DATE_STR, dateFormat, "日期格式应该正确");
        
        String timeFormat = DateUtil.format(date, DateUtil.PATTERN_TIME);
        assertEquals("14:30:45", timeFormat, "时间格式应该正确");
        
        String dateTimeFormat = DateUtil.format(date, DateUtil.PATTERN_DATETIME);
        assertEquals(TEST_DATETIME_STR, dateTimeFormat, "日期时间格式应该正确");
    }
    
    @Test
    public void testMiniFormats() {
        Date date = DateUtil.getDate(TEST_DATETIME_STR);
        
        String miniDateFormat = DateUtil.format(date, DateUtil.PATTERN_DATE_MINI);
        assertEquals("20231225", miniDateFormat, "简化日期格式应该正确");
        
        String miniDateTimeFormat = DateUtil.format(date, DateUtil.PATTERN_DATETIME_MINI);
        assertEquals("20231225143045", miniDateTimeFormat, "简化日期时间格式应该正确");
    }
}