package com.dlz.kit.util;

import com.dlz.kit.exception.SystemException;
import com.dlz.kit.json.JSONList;
import com.dlz.kit.json.JSONMap;
import com.dlz.kit.json.InternalJsonMapper;
import com.dlz.kit.json.core.Json;
import com.dlz.kit.json.core.JsonMapper;
import com.dlz.kit.json.core.JsonTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JacksonUtil工具类单元测试
 * 
 * 测试JacksonUtil的各种JSON处理功能
 * 
 * @author dk
 */
@DisplayName("JsonUtil工具类测试")
class JsonUtilTest {

    @Nested
    @DisplayName("基础功能测试")
    class BasicFunctionalityTests {

        @Test
        @DisplayName("获取JsonMapper实例测试")
        void testGetInstance() {
            JsonMapper mapper = JsonUtil.getMapper();
            assertNotNull(mapper);
            assertSame(mapper, JsonUtil.getMapper());
            assertTrue(mapper instanceof InternalJsonMapper);
        }

        @Test
        @DisplayName("对象转JSON字符串测试")
        void testGetJson() {
            Map<String, Object> map = new HashMap<>();
            map.put("name", "测试");
            map.put("age", 25);
            map.put("active", true);
            
            String json = JsonUtil.getJson(map);
            assertNotNull(json);
            assertTrue(json.contains("\"name\":\"测试\""));
            assertTrue(json.contains("\"age\":25"));
            assertTrue(json.contains("\"active\":true"));
        }

        @Test
        @DisplayName("对象转JSON字节数组测试")
        void testToJsonAsBytes() {
            Map<String, Object> map = new HashMap<>();
            map.put("test", "测试");
            
            byte[] bytes = JsonUtil.toJsonAsBytes(map);
            assertNotNull(bytes);
            assertTrue(bytes.length > 0);
        }
    }

    @Nested
    @DisplayName("反序列化测试")
    class DeserializationTests {

        @Test
        @DisplayName("字符串反序列化为JSONMap测试")
        void testReadValueStringToJSONMap() {
            String json = "{\"name\":\"测试用户\",\"age\":30,\"active\":true}";
            JSONMap map = JsonUtil.readValue(json);
            
            assertNotNull(map);
            assertEquals("测试用户", map.getStr("name"));
            assertEquals(Integer.valueOf(30), map.getInt("age"));
            assertEquals(Boolean.TRUE, map.getBoolean("active"));
        }

        @Test
        @DisplayName("字符串反序列化为指定类型测试")
        void testReadValueStringToSpecificType() {
            String json = "{\"name\":\"测试\",\"age\":25}";
            Map<String, Object> map = JsonUtil.readValue(json, Map.class);
            assertEquals("测试", map.get("name"));
            assertEquals(Integer.valueOf(25), map.get("age"));
        }

        @Test
        @DisplayName("字符串反序列化为列表测试")
        void testReadListValue() {
            String jsonArray = "[\"元素1\", \"元素2\", \"元素3\"]";
            List<String> list = JsonUtil.readListValue(jsonArray, String.class);
            
            assertEquals(3, list.size());
            assertEquals("元素1", list.get(0));
            assertEquals("元素2", list.get(1));
            assertEquals("元素3", list.get(2));
        }

        @Test
        @DisplayName("字符串反序列化为JSONList测试")
        void testReadList() {
            String jsonArray = "[\"元素1\", 123, true]";
            JSONList list = JsonUtil.readList(jsonArray);
            
            assertEquals(3, list.size());
            assertEquals("元素1", list.getStr(0));
            assertEquals(Integer.valueOf(123), list.getInt(1));
            assertEquals(Boolean.TRUE, list.getBoolean(2));
        }

        @Test
        @DisplayName("无效JSON处理测试")
        void testInvalidJsonHandling() {
            assertThrows(SystemException.class, () -> JsonUtil.readValue("{invalid json}", JSONMap.class));
            assertThrows(SystemException.class, () -> JsonUtil.readListValue("{invalid json}", String.class));
        }
    }

    @Nested
    @DisplayName("原生JSON树操作测试")
    class JsonNodeTests {

