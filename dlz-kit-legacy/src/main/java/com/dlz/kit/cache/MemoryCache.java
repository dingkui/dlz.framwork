package com.dlz.kit.cache;

import com.dlz.kit.util.JacksonUtil;
import com.dlz.kit.util.ValUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 内存缓存实现类
 * 
 * 使用内存实现缓存功能，支持设置过期时间、获取、设置、删除等操作
 * 
 * @author dk
 * @since 2023
 */
@Slf4j
public class MemoryCache implements ICache {
    /**
     * 缓存存储结构：外层Map的key为缓存名称，内层Map的key为缓存键，value为缓存元素
     */
    private static final Map<String, Map<Serializable, Element>> CACHE = new ConcurrentHashMap<>();

    /**
     * 使用守护线程回收长期未访问的过期项；缓存读取仍会执行惰性过期检查。
     */
    private static final ScheduledExecutorService EXPIRATION_CLEANER =
            Executors.newSingleThreadScheduledExecutor(runnable -> {
                Thread thread = new Thread(runnable, "dlz-memory-cache-cleaner");
                thread.setDaemon(true);
                return thread;
            });

    static {
        EXPIRATION_CLEANER.scheduleWithFixedDelay(
                MemoryCache::purgeExpiredEntries, 1, 1, TimeUnit.SECONDS);
    }

    public MemoryCache() {
    }
    /**
     * 构造函数，初始化过期处理线程
     */
    public MemoryCache(String cacheName) {
        this();
        if(cacheName != null){
            CacheHolder.add(cacheName, this);
        }
    }

    /**
     * 获取指定名称的缓存映射
     * 
     * @param name 缓存名称
     * @return 缓存映射
     */
    protected static Map<Serializable, Element> getCache(String name) {
        return CACHE.computeIfAbsent(name, key -> new ConcurrentHashMap<>());
    }

    /**
     * 获取缓存值
     * 
     * @param name 缓存名称
     * @param key 缓存键
     * @param tClass 缓存值类型
     * @param <T> 缓存值类型泛型
     * @return 缓存值
     */
    @Override
    public <T extends Serializable> T get(String name, Serializable key, Type tClass) {
        Map<Serializable, Element> cache = getCache(name);
        Element element = cache.get(key);
        if (element == null) {
            return null;
        }
        if (element.isExpired(System.currentTimeMillis())) {
            cache.remove(key, element);
            return null;
        }
        if (tClass != null) {
            return ValUtil.toObj(element.item, JacksonUtil.mkJavaType(tClass));
        }
        return (T) element.item;
    }

    /**
     * 设置缓存值
     * 
     * @param name 缓存名称
     * @param key 缓存键
     * @param value 缓存值
     * @param seconds 过期时间（秒），小于等于0表示永不过期
     */
    @Override
    public void put(String name, Serializable key, Serializable value, int seconds) {
        long expireAtMillis = seconds > 0
                ? System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(seconds)
                : Element.NEVER_EXPIRES;
        getCache(name).put(key, new Element(value, expireAtMillis));
    }

    /**
     * 删除缓存值
     * 
     * @param name 缓存名称
     * @param key 缓存键
     */
    @Override
    public void remove(String name, Serializable key) {
        getCache(name).remove(key);
    }

    /**
     * 删除所有缓存
     * 
     * @param name 缓存名称
     */
    @Override
    public void removeAll(String name) {
        getCache(name).clear();
    }

    /**
     * 获取指定前缀的缓存键集合
     * 
     * @param name 缓存名称
     * @param keyPrefix 键前缀，支持通配符*
     * @return 缓存键集合
     */
    @Override
    public Set<String> keys(String name, String keyPrefix) {
        Map<Serializable, Element> cache = getCache(name);
        long now = System.currentTimeMillis();
        Stream<String> stringStream = cache.entrySet().stream()
                .filter(entry -> retainUnexpired(cache, entry.getKey(), entry.getValue(), now))
                .map(entry -> ValUtil.toStr(entry.getKey()));

        return filterByPrefix(stringStream, keyPrefix)
                .collect(Collectors.toSet());
    }

    /**
     * 获取指定前缀的所有缓存
     * 
     * @param name 缓存名称
     * @param keyPrefix 键前缀，支持通配符*
     * @return 缓存映射
     */
    @Override
    public Map<String, Serializable> all(String name, String keyPrefix) {
        Map<Serializable, Element> cache = getCache(name);
        Map<String, Serializable> result = new ConcurrentHashMap<>();
        long now = System.currentTimeMillis();
        cache.forEach((key, element) -> {
            if (retainUnexpired(cache, key, element, now)) {
                String keyString = ValUtil.toStr(key);
                if (matchesPrefix(keyString, keyPrefix)) {
                    result.put(keyString, element.item);
                }
            }
        });
        return result;
    }

    private static boolean retainUnexpired(Map<Serializable, Element> cache,
                                           Serializable key, Element element, long now) {
        if (!element.isExpired(now)) {
            return true;
        }
        cache.remove(key, element);
        return false;
    }

    private static void purgeExpiredEntries() {
        try {
            long now = System.currentTimeMillis();
            CACHE.values().forEach(cache -> cache.forEach((key, element) ->
                    retainUnexpired(cache, key, element, now)));
        } catch (RuntimeException e) {
            log.warn("Failed to clean expired memory cache entries", e);
        }
    }

    /**
     * 将 glob 风格的 keyPrefix 安全地转换为正则表达式
     * 对非通配符部分使用 Pattern.quote 转义，防止 ReDoS 攻击
     */
    private static String sanitizeGlobToRegex(String glob) {
        StringBuilder sb = new StringBuilder();
        int start = 0;
        for (int i = 0; i < glob.length(); i++) {
            if (glob.charAt(i) == '*') {
                if (i > start) {
                    sb.append(Pattern.quote(glob.substring(start, i)));
                }
                sb.append(".*");
                start = i + 1;
            }
        }
        if (start < glob.length()) {
            sb.append(Pattern.quote(glob.substring(start)));
        }
        return sb.toString();
    }

    /**
     * 根据通配符前缀过滤键流（使用 sanitizeGlobToRegex 防止 ReDoS）
     *
     * @param keys 键流
     * @param keyPrefix 键前缀，支持通配符*
     * @return 过滤后的键流
     */
    private static boolean matchesPrefix(String key, String keyPrefix) {
        if ("*".equals(keyPrefix) || ".*".equals(keyPrefix)) {
            return true;
        }
        return Pattern.compile(sanitizeGlobToRegex(keyPrefix)).matcher(key).matches();
    }

    private static Stream<String> filterByPrefix(Stream<String> keys, String keyPrefix) {
        return keys.filter(key -> matchesPrefix(key, keyPrefix));
    }

    /**
     * 缓存元素内部类
     */
    private static final class Element {
        private static final long NEVER_EXPIRES = -1L;
        private final long expireAtMillis;
        private final Serializable item;

        private Element(Serializable item, long expireAtMillis) {
            this.item = item;
            this.expireAtMillis = expireAtMillis;
        }

        private boolean isExpired(long now) {
            return expireAtMillis != NEVER_EXPIRES && expireAtMillis <= now;
        }
    }
}