package com.dlz.kit.util;

import com.dlz.kit.json.JSONMap;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * StringUtils 工具类单元测试
 * 
 * 测试字符串处理的各种功能
 * 
 * @author test
 */
public class StringUtilsTest {
    
    private static final Logger log = LoggerFactory.getLogger(StringUtilsTest.class);
    
    @Test
    public void testNVL() {
        // 测试基本NVL功能
        assertEquals("", StringUtils.NVL(null), "空字符串测试");
        assertEquals("test", StringUtils.NVL("test"), "正常字符串测试");
        assertEquals("", StringUtils.NVL(""), "空字符串保持");
        
        // 测试带默认值的NVL
        assertEquals("default", StringUtils.NVL(null, "default"), "null默认值测试");
        assertEquals("test", StringUtils.NVL("test", "default"), "正常值测试");
        assertEquals("", StringUtils.NVL("", "default"), "空字符串保持");
    }
    
    @Test
    public void testGetBeanId() {
        // 测试类名转Bean ID
        assertEquals("stringUtils", StringUtils.getBeanId("com.dlz.kit.util.StringUtils"), "简单类名测试");
        assertEquals("test", StringUtils.getBeanId("Test"), "单级包名测试");
        assertEquals("className", StringUtils.getBeanId("com.example.package.ClassName"), "多级包名测试");
        
        // 测试Class对象转Bean ID
        assertEquals("stringUtils", StringUtils.getBeanId(StringUtils.class), "Class对象测试");
        assertEquals("string", StringUtils.getBeanId(String.class), "String类测试");
    }
    
    @Test
    public void testFormatMsg_Object_ObjectArray() {
        // 基本格式化测试
        assertEquals("this is a for b", StringUtils.formatMsg("this is {} for {}", "a", "b"), "基本占位符测试");
        
        // 指定下标测试
        assertEquals("this is b for a", StringUtils.formatMsg("this is {1} for {0}", "a", "b"), "指定下标测试");
        
        // 混合下标测试
        assertEquals("this is b for b and a", StringUtils.formatMsg("this is {1} for {} and {0}", "a", "b"), "混合下标测试");
        
        // 说明+下标测试
        assertEquals("this is b for a", StringUtils.formatMsg("this is {param1} for {0}", "a", "b"), "说明加下标测试");
        
        // 下标越界测试
        assertEquals("this is {9} for b", StringUtils.formatMsg("this is {9} for {}", "a", "b"), "下标越界测试");
        
        // 参数不足测试
        assertEquals("this is a for b and {}", StringUtils.formatMsg("this is {} for {} and {}", "a", "b"), "参数不足测试");
        
        // 空参数测试
        assertEquals("this is  for ", StringUtils.formatMsg("this is {} for {}", "", ""), "空参数测试");
        
        // null参数测试
        assertEquals("this is null for null", StringUtils.formatMsg("this is {} for {}", null, null), "null参数测试");
    }
    
    @Test
    public void testFormatMsg_Object_JSONMap() {
        // 准备测试数据
        JSONMap map = new JSONMap();
        map.put("name", "张三");
        map.put("age", "25");
        map.put("city.name", "北京");
        
        // 基本替换测试
        assertEquals("姓名：张三，年龄：25", StringUtils.formatMsg("姓名：${name}，年龄：${age}", map), "基本替换测试");
        
        // 嵌套属性测试
        assertEquals("来自北京", StringUtils.formatMsg("来自${city.name}", map), "嵌套属性测试");
        
        // 不存在的属性测试
        assertEquals("姓名：张三，地址：{address}", StringUtils.formatMsg("姓名：${name}，地址：${address}", map), "不存在属性测试");
        
        // 空字符串测试
        assertEquals("", StringUtils.formatMsg("", map), "空字符串测试");
        
        // null输入测试
        assertEquals("", StringUtils.formatMsg(null, map), "null输入测试");
    }
    