        @Test
        @DisplayName("对象转原生树测试")
        void testValueToTree() {
            Map<String, Object> map = new HashMap<>();
            map.put("name", "测试");
            map.put("age", 25);
            
            Map<String, Object> node = Json.parseObject(JsonUtil.getJson(map));
            assertEquals("测试", node.get("name"));
            assertEquals(25, node.get("age"));
        }

        @Test
        @DisplayName("内容转原生树测试")
        void testReadTree() {
            String json = "{\"name\":\"测试\",\"nested\":{\"value\":123}}";
            Map<String, Object> node = Json.parseObject(json);
            assertEquals("测试", node.get("name"));
            assertEquals(123, ((Map<?, ?>) node.get("nested")).get("value"));
        }
    }

    @Nested
    @DisplayName("类型转换测试")
    class TypeConversionTests {

        @Test
        @DisplayName("coverObj方法测试")
        void testCoverObj() {
            String jsonString = "{\"name\":\"测试\",\"age\":25}";
            JSONMap map = JsonUtil.coverObj(jsonString, JSONMap.class);
            assertEquals("测试", map.getStr("name"));
            assertEquals(Integer.valueOf(25), map.getInt("age"));
            
            JSONMap sameMap = new JSONMap();
            sameMap.put("test", "值");
            JSONMap result = JsonUtil.coverObj(sameMap, JSONMap.class);
            assertSame(sameMap, result);
            
            assertNull(JsonUtil.coverObj(null, String.class));
        }

        @Test
        @DisplayName("convertValue方法测试")
        void testConvertValue() {
            Map<String, Object> sourceMap = new HashMap<>();
            sourceMap.put("name", "测试");
            sourceMap.put("age", 25);
            
            LinkedHashMap<String, Object> linkedMap = JsonUtil.convertValue(sourceMap, LinkedHashMap.class);
            assertEquals("测试", linkedMap.get("name"));
            assertEquals(Integer.valueOf(25), linkedMap.get("age"));
        }

        @Test
        @DisplayName("canSerialize方法测试")
        void testCanSerialize() {
            assertTrue(JsonUtil.canSerialize(null));
            assertTrue(JsonUtil.canSerialize(new HashMap<>()));
            assertTrue(JsonUtil.canSerialize("测试"));
            assertTrue(JsonUtil.canSerialize(123));
        }
    }

    @Nested
    @DisplayName("路径取值测试")
    class PathExtractionTests {

        @Test
        @DisplayName("at方法基础测试")
        void testAtBasic() {
            String json = "{\"name\":\"测试\",\"age\":25,\"active\":true}";
            
            String name = (String) JsonUtil.at(json, "name");
            assertEquals("测试", name);
            
            Integer age = (Integer) JsonUtil.at(json, "age");
            assertEquals(Integer.valueOf(25), age);
            
            Boolean active = (Boolean) JsonUtil.at(json, "active");
            assertEquals(Boolean.TRUE, active);
        }

        @Test
        @DisplayName("at方法嵌套对象测试")
        void testAtNestedObject() {
            String json = "{\"user\":{\"name\":\"测试用户\",\"profile\":{\"email\":\"test@example.com\"}}}";
            
            String email = (String) JsonUtil.at(json, "user.profile.email");
            assertEquals("test@example.com", email);
            
            JSONMap userProfile = (JSONMap) JsonUtil.at(json, "user.profile");
            assertEquals("test@example.com", userProfile.getStr("email"));
        }

        @Test
        @DisplayName("at方法数组索引测试")
        void testAtArrayIndex() {
            String json = "{\"items\":[{\"name\":\"项目1\"},{\"name\":\"项目2\"},{\"name\":\"项目3\"}]}";
            
            String itemName = (String) JsonUtil.at(json, "items[1].name");
            assertEquals("项目2", itemName);
            
            String lastItemName = (String) JsonUtil.at(json, "items[-1].name");
            assertEquals("项目3", lastItemName);
        }

        @Test
        @DisplayName("at方法指定类型测试")
        void testAtWithType() {
            String json = "{\"name\":\"测试用户\",\"age\":\"25\",\"score\":\"95.5\"}";
            
            String name = JsonUtil.at(json, "name", String.class);
            assertEquals("测试用户", name);
            
            Integer age = JsonUtil.at(json, "age", Integer.class);
            assertEquals(Integer.valueOf(25), age);
            
            Double score = JsonUtil.at(json, "score", Double.class);
            assertEquals(Double.valueOf(95.5), score);
        }

