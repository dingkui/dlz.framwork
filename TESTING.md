# TESTING

## 文档检查

- 检查 Markdown 相对链接目标存在；
- 检查代码围栏成对；
- 扫描示例中的包名、类名和方法名是否存在；
- 变更行为说明时运行对应模块测试。

## Maven

```text
mvn -q -DskipTests validate
mvn -q test
```

核心模块编译目标为 Java 8。发布前应使用 CI 等价的 JDK 矩阵验证，并确认文档示例与对应测试保持一致。性能数据只有在固定 JDK、版本、机器、预热和统计方法后才可公开。