    @Test
    public void testAddZeroBefor() {
        // 基本测试
        assertEquals("00123", StringUtils.addZeroBefor(123, 5), "基本补零测试");
        assertEquals("12345", StringUtils.addZeroBefor(12345, 5), "刚好长度测试");
        assertEquals("123456", StringUtils.addZeroBefor(123456, 5), "超长测试");
        assertEquals("-0123", StringUtils.addZeroBefor(-123, 5), "负数测试");
        assertEquals("00000", StringUtils.addZeroBefor(0, 5), "零测试");
    }
    
    @Test
    public void testIsEmpty() {
        // null测试
        assertTrue(StringUtils.isEmpty(null), "null应该为空");
        
        // CharSequence测试
        assertTrue(StringUtils.isEmpty(""), "空字符串应该为空");
        assertFalse(StringUtils.isEmpty("test"), "非空字符串不应该为空");
        assertFalse(StringUtils.isEmpty(" "), "空格字符串不应该为空");
        
        // Collection测试
        assertTrue(StringUtils.isEmpty(new ArrayList<>()), "空List应该为空");
        assertFalse(StringUtils.isEmpty(Arrays.asList("test")), "非空List不应该为空");
        
        // Map测试
        assertTrue(StringUtils.isEmpty(new HashMap<>()), "空Map应该为空");
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        assertFalse(StringUtils.isEmpty(map), "非空Map不应该为空");
        
        // Array测试
        assertTrue(StringUtils.isEmpty(new String[0]), "空数组应该为空");
        assertFalse(StringUtils.isEmpty(new String[]{"test"}), "非空数组不应该为空");
        
        // Optional测试
        assertTrue(StringUtils.isEmpty(Optional.empty()), "空Optional应该为空");
        assertFalse(StringUtils.isEmpty(Optional.of("test")), "非空Optional不应该为空");
        
        // 普通对象测试
        assertFalse(StringUtils.isEmpty(new Object()), "普通对象不应该为空");
    }
    
    @Test
    public void testIsAnyEmpty() {
        // 全部非空测试
        assertFalse(StringUtils.isAnyEmpty("a", "b", "c"), "全部非空应该返回false");
        
        // 包含null测试
        assertTrue(StringUtils.isAnyEmpty("a", null, "c"), "包含null应该返回true");
        
        // 包含空字符串测试
        assertTrue(StringUtils.isAnyEmpty("a", "", "c"), "包含空字符串应该返回true");
        
        // 全部空测试
        assertTrue(StringUtils.isAnyEmpty(null, "", new ArrayList<>()), "全部空应该返回true");
        
        // 单个参数测试
        assertTrue(StringUtils.isAnyEmpty((Object) null), "单个null参数应该返回true");
        assertFalse(StringUtils.isAnyEmpty("test"), "单个非空参数应该返回false");
    }
    
    @Test
    public void testIsBlank() {
        // 基本测试
        assertTrue(StringUtils.isBlank(null), "null应该为空白");
        assertTrue(StringUtils.isBlank(""), "空字符串应该为空白");
        assertTrue(StringUtils.isBlank(" "), "只有空格应该为空白");
        assertTrue(StringUtils.isBlank("   "), "多个空格应该为空白");
        assertTrue(StringUtils.isBlank("\t"), "制表符应该为空白");
        assertTrue(StringUtils.isBlank("\n"), "换行符应该为空白");
        assertTrue(StringUtils.isBlank(" \t\n "), "混合空白字符应该为空白");
        
        // 非空白测试
        assertFalse(StringUtils.isBlank("a"), "包含字符不应该为空白");
        assertFalse(StringUtils.isBlank(" a "), "前后有空格但中间有字符不应该为空白");
        assertFalse(StringUtils.isBlank("123"), "数字字符不应该为空白");
    }
    
    @Test
    public void testIsAnyBlank() {
        // 全部非空白测试
        assertFalse(StringUtils.isAnyBlank("a", "b", "c"), "全部非空白应该返回false");
        
        // 包含空白测试
        assertTrue(StringUtils.isAnyBlank("a", " ", "c"), "包含空白应该返回true");
        assertTrue(StringUtils.isAnyBlank("a", null, "c"), "包含null应该返回true");
        assertTrue(StringUtils.isAnyBlank("a", "", "c"), "包含空字符串应该返回true");
        
        // 全部空白测试
        assertTrue(StringUtils.isAnyBlank(null, "", "   "), "全部空白应该返回true");
        
        // 单个参数测试
        assertTrue(StringUtils.isAnyBlank(" "), "单个空白参数应该返回true");
        assertFalse(StringUtils.isAnyBlank("test"), "单个非空白参数应该返回false");
    }
    