        @Test
        @DisplayName("at方法边界情况测试")
        void testAtEdgeCases() {
            String json = "{\"data\":{\"items\":[1,2,3]}}";
            
            Object result1 = JsonUtil.at(json, "");
            assertEquals(json, result1);
            
            Object result2 = JsonUtil.at(null, "any.path");
            assertNull(result2);
            
            Object result3 = JsonUtil.at(json, "nonexistent.path");
            assertNull(result3);

            String data="{" +
                    "\"info\":{\"a\":[" +
                    "                   [{\"b\":1},{\"c\":2}]," +
                    "                   [{\"d\":3},{\"e\":4},{\"f\":5}]" +
                    "                 ]" +
                    "          }" +
                    "}";
            assertEquals(2, JsonUtil.at(data,"info.a[0][1].c"));
            assertEquals(5, JsonUtil.at(data,"info.a[1][2].f"));
            assertEquals("{\"f\":5}", JsonUtil.at(data,"info.a[1][2]").toString());
            assertEquals("{\"f\":5}", JsonUtil.at(data,"info.a[1][-1]").toString());
        }
    }

    @Nested
    @DisplayName("Type构建测试")
    class JavaTypeConstructionTests {

        @Test
        @DisplayName("JsonTypes基础测试")
        void testMkJavaTypeBasic(){
            java.lang.reflect.Type listType = JsonTypes.listOf(String.class);
            assertEquals("java.util.List<java.lang.String>", listType.getTypeName());

            java.lang.reflect.Type mapType = JsonTypes.parameterized(Map.class, String.class, Float.class);
            Map<String,Float> b = JsonUtil.readValue("{\"a\":1}", mapType);
            assertEquals(1.0f, b.get("a"));
        }
    }

    @Nested
    @DisplayName("JSON格式判断测试")
    class JsonFormatDetectionTests {

        @Test
        @DisplayName("isJsonObj - 空对象")
        void testIsJsonObjEmptyObject() {
            assertTrue(JsonUtil.isJsonObj("{}"));
            assertTrue(JsonUtil.isJsonObj("{ }"));
            assertTrue(JsonUtil.isJsonObj(" { } "));
            assertTrue(JsonUtil.isJsonObj("\t{}\t"));
            assertTrue(JsonUtil.isJsonObj("\n{}\n"));
        }

        @Test
        @DisplayName("isJsonObj - 简单键值对")
        void testIsJsonObjSimpleKeyValue() {
            assertTrue(JsonUtil.isJsonObj("{\"key\":\"value\"}"));
            assertTrue(JsonUtil.isJsonObj("{\"name\":\"测试\"}"));
            assertTrue(JsonUtil.isJsonObj("{\"age\":25}"));
            assertTrue(JsonUtil.isJsonObj("{\"active\":true}"));
            assertTrue(JsonUtil.isJsonObj("{\"data\":null}"));
        }

        @Test
        @DisplayName("isJsonObj - 带连字符的键")
        void testIsJsonObjKeyWithHyphen() {
            assertTrue(JsonUtil.isJsonObj("{\"xx-xx\":123}"));
            assertTrue(JsonUtil.isJsonObj("{\"my-key\":\"value\"}"));
            assertTrue(JsonUtil.isJsonObj("{\"user-name\":\"张三\"}"));
        }

        @Test
        @DisplayName("isJsonObj - 多个键值对")
        void testIsJsonObjMultipleKeys() {
            assertTrue(JsonUtil.isJsonObj("{\"a\":1,\"b\":2}"));
            assertTrue(JsonUtil.isJsonObj("{\"name\":\"test\",\"age\":20}"));
            assertTrue(JsonUtil.isJsonObj("{\"x\":1,\"y\":2,\"z\":3}"));
        }

        @Test
        @DisplayName("isJsonObj - 嵌套对象")
        void testIsJsonObjNestedObject() {
            assertTrue(JsonUtil.isJsonObj("{\"outer\":{\"inner\":\"value\"}}"));
            assertTrue(JsonUtil.isJsonObj("{\"user\":{\"name\":\"test\",\"age\":25}}"));
        }

