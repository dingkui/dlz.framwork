# dlz-json-jackson

`dlz-json-jackson` is the optional interoperability bridge between DLZ JSON and Jackson APIs.

## Default mode

Depending on `dlz-kit` alone uses the built-in JSON parser, writer and JDK reflection mapper.
Jackson is not present in the runtime dependency graph.

```xml
<dependency>
    <groupId>top.dlzio</groupId>
    <artifactId>dlz-kit</artifactId>
    <version>6.7.2</version>
</dependency>
```

```java
JSONMap data = new JSONMap("{\"user\":{\"name\":\"DLZ\"}}");
String name = data.getStr("user.name");
User user = JsonUtil.readValue("{\"name\":\"DLZ\"}", User.class);
```

## Jackson mode

Add this module when Jackson annotations, modules, `JsonNode`, `JavaType` or advanced
Bean mapping are required:

```xml
<dependency>
    <groupId>top.dlzio</groupId>
    <artifactId>dlz-json-jackson</artifactId>
    <version>6.7.2</version>
</dependency>
```

Adding this module does not replace the built-in `JsonUtil` implementation. Jackson behavior
is always selected explicitly through `JacksonUtil` or an application-owned `ObjectMapper`.

```java
User user = JacksonUtil.readValue(json, User.class);
```

Jackson-specific APIs are deliberately kept out of `JsonUtil`:

```java
ObjectMapper mapper = JacksonUtil.getInstance();
JsonNode node = JacksonUtil.readTree(json);
JavaType type = JacksonUtil.mkJavaType(List.class, User.class);
List<User> users = JacksonUtil.readValue(json, type);
```