    @Test
    public void testIsAllBlank() {
        // 全部空白测试
        assertTrue(StringUtils.isAllBlank(null, null), "全部null应该返回true");
        assertTrue(StringUtils.isAllBlank("", ""), "全部空字符串应该返回true");
        assertTrue(StringUtils.isAllBlank(" ", "   ", "\t"), "全部空白字符应该返回true");
        assertTrue(StringUtils.isAllBlank(null, "", " "), "混合空白应该返回true");
        
        // 包含非空白测试
        assertFalse(StringUtils.isAllBlank("a", " ", "c"), "包含非空白应该返回false");
        assertFalse(StringUtils.isAllBlank("", "test"), "部分空白应该返回false");
        
        // 单个参数测试
        assertTrue(StringUtils.isAllBlank(" "), "单个空白参数应该返回true");
        assertFalse(StringUtils.isAllBlank("test"), "单个非空白参数应该返回false");
    }
    
    @Test
    public void testStartsWithAny() {
        // 基本测试
        assertTrue(StringUtils.startsWithAny("abcdef", "abc", "xyz"), "应该以任一字符串开头");
        assertFalse(StringUtils.startsWithAny("abcdef", "xyz", "123"), "不应该以任何字符串开头");
        
        // null测试
        assertFalse(StringUtils.startsWithAny(null, "abc"), "null序列应该返回false");
        assertFalse(StringUtils.startsWithAny("abcdef"), "空搜索数组应该返回false");
        
        // 空序列测试
        assertFalse(StringUtils.startsWithAny("", "abc"), "空序列应该返回false");
        
        // 边界情况测试
        assertTrue(StringUtils.startsWithAny("abc", "abc"), "相同字符串应该返回true");
        assertFalse(StringUtils.startsWithAny("ab", "abc"), "较短序列应该返回false");
    }
    
    @Test
    public void testStartsWith() {
        // 基本测试
        assertTrue(StringUtils.startsWith("abcdef", "abc"), "应该以指定字符串开头");
        assertFalse(StringUtils.startsWith("abcdef", "xyz"), "不应该以指定字符串开头");
        
        // 边界测试
        assertTrue(StringUtils.startsWith("abc", "abc"), "相同字符串应该返回true");
        assertFalse(StringUtils.startsWith("ab", "abc"), "较长搜索串应该返回false");
        assertFalse(StringUtils.startsWith(null, "abc"), "null序列应该返回false");
        assertFalse(StringUtils.startsWith("", "abc"), "空序列应该返回false");
        assertTrue(StringUtils.startsWith("abc", ""), "空搜索串应该返回true");
    }
    
    @Test
    public void testIsNumber() {
        // 数字测试
        assertTrue(StringUtils.isNumber("123"), "整数应该返回true");
        assertTrue(StringUtils.isNumber("-123"), "负数应该返回true");
        assertTrue(StringUtils.isNumber("+123"), "正数应该返回true");
        assertTrue(StringUtils.isNumber("123.45"), "小数应该返回true");
        assertTrue(StringUtils.isNumber("-123.45"), "负小数应该返回true");
        
        // 非数字测试
        assertFalse(StringUtils.isNumber("123a"), "包含字母不应该返回true");
        assertFalse(StringUtils.isNumber(""), "空字符串不应该返回true");
        assertFalse(StringUtils.isNumber(null), "null不应该返回true");
        assertFalse(StringUtils.isNumber("-"), "只有符号不应该返回true");
        assertFalse(StringUtils.isNumber("12.34.56"), "多个小数点不应该返回true");
    }
    