        @Test
        @DisplayName("isJsonObj - 包含数组")
        void testIsJsonObjWithArray() {
            assertTrue(JsonUtil.isJsonObj("{\"items\":[1,2,3]}"));
            assertTrue(JsonUtil.isJsonObj("{\"list\":[\"a\",\"b\"]}"));
        }

        @Test
        @DisplayName("isJsonObj - 复杂结构")
        void testIsJsonObjComplexStructure() {
            assertTrue(JsonUtil.isJsonObj("{\"data\":{\"items\":[{\"id\":1},{\"id\":2}]}}"));
        }

        @Test
        @DisplayName("isJsonObj - 带空格和换行")
        void testIsJsonObjWithWhitespace() {
            assertTrue(JsonUtil.isJsonObj("{ \"key\" : \"value\" }"));
            assertTrue(JsonUtil.isJsonObj("{\n  \"name\": \"test\",\n  \"age\": 25\n}"));
            assertTrue(JsonUtil.isJsonObj("{  \"a\"  :  1  ,  \"b\"  :  2  }"));
        }

        @Test
        @DisplayName("isJsonObj - null和边界值")
        void testIsJsonObjNullAndEdgeCases() {
            assertFalse(JsonUtil.isJsonObj(null));
            assertFalse(JsonUtil.isJsonObj(""));
            assertFalse(JsonUtil.isJsonObj("   "));
        }

        @Test
        @DisplayName("isJsonObj - 非JSON对象格式")
        void testIsJsonObjNonObjectFormat() {
            assertFalse(JsonUtil.isJsonObj("[]"));
            assertFalse(JsonUtil.isJsonObj("[1,2,3]"));
            assertFalse(JsonUtil.isJsonObj("plain text"));
            assertFalse(JsonUtil.isJsonObj("123"));
            assertFalse(JsonUtil.isJsonObj("true"));
            assertFalse(JsonUtil.isJsonObj("null"));
        }

        @Test
        @DisplayName("isJsonObj - 不完整或错误格式")
        void testIsJsonObjIncompleteOrInvalidFormat() {
            // 缺少闭合括号
            assertFalse(JsonUtil.isJsonObj("{"));
            assertFalse(JsonUtil.isJsonObj("{\"key\":\"value\""));
            assertFalse(JsonUtil.isJsonObj("{\"key\":"));
            
            // 只有右括号
            assertFalse(JsonUtil.isJsonObj("}"));
            assertFalse(JsonUtil.isJsonObj("}\"key\":\"value\"{"));
            
            // 括号不匹配
            assertFalse(JsonUtil.isJsonObj("{]"));
            assertFalse(JsonUtil.isJsonObj("[}"));
        }

        @Test
        @DisplayName("isJsonObj - 不带引号的键（非标准JSON但能识别为对象格式）")
        void testIsJsonObjUnquotedKeys() {
            // 浅层校验只检查 {} 结构，不验证内部格式
            assertTrue(JsonUtil.isJsonObj("{a:1}"));
            assertTrue(JsonUtil.isJsonObj("{name:test}"));
            assertTrue(JsonUtil.isJsonObj("{a:1,b:2}"));
            assertTrue(JsonUtil.isJsonObj("{key:value}"));
            assertTrue(JsonUtil.isJsonObj("{x:123,y:true,z:null}"));
        }

        @Test
        @DisplayName("isJsonObj - 特殊字符和转义")
        void testIsJsonObjSpecialCharacters() {
            assertTrue(JsonUtil.isJsonObj("{\"key\\nwith\\nnewline\":\"value\"}"));
            assertTrue(JsonUtil.isJsonObj("{\"path\":\"C:\\\\Users\\\\test\"}"));
            assertTrue(JsonUtil.isJsonObj("{\"quote\":\"say \\\"hello\\\"\"}"));
        }

        @Test
        @DisplayName("isJsonObj - 数字类型值")
        void testIsJsonObjNumericValues() {
            assertTrue(JsonUtil.isJsonObj("{\"int\":123}"));
            assertTrue(JsonUtil.isJsonObj("{\"float\":12.34}"));
            assertTrue(JsonUtil.isJsonObj("{\"negative\":-56}"));
            assertTrue(JsonUtil.isJsonObj("{\"scientific\":1.23e10}"));
            assertTrue(JsonUtil.isJsonObj("{\"zero\":0}"));
        }

