# CHANGELOG


---

## v6.7.5

- MemoryCache 在写入时拒绝 null（IllegalArgumentException）；加载器返回 null 不缓存，get 返回 null 统一表示缺失或过期。
- 压缩 SQL 前移除 -- 行注释，保留单行展示且避免后续参数被注释吞掉；耗时不再计入日志格式化，诊断关闭时直接执行原调用。
- enabled 统一为注册阶段开关，拦截器运行时仅根据日志级别及诊断选项工作。
- 增加 TRACE 按需定位详情，与 SQL 合并输出，复用一次抓栈，保留普通日志格式。
- 修复 JDK 8 动态代理栈帧遮挡真实业务调用位置的问题。
- 修复 MyBatis SQL 日志等诊断异常干扰业务结果、覆盖原数据库异常的问题。
- 新增 dlz-caller-mybatis-spring-boot-starter，兼容 Boot 2/3、MyBatis/MyBatis-Plus 标准自动配置。
- Starter 无需手动 Bean；支持 YAML、自定义配置和拦截器 Bean、禁用。SQL 固定 DEBUG 输出，由日志框架控制，TRACE 附加定位详情。
- 增加异常回归与真实 H2 查询自动装配测试，更新接入文档。

## v6.7.4

- 新增 DlzCaller 日志诊断框架（ThreadLocal + MDC + 栈帧解析）
- 新增 dlz-caller-mybatis 插件（MyBatis SQL 日志 + 调用者定位）
- 新增内存缓存体系（ICache 接口 + MemoryCache 实现 + CacheUtil 门面）
- 新增 CacheHolder 缓存注册中心
- 新增 CacheMap 增强型 ConcurrentHashMap
- 重构 JSON 解析器为自研实现（核心模块零依赖 Jackson）
- JSONMap 父类由 HashMap 改为 LinkedHashMap，保留插入顺序
- 新增 ConvertUtil 统一类型转换工具，替代 BeanUtil
- 移除 BeanUtil（功能合并到 ConvertUtil，支持 Bean ↔ Map、Bean ↔ Bean、集合/数组转换）
- ValUtil 新增 `at()` 路径取值和 `set()` 路径设值方法（Map/Bean/List/数组通用）
- Caller MDC key 固定为 `dlz-caller`（`DlzCaller.MDC_KEY_DLZ_CALLER` 常量）
- `injectCallerMdc` 默认值改为 false（需显式开启）
- 新增 IUniversalVals 万能取值器接口体系
- 新增 VAL 多值返回类型（VAL/VAL3/VAL4/VAL5）
- 新增异常体系（BaseException / BusinessException / SystemException / ValidateException）
- 新增反射工具集（FieldReflections / MethodReflections / Reflections）
- 新增 TraceUtil / UuidUtil 工具
- 新增 DlzFn 函数式接口系列
- Jackson 改为可选插件（dlz-json-jackson）

## v6.7.0

- 自研 JSON 解析器落地，核心模块解除 Jackson 依赖
- Caller 日志框架首个可用版本
- 内存缓存模块首个可用版本

## v6.6.3 (2024-03-15)

- 完善文档体系
- 优化路径解析边界处理
- 修复类型转换精度问题

## v6.6.0

- 新增 `@SetValue` 注解支持
- 新增多维数组支持
- 优化类型转换逻辑

## v6.5.0

- 新增负索引支持
- 新增 JSONList 类
- 新增工具类集合（ValUtil, JacksonUtil, DateUtil, StringUtils, BeanUtil）（注：BeanUtil 在 v6.7.4 被 ConvertUtil 替代）

## v6.4.0

- 初始版本发布，核心功能实现

---

## 升级指南

从 6.5.x 升级到 6.6.x：无破坏性变更，直接升级。

---