    @Test
    public void testIsLongOrInt() {
        // 整数测试
        assertTrue(StringUtils.isLongOrInt("123"), "整数应该返回true");
        assertTrue(StringUtils.isLongOrInt("-123"), "负整数应该返回true");
        assertTrue(StringUtils.isLongOrInt("+123"), "正整数应该返回true");
        
        // 非整数测试
        assertFalse(StringUtils.isLongOrInt("123.45"), "小数不应该返回true");
        assertFalse(StringUtils.isLongOrInt("123a"), "包含字母不应该返回true");
        assertFalse(StringUtils.isLongOrInt(""), "空字符串不应该返回true");
        assertFalse(StringUtils.isLongOrInt(null), "null不应该返回true");
    }
    
    @Test
    public void testIsNotEmpty() {
        // 非空测试
        assertTrue(StringUtils.isNotEmpty("test"), "非空字符串应该返回true");
        assertTrue(StringUtils.isNotEmpty(" "), "空格字符串应该返回true");
        assertTrue(StringUtils.isNotEmpty(Arrays.asList("test")), "非空集合应该返回true");
        
        // 空测试
        assertFalse(StringUtils.isNotEmpty(null), "null应该返回false");
        assertFalse(StringUtils.isNotEmpty(""), "空字符串应该返回false");
        assertFalse(StringUtils.isNotEmpty(new ArrayList<>()), "空集合应该返回false");
    }
    
    @Test
    public void testCapitalize() {
        // 基本测试
        assertEquals("Test", StringUtils.capitalize("test"), "首字母应该大写");
        assertEquals("Test", StringUtils.capitalize("Test"), "已经大写应该保持");
        assertEquals("A", StringUtils.capitalize("a"), "单字符应该大写");
        
        // 边界测试
        assertNull(StringUtils.capitalize(null), "null应该返回null");
        assertEquals("", StringUtils.capitalize(""), "空字符串应该保持");
        assertEquals("!test", StringUtils.capitalize("!test"), "特殊字符应该保持");
    }
    
    @Test
    public void testLeftPad() {
        // 基本测试
        assertEquals("zzzbat", StringUtils.leftPad("bat", 6, 'z'), "应该左填充");
        assertEquals("bat", StringUtils.leftPad("bat", 3, 'z'), "刚好长度应该保持");
        assertEquals("bat", StringUtils.leftPad("bat", 1, 'z'), "长度不足应该保持");
        assertEquals("bat", StringUtils.leftPad("bat", -1, 'z'), "负长度应该保持");
        
        // 边界测试
        assertNull(StringUtils.leftPad(null, 5, 'z'), "null应该返回null");
        assertEquals("zzz", StringUtils.leftPad("", 3, 'z'), "空字符串应该填充");
    }
    
    @Test
    public void testRepeat() {
        // 基本测试
        assertEquals("eee", StringUtils.repeat('e', 3), "应该重复字符");
        assertEquals("", StringUtils.repeat('e', 0), "零次重复应该返回空");
        assertEquals("", StringUtils.repeat('e', -2), "负数重复应该返回空");
        assertEquals("a", StringUtils.repeat('a', 1), "单次重复应该返回单字符");
    }
    
    @Test
    public void testJoin_Array() {
        String[] array = {"a", "b", "c"};
        
        // 基本测试
        assertEquals("a;b;c", StringUtils.join(array, ";"), "应该正确连接");
        assertEquals("abc", StringUtils.join(array, null), "null分隔符应该无分隔符连接");
        assertEquals("a|b|c", StringUtils.join(array, "|"), "空字符串分隔符");
        
        // 边界测试
        assertNull(StringUtils.join((String[]) null, ";"), "null数组应该返回null");
        assertEquals("", StringUtils.join(new String[0], ";"), "空数组应该返回空字符串");
        
        // 包含null元素测试
        String[] arrayWithNull = {"a", null, "c"};
        assertEquals("a;;c", StringUtils.join(arrayWithNull, ";"), "null元素应该变为空字符串");
    }
    