        @Test
        @DisplayName("isJsonObj - 字符串值包含特殊内容")
        void testIsJsonObjStringValuesWithSpecialContent() {
            assertTrue(JsonUtil.isJsonObj("{\"url\":\"https://example.com\"}"));
            assertTrue(JsonUtil.isJsonObj("{\"email\":\"test@example.com\"}"));
            assertTrue(JsonUtil.isJsonObj("{\"chinese\":\"中文测试\"}"));
            assertTrue(JsonUtil.isJsonObj("{\"emoji\":\"😀😃😄\"}"));
            assertTrue(JsonUtil.isJsonObj("{\"empty\":\"\"}"));
        }

        @Test
        @DisplayName("isJsonObj - 深层嵌套结构")
        void testIsJsonObjDeepNesting() {
            assertTrue(JsonUtil.isJsonObj("{\"a\":{\"b\":{\"c\":{\"d\":1}}}}"));
            assertTrue(JsonUtil.isJsonObj("{\"level1\":{\"level2\":{\"level3\":{\"level4\":{\"level5\":\"deep\"}}}}}"));
        }

        @Test
        @DisplayName("isJsonObj - 数组中包含复杂对象")
        void testIsJsonObjArrayWithComplexObjects() {
            assertTrue(JsonUtil.isJsonObj("{\"users\":[{\"name\":\"Alice\",\"age\":25},{\"name\":\"Bob\",\"age\":30}]}"));
            assertTrue(JsonUtil.isJsonObj("{\"matrix\":[[1,2,3],[4,5,6],[7,8,9]]}"));
        }

        @Test
        @DisplayName("isJsonArray - 空数组")
        void testIsJsonArrayEmptyArray() {
            assertTrue(JsonUtil.isJsonArray("[]"));
            assertTrue(JsonUtil.isJsonArray("[ ]"));
            assertTrue(JsonUtil.isJsonArray(" [ ] "));
            assertTrue(JsonUtil.isJsonArray("\t[]\t"));
            assertTrue(JsonUtil.isJsonArray("\n[]\n"));
        }

        @Test
        @DisplayName("isJsonArray - 简单元素")
        void testIsJsonArraySimpleElements() {
            assertTrue(JsonUtil.isJsonArray("[1,2,3]"));
            assertTrue(JsonUtil.isJsonArray("[\"a\",\"b\",\"c\"]"));
            assertTrue(JsonUtil.isJsonArray("[true,false,true]"));
            assertTrue(JsonUtil.isJsonArray("[null,null]"));
        }

        @Test
        @DisplayName("isJsonArray - 混合类型")
        void testIsJsonArrayMixedTypes() {
            assertTrue(JsonUtil.isJsonArray("[1,\"text\",true,null]"));
            assertTrue(JsonUtil.isJsonArray("[\"a\",123,false]"));
        }

        @Test
        @DisplayName("isJsonArray - 嵌套结构")
        void testIsJsonArrayNestedStructure() {
            assertTrue(JsonUtil.isJsonArray("[[1,2],[3,4]]"));
            assertTrue(JsonUtil.isJsonArray("[{\"a\":1},{\"b\":2}]"));
            assertTrue(JsonUtil.isJsonArray("[{\"nested\":{\"deep\":\"value\"}}]"));
        }

        @Test
        @DisplayName("isJsonArray - 带空格")
        void testIsJsonArrayWithWhitespace() {
            assertTrue(JsonUtil.isJsonArray("[ 1 , 2 , 3 ]"));
            assertTrue(JsonUtil.isJsonArray("[ \"a\" , \"b\" ]"));
            assertTrue(JsonUtil.isJsonArray("[\n  1,\n  2\n]"));
        }

        @Test
        @DisplayName("isJsonArray - null和边界值")
        void testIsJsonArrayNullAndEdgeCases() {
            assertFalse(JsonUtil.isJsonArray(null));
            assertFalse(JsonUtil.isJsonArray(""));
            assertFalse(JsonUtil.isJsonArray("   "));
        }

