package com.dlz.framework.cache.aspect;

import com.dlz.comm.exception.SystemException;
import com.dlz.comm.json.JSONMap;
import com.dlz.comm.util.ValUtil;
import com.dlz.framework.cache.ICache;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * AOP 缓存操作
 * 系统方法添加注解 @CacheAnno则支持缓存
 * @author dk 2020-06-05
 */
@Slf4j
@Aspect
public class CacheAspect {
    ICache cache;

    public CacheAspect(ICache cache){
        this.cache = cache;
    }

    @Pointcut("@annotation(com.dlz.framework.cache.aspect.CacheAnno)")
    public void pointcutCache() {
    }

    @Pointcut("@annotation(com.dlz.framework.cache.aspect.CacheEvictAnno)")
    public void pointcutCacheEvict() {
    }

    private <T extends Annotation> T getApiAnnotation(JoinPoint joinPoint, Class<T> anno) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        return method.getAnnotation(anno);
    }
    private Serializable getKey(JoinPoint point,String key1){
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        Parameter[] parameters = method.getParameters();
        Object[] args = point.getArgs();
        JSONMap paraMap = new JSONMap();
        for (int i = 0; i < parameters.length; i++) {
            paraMap.put(parameters[i].getName(),args[i]);
        }
        Serializable key = null;
        if(!paraMap.isEmpty()){
            key = paraMap.getStr(key1);
        }
        SystemException.notNull(key , () -> "key取得失败：" + method.getName());
        return key;
    }

    @Around("pointcutCache()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        CacheAnno cacheAnno = getApiAnnotation(point, CacheAnno.class);

        String cacheName = cacheAnno.value();
        String keySet = cacheAnno.key();
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        SystemException.notNull(cacheName, () -> "cacheName 不能为空：" + method.getName() + "无法缓存！");

        Serializable key;
        if(keySet == null){
            key = "no_para_entry";
        }else{
            key = getKey(point, keySet);
        }

        Serializable t = cache.get(cacheName, keySet, method.getGenericReturnType());
        if (t != null) {
            return t;
        }

        // 执行方法取得数据
        Object result = point.proceed();
        if(result == null){
            return result;
        }
        SystemException.isTrue(!Serializable.class.isAssignableFrom(result.getClass()) , () -> "类型：" + result.getClass() + "无法缓存！");
        //数据保存进缓存
        cache.put(cacheName, key, (Serializable) result, ValUtil.getInt(cacheAnno.cacheTime()));
        return result;
    }

    /**
     * 缓存失效处理
     * @param point
     */
    @After("pointcutCacheEvict()")
    public void aroundEvict(JoinPoint point){
        CacheEvictAnno cacheAnno = getApiAnnotation(point, CacheEvictAnno.class);
        String keySet = cacheAnno.key();
        if(keySet == null){
            cache.removeAll(cacheAnno.value());
        }else{
            cache.remove(cacheAnno.value(), getKey(point, keySet));
        }
    }
}