    @Test
    public void testJoin_Collection() {
        List<String> list = Arrays.asList("a", "b", "c");
        
        // 基本测试
        assertEquals("a;b;c", StringUtils.join(list, ";"), "应该正确连接");
        assertEquals("a|b|c", StringUtils.join(list, '|'), "字符分隔符测试");
        
        // 边界测试
        assertEquals("", StringUtils.join(new ArrayList<>(), ";"), "空集合应该返回空字符串");
        assertEquals("a;;c", StringUtils.join(Arrays.asList("a", null, "c"), ";"), "null元素测试");
    }
    
    @Test
    public void testJoin_Iterable() {
        List<String> list = Arrays.asList("a", "b", "c");
        
        // 基本测试
        assertEquals("a;b;c", StringUtils.join(";", list), "Iterable连接测试");
        
        // 空Iterable测试
        assertEquals("", StringUtils.join(";", new ArrayList<>()), "空Iterable测试");
    }
    
    @Test
    public void testArrayToList() {
        String[] array = {"a", "b", "c"};
        List<String> list = StringUtils.arrayToList(array);
        
        assertNotNull(list, "返回列表不应该为null");
        assertEquals(3, list.size(), "列表大小应该正确");
        assertEquals("a", list.get(0), "第一个元素应该正确");
        assertEquals("c", list.get(2), "最后一个元素应该正确");
        
        // 空数组测试
        List<String> emptyList = StringUtils.arrayToList(new String[0]);
        assertNotNull(emptyList, "空数组转换不应该为null");
        assertTrue(emptyList.isEmpty(), "空数组转换应该为空列表");
    }
    
    @Test
    public void testListToArray() {
        List<String> list = Arrays.asList("a", "b", "c");
        Object[] array = StringUtils.listToArray(list);
        
        assertNotNull(array, "返回数组不应该为null");
        assertEquals(3, array.length, "数组长度应该正确");
        assertEquals("a", array[0], "第一个元素应该正确");
        assertEquals("c", array[2], "最后一个元素应该正确");
        
        // 空列表测试
        Object[] emptyArray = StringUtils.listToArray(new ArrayList<>());
        assertNotNull(emptyArray, "空列表转换不应该为null");
        assertEquals(0, emptyArray.length, "空列表转换应该为空数组");
    }
    
    @Test
    public void testSplit() {
        // 基本测试
        String[] result = StringUtils.split("a,b,c", ",");
        assertArrayEquals(new String[]{"a", "b", "c"}, result, "应该正确分割");
        
        // 正则表达式测试
        String[] regexResult = StringUtils.split("a1b2c", "\\d");
        assertArrayEquals(new String[]{"a", "b", "c"}, regexResult, "正则分割测试");
        
        // 边界测试
        assertNull(StringUtils.split(null, ","), "null输入应该返回null");
        String[] emptyResult = StringUtils.split("", ",");
        assertArrayEquals(new String[]{""}, emptyResult, "空字符串应该返回空数组");
        
        // 无匹配分割符测试
        String[] noMatchResult = StringUtils.split("abc", ",");
        assertArrayEquals(new String[]{"abc"}, noMatchResult, "无匹配应该返回原字符串");
    }
    
    @Test
    public void testGetReplaceStr() {
        // 准备测试数据
        Map<String, String> testData = new HashMap<>();
        testData.put("name", "张三");
        testData.put("age", "25");
        testData.put("info", "${name}今年${age}岁");
        
        // 测试Function包装
        java.util.function.Function<String, Object> getter = testData::get;
        
        // 基本测试
        Object result = StringUtils.getReplaceStr("name", getter, 0);
        assertEquals("张三", result, "基本获取测试");
        
        // 递归替换测试
        Object recursiveResult = StringUtils.getReplaceStr("info", getter, 0);
        assertEquals("张三今年25岁", recursiveResult, "递归替换测试");
        
        // null值处理测试
        Object nullResult = StringUtils.getReplaceStr("notExist", getter, 0);
        assertNull(nullResult, "nullType=0应该返回null");
        
        Object nullResult1 = StringUtils.getReplaceStr("notExist", getter, 1);
        assertEquals("notExist", nullResult1, "nullType=1应该返回键名");
        
        Object nullResult2 = StringUtils.getReplaceStr("notExist", getter, 2);
        assertEquals("{notExist}", nullResult2, "nullType=2应该返回{键名}");
    }
}