        @Test
        @DisplayName("isJsonArray - 非JSON数组格式")
        void testIsJsonArrayNonArrayFormat() {
            assertFalse(JsonUtil.isJsonArray("{}"));
            assertFalse(JsonUtil.isJsonArray("{\"key\":\"value\"}"));
            assertFalse(JsonUtil.isJsonArray("plain text"));
            assertFalse(JsonUtil.isJsonArray("123"));
        }

        @Test
        @DisplayName("isJsonArray - 不完整或错误格式")
        void testIsJsonArrayIncompleteOrInvalidFormat() {
            // 缺少闭合括号
            assertFalse(JsonUtil.isJsonArray("["));
            assertFalse(JsonUtil.isJsonArray("[1,2,3"));
            assertFalse(JsonUtil.isJsonArray("[1,"));
            
            // 只有右括号
            assertFalse(JsonUtil.isJsonArray("]"));
            assertFalse(JsonUtil.isJsonArray("]1,2,3["));
            
            // 括号不匹配
            assertFalse(JsonUtil.isJsonArray("[}"));
            assertFalse(JsonUtil.isJsonArray("{]"));
        }

        @Test
        @DisplayName("isJsonArray - 不带引号的元素（非标准JSON但能识别为数组格式）")
        void testIsJsonArrayUnquotedElements() {
            // 浅层校验只检查 [] 结构，不验证内部格式
            assertTrue(JsonUtil.isJsonArray("[a,b,c]"));
            assertTrue(JsonUtil.isJsonArray("[1,2,3]"));
            assertTrue(JsonUtil.isJsonArray("[x:y,z:w]"));
        }

        @Test
        @DisplayName("isJsonArray - 数字类型元素")
        void testIsJsonArrayNumericElements() {
            assertTrue(JsonUtil.isJsonArray("[123]"));
            assertTrue(JsonUtil.isJsonArray("[1.23,4.56,7.89]"));
            assertTrue(JsonUtil.isJsonArray("[-1,-2,-3]"));
            assertTrue(JsonUtil.isJsonArray("[1.23e10,4.56e-2]"));
            assertTrue(JsonUtil.isJsonArray("[0]"));
        }

        @Test
        @DisplayName("isJsonArray - 字符串包含特殊内容")
        void testIsJsonArrayStringWithSpecialContent() {
            assertTrue(JsonUtil.isJsonArray("[\"https://example.com\"]"));
            assertTrue(JsonUtil.isJsonArray("[\"test@example.com\"]"));
            assertTrue(JsonUtil.isJsonArray("[\"中文\",\"English\",\"日本語\"]"));
            assertTrue(JsonUtil.isJsonArray("[\"😀😃😄\"]"));
            assertTrue(JsonUtil.isJsonArray("[\"\"]"));
        }

        @Test
        @DisplayName("isJsonArray - 深层嵌套结构")
        void testIsJsonArrayDeepNesting() {
            assertTrue(JsonUtil.isJsonArray("[[[1]]]"));
            assertTrue(JsonUtil.isJsonArray("[[[\"deep\"]]]"));
            assertTrue(JsonUtil.isJsonArray("[{\"a\":{\"b\":{\"c\":1}}}]"));
        }

        @Test
        @DisplayName("isJsonArray - 对象数组")
        void testIsJsonArrayOfObjects() {
            assertTrue(JsonUtil.isJsonArray("[{\"name\":\"Alice\"},{\"name\":\"Bob\"}]"));
            assertTrue(JsonUtil.isJsonArray("[{\"id\":1,\"value\":\"a\"},{\"id\":2,\"value\":\"b\"},{\"id\":3,\"value\":\"c\"}]"));
        }

        @Test
        @DisplayName("isJsonArray - 混合复杂结构")
        void testIsJsonArrayMixedComplexStructure() {
            assertTrue(JsonUtil.isJsonArray("[1,\"text\",true,null,{\"key\":\"value\"},[1,2,3]]"));
            assertTrue(JsonUtil.isJsonArray("[{\"users\":[{\"name\":\"Alice\"}]},{\"count\":42}]"));
        }

        @Test
        @DisplayName("isJsonArray - 单个元素")
        void testIsJsonArraySingleElement() {
            assertTrue(JsonUtil.isJsonArray("[1]"));
            assertTrue(JsonUtil.isJsonArray("[\"single\"]"));
            assertTrue(JsonUtil.isJsonArray("[true]"));
            assertTrue(JsonUtil.isJsonArray("[null]"));
            assertTrue(JsonUtil.isJsonArray("[{\"only\":\"one\"}]"));
            assertTrue(JsonUtil.isJsonArray("[[nested]]"));
        }

