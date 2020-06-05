package com.dlz.framework.cache.aspect;

import com.dlz.comm.exception.SystemException;
import com.dlz.comm.json.JSONMap;
import com.dlz.comm.util.StringUtils;
import com.dlz.comm.util.ValUtil;
import com.dlz.framework.cache.CacheHolder;
import com.dlz.framework.cache.ICache;
import com.dlz.framework.holder.SpringHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * AOP 缓存操作
 * 系统方法添加注解 @CacheAnno则支持缓存
 * @author dk 2020-06-05
 */
@Slf4j
@Aspect
@Component
public class CacheAspect {
    private final static String CACHE_NAME = "methodCaches";
    @Autowired
    @Nullable
    ICache cache;

    @PostConstruct
    private void _init() {
        cache = CacheHolder.add(CACHE_NAME, cache);
    }


    private CacheAnno getApiAnnotation(JoinPoint joinPoint) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        return method.getAnnotation(CacheAnno.class);
    }

    @Pointcut("@annotation(com.dlz.framework.cache.aspect.CacheAnno)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        CacheAnno cacheAnno = getApiAnnotation(point);
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        Class<?> returnType = method.getReturnType();
//        SystemException.isTrue(!method.getName().startsWith("get"), () -> "缓存必须是get方法：" + method.getName());
        SystemException.isTrue(!Serializable.class.isAssignableFrom(returnType), () -> "类型：" + returnType + "无法缓存！");


        Parameter[] parameters = method.getParameters();
        Object[] args = point.getArgs();
        JSONMap paraMap = new JSONMap();
        for (int i = 0; i < parameters.length; i++) {
            paraMap.put(parameters[i].getName(),args[i]);
        }
        Serializable key = paraMap.getStr(cacheAnno.key());
        SystemException.isTrue(key==null, () -> "未取得有效的key！");

        boolean empty = StringUtils.isEmpty(cacheAnno.value());
        String cacheName = empty ? CACHE_NAME+method.getName() : cacheAnno.value();
        ICache iCache = empty ? cache : CacheHolder.get(cacheName, cache);

        Serializable t = iCache.get(cacheName, key, (Class<? extends Serializable>) returnType);
        if (t != null) {
            return t;
        }

        // 执行方法取得数据
        Object result = point.proceed();

        //数据保存进缓存
        iCache.put(cacheName, key, (Serializable) result, ValUtil.getInt(cacheAnno.cacheTime()));

        return result;
    }
}