        @Test
        @DisplayName("isJsonObj vs isJsonArray - 互斥性测试")
        void testIsJsonObjVsIsJsonArrayMutualExclusion() {
            // JSON对象应该被isJsonObj识别，但不被isJsonArray识别
            String[] jsonObjects = {"{}", "{\"key\":\"value\"}", "{a:1}", "{\"nested\":{}}"};
            for (String obj : jsonObjects) {
                assertTrue(JsonUtil.isJsonObj(obj), "应该是JSON对象: " + obj);
                assertFalse(JsonUtil.isJsonArray(obj), "不应该是JSON数组: " + obj);
            }
            
            // JSON数组应该被isJsonArray识别，但不被isJsonObj识别
            String[] jsonArrays = {"[]", "[1,2,3]", "[a,b,c]", "[{\"nested\":[]}]"};
            for (String arr : jsonArrays) {
                assertFalse(JsonUtil.isJsonObj(arr), "不应该是JSON对象: " + arr);
                assertTrue(JsonUtil.isJsonArray(arr), "应该是JSON数组: " + arr);
            }
        }

        @Test
        @DisplayName("isJsonObj/isJsonArray - 常见错误格式")
        void testCommonInvalidFormats() {
            // 这些既不是JSON对象也不是JSON数组
            String[] invalidFormats = {
                "",
                "   ",
                null,
                "plain text",
                "12345",
                "true",
                "false",
                "null",
                "{",
                "}",
                "[",
                "]",
//                "{{}}",
//                "{}{}",
//                "[][]",
                "}{",
                "][",
                "abc{def}",
                "xyz[123]"
            };
            
            for (String invalid : invalidFormats) {
                if (invalid == null || invalid.trim().isEmpty()) {
                    assertFalse(JsonUtil.isJsonObj(invalid), "null/空字符串不应是JSON对象");
                    assertFalse(JsonUtil.isJsonArray(invalid), "null/空字符串不应是JSON数组");
                } else {
                    assertFalse(JsonUtil.isJsonObj(invalid), "不应是JSON对象: " + invalid);
                    assertFalse(JsonUtil.isJsonArray(invalid), "不应是JSON数组: " + invalid);
                }
            }
        }

        @Test
        @DisplayName("isJsonObj - 性能相关的简单场景")
        void testIsJsonObjPerformanceRelevantCases() {
            // 这些是实际使用中常见的简单场景，应该快速通过
            assertTrue(JsonUtil.isJsonObj("{}"));
            assertTrue(JsonUtil.isJsonObj("{\"id\":1}"));
            assertTrue(JsonUtil.isJsonObj("{\"status\":\"ok\"}"));
            assertTrue(JsonUtil.isJsonObj("{\"code\":200,\"msg\":\"success\"}"));
            assertTrue(JsonUtil.isJsonObj("{\"data\":{}}"));
            assertTrue(JsonUtil.isJsonObj("{\"result\":[]}"));
        }

        @Test
        @DisplayName("isJsonArray - 性能相关的简单场景")
        void testIsJsonArrayPerformanceRelevantCases() {
            // 这些是实际使用中常见的简单场景，应该快速通过
            assertTrue(JsonUtil.isJsonArray("[]"));
            assertTrue(JsonUtil.isJsonArray("[1]"));
            assertTrue(JsonUtil.isJsonArray("[1,2,3]"));
            assertTrue(JsonUtil.isJsonArray("[\"a\",\"b\"]"));
            assertTrue(JsonUtil.isJsonArray("[{}]"));
            assertTrue(JsonUtil.isJsonArray("[[]]"));
        }
    }

    @Nested
    @DisplayName("splitKey1方法测试")
    class SplitKey1Tests {

        @Test
        @DisplayName("普通点分隔 - a.b[1].c")
        void testSplitKey1DotSeparated() {
            VAL<String, String> result = JsonUtil.splitKey("a.b[1].c");
            assertEquals("a", result.v1);
            assertEquals("b[1].c", result.v2);
        }

        @Test
        @DisplayName("多维数组 - b[0][2].c")
        void testSplitKey1MultiDimensionalArray() {
            VAL<String, String> result = JsonUtil.splitKey("b[0][2].c");
            assertEquals("b[0][2]", result.v1);
            assertEquals("c", result.v2);
        }

        @Test
        @DisplayName("以括号开头 - [2].c")
        void testSplitKey1StartWithBracket() {
            VAL<String, String> result = JsonUtil.splitKey("[2].c");
            assertEquals("[2]", result.v1);
            assertEquals("c", result.v2);
        }

        @Test
        @DisplayName("单个键名 - c")
        void testSplitKey1SingleKey() {
            VAL<String, String> result = JsonUtil.splitKey("c");
            assertEquals("c", result.v1);
            assertNull(result.v2);
        }

        @Test
        @DisplayName("简单点分隔 - user.name")
        void testSplitKey1SimpleDot() {
            VAL<String, String> result = JsonUtil.splitKey("user.name");
            assertEquals("user", result.v1);
            assertEquals("name", result.v2);
        }

        @Test
        @DisplayName("数组带属性 - items[3].title")
        void testSplitKey1ArrayWithProperty() {
            VAL<String, String> result = JsonUtil.splitKey("items[3].title");
            assertEquals("items[3]", result.v1);
            assertEquals("title", result.v2);
        }

        @Test
        @DisplayName("多级点分隔 - a.b.c.d")
        void testSplitKey1MultipleDots() {
            VAL<String, String> result = JsonUtil.splitKey("a.b.c.d");
            assertEquals("a", result.v1);
            assertEquals("b.c.d", result.v2);
        }

        @Test
        @DisplayName("嵌套数组带路径 - list[0][1].name")
        void testSplitKey1NestedArrayWithPath() {
            VAL<String, String> result = JsonUtil.splitKey("list[0][1].name");
            assertEquals("list[0][1]", result.v1);
            assertEquals("name", result.v2);
        }

        @Test
        @DisplayName("只有数组 - arr[5]")
        void testSplitKey1ArrayOnly() {
            VAL<String, String> result = JsonUtil.splitKey("arr[5]");
            assertEquals("arr[5]", result.v1);
            assertNull(result.v2);
        }

        @Test
        @DisplayName("以括号开头无后续 - [0]")
        void testSplitKey1BracketOnlyStart() {
            VAL<String, String> result = JsonUtil.splitKey("[0]");
            assertEquals("[0]", result.v1);
            assertNull(result.v2);
        }

        @Test
        @DisplayName("多维数组无后续 - data[1][2][3]")
        void testSplitKey1MultiDimensionalArrayOnly() {
            VAL<String, String> result = JsonUtil.splitKey("data[1][2][3]");
            assertEquals("data[1][2][3]", result.v1);
            assertNull(result.v2);
        }

        @Test
        @DisplayName("复杂路径 - a.b[0].c[1].d")
        void testSplitKey1ComplexPath() {
            VAL<String, String> result = JsonUtil.splitKey("a.b[0].c[1].d");
            assertEquals("a", result.v1);
            assertEquals("b[0].c[1].d", result.v2);
        }

        @Test
        @DisplayName("以括号开头带点 - [5].items[2].name")
        void testSplitKey1BracketStartWithDot() {
            VAL<String, String> result = JsonUtil.splitKey("[5].items[2].name");
            assertEquals("[5]", result.v1);
            assertEquals("items[2].name", result.v2);
        }

        @Test
        @DisplayName("异常情况 - 空字符串")
        void testSplitKey1EmptyString() {
            assertThrows(SystemException.class, () -> JsonUtil.splitKey(""));
        }

        @Test
        @DisplayName("异常情况 - null值")
        void testSplitKey1Null() {
            assertThrows(SystemException.class, () -> JsonUtil.splitKey(null));
        }

        @Test
        @DisplayName("异常情况 - 只有点")
        void testSplitKey1OnlyDot() {
            assertThrows(SystemException.class, () -> JsonUtil.splitKey("."));
        }

        @Test
        @DisplayName("异常情况 - 以括号开头缺少右括号")
        void testSplitKey1StartWithBracketMissingRight() {
            assertThrows(SystemException.class, () -> JsonUtil.splitKey("[2.c"));
        }
    }
}
